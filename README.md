# Example Test Code using QuickTheories

Read API documentation
[here](https://themarlogroup.gitlab.io/examples/quickcheck/).

## Build Package

Build with:

```bash
export MAVEN_CLI_OPTS="-s .m2/settings.xml"
mvn $MAVEN_CLI_OPTS clean validate fmt:format sortpom:sort package
```

## Generate JavaDoc

Generate
[JavaDoc](https://www.oracle.com/technetwork/java/javase/documentation/index-137868.html)
with:

```bash
export MAVEN_CLI_OPTS="-s .m2/settings.xml"
mvn $MAVEN_CLI_OPTS javadoc:javadoc site
```

## Run Example

Run using Maven with:

```bash
cat README.md | mvn exec:java -Dexec.mainClass=com.marlo.quickcheck.ExampleApp
```

Alternatively, run JAR using:

```bash
cat README.md | java -cp target/com.marlo.example-quickcheck.jar com.marlo.quickcheck.ExampleApp
```

## References

* [Code Point](https://en.wikipedia.org/wiki/Code_point)
* [JUnit QuickCheck (GitHub)](https://github.com/pholser/junit-quickcheck)
* [Unicode lookup](http://unicode.scarfboy.com/)
* [Property Based Testing in Java](https://www.veracode.com/blog/managing-appsec/property-based-testing-java)
* [Java 8 Characters](https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html)

