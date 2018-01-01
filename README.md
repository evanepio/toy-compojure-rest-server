# compojure-rest-test-project

Simple REST web service implementation for me to play around with.

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

The server will be running on http://localhost:3000/

## Docker

To run with Docker, use the following command:

    docker-compose up -d
    
You'll then have the server running on http://localhost:3000/ (on Docker for Mac for sure), along with a postgresql database. To see the logs, run:

    docker-compose logs
    
And of course to shut down, use:

    docker-compose down

## License

Copyright © 2013  Evan Porter
