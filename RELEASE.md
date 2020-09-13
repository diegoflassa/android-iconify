# Publish to Play Store

Setup `iconivoid_SAMPLE_KEYSTORE_PASSWORD` in your `~/.gradle/gradle.properties` then

```
gradle assembleRelease
```

# Publish to Maven central

```
gradle uploadArchives
```

Promote release in [central](https://oss.sonatype.org/)

```
Staging Repositories > Choose the release > Close > Release
```