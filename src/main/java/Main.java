import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            usage();
            System.exit(1);
        }
        String name = "";
        boolean writeInplace = false;
        if (args.length == 1) {
            name = args[0];
        }
        if (args.length == 2) {
            if (args[0].equals("-i")) {
                writeInplace = true;
            } else {
                usage();
                System.exit(1);
            }
            name = args[1];
        }

        Path path = Paths.get(name);
        if (!path.toFile().exists()) {
            System.err.printf("file %s doesn't exists\n", path.getFileName());
            System.exit(1);
        }
        try {
            String translatedText = translate(path);
            if (writeInplace) {
                Files.write(path, translatedText.getBytes());
            } else {
                System.out.print(translatedText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void usage() {
        System.err.printf("usage: [-i] FILENAME\n");
    }

    public static String translate(Path path) throws IOException {
        CharStream f = CharStreams.fromPath(path);
        CommonTokenStream tokenStream = new CommonTokenStream(new JavaLexer(f));

        JavaParser javaParser = new JavaParser(tokenStream);
        JavaParser.CompilationUnitContext tree = javaParser.compilationUnit();

        MyListener listener = new MyListener(tokenStream);
        ParseTreeWalker.DEFAULT.walk(listener, tree);

        return listener.rewriter.getText();
    }
}
