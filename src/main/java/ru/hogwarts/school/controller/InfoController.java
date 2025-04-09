package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Info;
import ru.hogwarts.school.repository.InfoRepository;

@RestController
public class InfoController {

    @Autowired
    private InfoRepository infoRepository;

    @GetMapping
    public Info getInfo() {
        return infoRepository.getInfo();
    }

}
