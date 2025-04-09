package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Start method addStudent");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Start method findStudent");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Start method editStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Start method deleteStudent");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findAllByAge(int age) {
        logger.info("Start method findAllByAge");
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Start method findByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Student findStudentsInFaculty(long id) {
        logger.info("Start method findStudentsInFaculty");
        return studentRepository.findById(id).get();
    }

    public Integer totalStudents() {
        logger.info("Start method totalStudents");
        return studentRepository.totalStudents();
    }

    public Integer getAverageAge() {
        logger.info("Start method getAverageAge");
        return studentRepository.getAverageAge();
    }

    public Collection<Student> getFiveStudents() {
        logger.info("Start method getFiveStudents");
        return studentRepository.getFiveStudents();
    }

    public List<Student> getStudentsByName(String name) {
        logger.info("Start method getStudentsByName");
        return studentRepository.getStudentsByName(name);
    }
}
