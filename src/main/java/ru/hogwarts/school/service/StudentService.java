package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> findAllByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Student findStudentsInFaculty(long id) {
        return studentRepository.findById(id).get();
    }

    public Integer totalStudents() {
        return studentRepository.totalStudents();
    }

    public Integer getAverageAge() {
        return studentRepository.getAverageAge();
    }

    public Collection<Student> getFiveStudents() {
        return studentRepository.getFiveStudents();
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.getStudentsByName(name);
    }
}
