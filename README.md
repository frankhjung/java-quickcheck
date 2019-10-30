# Example Test Code using QuickTheories

## Build Example

Build with:

```bash
export MAVEN_CLI_OPTS="-s .m2/settings.xml"
mvn $MAVEN_CLI_OPTS clean validate fmt:format sortpom:sort package
```

## Run Example

Run with:

```bash
cat README.md | mvn exec:java -Dexec.mainClass=com.marlo.quickcheck.ExampleApp
```

## References

* [QuickTheories (GitHub)](https://github.com/quicktheories/QuickTheories)
* [Unicode lookup](http://unicode.scarfboy.com/)

