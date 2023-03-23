package com.customannotationtag.learningspring;


import com.customannotationtag.learningspring.annotation.BananaType;
import com.customannotationtag.learningspring.controllers.PeeledBananaController;
import com.customannotationtag.learningspring.controllers.UnpeledBananaController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BananaTypeAnnotationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PeeledBananaController peeled;

    private BananaType getAnnotation(Class<?> reflectClass) {
        if (reflectClass.isAnnotationPresent(BananaType.class)) {
            return reflectClass.getAnnotation(BananaType.class);
        }
        return null;
    }

    @Test
    void testAnnotationPresentInPeeledControllerClass() {
        BananaType annotation = this.getAnnotation(PeeledBananaController.class);
        assert annotation != null;
        assertEquals("peeled", annotation.type());
        assertEquals("/bananas/{type}", annotation.path());
        assertTrue(PeeledBananaController.class.isAnnotationPresent(BananaType.class));
    }

    @Test
    void testAnnotationPresentInUnpeledControllerClass() {
        BananaType annotation = this.getAnnotation(UnpeledBananaController.class);
        assert annotation != null;
        assertEquals("unpeled", annotation.type());
        assertEquals("/bananas/{type}", annotation.path());
        assertTrue(UnpeledBananaController.class.isAnnotationPresent(BananaType.class));
    }

    @Test
    void testBananaTypeAnnotationPeeledController() throws Exception {
        var access = peeled.doSomething();
        var result = this.mockMvc.perform(post("/bananas/peeled/eat")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PeeledBananaController.class))
                .andExpect(content().string("Delicious"))
                .andReturn();
        assertTrue(true);
        assertNotNull(result);
    }

    @Test
    void testBananaTypeAnnotationUnpeled() throws Exception {
        var result = this.mockMvc.perform(post("/bananas/unpeled/eat")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UnpeledBananaController.class))
                .andExpect(content().string("Eww banana skin"))
                .andReturn();
        assertTrue(true);
        assertNotNull(result);
    }

    @Test
    void testNoMappingFoundForWrongURL() throws Exception {
        this.mockMvc.perform(post("/bananas/wrongtype/eat")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

}
