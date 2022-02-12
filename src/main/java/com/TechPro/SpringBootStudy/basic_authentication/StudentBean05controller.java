package com.TechPro.SpringBootStudy.basic_authentication;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")//BU methodu ROLE  göre yetkilendir
    public StudentBean05 selectStudentById(@PathVariable Long id) {

        return stdSrvc.selectStudentById(id);
    }

    @GetMapping(path = "/selectAllStudents")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    public List<StudentBean05> selectAllStudents() {
        return stdSrvc.selectAllStudents();
    }

    @PutMapping(path = "/updateFullyStudentById/{id}")
    @PreAuthorize("hasAuthority('student:Write')")//BU methodu permissiona göre yetkilendir
    public StudentBean05 updatefulltStudentIdile(@PathVariable Long id,@Validated @RequestBody StudentBean05 newStd){


        return stdSrvc.updateFullyStudentById(id,newStd);
    }
    @DeleteMapping(path="/deleteStudentById/{id}")
    public String deleteStdntById(@PathVariable Long id){
        return stdSrvc.deletStudentById(id);//service layer method call
    }
    @PatchMapping(path="/updatePartialStudentById/{id}")
    @PreAuthorize("hasAuthority('student:Write')")//BU methodu permissiona göre yetkilendir
    public StudentBean05 updatePartialStdntById( @PathVariable Long id,@Validated @RequestBody StudentBean05 newStdnt){

        return  stdSrvc.updatePatchStudentById(id,newStdnt);

    }
    @PostMapping(path="/addStudent")
    @PreAuthorize("hasAuthority('student:Write')")//BU methodu permissiona göre yetkilendir
    public StudentBean05 addStndt(@Validated @RequestBody StudentBean05 newStdnt) throws SQLException, ClassNotFoundException {
        return stdSrvc.addStudent(newStdnt);
    }
}