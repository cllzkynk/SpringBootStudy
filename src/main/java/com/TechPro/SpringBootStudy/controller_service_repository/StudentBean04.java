package com.TechPro.SpringBootStudy.controller_service_repository;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.Period;

@Entity//Hibernate bu class'ı tablo olarak DB'de create eder. TabloName:className columb name :veriables
// @Entity-->@Id olmadan calısmaz(PK)
@Table(name = "students")//classname olan tabloname modifiye edildi
public class StudentBean04 {
    @Id
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private Integer age;
    private String errMsg;

    public StudentBean04() {
        this.errMsg = "Agam olsa dukkan senin senin ogrenci araziiii :-(";
    }

    public StudentBean04(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;

        this.errMsg = "batı yakası sakin DEWAMKEEE...";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {

        this.dob = dob;
    }

    public Integer getAge() {
        if (this.dob == null) {
            return null;
        }
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "StudentBean03{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
