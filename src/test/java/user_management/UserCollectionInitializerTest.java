package user_management;

import org.junit.Test;
import parsing_json.ElementCollectionInitializer;

import java.io.IOException;

import static org.junit.Assert.*;

public class UserCollectionInitializerTest {

    @Test
    public void generate() throws IOException {
        assertEquals(1000, UserCollectionInitializer.generate().size());
    }
}