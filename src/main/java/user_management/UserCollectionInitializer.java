package user_management;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

public class UserCollectionInitializer {

    public static UserCollection generate() throws IOException {
        final Type REVIEW_TYPE = new TypeToken<UserCollection>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("/Users/vincentgasbarro/Dev/test3/JavaAssessment3/src/main/resources/users.json"));
        return gson.fromJson(reader, REVIEW_TYPE);
    }
}
