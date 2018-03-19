package parsing_json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;

public class ElementCollectionInitializer {


    public static ElementCollection generate() throws IOException {
        final Type REVIEW_TYPE = new TypeToken<ElementCollection>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("/Users/vincentgasbarro/Dev/test3/JavaAssessment3/src/main/resources/periodic_table.json"));
        return gson.fromJson(reader, REVIEW_TYPE);
    }
}
