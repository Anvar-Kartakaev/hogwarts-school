package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Info;
import ru.hogwarts.school.repository.InfoRepository;

@Service
@Profile("two")
public class InfoServiceTwo implements InfoRepository {

    Logger logger = LoggerFactory.getLogger(InfoServiceTwo.class);

    @Value("${hogwarts.server.port.two}")
    private String port;

    @Override
    public Info getInfo() {
        logger.info("Start method getInfo");
        Info info = new Info();
        info.setPort(port);
        return info;
    }

}
