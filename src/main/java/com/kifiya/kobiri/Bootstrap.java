package com.kifiya.kobiri;

import com.kifiya.kobiri.services.InitialisationService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {
    private static Logger logger = Logger.getLogger(Bootstrap.class);
    @Autowired
    InitialisationService initialisationService;

    @PostConstruct
    public void init() {
        try{
            //initialisationService.init();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
