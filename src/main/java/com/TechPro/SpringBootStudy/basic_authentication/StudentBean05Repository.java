package com.TechPro.SpringBootStudy.basic_authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository                                                 // <Kullanılacak model class,PK data type>
public interface StudentBean05Repository extends JpaRepository<StudentBean05,Long> {//repository'in ihtiyac duyacagı id ile data alma vs bazı methodları kullanmak içinextend edildi


    Optional<StudentBean05> findStudentBean05ByEmail(String email);//e mail ile data bulan metod


}
