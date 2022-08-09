package com.gmail.juliarusakevich.classifier.controller.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        var fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"); // "yyyy-MM-dd'T'HH:mm:ssxxx"
        var date =
                localDateTime.atZone(ZoneId.systemDefault()).format(fmt);
        jsonGenerator.writeString(date);
    }
}

/*
если надо в как число представить
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            gen.writeNumber(value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        else {
            gen.writeNull();
        }
    }
 */