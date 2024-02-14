package com.campusland.utils.conexionpersistencia.conexionbdjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonConfig {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        // Habilitar la serialización de fechas como timestamps
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // Registrar el módulo JavaTimeModule para soportar tipos de Java 8 Date/Time
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}