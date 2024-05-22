package com.rdm.rdm.config;

import Interfaces.CreateAnimalService;
import com.rdm.rdm.implementations.CreateAnimalServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(StarterProps.class)
public class StarterConfiguration {

    @Bean
    public CreateAnimalService createAnimalService(StarterProps cfg) {
        return new CreateAnimalServiceImpl(cfg.getCatNames(), cfg.getDogNames(),
                cfg.getSharkNames(), cfg.getWolfNames());
    }
}
