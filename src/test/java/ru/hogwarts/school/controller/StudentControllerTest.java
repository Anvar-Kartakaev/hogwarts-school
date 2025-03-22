package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;

import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private AvatarService avatarService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void ContextLoads() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testDefaultMessage() throws Exception {
        Assertions.assertThat(restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isEqualTo("Приложение работает!");
    }

    @Test
    void getStudentInfo() throws Exception {
        Student student = new Student();
        student.setId(1);
        Assertions.assertThat(studentController.getStudentInfo(student.getId())).isNotNull();
    }

    @Test
    void findAllByAge() throws Exception {
        Student student = new Student();
        student.setId(1);
        Assertions.assertThat(studentController.findAllByAge(student.getAge())).isNotNull();
    }

    @Test
    void createStudent() throws Exception {
        Student student = new Student();
        student.setName("Name");
        student.setAge(999);
        Assertions.assertThat(studentController.createStudent(student)).isNotNull();

    }

    @Test
    void editStudent() throws Exception {
        Student student = new Student();
        student.setName("Name");
        student.setAge(999);
        Assertions.assertThat(studentController.editStudent(student)).isNotNull();
    }

    @Test
    void deleteStudent() throws Exception {
        Student student = new Student();
        student.setId(10);
        Assertions.assertThat(studentController.deleteStudent(student.getId())).isNotNull();
    }

    @Test
    void findByAgeBetween() throws Exception {
        Student student = new Student();
        student.setAge(20);
        Assertions.assertThat(studentController.findByAgeBetween(student.getAge(), student.getAge())).isNotNull();
    }

    @Test
    void findStudentsInFaculty() throws Exception {
        Faculty faculty = new Faculty(1, "Name", "COLOR");
        AtomicReference<Student> student = new AtomicReference<>(new Student());
        student.get().setFaculty(faculty);
        Assertions.assertThat(studentController.findStudentsInFaculty(faculty.getId())).isNotNull();
    }

    @Test
    void downloadAvatar() throws Exception {
        long id = 1;
        Avatar avatar = avatarService.findAvatar(id);
        Assertions.assertThat(avatar).isNotNull();
    }

    @Test
    void testDownloadAvatar() throws Exception {
        long id = 1;
        Avatar avatar = avatarService.findAvatar(id);
        Assertions.assertThat(avatar).isNotNull();
    }
}