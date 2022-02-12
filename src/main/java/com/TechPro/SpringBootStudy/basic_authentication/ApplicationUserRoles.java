package com.TechPro.SpringBootStudy.basic_authentication;



import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.TechPro.SpringBootStudy.basic_authentication.ApplicationUserPermission.STUDENT_READ;
import static com.TechPro.SpringBootStudy.basic_authentication.ApplicationUserPermission.STUDENT_WRITE;


public enum ApplicationUserRoles {//ENUM:app sabit(fix) dataların saklandıgı yapıdır

    STUDENT(Sets.newHashSet(STUDENT_READ)) , ADMIN(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE));


    private  final Set<ApplicationUserPermission> permissions;// STUDENT_READ("student:read"), STUDENT_WRITE("student:write")
// ApplicationUserPermission enumdaki dataları call etmek için ve datalar uniq oldg set data type'nde veriable create edildi

    ApplicationUserRoles(Set<ApplicationUserPermission> permissions) {//fianl olan veriable initial olması için p'li cons
        this.permissions = permissions;
    }
    /*

    public Set<ApplicationUserPermission> izinleriGetirenMethod() {//permissions obj data field(STUDENT_READ,STUDENT_WRITE) okumak için get
        return permissions;
    }

   */
    public Set<SimpleGrantedAuthority> izinOnayla () {

        //Set<SimpleGrantedAuthority> onaylananIzinler= izinleriGetirenMethod().
        Set<SimpleGrantedAuthority> onaylananIzinler= permissions.
                stream().
                map(t-> new SimpleGrantedAuthority(t.getPermission())).
                collect(Collectors.toSet());
        onaylananIzinler.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return onaylananIzinler;
    }

}