# Lunch Microservice

The service provides an endpoint that will determine, from a set of recipes, what I can have for lunch at a given date, based on my fridge ingredient's expiry date, so that I can quickly decide what I’ll be having to eat, and the ingredients required to prepare the meal.

## Prerequisites

* [Java 11 Runtime](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Docker](https://docs.docker.com/get-docker/) & [Docker-Compose](https://docs.docker.com/compose/install/)

*Note: Docker is used for the local MySQL database instance, feel free to use your own instance or any other SQL database and insert data from lunch-data.sql script* 

## Assumptions

* For GET /lunch/recipes/nonExpired, if there is no use by date provided, then use current date as filtering criteria.
* For GET /lunch/recipes/withTitle, use fuzzy match for the recipe title even it is used as primary key; if there is no recipe title provided, then return all recipes. This is for the real-life scenario considerations, since people usually lookup information based on certain keywords instead of the exact matching words.
* For GET /lunch/recipes/withoutIngredients, pass multiple parameters in request for collecting the ingredients instead of passing one string with delimiters; if there is no recipe title provided, then return all recipes.

## TODOs

Due to the project size and the time limitations, some features are not implemented. But for a real project in Production, the following TODOs may need to be considered.

* Authentication
* Paging
* API documentation

### Run

1. Start database:

    ```
    docker-compose up -d
    ```
   
2. Add test data from  `sql/lunch-data.sql` to the database. Here's a helper script if you prefer:


    ```
    CONTAINER_ID=$(docker inspect --format="{{.Id}}" lunch-db)
    ```
    
    ```
    docker cp sql/lunch-data.sql $CONTAINER_ID:/lunch-data.sql
    ```
    
    ```
    docker exec $CONTAINER_ID /bin/sh -c 'mysql -u root -prezdytechtask lunch </lunch-data.sql'
    ```
    
3. Run Springboot LunchApplication
