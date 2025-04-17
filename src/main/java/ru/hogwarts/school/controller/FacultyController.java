package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public String defaultMessage() {
        return "Приложение работает!";
    }

    @GetMapping("info/{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findByColorOrName")
    public ResponseEntity<Collection<Faculty>> findByColorOrName(@RequestParam(required = false) String color, @RequestParam(required = false) String name) {
        return ResponseEntity.ok(facultyService.findByColorOrName(color, name));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Faculty> findFacultyInStudent(@PathVariable long id) {
        return ResponseEntity.ok(facultyService.findFacultyInStudent(id));
    }

    @GetMapping("get-faculty/{color}")
    public ResponseEntity<List<Faculty>> getByColor(@PathVariable("color") String color) {
        List<Faculty> faculty = facultyService.getByColor(color);
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("get-faculty/{name}")
    public ResponseEntity<List<Faculty>> getByName(@PathVariable("name") String name) {
        List<Faculty> faculty = facultyService.getByName(name);
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/all-faculty")
    public ResponseEntity<List<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.findAll());
    }

    @GetMapping("/long-faculty-name")
    public String getFacultyLongName() {
        List<String> faculties = facultyService.findAll().stream().map(Faculty::getName).toList();
        String max = Collections.max(faculties, Comparator.comparing(s -> s.length()));
        return max;
    }

    @GetMapping("/int-return")
    public void getIntReturn() {
        long start = System.currentTimeMillis();
//        int sum = Stream.iterate(1, a -> a +1).limit(1_000_000).reduce(0, (a, b) -> a + b );
        int sum = IntStream.range(1, 1_000_000).sum();
        long end = System.currentTimeMillis();
        System.out.println(sum + "\n" + (end - start));
    }
}
