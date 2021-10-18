## Referenciák

* Craig Walls: Spring in Action, Fifth Edition (Manning)
* Kevin Hoffman: Beyond the Twelve-Factor App (O'Reilly)
* [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

---

## Spring Boot - 1.

* Autoconfiguration: classpath-on lévő osztályok, 3rd party library-k, környezeti változók és
  egyéb körülmények alapján komponensek automatikus <br /> létrehozása és konfigurálása
* Intelligens alapértékek, convention over configuration, <br /> konfiguráció nélkül is működjön
    * Saját konfiguráció írása csak akkor, ha eltérnénk az alapértelmezettől
    * Automatically, automagically
* Self-contained: az alkalmazás tartalmazza <br /> a web konténert is (pl. Tomcat)

---

## Spring Boot - 2.

* Nagyvállalati üzemeltethetőség: Actuatorok
    * Pl. monitorozás, konfiguráció, beavatkozás, <br /> naplózás állítása, stb.
* Gyors kezdés: Spring Initializr [https://start.spring.io/](https://start.spring.io/)
* Starter projektek: függőségek, <br /> előre beállított verziószámokkal (tesztelt)
* Ezért különösen alkalmas microservice-ek fejlesztésére

---
---

## Twelve-factor app

* [Twelve-factor app](https://12factor.net/) egy manifesztó, metodológia felhőbe <br /> telepíthető alkalmazások fejlesztésére
* Heroku platform fejlesztőinek ajánlása
* Előtérben a cloud, PaaS, continuous deployment
* PaaS: elrejti az infrastruktúra részleteit
    * Pl. Google App Engine, Redhat Open Shift, Pivotal Cloud Foundry, <br /> Heroku, AppHarbor, Amazon AWS, stb.

---

## Cloud native

* Jelző olyan szervezetekre, melyek képesek az automatizálás előnyeit kihasználva <br /> gyorsabban megbízható és skálázható alkalmazásokat szállítani
* Pivotal, többek között a Spring mögött álló cég
* Előtérben a continuous delivery, DevOps, microservices
* Alkalmazás jellemzői
    * PaaS-on fut (cloud)
    * Elastic: automatikus horizontális skálázódás

---

## Twelve-Factor app ajánlások - 1.

* Verziókezelés: "One codebase tracked in revision control, many deploys"
* Függőségek: "Explicitly declare and isolate dependencies"
* Konfiguráció: "Store config in the environment"
* Háttérszolgáltatások: "Treat backing services as attached resources"
* Build, release, futtatás: "Strictly separate build and run stages"
* Folyamatok: "Execute the app as one or more stateless processes"

---

## Twelve-Factor app ajánlások - 2.

* Port hozzárendelés: "Export services via port binding"
* Párhuzamosság: "Scale out via the process model"
* Disposability: "Maximize robustness with fast startup and graceful shutdown"
* Éles és fejlesztői környezet hasonlósága: <br /> "Keep development, staging, and production as similar as possible"
* Naplózás: "Treat logs as event streams"
* Felügyeleti folyamatok: <br /> "Run admin/management tasks as one-off processes"

---

## Docker

* Operációs rendszer szintű virtualizáció
* Jól elkülönített környezetek, saját fájlrendszerrel és telepített szoftverekkel
* Jól meghatározott módon kommunikálhatnak egymással
* Kevésbé erőforrásigényes, mint a virtualizáció

---

## Megvalósítása

* Kliens - szerver architektúra, REST API
* Kernelt nem tartalmaz, hanem a host Linux kernel izoláltan futtatja
 * namespaces: operációs rendszer szintű elemek izolálására: folyamatok, InterProcess Communication (IPC), 
     fájlrendszer, hálózat, UTS (UNIX Timesharing System - host- és domainnév), felhasználók
 * cGroups (Control Groups): erőforrás limitáció
* Union FS (írásvédett, vagy írható/olvasható rétegek)

---

## Docker

<img src="images/container-what-is-container.png" alt="Docker" width="500" />

---

## Docker Windowson

* Docker Toolbox: VirtualBoxon futó Linuxon
* Docker Desktop
  * Hyper-V megoldás: LinuxKit, Linux Containers for Windows (LCOW), MobyVM
  * WSL2 - Windows Subsystem for Linux - 2-es verziótól Microsoft által Windowson fordított és futtatott Linux kernel

---

## Docker felhasználási módja

* Saját fejlesztői környezetben reprodukálható erőforrások
  * Adatbázis (relációs/NoSQL), cache, kapcsolódó rendszerek <br /> (kifejezetten microservice környezetben)
* Saját fejlesztői környezettől való izoláció
* Docker image tartalmazza a teljes környezetet, függőségeket is
* Portabilitás (különböző környezeten futattható, pl. saját gép, <br /> privát vagy publikus felhő)

---

## További Docker komponensek

* Docker Hub - publikus szolgáltatás image-ek megosztására
* Docker Compose - több konténer egyidejű kezelése
* Docker Swarm - natív cluster támogatás
* Docker Machine - távoli Docker környezetek üzemeltetéséhez

---

## Docker fogalmak

<img src="images/docker-containers.jpg" alt="Image és container" width="500" />

---

## Docker folyamat

* Alkalmazás
* Dockerfile
* Image
* Konténer

---

## Docker konténerek

```shell
docker version
docker run hello-world
docker run -p 8080:80 nginx
docker run -d -p 8080:80 nginx
docker ps
docker stop 517e15770697
docker run -d -p 8080:80 --name nginx nginx
docker stop nginx
docker ps -a
docker start nginx
docker logs -f nginx
docker stop nginx
docker rm nginx
```

Használható az azonosító első n karaktere is, amely egyedivé teszi

---

## Műveletek image-ekkel

```shell
docker images
docker rmi nginx
```

---

## Linux elindítása, bejelentkezés

```shell
docker run  --name myubuntu -d ubuntu tail -f /dev/null
docker exec -it myubuntu bash
```

---

## Saját image összeállítása

`Dockerfile` fájl tartalma:

```dockerfile
FROM adoptopenjdk:14-jre-hotspot
WORKDIR /opt/app
COPY target/*.jar employees.jar
CMD ["java", "-jar", "employees.jar"]
```

Parancsok:

```shell
docker build -t employees .
docker run -d -p 8080:8080 employees
```

---

## Spring támogatás

* Spring 2.3.0.M2-től
    * [Bejelentés](https://spring.io/blog/2020/01/27/creating-docker-images-with-spring-boot-2-3-0-m1)
* Layered JAR-s
* Buildpacks

---

## Layered JAR-s

* A JAR felépítése legyen layered
* Ki kell csomagolni
* Létrehozni a Docker image-t

---

## Layered JAR

```xml
<plugin>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-maven-plugin</artifactId>
  <configuration>
    <layers>
      <enabled>true</enabled>
    </layers>
  </configuration>
</plugin>
```

---

## Kicsomagolás

```shell
java -Djarmode=layertools -jar target/employees-0.0.1-SNAPSHOT.jar list

java -Djarmode=layertools -jar target/employees-0.0.1-SNAPSHOT.jar extract
```

---

## Dockerfile

```dockerfile
FROM adoptopenjdk:14-jre-hotspot as builder
WORKDIR application
COPY target/employees-0.0.1-SNAPSHOT.jar employees.jar
RUN java -Djarmode=layertools -jar employees.jar extract

FROM adoptopenjdk:14-jre-hotspot
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]
```

---

## Spring Data JPA

* Egyszerűbbé teszi a perzisztens réteg implementálását
* Tipikusan CRUD műveletek támogatására, olyan gyakori igények <br />megvalósításával, mint a rendezés és a lapozás
* Interfész alapján repository implementáció generálás
* Query by example
* Ismétlődő fejlesztési feladatok redukálása, *boilerplate* kódok csökkentése

---

## Spring Data JPA használatba <br /> vétele

* `org.springframework.boot:spring-boot-starter-data-jpa` függőség
* Entitás létrehozása
* `JpaRepository` kiterjesztése
* `@Transactional` alkalmazása a service rétegben
* `application.properties`

```properties
spring.jpa.show-sql=true
```

---

## JpaRepository

* `save(S)`, `saveAll(Iterable<S>)`, `saveAndFlush(S)`
* `findById(Long)`, `findOne(Example<S>)`, <br />`findAll()` különböző paraméterezésű metódusai (lapozás, `Example`), <br />`findAllById(Iterable<ID>)`
* `getOne(ID)` (nem `Optional` példánnyal tér vissza)
* `exists(Example<S>)`, `existsById(ID)`
* `count()`, `count(Example<S>)`
* `deleteById(ID)`, `delete(S)`, <br /> `deleteAll()` üres és `Iterable` paraméterezéssel,
  <br />`deleteAllInBatch()`, <br />`deleteInBatch(Iterable<S>)`
* `flush()`

---

## Entitás

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "emp_name")
    private String name;

    public Employee(String name) {
        this.name = name;
    }
}
```

---

## Repository

```java
public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where upper(e.name) like upper(:name)")
    List<Employee> findAllByPrefix(String name);

}
```
---

## PostgreSQL indítása Dockerrel

```shell
docker run
  -d
  -e POSTGRES_PASSWORD=password 
  -p 5432:5432 
  --name employees-postgres 
  postgres
```

---

## Driver

`pom.xml`-be:

```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```

---

## Inicializálás

* `application.properties` konfiguráció

```properties
spring.datasource.url=jdbc:postgresql:postgres
spring.datasource.username=postgres
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=create-drop
```

---

## Liquibase

`pom.xml`-ben

```xml
<dependency>
  <groupId>org.liquibase</groupId>
  <artifactId>liquibase-core</artifactId>
</dependency>
```

`application.properties` állományban

```properties
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml
```

---

## Change log

A `db.changelog-master.xml` fájl:

```xml
<databaseChangeLog
    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ext="http://www.liquibase.org/xml/ns/dbchangelog-ext"
    xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog 
      http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd
      http://www.liquibase.org/xml/ns/dbchangelog-ext 
      http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd">

  <changeSet id="create-employee-table" author="vicziani">
    <sqlFile path="create-employee-table.sql"
      relativeToChangelogFile="true" />
  </changeSet>
</databaseChangeLog>
```

---

## SQL migráció

`create-employee-table.sql` fájl MariaDB esetén:

```sql
create table employees (id bigint not null auto_increment, emp_name varchar(255), primary key (id));
```

`create-employee-table.sql` fájl PostgreSQL esetén:

```sql
create table employees (id int8 generated by default as identity, emp_name varchar(255), 
  primary key (id));
```

## Swagger UI

* API dokumentáció generálására
* Az API ki is próbálható
* OpenAPI Specification (eredetileg Swagger Specification)
  * RESTful webszolgáltatások leírására
  * Kód és dokumentáció generálás
  * Programozási nyelv független
  * JSON/YAML formátumú
  * JSON Scheman alapul
* Keretrendszer független
* Annotációkkal személyre szabható

---

## springdoc-openapi projekt

* Swagger UI automatikus elindítása a `/swagger-ui.html` címen
* OpenAPI elérhetőség a `/v3/api-docs` címen (vagy `/v3/api-docs.yaml`)

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-ui</artifactId>
  <version>${springdoc-openapi-ui.version}</version>
</dependency>
```

---

## Globális testreszabás

```java
@Bean
public OpenAPI customOpenAPI() {
  return new OpenAPI()
  .info(new Info()
  .title("Employees API")
  .version("1.0.0")
  .description("Operations with employees"));
}
```

---

## Séma testreszabás

* Figyelembe veszi a Bean Validation annotációkat

```java
public class CreateEmployeeCommand {

    @NotNull
    @Schema(description="name of the employee", example = "John Doe")
    private String name;
}
```

---

## Osztály és metódus szint

* Figyelembe veszi a Spring MVC annotációkat

```java
@RestController
@RequestMapping("/api/employees")
@Tag( name = "Operations on employees")
public class EmployeesController {

  @GetMapping("/{id}")
  @Operation(summary = "Find employee by id",
    description = "Find employee by id.")
  @ApiResponse(responseCode = "404",
    description = "Employee not found")
  public EmployeeDto findEmployeeById(
      @Parameter(description = "Id of the employee",
        example = "12")
      @PathVariable("id") long id) {
    // ...
  }

}
```
