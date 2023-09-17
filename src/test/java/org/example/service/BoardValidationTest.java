package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.BoardRegRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
public class BoardValidationTest {
    private static ValidatorFactory factory;
    private static Validator valiator;

    @BeforeAll
    static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        valiator = factory.getValidator();
    }

    @AfterAll
    static void close() {
        factory.close();
    }

    @Test
    void 빈문자열_전송_테스트() {
        BoardRegRequest boardRegRequest = BoardRegRequest.builder()
                .title("")
                .regName("")
                .content("baby dinosaur dooly")
                .build();

        Set<ConstraintViolation<BoardRegRequest>> validations = valiator.validate(boardRegRequest);

        /*
        Property : title , message : Please enter a title
        Property : regName , message : Please enter your name
         */
        validations.forEach(error -> log.info("Property : {} , message : {}", error.getPropertyPath(), error.getMessage()));

    }
}
