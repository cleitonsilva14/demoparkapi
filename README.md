# demoparkapi


### Ajustes intelij:
``` markdown
SDK:
    -> File > Project Structure (CTRL+ALT+SHIFT+S) > Project Settings: 
    SDK: 21
    Language level: 21

Auto reload:
    Settings > Preferences 

```

### java -version:
``` bash
 java -version
# 21.0.2
```


### Configs pom.xml _modelmapper_:

``` xml
<dependency>
  <groupId>org.modelmapper</groupId>
  <artifactId>modelmapper</artifactId>
  <version>3.0.0</version>
</dependency>
```

### Configs pom.xml _springdoc_ swagger:
```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.8.4</version>
</dependency>
```
```text
springdoc: 2.7.x (2.8.x) is only compatible with SpringBoot 3.4.x

If you are using SpringBoot 3.3.x you need to stick with 2.6.x

For more details, check the compatibility matrix of springdoc-openapi with spring-boot
https://springdoc.org/#what-is-the-compatibility-matrix-of-springdoc-openapi-with-spring-boot
```
