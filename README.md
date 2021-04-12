# BONITA REST API

![logo](https://github.com/Falciighol/bonita-test-api/blob/main/doc/images/yourlogo.png)

**Prerequisites:**

- A git client
- Java (jdk8 or higher)
- Maven
- MySQL

You can import the project on your favorite IDE make sure you have the correct configuration to run Java.

------

## Database setup

Execute the code on the `demoDS.sql` file to create the database.

> Note: if you change the database name, make sure to change it in the Data Source definition too.
>

## Data source configuration

Edit the file `bonita.xml` and add the Data Source:

`%BONITA_HOME%\workspace\tomcat\server\conf\Catalina\localhost\bonita.xml`


```xml
<Resource name="demoDS"

  auth="Container"

  type="javax.sql.DataSource"

  maxActive="10"

  minIdle="1"

  maxWait="10000"

  initialSize="3"

  removeAbandoned="true"

  logAbandoned="true"

  username="root"

  password="admin"

  driverClassName="com.mysql.cj.jdbc.Driver"

  url="jdbc:mysql://localhost:3306/demods"/>
```
## Deploying

+ Run `mvnw` (on windows).
+ Take the built artifact `test-api-1.0.zip` and upload it to your Bonita platform (see https://documentation.bonitasoft.com/bonita/7.12/rest-api-extensions#toc7)

## Testing

+ Import the **Postman** collection & change the host (if not running at `localhost:8080`).