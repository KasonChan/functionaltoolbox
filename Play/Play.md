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

### LESS CSS

LESS CSS is a dynamic stylesheet language. It provides greater flexibility in
writing CSS files. The LESS source file at `app/assets/stylesheets/main.less`
will become a standard resource. 

Include `addSbtPlugin("com.typesafe.sbt" % sbt-less" % "1.0.6")` in 
`project/plugins.sbt`. LESS sources are compiled automatically during `compile`,
or when refresh any page in browser in development mode.

Partial LESS sources files can be defined with underscore `_` character to 
prevent library files from being compiled individually (or imported).

```
    app
     |-assets
        |-stylesheets
           |-main.less
           |-utils
              |-_less.less
```

```less
    // main.less
    @import "utils/_colors.less";
    
    body {
      background-color: @red'
    }
```

```less
    // _color.less
    @red: #F44336;
```

The resulting CSS file will be compiled as `public/stylesheets/main.css`, and
it can be use in template as any regular public asset 
`<link rel="stylesheet href="@routes.Assets.at("stylesheets/main.css")">`. 
A minified version too
`<link rel="stylesheet" href="@routes.Assets.at("stylesheets/main.min.css")">`

### SASS/SCSS CSS

SASS/SCSS CSS is another dynamic stylesheet language. The source file at 
`app/assets/stylesheets/main.sass` or `app/assets/stylesheets/main.scss`
will become a standard resource. 

Include 
`resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"`
and `addSbtPlugin("net.litola" % "play-sass" % "0.4.0")` in 
`project/plugins.sbt`. 
SASS/SCSS sources are compiled automatically during `compile`,
or when refresh any page in browser in development mode. Include the following
in the `build.sbt` file:
```
    lazy val root = (project in file("."))
      .enablePlugins(PlayScala, net.litola.SassPlugin)
      .settings(
        sassOptions := Seq("--compass")
      )
```
