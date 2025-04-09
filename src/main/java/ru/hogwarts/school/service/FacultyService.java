package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Start method addFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Start method findFaculty");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Start method editFaculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Start method deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Start method findByColor");
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByName(String name) {
        logger.info("Start method findByName");
        return facultyRepository.findByName(name);
    }

    public Collection<Faculty> findByColorOrName(String color, String name) {
        logger.error("Start method findByColorOrName");
        if (color != null && !color.isBlank()) {
            logger.error("Find faculty by color");
            return findByColor(color);
        }
        if (name != null && !name.isBlank()) {
            logger.error("Find faculty by name");
            return findByName(name);
        }
        logger.info("Find faculty by color and name");
        return facultyRepository.findByColorOrName(color, name);
    }

    public Faculty findFacultyInStudent(long id) {
        logger.info("Start method findFacultyInStudent");
        return (Faculty) findFaculty(id).getStudents().stream().toList();
    }

    public List<Faculty> getByColor(String color) {
        logger.info("Start method getByColor");
        return facultyRepository.getByColor(color);
    }

    public List<Faculty> getByName(String name) {
        logger.info("Start method getByName");
        return facultyRepository.getByName(name);
    }
}
