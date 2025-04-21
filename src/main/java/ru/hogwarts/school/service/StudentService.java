package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Start method addStudent: {}", student);
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Start method findStudent: {}", id);
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Start method editStudent: {}", student);
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Start method deleteStudent: {}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> findAllByAge(int age) {
        logger.info("Start method findAllByAge: {}", age);
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Start method findByAgeBetween: min - {}, max - {}", min, min);
        return studentRepository.findByAgeBetween(min, max);
    }

    public Student findStudentsInFaculty(long id) {
        logger.info("Start method findStudentsInFaculty: {}", id);
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
        logger.info("Start method getStudentsByName: {}", name);
        return studentRepository.getStudentsByName(name);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public void parallelStudentsName() {
        List<Student> students = findAll();

        System.out.println("Student #1 - " + students.get(0).getName());
        System.out.println("Student #2 - " + students.get(1).getName());

        new Thread(() -> {
            System.out.println("Student #3 - " + students.get(2).getName());
            System.out.println("Student #4 - " + students.get(3).getName());
        }).start();

        new Thread(() -> {
            System.out.println("Student #5 - " + students.get(4).getName());
            System.out.println("Student #6 - " + students.get(5).getName());
        }).start();
    }

    final Object flag = new Object();
    final Object flag2 = new Object();

    public void synchronizedNames() {
        int count = 0;
        List<Student> students = findAll();

        System.out.println(count++ + ". Student #1 - " + students.get(0).getName());
        System.out.println(count++ + ". Student #2 - " + students.get(1).getName());

        synchronized (flag) {
            System.out.println(count++ + ". Student #3 - " + students.get(2).getName());
            System.out.println(count++ + ". Student #4 - " + students.get(3).getName());
        }

        synchronized (flag2) {
            System.out.println(count++ + ". Student #5 - " + students.get(4).getName());
            System.out.println(count++ + ". Student #6 - " + students.get(5).getName());
        }
    }
}
