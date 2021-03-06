package com.kifiya.kobiri.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("erreurhttp")
@Tag("junit5")
@ExtendWith(SpringExtension.class)
public class GestionErreurTest {
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new GestionErreur()).build();
    }

    @Test
    public void erreur404() throws Exception {
        mockMvc.perform(get("/404"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}