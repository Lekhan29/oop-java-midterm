package com.example.midtermexam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class StudentViewController {

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, Long> studentNumCol;

    @FXML
    private TableColumn<Student, String> firstNameCol;

    @FXML
    private TableColumn<Student, String> lastNameCol;

    @FXML
    private TableColumn<Student, String> telephoneCol;

    @FXML
    private TableColumn<Student, String> addressCol;

    @FXML
    private TableColumn<Student, String> provinceCol;

    @FXML
    private TableColumn<Student, Integer> avgGradeCol;

    @FXML
    private TableColumn<Student, String> majorCol;

    @FXML
    private CheckBox ontarioCheckBox;

    @FXML
    private Label numOfStudentsLabel;

    @FXML
    private CheckBox honourRollCheckBox;

    @FXML
    private ComboBox<String> areaCodeComboBox;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        areaCodeComboBox.getItems().add("All");
        loadStudents();
        setupTable();
        updateNumOfStudentsLabel();
        populateAreaCodes();

        // Add listener to call applyFilter whenever any filter is changed
        ontarioCheckBox.setOnAction(e -> applyFilter());
        honourRollCheckBox.setOnAction(e -> applyFilter());
        areaCodeComboBox.setOnAction(e -> applyFilter());
    }

    private void loadStudents() {
        List<Student> studentsFromDB = DBUtility.getStudentsFromDB();
        studentList.addAll(studentsFromDB);
    }

    private void setupTable() {
        studentNumCol.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));
        avgGradeCol.setCellValueFactory(new PropertyValueFactory<>("averageGrade"));
        majorCol.setCellValueFactory(new PropertyValueFactory<>("major"));

        tableView.setItems(studentList);
    }

    private void updateNumOfStudentsLabel() {
        numOfStudentsLabel.setText("Number of Students: " + tableView.getItems().size());
    }

    private void populateAreaCodes() {
        Set<String> areaCodes = new TreeSet<>();
        for (Student student : studentList) {
            String areaCode = student.getTelephone().substring(0, 3);
            areaCodes.add(areaCode);
        }
        areaCodeComboBox.getItems().addAll(areaCodes);
    }

    @FXML
    private void applyFilter() {
        ObservableList<Student> filteredList = FXCollections.observableArrayList(studentList);

        if (ontarioCheckBox.isSelected()) {
            filteredList.removeIf(student -> !student.getProvince().equals("ON"));
        }

        if (honourRollCheckBox.isSelected()) {
            filteredList.removeIf(student -> student.getAverageGrade() < 80);
        }

        String selectedAreaCode = areaCodeComboBox.getSelectionModel().getSelectedItem();
        if (!selectedAreaCode.equals("All")) {
            filteredList.removeIf(student -> !student.getTelephone().startsWith(selectedAreaCode));
        }

        tableView.setItems(filteredList);
        updateNumOfStudentsLabel();
    }
}
