# Example Test Code using junit-quickcheck

Read API documentation
[here](https://themarlogroup.gitlab.io/examples/quickcheck/).

## Sort POM and Format Code

Sort POM and format code using Google style with:

```bash
mvn sortpom:sort fmt:format
```

## Build Package

Build package and site documentation with:

```bash
mvn clean install
```

## Run Tests

Run tests with:

```bash
mvn test
```

## Build JavaDoc

Generate
[JavaDoc](https://www.oracle.com/technetwork/java/javase/documentation/index-137868.html)
with:

```bash
mvn javadoc:javadoc site
```

## Run Example Application

Run using Maven with:

```bash
cat README.md | mvn exec:java -Dexec.mainClass=com.marlo.quickcheck.ExampleApp
```

Alternatively, run using JAR with:

```bash
cat README.md | java -cp target/com.marlo.example-quickcheck.jar com.marlo.quickcheck.ExampleApp
```

## References

* [Code Point](https://en.wikipedia.org/wiki/Code_point)
* [Java 8 Characters](https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html)
* [junit-quickcheck (GitHub)](https://github.com/pholser/junit-quickcheck)
* [Property Based Testing in Java](https://www.veracode.com/blog/managing-appsec/property-based-testing-java)
* [Unicode lookup](http://unicode.scarfboy.com/)