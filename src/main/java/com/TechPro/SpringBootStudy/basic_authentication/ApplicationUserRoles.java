package com.TechPro.SpringBootStudy.basic_authentication;



import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.TechPro.SpringBootStudy.basic_authentication.ApplicationUserPermission.STUDENT_READ;
import static com.TechPro.SpringBootStudy.basic_authentication.ApplicationUserPermission.buIzinPutPostPatchYapar;


public enum ApplicationUserRoles {//ENUM:app sabit(fix) dataların saklandıgı yapıdır

    STUDENT(Sets.newHashSet(STUDENT_READ)) , ADMIN(Sets.newHashSet(STUDENT_READ, buIzinPutPostPatchYapar));


private  final Set<ApplicationUserPermission> permissions;// STUDENT_READ("student:read"), STUDENT_WRITE("student:write")
// ApplicationUserPermission enumdaki dataları call etmek için ve datalar uniq oldg set data type'nde veriable create edildi

    ApplicationUserRoles(Set<ApplicationUserPermission> permissions) {//fianl olan veriable initial olması için p'li cons
        this.permissions = permissions;
    }
  // public Set<ApplicationUserPermission> izinleriGetirenMethod() {//permissions obj data field(STUDENT_READ,STUDENT_WRITE) okumak için get
  //     return permissions;
  // }

    public Set<SimpleGrantedAuthority> izinOnayla() {
        /*
        Bu method STUDENT_READ("student:read"), STUDENT_WRITE("student:write"); Role obj'deki "student:read" ve "student:write"
         izinlerinin onaylanmıs set'ini return eder.
         SimpleGrantedAuthority : Authentication obj verilen bir Authority'i(izin yetki) String degerini saklar.
         */
       // Set<SimpleGrantedAuthority> onaylananIzinler= izinleriGetirenMethod().
        Set<SimpleGrantedAuthority> onaylananIzinler= permissions.//permission enumdaki datalrın field'larını("student:read", "student:write") greturn ediyor
                stream().//gelen izinler akısa alındı
                map(t-> new SimpleGrantedAuthority(t.getPermission())).//akısdaki izinler springboot security geregi onaylanıyor
                collect(Collectors.toSet());//akısdan onaylanarak cıkan izinler set'e atandı
        onaylananIzinler.add(new SimpleGrantedAuthority("ROLE_"+this.name()));//springboot formatı geregi onaylana sette izinleri
        return onaylananIzinler;
    }

}
