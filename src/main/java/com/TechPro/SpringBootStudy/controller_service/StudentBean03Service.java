package com.TechPro.SpringBootStudy.controller_service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentBean03Service {


    List<StudentBean03> listOfStudent=List.of(

 new StudentBean03(101L,"zekeriya canbal","ac@cimeyıl.com",LocalDate.of(1980,12,5)),
 new StudentBean03(102L,"hatice hanım","abc@cimeyıl.com",LocalDate.of(1981,1,15)),
 new StudentBean03(103L,"yıldız parlak","xyz@cimeyıl.com",LocalDate.of(1970,3,4)),
 new StudentBean03(104L,"seyma hanımefendi","asd@cimeyıl.com",LocalDate.of(1990,5,7))

    );

//id ile obj datası veren method
    public StudentBean03 getStudentById(Long id){
if (listOfStudent.stream().filter(t->t.getId()==id).collect(Collectors.toList()).isEmpty()){
    return new StudentBean03();//p'siz cons return et
}

        return listOfStudent.stream().filter(t->t.getId()==id).findFirst().get();
    }
    //tum student listi return eden method
    public List<StudentBean03> getAllStudent(){
        return listOfStudent;

    }

}
