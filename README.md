# Quarkus project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running [Jaeger](www.jaegertracing.io)

```
docker run -d --net=host --name=jaeger jaegertracing/all-in-one
```

UI Dashboard

```
http://localhost:16686
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application is packageable using `./mvnw package`.
It produces the executable `quarkus-1.0-SNAPSHOT-runner.jar` file in `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/quarkus-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or you can use Docker to build the native executable using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your binary: `./target/quarkus-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide .

# Endpoints

## Health checks

Accumulating all health check procedures in the application

`http://localhost:8080/health`

The application is up and running

`http://localhost:8080/health/live`

The application is ready to serve requests

`http://localhost:8080/health/ready`

## Metrics

`http://localhost:8080/metrics`

## Acquirers

Create

```
POST http://localhost:8080/api/v1/acquirers

{
"card" : "1234567890",
"value" : 55.44
}
```

Get All

`GET http://localhost:8080/api/v1/acquirers`

# References

[Quarkus Opentracing](https://quarkus.io/guides/opentracing#quarkus-jaeger_quarkus.jaeger.propagation)

[Quarkus Health Checks](https://quarkus.io/guides/microprofile-health)

[Quarkus Metrics](https://quarkus.io/guides/microprofile-metrics)