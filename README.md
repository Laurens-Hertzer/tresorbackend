# Backend for the _secret tresor application_

This is the backend is a Java Springboot application.

It can be used by API-calls.

The data's are stored in a database.

### information about database

prepare a database server and the database used by the application<br>
see [tresordb-example.sql](tresordb-example.sql) for an example schema<br>
see [application-example.properties](src/main/resources/application-example.properties) for the application configuration template

## Requests examples

see [UserRequests.http](httprequest/UserRequests.http)<br>
see [SecretRequests.http](httprequest/SecretRequests.http)

## Environment variables

Copy `src/main/resources/application-example.properties` to `application.properties` and set `DB_PASSWORD`, `PEPPER`, and `RECAPTCHA_SECRET` as environment variables. Keep the local `application.properties` out of version control.

## Build image

see Dockerfile

```Bash
docker build -t tresorbackendimg .
```



## Start container local

```Bash
docker run -p 8080:8080 --name tresorbackend tresorbackendimg
```

(c) P.Rutschmann
