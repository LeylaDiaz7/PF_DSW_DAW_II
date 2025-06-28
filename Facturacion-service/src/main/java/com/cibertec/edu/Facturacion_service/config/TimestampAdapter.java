package com.cibertec.edu.Facturacion_service.config;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampAdapter extends XmlAdapter<String, Timestamp> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Override
    public Timestamp unmarshal(String v) throws Exception {
        return Timestamp.from(OffsetDateTime.parse(v, FORMATTER).toInstant());
    }

    @Override
    public String marshal(Timestamp v) throws Exception {
        return FORMATTER.format(v.toInstant().atOffset(OffsetDateTime.now().getOffset()));
    }
}

