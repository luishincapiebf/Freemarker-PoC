# FreemarkerPoC

Apache FreeMarker™ is a template engine: a Java library to generate text output (HTML web pages, e-mails, configuration files, source code, etc.) based on templates and changing data. Templates are written in the FreeMarker Template Language (FTL)


A few highlights of FreeMarker:

- Powerful template language: Conditional blocks, iterations, assignments, string and arithmetic operations and formatting, macros and functions, including other templates, escaping by default (optional), and many more
- Multipurpose and lightweight: Zero dependencies, any output format, can load templates from any place (pluggable), many configuration options
- Internationalization/localization-aware: Locale sensitive number and date/time formatting, localized template variations.
- XML processing capabilities: Drop XML DOM-s into the data-model and traverse them, or even process them declaratively
- Versatile data-model: Java objects are exposed to the template as a tree of variables through pluggable adapters, which decides how the template sees them.


Apache FreeMarker is Free software, licensed under the Apache License, Version 2.0.

Usefully links

https://try.freemarker.apache.org/

https://freemarker.apache.org/index.html


In a Spring Boot application, we can simplify the needed configuration by using the spring-boot-starter-freemarker dependency:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
    <version>2.5.6</version>
</dependency>
```


This starter adds the necessary auto-configuration. All we need to do is start placing our template files in the resources/templates folder.



## Development

When starting the application `docker compose up` is called and the app will connect to the contained services.
[Docker](https://www.docker.com/get-started/) must be available on the current system.

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be
added in the VM options of the Run Configuration after enabling this property in "Modify options". Create your own
`application-local.yml` file to override settings for development.

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
mvnw clean package
```

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/Freemarker-PoC-0.0.1-SNAPSHOT.jar
```

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as
environment variable when running the container.

```
mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=dev.lucho/freemarker-po-c
```