package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Info;
import ru.hogwarts.school.repository.InfoServiceImpl;

@RestController
public class InfoController {

    private final InfoServiceImpl infoServiceImpl;

    public InfoController(InfoServiceImpl infoServiceImpl) {
        this.infoServiceImpl = infoServiceImpl;
    }

    @GetMapping("/port")
    public Info getInfo() {
        return infoServiceImpl.getInfo();
    }

}
