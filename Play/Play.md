## Play

Play's actions are asynchronous action by default. `Promise[Result]` enables 
generating the result without blocking. A `Promise[Result]` will eventually be 
redeemed with a value of type `Result`. Then, Play will serve this result as 
soon as the promise is redeemed.

### Explicit asynchronous action on server side

```scala
    //  Explicit asynchronous action
    def action = Action.async {
      Future.successful(Ok)
    }
```

### Asynchronous and nonblocking

```scala
    //  Async and nonblocking action
    def action = Action.async {
      Promise.timeout(Ok("Asynchronous and non blocking"), 5.seconds)
    }
```

### Web services

```scala
    def webservicerequest = Action.async {
      val googleFuture: Future[WSResponse] = WS.url("https://www.google.com/play").get()
      googleFuture.map(response => Ok(response.body))
    }
```

### Logger

To use Logger API, you need to `import play.api.Logger`. `info` for logging 
runtime events, `debug` is for logging detailed information on the flow through
the system, etc.

```scala
    ...
    Logger.info(...)
    ...
```
