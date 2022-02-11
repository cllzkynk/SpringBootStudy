package com.TechPro.SpringBootStudy.basic_authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
    @Bean
    public PasswordEncoder passSifrele(){//return type obj olam bir method .Bumethod password encode ettiği için password olan class'a call edilmeli
        return new BCryptPasswordEncoder(10);//crypto guvenlik seviyesi genelde 10-8 kullnılır
    }
}
