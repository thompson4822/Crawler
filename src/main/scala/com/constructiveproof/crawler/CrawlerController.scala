package com.constructiveproof.crawler

import org.scalatra._
import scalate.ScalateSupport
import java.net.URL
import java.nio.charset.StandardCharsets
import org.scalatra.FutureSupport

import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source

class CrawlerController extends CrawlerStack with FutureSupport {

  protected implicit def executor = ExecutionContext.global

  get("/") {
    contentType = "text/html"
    Future {
      Grabber.evaluate(new URL(params("url")))
    }
  }
}

object Grabber {
  def evaluate(url: URL): String = {
    val content = Source.fromURL(url, StandardCharsets.UTF_8.name()).mkString
    content.contains("Scala") match {
      case true => "Its a Scala site, so good on you!"
      case false => "Hmmm, no Scala in evidence. Are you a character of low cunning?"
    }
  }
}