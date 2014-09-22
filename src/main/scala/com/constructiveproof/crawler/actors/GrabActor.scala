package com.constructiveproof.crawler.actors

import java.net.URL
import java.nio.charset.StandardCharsets

import akka.actor.Actor
import scala.io.Source
/**
 * Created by steve on 9/21/14.
 */
class GrabActor extends Actor {

  def receive = {
    case url: URL => evaluate(url)
    case _ => sender ! "That wasn't a URL. This violation has been noted in your records."
  }

  def evaluate(url: URL) = {
    val content = Source.fromURL(url, StandardCharsets.UTF_8.name()).mkString
    content.contains("Akka") match {
      case true => sender ! "I see Akka on this site. My Spider Senses are a-tingle!"
      case false => sender ! "No Akka means no Shakalaka. Make of that what you will!"
    }
  }
}
