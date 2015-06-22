package controllers

import java.util.UUID
import java.util.concurrent.TimeUnit

import org.specs2.mutable.Specification
import play.api.libs.json.Json
import play.api.libs.json.JsNull
import play.api.test.{FakeRequest, FakeApplication}
import play.api.test.Helpers._

import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration

/**
 * Created by gavin.liu on 6/19/2015.
 */
class CategoryNodeIT extends Specification {

  val timeout: FiniteDuration = FiniteDuration(5, TimeUnit.SECONDS)

  "Category" should {

    "insert a valid json" in {
      running(FakeApplication()) {
        val request = FakeRequest.apply(POST, "/categoryNode").withJsonBody(Json.obj(
          "id" -> UUID.randomUUID().toString(),
          "name" -> "Food",
          "description" -> "TODO",
          "subCategories" -> Json.arr(
            Json.obj(
              "id" -> UUID.randomUUID().toString(),
              "name" -> "Meat",
              "description" -> "TODO",
              "subCategories" -> Json.arr()
            ),
            Json.obj(
              "id" -> UUID.randomUUID().toString(),
              "name" -> "Fruit",
              "description" -> "TODO",
              "subCategories" -> Json.arr()
            )
          )
        )
        )
        val response = route(request)
        response.isDefined mustEqual true
        val result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)
      }
    }
  }
}
