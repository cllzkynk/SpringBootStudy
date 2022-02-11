package com.TechPro.SpringBootStudy.controller;

import org.springframework.stereotype.Component;

@Component//bileşen tamamlayıcı :kullanılan class'tan obj IOC cretate eder
// new StudenBean01();--> tight coupling  ama @Component loose coupling (obj inject)
public class StudentBean01 implements StudentInterface{

    //pojo plan old java obj

    private String name;
    private  int age;
    private String  id;

    public StudentBean01() {
        System.out.println("parametresiz cons run edildi...");
    }

    public StudentBean01(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentBean01{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public String study() {
        return "Agam ben StudentBean01 class'dan selam geririrem :-)";
    }
}
