package controllers

import java.util.UUID
import java.util.concurrent.TimeUnit

import org.specs2.mutable.Specification
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeApplication, FakeRequest}

import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration

/**
 * Created by eric.lin on 6/18/2015.
 */
class ReviewIT extends Specification {
  val timeout: FiniteDuration = FiniteDuration(5, TimeUnit.SECONDS)

  "Reviews" should {


    "insert a review" in {
      running(FakeApplication()) {
        val productId = "47c77366-2a2d-4c31-aa53-13283af295f6"
        val userId = "abc111"
        val content = "Good"
        val rating = 5
        val request = FakeRequest.apply(POST, "/addReview/" + productId + "/" + userId + "/" + content + "/" + rating)
        val response = route(request)
        response.isDefined mustEqual true
        val result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)
      }
    }

        "get review" in {
          running(FakeApplication()) {
            val productId = "47c77366-2a2d-4c31-aa53-13283af295f6"
            val request = FakeRequest.apply(GET, "/getReview/" + productId)
            val response = route(request)
            response.isDefined mustEqual true
            val result = Await.result(response.get, timeout)
            //contentAsString(response.get) mustEqual "invalid json"
            result.header.status mustEqual OK

          }
        }

  }

}
