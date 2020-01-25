package com.kifiya.kobiri;

import com.kifiya.kobiri.services.InitialisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {

    @Autowired
    InitialisationService initialisationService;

    @PostConstruct
    public void init() {
        try{
            initialisationService.initTransfert();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
