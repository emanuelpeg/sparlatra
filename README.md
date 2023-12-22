# Sparlatra #

## Build & Run ##

```sh
$ sbt ~Jetty/start
```

Open [http://localhost:8080/](http://localhost:8080/) in your browser.


## Slick
In order to use the slick db callings, we must create the schema en the h2 DB.
The order to use these test is providing in the next way: 

#### Creates the schema
>http://localhost:8080/db/create-tables
#### Load data in the table
>http://localhost:8080/db/load-data
#### List the data that it is stored
>http://localhost:8080/db/profiles