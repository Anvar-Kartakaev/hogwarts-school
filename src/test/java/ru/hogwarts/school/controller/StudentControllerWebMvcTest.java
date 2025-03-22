package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentRepository studentRepository;

    @MockitoSpyBean
    private StudentService studentService;

    @MockitoSpyBean
    private AvatarService avatarService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void defaultMessage() throws Exception {
        String statusApp = "Приложение работает!";

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(header().string("Приложение работает!", statusApp));
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
        when(studentRepository.findById(any(long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
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

        when(studentService.findAllByAge((int) student.getId()));
        when(studentRepository.findById(any(long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student/age/")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void createStudent() throws Exception {
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

        when(studentRepository.save(student)).thenReturn(student);
        when(studentRepository.findById(any(long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.
                        post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void editStudent() throws Exception {
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

        when(studentRepository.save(student)).thenReturn(student);
        when(studentRepository.findById(any(long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.
                        put("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
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

        when(studentController.deleteStudent(student.getId())).thenReturn(null);
        when(studentRepository.findById(any(long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.
                        delete("/student/delete-")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void findByAgeBetween() throws Exception {
        long id = 1;
        String name = "Student Name";
        int age = 32;

        long id2 = 2;
        String name2 = "Student2 Name";
        int age2 = 30;

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        JSONObject student2Object = new JSONObject();
        student2Object.put("id2", id2);
        student2Object.put("name2", name2);
        student2Object.put("age2", age2);

        AtomicReference<Student> student2 = new AtomicReference<>(new Student());
        student2.get().setId(id2);
        student2.get().setName(name2);
        student2.get().setAge(age2);

        when(studentController.findByAgeBetween(age, age2));
        when(studentRepository.findById(any(long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student/findByAgeBetween")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
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

        when(studentController.findStudentsInFaculty(student.getId()));
        when(studentRepository.findById(any(long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student/faculty-")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value(studentId))
                .andExpect(jsonPath("$.studentAge").value(studentAge))
                .andExpect(jsonPath("$.studentName").value(studentName));
    }

    @Test
    void uploadAvatar() throws Exception {
        long id = 1;
        MultipartFile file = new MockMultipartFile("file", "avatar.jpg", "image/jpeg", new byte[]{});

        JSONObject avatarObject = new JSONObject();
        avatarObject.put("id", id);
        avatarObject.put("name", file.getOriginalFilename());

        Avatar avatar = new Avatar();
        avatar.setId(id);
        avatar.setFilePath(file.getOriginalFilename());

        when(studentController.uploadAvatar(avatar.getId(), file));

        mockMvc.perform(MockMvcRequestBuilders.post("/student/upload-avatar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.file").value(file.getOriginalFilename()));
    }

    @Test
    void downloadAvatar() throws Exception {
        long id = 1;
        MultipartFile file = new MockMultipartFile("file", "avatar.jpg", "image/jpeg", new byte[]{});

        JSONObject avatarObject = new JSONObject();
        avatarObject.put("id", id);
        avatarObject.put("name", file.getOriginalFilename());

        Avatar avatar = new Avatar();
        avatar.setId(id);
        avatar.setFilePath(file.getOriginalFilename());

        when(avatarService.findAvatar(avatar.getId())).thenReturn(avatar);

        mockMvc.perform(MockMvcRequestBuilders.
                get("/student/" + avatar.getId() + "/avatar/preview")
        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.file").value(file.getOriginalFilename()));
    }

    @Test
    void testDownloadAvatar() throws Exception {
        long id = 1;
        MultipartFile file = new MockMultipartFile("file", "avatar.jpg", "image/jpeg", new byte[]{});

        JSONObject avatarObject = new JSONObject();
        avatarObject.put("id", id);
        avatarObject.put("name", file.getOriginalFilename());

        Avatar avatar = new Avatar();
        avatar.setId(id);
        avatar.setFilePath(file.getOriginalFilename());

        when(avatarService.findAvatar(avatar.getId())).thenReturn(avatar);

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/student/" + avatar.getId() + "/avatar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.file").value(file.getOriginalFilename()));
    }
}