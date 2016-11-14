package com.jamp.app.config;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Component
public class JsonDateDeSerializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctx) throws IOException, JsonProcessingException {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy'T'hh:mm:ss");
        String dt = jp.getText();
        if (dt == null || dt.trim().length() == 0) {
            return null;
        }
        Date date = null;
        try {
            date = fmt.parse(dt);
        } catch (ParseException e) {
            throw new IOException(e);
        }

        return date;
    }
}
