
# Spring Boot Recruitment Sandbox

A simple repository for recruitment tasks (Java 21 + Spring Boot 3.x + MapStruct).

## How to Run

Requirements: Java 21, Maven 3.9+.


```bash
mvn spring-boot:run
```

Application: `http://localhost:8080`  
H2 console: `http://localhost:8080/h2` (JDBC URL: `jdbc:h2:mem:recruit`, user: `sa`, pass: `sa`)

### Quick Smoke Test
```bash
curl 'http://localhost:8080/api/books'
```

## DTO Mapping (MapStruct)

The project is configured with **MapStruct**. Mapper: `com.company.recruitment.mapper.BookMapper` (Spring bean).
In the tasks, please **use MapStruct for mapping** (instead of manual `from(...)` methods).

