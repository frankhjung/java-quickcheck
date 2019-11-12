# Example Test Code using QuickTheories

Read API documentation
[here](https://themarlogroup.gitlab.io/examples/quickcheck/).

## Build Example

Build with:

```bash
export MAVEN_CLI_OPTS="-s .m2/settings.xml"
mvn $MAVEN_CLI_OPTS clean validate fmt:format sortpom:sort package
```

Build documentation with:

```bash
mvn $MAVEN_CLI_OPTS javadoc:javadoc site
```

## Run Example

Run with:

```bash
cat README.md | mvn exec:java -Dexec.mainClass=com.marlo.quickcheck.ExampleApp
```

Or using:

```bash
cat README.md | java -cp target/com.marlo.example-quickcheck.jar com.marlo.quickcheck.ExampleApp
```

## Generate JavaDoc

Generate
[JavaDoc](https://www.oracle.com/technetwork/java/javase/documentation/index-137868.html)
with:

```bash
export MAVEN_CLI_OPTS="-s .m2/settings.xml"
mvn $MAVEN_CLI_OPTS javadoc:javadoc site
```

## References

* [Code Point](https://en.wikipedia.org/wiki/Code_point)
* [JUnit QuickCheck (GitHub)](https://github.com/pholser/junit-quickcheck)
* [Unicode lookup](http://unicode.scarfboy.com/)

