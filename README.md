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

### Get Started With the API

Once the app is up and running, you can create a user for yourself 
using the `players` endpoint.
> POST http://localhost:9000/players 
> {
"name": "myusername",
"password": "mypassword"
}

Now you can use the `login` endpoint that you use to authenticate.
> POST http://localhost:9000/login 
> {
"name": "myusername",
"password": "mypassword"
}

This will return an authentication token for your new user that you can use to
utilize the rest of the API's. To use this token, us the `X-AUTH-TOKEN`
request header. Example:
> X-AUTH-TOKEN: 231801e9-5ec2-4f15-a3b2-18c8c6150d3b

Next you can create a new game using the `games` endpoint. Post to this endpoint
with the desired shoe size. This will return the game ID you can use to access 
the game going forward.
> POST http://localhost:9000/games
> {
"shoeSize" : 6
}

Now you can the games endpoint to deal off the top of the shoe:
> GET http://localhost:9000/games/<game_id>/next

