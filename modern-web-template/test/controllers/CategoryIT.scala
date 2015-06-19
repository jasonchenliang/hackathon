package controllers

import java.util.UUID

import scala.concurrent._
import duration._
import org.specs2.mutable._

import play.api.libs.json._
import play.api.test._
import play.api.test.Helpers._
import java.util.concurrent.TimeUnit


/**
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class CategoryIT extends Specification {

  val timeout: FiniteDuration = FiniteDuration(5, TimeUnit.SECONDS)

  "Category" should {

    "insert a valid json" in {
      running(FakeApplication()) {
        val request = FakeRequest.apply(POST, "/category").withJsonBody(Json.obj(
          "cid" -> UUID.randomUUID().toString(),
          "parentId" -> "2657ccba-a7c4-4934-931b-86f86284044a",
          "name" -> "Test-1",
          "description" -> "TODO")
          )
        val response = route(request)
        response.isDefined mustEqual true
        val result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)
      }
    }

    "get a list of category" in {
      running(FakeApplication()) {
        val request = FakeRequest.apply(GET, "/getCategories/0")
        val response = route(request)
        response.isDefined mustEqual true
        val result = Await.result(response.get, timeout)
        result.header.status must equalTo(OK)
      }
    }
  }
}
