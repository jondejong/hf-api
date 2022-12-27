# hf-api
This is the start of a custom version of the card game Hand And Foot that I
play with friends and family. This is a side project for the purposes of learning
and for having some fun. As such, it's an early Work In Progress, as may very 
well always be.

### Database Set Up
The API depends on a Postgres database instance, both for the application and for
the tests. Currently, the credentials for this are hardcoded for a local instance. 
This is instance can be launched via Docker compose:
>docker compose up

As such, the database can be brought down via Docker as well.
> docker compose down

### To run
The app has only been run via Gradle.
> ./gradlew run

At some point, I'll get need to add a shadow jar task. Lots to do before that's
relevant to me.

### To test
> ./gradlew test


