package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByName(String name) {
        return facultyRepository.findByName(name);
    }

    public Collection<Faculty> findByColorOrName(String color, String name) {
        if (color != null && !color.isBlank()) {
            return findByColor(color);
        }
        if (name != null && !name.isBlank()) {
            return findByName(name);
        }
        return facultyRepository.findByColorOrName(color, name);
    }

    public Faculty findFacultyInStudent(long id) {
        return (Faculty) findFaculty(id).getStudents().stream().toList();
    }

    public List<Faculty> getByColor(String color) {
        return facultyRepository.getByColor(color);
    }

    public List<Faculty> getByName(String name) {
        return facultyRepository.getByName(name);
    }
}
