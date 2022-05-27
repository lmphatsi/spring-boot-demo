package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner (StudentRepository studentRepository){
        return args -> {
            Student aStudent1 = new Student("Lebajoa", "la@nul.ls", LocalDate.of(1986, 9, 20));
            Student aStudent2 = new Student("Mamphatsi", "mg@nul.ls", LocalDate.of(1996, 10, 20));

            studentRepository.saveAll(List.of(aStudent1,aStudent2));
        };
    }


}
