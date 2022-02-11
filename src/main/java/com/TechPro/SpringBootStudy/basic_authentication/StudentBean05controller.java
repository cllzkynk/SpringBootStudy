package com.TechPro.SpringBootStudy.basic_authentication;


import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class StudentBean05controller {
    private StudentBean05Service stdSrvc;//service layer' ulaşmak için obj create edildi

    public StudentBean05controller(StudentBean05Service stdSrvc) {
        this.stdSrvc = stdSrvc;
    }

    //bu method id ile ogrc returnn eden service methodu call edecek
    @GetMapping(path = "/selectStudentById/{id}")
    public StudentBean05 selectStudentById(@PathVariable Long id) {

        return stdSrvc.selectStudentById(id);
    }

    @GetMapping(path = "/selectAllStudents")
    public List<StudentBean05> selectAllStudents() {
        return stdSrvc.selectAllStudents();
    }

    @PutMapping(path = "/updateFullyStudentById/{id}")
    public StudentBean05 updatefulltStudentIdile(@PathVariable Long id,@RequestBody StudentBean05 newStd){


        return stdSrvc.updateFullyStudentById(id,newStd);
    }

    @DeleteMapping(path="/deleteStudentById/{id}")
    public String deleteStdntById(@PathVariable Long id){
        return stdSrvc.deletStudentById(id);//service layer method call
    }

    @PatchMapping(path = "/updatePartialStudentById/{id}")
    public StudentBean05 updatePertialStdntById(@PathVariable Long id ,@RequestBody StudentBean05 newStdnt){
        return stdSrvc.updatePatchStudentById(id,newStdnt);
    }

    @PostMapping(path = "/addStudent")
    public StudentBean05 addStdnt (@RequestBody StudentBean05 newStdnt) throws SQLException, ClassNotFoundException {


        return stdSrvc.addStudent(newStdnt);
    }






}
