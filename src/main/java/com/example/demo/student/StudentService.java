package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents() {
         //return List.of(aStudent1, aStudent2);

        return(studentRepository.findAll());
    }

    public void addNewStudent(Student student) {
        Optional <Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
        if(optionalStudent.isPresent()){
            throw new IllegalStateException("Email already taken");
        }
        else{
            studentRepository.save(student);
        }
        //System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        //is student there?
        boolean exists = studentRepository.existsById(studentId);

        if(!exists){
            throw new IllegalStateException("Student with id "+ studentId + " does not exist!");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException("Student with id "+studentId+" does not exist!"));

        if(name!=null && name.length()>0 && !(student.getName().equals(name))) {
            student.setName(name);
        }

        if(email!=null && email.length()>0 && !(student.getEmail().equals(email))){
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
            if(optionalStudent.isPresent()){
                throw new IllegalStateException("Email is already taken!");
            }
            student.setEmail(email);
        }
    }
}
