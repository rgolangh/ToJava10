import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class LocaVarTranslationTest {

    @Test
    public void testLocalVarTranslation() throws IOException {
        Path path = Paths.get("src/main/resources/Simple.java");
        String translatedText = Main.translate(path);

        System.out.println(translatedText);

        Assert.assertTrue(translatedText.contains("var age = 1"));
        Assert.assertTrue(translatedText.contains("var map = new ConcurrentHashMap<>()"));
    }
}
