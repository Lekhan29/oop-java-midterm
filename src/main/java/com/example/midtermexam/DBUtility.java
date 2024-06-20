package com.example.midtermexam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtility {

    private static final String URL = "jdbc:mysql://127.0.0.1:3308/assign1";
    private static final String USER = "root";
    private static final String PASSWORD = "Kharoud111@";

    public static List<Student> getStudentsFromDB() {
        List<Student> students = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

            while (rs.next()) {
                long studentNumber = rs.getLong("studentNum");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String telephone = rs.getString("telephone");
                String address = rs.getString("homeAddress");
                String province = rs.getString("province");
                int avgGrade = rs.getInt("avgGrade");
                String major = rs.getString("major");

                students.add(new Student(studentNumber, firstName, lastName, telephone, address, province, avgGrade, major));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}
