package com.TechPro.SpringBootStudy.basic_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class StudentBean05Service {
    private StudentBean05Repository studentRepo;//Repository layer'a ulaşmak için data type'nan obj create edildi.

    //obj degerini cons'dan alacak
    @Autowired
    public StudentBean05Service(StudentBean05Repository studentRepo) {
        this.studentRepo = studentRepo;
    }

    //Bu method id ile ögrc return edecek
    public StudentBean05 selectStudentById(Long id) {
        // return studentRepo.findById(id).get();--> olmayan id hata verir code kırlrı bununiçin kontrol if çalışmalı
        if (studentRepo.findById(id).isPresent()) {

            return studentRepo.findById(id).get();
        }
        return new StudentBean05();//isteen id yoksa bos cons run edilecek
    }//service layer'de repository'den alınan datalar methodda çalıştırıldı. bu metthod controlle layer'da call edilmeli

    //bu get method tum ogrc return eder
    public List<StudentBean05> selectAllStudents() {
        return studentRepo.findAll();

    }

    // Bu method var olan ogrc tum datalarını (PUT:fully update) update eder

    public StudentBean05 updateFullyStudentById(Long id, StudentBean05 kullanıcınınOgrc) {//kullanıcıdan gelen id ve yeni bilgilerle id li studenti güncelliyecez

        StudentBean05 eskiOgrc = studentRepo.
                findById(id).
                orElseThrow(() -> new IllegalStateException("AGAM niddin :-( giridigin " + id + " li ogrenci araziii"));

        //name aupdate edilecek
        if (kullanıcınınOgrc.getName() == null) {//kullanıcı yeni bir isim girmezse
            eskiOgrc.setName(null);//eski ogrc ismini bos bırak
        } else if (!eskiOgrc.getName().equals(kullanıcınınOgrc.getName())) {//kullanıcının ogrc isimi ile eski ogrc ismi farklı ise
            eskiOgrc.setName(kullanıcınınOgrc.getName());//kullanıcı ogrc ismi eski ogrc ismine ata
        }


        //email aupdate edilecek
        /*
        brd:

        1) email tekararlı olmaz uniq-->EXCEPTION
        2) email gecerli (@ içermeli) olmalı-->EXCEPTION
        3) email null olamaz -->EXCEPTION
        4) email eski ve yeni aynı ise gereksiz işlem için update etmemeli
         */
        if (kullanıcınınOgrc.getEmail()==null){
            kullanıcınınOgrc.setEmail("");
        }
        Optional<StudentBean05> emailOLaneskiOgrc = studentRepo.findStudentBean05ByEmail(kullanıcınınOgrc.getEmail());
        if (emailOLaneskiOgrc.isPresent()) {//1. sart kontrol edildi eger emailolanogc containerde varsa
            throw new IllegalStateException("daha once bu email kullanıldı");
        }else if (!kullanıcınınOgrc.getEmail().contains("@")&& kullanıcınınOgrc.getEmail()!=""){//2 . sart kontrol edilecek
            throw new IllegalStateException("@ karakteri kullanmalısınız");
        }else if(kullanıcınınOgrc.getEmail()==null){//3. sart kontrol edilecek
            throw new IllegalStateException("mutlaka bir email girmelisiniz");
        }else if(!kullanıcınınOgrc.getEmail().equals(eskiOgrc.getEmail())){
            eskiOgrc.setEmail(kullanıcınınOgrc.getEmail());
        }else {
            throw new IllegalStateException("aynı e mail update edilmez");
        }

        //dob aupdate edilecek
        /*
        1) girilen dob gelecekten olmamalı hayatın akısına ters oldg için excp
        2) girilen dop yanı olmamalı gereksia işelem için excp
         */
        if (Period.between(kullanıcınınOgrc.getDob(), LocalDate.now()).isNegative()) {//1. sart kontrol edildi
            throw new IllegalStateException("hatalı dob giridiniz");

        }else if (!kullanıcınınOgrc.getDob().equals(eskiOgrc.getDob())) {//2. sart kontrol edildi
            eskiOgrc.setDob(kullanıcınınOgrc.getDob());

        }

        return studentRepo.save(eskiOgrc);
    }


}
