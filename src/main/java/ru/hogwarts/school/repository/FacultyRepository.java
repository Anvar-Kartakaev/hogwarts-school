package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findByColor(String color);

    List<Faculty> findByName(String name);

    List<Faculty> findByColorOrName(String color, String name);

    List<Faculty> getByColor(String color);

    List<Faculty> getByName(String name);

    List<Faculty> findAll();

}
