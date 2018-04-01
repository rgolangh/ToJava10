# ToJava10

## Translate java source code to use jdk's 10 _`var`_ keyword.

Uses the venerable [ANTLR](www.antlr.org) for generating the parser and the listeners.

## Build
Build the jar or download from the releases(when available):
```
mvn package

ls -la target/
...
toJava10.jar
```

## Usage
#### Point it at [some java class](src/main/resources/Simple.java), by default the change goes to stdout:

```java
java -jar target/toJava10.jar src/main/resources/Simple.java

/**
 * A simple java class with 2 local variables declaration in the constructor.
 * A test should use those for conversion to 'var' and  analyze the result.
 */
public class Simple {

    private int x = 42;

    public Simple() {
        // foo comment
        var age = 1;
        var map = new ConcurrentHashMap<>();
    }
}
```

#### Add `-i` to write the changes to the file _*in-place*_:

```bash
java -jar target/toJava10.jar -i src/main/resources/Simple.java
```

#### Convert your whole git repo:

```bash
git ls-files '*.java' | xargs -I '{}' java -jar target/toJava10.jar -i '{}'
```
