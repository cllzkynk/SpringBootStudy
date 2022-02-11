package com.TechPro.SpringBootStudy.basic_authentication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
//Bu class'da data create edip DB'deki tabloya datalar gönderilecek:SQL insert komutu ile de yapılabilir
public class StudentBean05Config {
    @Bean//obj return edilen mwethodlar @bean annotation kullanılır..İnterview :@Bean sadece method level de kullanılır
    //veriable class ve cons level de kullanılmaz
    CommandLineRunner clr1(StudentBean05Repository studentRepo){


        return args-> studentRepo.saveAll(List.of(
                new StudentBean05(110L,"lutfullah bey","le@cimeyıl.com", LocalDate.of(1992,4,23)),
                new StudentBean05(111L,"mehmet bey","mhtr@cimeyıl.com", LocalDate.of(1990,6,3)),
                new StudentBean05(112L,"suleyman bey","slmn@cimeyıl.com", LocalDate.of(2000,10,12)),
                new StudentBean05(113L,"gökhan bey","gkhn@cimeyıl.com", LocalDate.of(1996,9,7)),
                new StudentBean05(133L,"islam bey","selam@cimeyıl.com", LocalDate.of(1996,9,7))

        ));

    }

}
