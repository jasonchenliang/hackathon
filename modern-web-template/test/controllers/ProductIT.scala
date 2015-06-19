package controllers

import java.util.{UUID, Calendar, Date}
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


//    "fail updating a non valid json" in {
//      running(FakeApplication()) {
//        val request = FakeRequest.apply(GET, "/getProduct/1")
//        val response = route(request)
//        response.isDefined mustEqual true
//        val result = Await.result(response.get, timeout)
//        contentAsString(response.get) mustEqual "invalid json"
//        //result.header.status mustEqual BAD_REQUEST
//
//      }
//    }

//    "insert a valid json" in {
//      running(FakeApplication()) {
//        val request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
//          "productId" -> UUID.randomUUID().toString(),
//          "name" -> "Product B",
//          "categoryId" -> "3",
//          "price" -> 11.50,
//          "description" -> "Blah Blah description",
//          "qty" -> 5,
//          "attribute" -> "attr1,attr2",
//          "imageUrl" -> "https://www.google.ca/images/srpr/logo11w.png",
//          "createDate" -> Calendar.getInstance().getTime()
//        ))
//        val response = route(request)
//        response.isDefined mustEqual true
//        val result = Await.result(response.get, timeout)
//        result.header.status must equalTo(CREATED)
//      }
//    }

    "fail updating a non valid json" in {
      running(FakeApplication()) {
        val request = FakeRequest.apply(GET, "/search/keyword/A/0")
        val response = route(request)
        response.isDefined mustEqual true
        val result = Await.result(response.get, timeout)
        //contentAsString(response.get) mustEqual "invalid json"
        result.header.status mustEqual OK

      }
    }

  }

}
