package com.TechPro.SpringBootStudy.basic_authentication;

import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
class StudentBean05Service {
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

    // Bu method id ile date(student) silecek

    public String deletStudentById(Long id){
        if (!studentRepo.existsById(id)){  //id'si verilen obj'nin DB'da varligini kontrol eder
            // --> id'li ogrenci yoksa code excute stop App stop
            throw new IllegalStateException("Agam niddin" + id + "'li ogrenci arazi");
        }
        studentRepo.deleteById(id); //id'li ogrenciyi delet eder
        return "Agam "+ id+ "'li ogrenci sizlere omur...";
    } // bu method run icin controller de call edilmeli


    // bu method objelerin PACTH(partikal kismi) datalarini update eder

    public StudentBean05 updatePatchStudentById(Long id, StudentBean05 newStudent){

        StudentBean05 existingStudentById= studentRepo.
                findById(id).orElseThrow(()-> new IllegalStateException(id+"'li ogrenci yok")); //Lambda expression

        // student email update edilecek BRD
        /*
        brd:

        1) email tekararlı olmaz uniq-->EXCEPTION
        2) email gecerli (@ içermeli) olmalı-->EXCEPTION
        3) email null olamaz -->EXCEPTION
        4) email eski ve yeni aynı ise gereksiz işlem için update etmemeli
         */
        if (newStudent.getEmail()==null){
            newStudent.setEmail("");
        }
        Optional<StudentBean05> emailOLaneskiOgrc = studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
        if (emailOLaneskiOgrc.isPresent()) {//1. sart kontrol edildi eger emailolanogc containerde varsa
            throw new IllegalStateException("daha once bu email kullanıldı");
        }else if (!newStudent.getEmail().contains("@")&& newStudent.getEmail()!=""){//2 . sart kontrol edilecek
            throw new IllegalStateException("@ karakteri kullanmalısınız");
        }else if(newStudent.getEmail()==null){//3. sart kontrol edilecek
            throw new IllegalStateException("mutlaka bir email girmelisiniz");
        }else if(!newStudent.getEmail().equals(existingStudentById.getEmail())){
            existingStudentById.setEmail(newStudent.getEmail());
        }else {
            throw new IllegalStateException("aynı e mail update edilmez");
        }

        return studentRepo.save(existingStudentById); // update edilecek ogrc action sonrasi save edilerek return edilir
    }

    public StudentBean05 addStudent(StudentBean05 newStudent) throws ClassNotFoundException, SQLException {
        //ogrc email datası girilecek
        //e mail tekrarsız olmalı BRD
        Optional<StudentBean05> existingStudenById= studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
        if (existingStudenById.isPresent()){//eski ogrc email varsa exc
            throw new IllegalStateException("AGAM bu "+newStudent.getEmail()+" 2. el sana ajente bir imeyıl lazım");

        }
        //ogrc name datası giriliecek
        if (newStudent.getName()==null) {//yeni ogrc henus name girmemis-->excp
            throw new IllegalStateException("AGAM adın yoksa sen de yoksun  :-( ");
        }

        //her yeni ogrc için app uniq id  cretae etmeli...
    /*
    LOGİC : DB'de varolan max id get edip +1 hali yeni id assaign edilmeli
     */
        //DB'ye JDBC connection ...
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC","root","1234");
        Statement st = con.createStatement();

        //max id get içinSQL query komutla
        String sqlQueryForMaxId="select max(id) from students";//birden cok (id tek olacagı içim bizm sorguda tek verir)sonuc  satır verir
        ResultSet result= st.executeQuery(sqlQueryForMaxId);//query sonrası satırlerı return eder loop ile istene  satır alınır
        Long maxId=0l;
        while(result.next()){//next() pointer bir sonraki satıra gider onceki satur return eder
           maxId= result.getLong(1);
        }
        newStudent.setId(maxId+1);
        newStudent.setAge(newStudent.getAge());
        newStudent.setErrMsg("AGAM müjde nur topu gibi ogrencin oldii");

        return studentRepo.save(newStudent);
    }// bu metod controllerde call edilmeli















}