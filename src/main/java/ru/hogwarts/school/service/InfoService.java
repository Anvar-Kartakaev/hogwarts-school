package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Info;
import ru.hogwarts.school.repository.InfoServiceImpl;

@Service
@Profile("!test")
public class InfoService implements InfoServiceImpl {

    Logger logger = LoggerFactory.getLogger(InfoService.class);

    @Value("${server.port}")
    private String port;

    public Info getInfo() {
        logger.info("Start method getInfo: {}", port);
        return new Info(port);
    }
}
