package com.TechPro.SpringBootStudy.controller_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudenBean03Controller {
  private   StudentBean03Service std;
  @Autowired
    public StudenBean03Controller(StudentBean03Service std){
      this.std = std;
  }
 // @GetMapping(path = "/getStudentById")
 //   public StudentBean03 getStudentIdile(){
 //     return  std.getStudentById(106L);
 // }
    @GetMapping(path = "/getStudentById/{id}")
    public StudentBean03 getStudentId(@PathVariable Long id){
      return std.getStudentById(id);
    }
    @GetMapping(path = "/getStudentList")
    public List<StudentBean03> getStudentHepisi(){
        return std.getAllStudent();
    }


}
