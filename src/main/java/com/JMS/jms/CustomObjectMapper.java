package com.JMS.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by Elvira on 04.06.2018.
 */
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        super();
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        enable(SerializationFeature.INDENT_OUTPUT);
    }
}
