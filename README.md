# DEMO PROJECT FOR CURRENDA INTERVIEW
Basic functionalities have been completed. Cases can be grouped by case states, and it is
possible to find addresses for active parties for proper cases.
## Technologies
* JAVA 17
* Spring
* Hibernate
* PostgreSQL database
* Docker
* JUnit

## Setup
Run database
```
$ docker pull postgres
$ docker run --name my-postgres -e POSTGRES_DB=database123 -e POSTGRES_USER=username123 -e POSTGRES_PASSWORD=password123 -d -p 5432:5432 postgres
```

Run backend
```
$ mvn clean package
$ java -jar filename.jar
```


##What can be added?
Due to the lack of time, the project has clearly a bit of space to improve.
* Firsly, application can work fully on Docker containers
* I strongly believe that data structure can be improved which may optimize the code
* Security for endpoints might be added
* Simple GUI could be done
