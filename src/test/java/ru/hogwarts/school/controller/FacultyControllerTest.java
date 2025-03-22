package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void ContextLoads() {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testDefaultMessage() throws Exception {
        Assertions.assertThat(restTemplate.getForObject("http://localhost:" + port + "/faculty", String.class))
                .isEqualTo("Приложение работает!");
    }

    @Test
    void getFacultyInfo() throws Exception {
        Faculty faculty = new Faculty(1, "NAME", "COLOR");
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/info-" + faculty.getId(), String.class)).isNotNull();
    }

    @Test
    void createFaculty() throws Exception {
        Faculty faculty = new Faculty(1, "NAME", "COLOR");
        Assertions.assertThat(facultyController.createFaculty(faculty)).isNotNull();
    }

    @Test
    void editFaculty() throws Exception {
        Faculty faculty = new Faculty(1, "NAME", "COLOR");
        Assertions.assertThat(facultyController.editFaculty(faculty)).isNotNull();
    }

    @Test
    void deleteFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(10);
        Assertions.assertThat(facultyController.deleteFaculty(faculty.getId())).isNotNull();
    }

    @Test
    void findByColorOrName() throws Exception {
        Faculty faculty = new Faculty(11, "NAME", "COLOR");
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/findByColorOrName" + faculty.getColor() + faculty.getName(), String.class)).isNotNull();
    }

    @Test
    void findFacultyInStudent() throws Exception {
        Faculty faculty = new Faculty(11, "NAME", "COLOR");
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/students-" + faculty.getId(), String.class)).isNotNull();

    }
}