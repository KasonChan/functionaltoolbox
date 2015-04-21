package controllers

import play.api.Logger
import play.api.Play.current
import play.api.libs.concurrent.Promise
import play.api.libs.ws.{WS, WSResponse}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

object Application extends Controller {

  def index = Action {
    Logger.info("You new application is ready.")
    Ok(views.html.index("Your new application is ready."))
  }

  //  Explicit asynchronous action and blocking
  def async = Action.async {
    Logger.info("Ok")
    Future.successful(Ok)
  }

  //  Async and nonblocking action
  def asyncnonblocking = Action.async {
    Logger.info("Asynchronous and non blocking")
    Promise.timeout(Ok("Asynchronous and non blocking"), 5.seconds)
  }

  //  Async and nonblocking web service request
  //  Reactive composition
  def webservicerequest = Action.async {
    val googleFuture: Future[WSResponse] = WS.url("https://www.google.com/play").get()
    val twitterFuture: Future[WSResponse] = WS.url("https://www.twitter.com").get()

    for {
      g <- googleFuture
      t <- twitterFuture
    } yield {
      Logger.info(t.body + g.body)
      Ok(t.body + g.body)
    }
  }

  def bluepromo = Action.async {
    val bluePromoFuture: Future[WSResponse] = WS.url("http://api.bluepromocode.com/v2/promotions").get()
    bluePromoFuture.map(
      response => {
        Logger.debug(response.body)
        Ok(response.body)
      }
    )
  }
}