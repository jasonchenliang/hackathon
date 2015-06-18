package controllers

import java.util.concurrent.TimeUnit

import models.User
import org.specs2.mutable.Specification
import play.api.libs.json.Json
import play.api.test.{FakeRequest, FakeApplication}
import play.api.test.Helpers._

import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration

/**
 * Created by eric.lin on 6/18/2015.
 */
class ProductIT extends Specification {
  val timeout: FiniteDuration = FiniteDuration(5, TimeUnit.SECONDS)

  "Products" should {

//    "render the index page" in {
//      running(FakeApplication()) {
//        val home = route(FakeRequest(GET, "/getProduct/1")).get
//        status(home) must equalTo(OK)
//                //contentType(home) must beSome.which(_ == "text/html")
//      }
//    }

    "fail updating a non valid json" in {
      running(FakeApplication()) {
        val request = FakeRequest.apply(GET, "/getProduct/1")
        val response = route(request)
        response.isDefined mustEqual true
        val result = Await.result(response.get, timeout)
        contentAsString(response.get) mustEqual "invalid json"
        //result.header.status mustEqual BAD_REQUEST

      }
    }

//    "fail inserting a non valid json" in {
//      running(FakeApplication()) {
//        val request = FakeRequest.apply(POST, "/user").withJsonBody(Json.obj(
//          "firstName" -> 98,
//          "lastName" -> "London",
//          "age" -> 27))
//        val response = route(request)
//        response.isDefined mustEqual true
//        val result = Await.result(response.get, timeout)
//        contentAsString(response.get) mustEqual "invalid json"
//        result.header.status mustEqual BAD_REQUEST
//      }
//    }
//
//    "update a valid json" in {
//      running(FakeApplication()) {
//        val request = FakeRequest.apply(PUT, "/user/Jack/London").withJsonBody(Json.obj(
//          "firstName" -> "Jack",
//          "lastName" -> "London",
//          "age" -> 27,
//          "active" -> true))
//        val response = route(request)
//        response.isDefined mustEqual true
//        val result = Await.result(response.get, timeout)
//        result.header.status must equalTo(CREATED)
//      }
//    }
//
//    "fail updating a non valid json" in {
//      running(FakeApplication()) {
//        val request = FakeRequest.apply(PUT, "/user/Jack/London").withJsonBody(Json.obj(
//          "firstName" -> "Jack",
//          "lastName" -> "London",
//          "age" -> 27))
//        val response = route(request)
//        response.isDefined mustEqual true
//        val result = Await.result(response.get, timeout)
//        contentAsString(response.get) mustEqual "invalid json"
//        result.header.status mustEqual BAD_REQUEST
//      }
//    }

  }

}
