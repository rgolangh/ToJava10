# ToJava10

Translate java source code to use jdk's 10 `var` keyword.

Uses the venerable [ANTLR](www.antlr.org) for generating the parser and the listeners.

Build the jar or download from the releases(when available):
```
mvn package

ls -la target/
...
ToJava10-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Point it at some java class and see the magic:
```
java -jar ToJava10-1.0-SNAPSHOT-jar-with-dependencies.jar src/main/resources/Simple.java

/**
 * A simple java class with 2 local variables declaration in the constructor.
 * A test should use those for conversion to 'var' and  analyze the result.
 */
public class Simple {

    private int x = 42;

    public Simple() {
        // foo comment
        var age = 1;
        var instance = new Simple();
    }
}
```
