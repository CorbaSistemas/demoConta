package com.example.demoConta.testUtil.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

/**
 * @author Dionízio Inácio on 28/05/2024
 */
public class JsonUtilsTest {


    public static String toJson(final Object obj) throws JsonProcessingException {
        return new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .writer()
            .withDefaultPrettyPrinter()
            .writeValueAsString(obj);
    }


    public static String toJson(final String file) throws IOException {

        return new ObjectMapper()
            .readTree((new File(file)))
            .toString();
    }
}
