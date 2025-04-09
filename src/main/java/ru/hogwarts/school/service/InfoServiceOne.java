package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Info;
import ru.hogwarts.school.repository.InfoRepository;

@Service
@Profile("one")
public class InfoServiceOne implements InfoRepository {

    Logger logger = LoggerFactory.getLogger(InfoServiceOne.class);

    @Value("${hogwarts.server.port.one}")
    private String port;

    @Override
    public Info getInfo() {
        logger.info("Start method getInfo");
        Info info = new Info();
        info.setPort(port);
        return info;
    }

}
