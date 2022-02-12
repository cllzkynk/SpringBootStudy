package com.TechPro.SpringBootStudy.basic_authentication;

public enum ApplicationUserPermission {

    STUDENT_READ("student:read"), STUDENT_WRITE("student:write");
    private final String permission;//final veriable oldg için initial edilmeli.Bunun için cons ile ilişkilendirilmeli

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {//final permission veriable obj ile degerini okumak için getter method create edildi
        return permission;
    }
}