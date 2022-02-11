package com.TechPro.SpringBootStudy.controller_service_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudenBean04Service {
    private StudentBean04Repository studentRepo;//Repository layer'a ulaşmak için data type'nan obj create edildi.
    //obj degerini cons'dan alacak
    @Autowired
    public StudenBean04Service (StudentBean04Repository studentRepo){
        this.studentRepo = studentRepo;
    }
    //Bu method id ile ögrc return edecek
    public StudentBean04 selectStudentById(Long id){
       // return studentRepo.findById(id).get();--> olmayan id hata verir code kırlrı bununiçin kontrol if çalışmalı
        if (studentRepo.findById(id).isPresent()){

            return studentRepo.findById(id).get();
        }
        return new  StudentBean04();//isteen id yoksa bos cons run edilecek
    }//service layer'de repository'den alınan datalar methodda çalıştırıldı. bu metthod controlle layer'da call edilmeli

}
