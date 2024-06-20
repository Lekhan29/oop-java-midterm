package com.example.midtermexam;

import java.util.Arrays;
import java.util.List;

public class Student {
    private long studentNumber;
    private String firstName;
    private String lastName;
    private String telephone;
    private String address;
    private String province;
    private double averageGrade;
    private String major;

    private static final List<String> VALID_PROVINCES = Arrays.asList(
            "AB","BC","MB","NB","NL","NS","NT","NU","ON","PE","QC","SK","YT"
    );

    public Student(long studentNumber, String firstName, String lastName, String telephone, String address, String province, double averageGrade, String major) {
        if (
                isValidName(firstName) &&
                isValidName(lastName) &&
                isValidTelephone(telephone) &&
                isValidAddress(address) &&
                isValidProvince(province) &&
                isValidAverageGrade(averageGrade) &&
                isValidMajor(major)) {

            this.studentNumber = studentNumber;
            this.firstName = firstName;
            this.lastName = lastName;
            this.telephone = telephone;
            this.address = address;
            this.province = province;
            this.averageGrade = averageGrade;
            this.major = major;
        } else {
            throw new IllegalArgumentException("Invalid input data");
        }
    }


    private boolean isValidName(String name) {
        return name != null && name.length() > 1;
    }

    private boolean isValidTelephone(String telephone) {
        return telephone != null && telephone.matches("^\\d{3}-\\d{3}-\\d{4}$");
    }

    private boolean isValidAddress(String address) {
        return address != null && address.length() > 6;
    }

    private boolean isValidProvince(String province) {
        return VALID_PROVINCES.contains(province);
    }

    private boolean isValidAverageGrade(double averageGrade) {
        return averageGrade >= 0 && averageGrade <= 100;
    }

    private boolean isValidMajor(String major) {
        return major != null && major.length() > 5;
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(long studentNumber) {
            this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (isValidName(firstName)) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("Invalid first name");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (isValidName(lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Invalid last name");
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        if (isValidTelephone(telephone)) {
            this.telephone = telephone;
        } else {
            throw new IllegalArgumentException("Invalid telephone number");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (isValidAddress(address)) {
            this.address = address;
        } else {
            throw new IllegalArgumentException("Invalid address");
        }
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        if (isValidProvince(province)) {
            this.province = province;
        } else {
            throw new IllegalArgumentException("Invalid province");
        }
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        if (isValidAverageGrade(averageGrade)) {
            this.averageGrade = averageGrade;
        } else {
            throw new IllegalArgumentException("Invalid average grade");
        }
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        if (isValidMajor(major)) {
            this.major = major;
        } else {
            throw new IllegalArgumentException("Invalid major");
        }
    }
}
