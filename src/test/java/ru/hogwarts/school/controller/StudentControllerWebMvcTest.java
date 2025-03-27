package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
@Import(StudentService.class)
class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @MockitoBean
    private AvatarService avatarService;

    @Test
    void defaultMessage() throws Exception {
        String statusApp = "Приложение работает!";

        mockMvc.perform(MockMvcRequestBuilders.get("/student")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void getStudentInfo() throws Exception {
        long id = 1;
        String name = "Student Name";
        int age = 32;

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentService.findStudent(student.getId())).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findAllByAge() throws Exception {
        int id = 1;
        String name = "Student Name";
        int age = 32;

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student/age/" + student.getId())
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createStudent() throws Exception {
        long id = 1;
        String name = "Student Name";
        int age = 32;

        long facultyId = 1;
        String facultyName = "Faculty Name";
        String facultyColor = "Faculty Color";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("facultyId", facultyId);
        facultyObject.put("facultyName", facultyName);
        facultyObject.put("facultyColor", facultyColor);

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", age);
        studentObject.put("faculty", facultyObject);

        Faculty faculty = new Faculty();
        faculty.setId(facultyId);
        faculty.setName(facultyName);
        faculty.setColor(facultyColor);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setFaculty(faculty);

        when(studentService.addStudent(student)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.faculty").value(faculty));
    }

    @Test
    void editStudent() throws Exception {
        long id = 1;
        String name = "Student Name";
        int age = 32;
        Faculty faculty = new Faculty(2, "Faculty Name", "Faculty Color");

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", age);
        studentObject.put("faculty", faculty);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setFaculty(faculty);

        when(studentService.editStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteStudent() throws Exception {
        long id = 1;
        String name = "Student Name";
        int age = 32;

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentService.findStudent(any(long.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.
                        delete("/student/" + student.getId())
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findByAgeBetween() throws Exception {
        int id = 1;
        String name = "Student Name";
        int age = 32;

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        List<Student> students = new ArrayList<>(List.of(student));

        JSONObject studentObject = new JSONObject();
        studentObject.put("list", students);
        int min = 20, max = 35;

        when(studentService.findByAgeBetween(min, max)).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/findByAgeBetween?min=" + student.getId() + "&max=" + student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findStudentsInFaculty() throws Exception {
        long id = 1;
        String name = "Faculty Name";
        String color = "Faculty Color";
        Collection<Student> students = new ArrayList<>();

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        long studentId = 1;
        String studentName = "StudentName";
        int studentAge = 28;

        Student student = new Student();
        student.setId(studentId);
        student.setName(studentName);
        student.setAge(studentAge);
        student.setFaculty(faculty);

        students.add(student);

        faculty.setStudents(students);

        when(studentService.findStudentsInFaculty(student.getId())).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student/faculty/" + student.getId())
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void uploadAvatar() throws Exception {
        long id = 1;
        File file = new File("src/test/resources/avatar.jpg");

        JSONObject avatarObject = new JSONObject();
        avatarObject.put("id", id);
        avatarObject.put("name", file.getName());

        Avatar avatar = new Avatar();
        avatar.setId(id);
        avatar.setFilePath(file.getPath());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student/" + avatar.getId() + "/avatar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void downloadAvatar() throws Exception {
        long id = 1;
        File file = new File("src/test/resources/avatar.jpg");

        JSONObject avatarObject = new JSONObject();
        avatarObject.put("id", id);
        avatarObject.put("name", file.getName());

        Avatar avatar = new Avatar();
        avatar.setId(id);
        avatar.setFilePath(file.getPath());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student/" + avatar.getId() + "/avatar/preview")
                        .content(avatarObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void testDownloadAvatar() throws Exception {
        long id = 1;
        File file = new File("src/test/resources/avatar.jpg");

        JSONObject avatarObject = new JSONObject();
        avatarObject.put("id", id);
        avatarObject.put("name", file.getName());

        Avatar avatar = new Avatar();
        avatar.setId(id);
        avatar.setFilePath(file.getPath());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + avatar.getId() + "/avatar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}