package br.com.contato.serializer;

import br.com.contato.config.serializer.CustomDateSerializer;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomDateSerializerTest {

    @Test
    public void testSerialize() throws IOException {
        CustomDateSerializer serializer = new CustomDateSerializer();
        SerializerProvider serializerProvider = null;

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String expectedDate = dateFormat.format(date);

        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);

        serializer.serialize(date, jsonGenerator, serializerProvider);
        jsonGenerator.flush();

        assertEquals("\"" + expectedDate + "\"", jsonWriter.toString());
    }
}