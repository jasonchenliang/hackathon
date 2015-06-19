package controllers

import java.util.UUID
import java.util.concurrent.TimeUnit

import org.specs2.mutable.Specification
import play.api.libs.json.Json
import play.api.test.{FakeRequest, FakeApplication}
import play.api.test.Helpers._

import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration

/**
 * Created by gavin.liu on 6/19/2015.
 */
class OrderIT extends Specification {

  val timeout: FiniteDuration = FiniteDuration(5, TimeUnit.SECONDS)

  "Order" should {

      "insert a valid json" in {
        running(FakeApplication()) {
          val request = FakeRequest.apply(POST, "/order").withJsonBody(Json.obj(
            "userId" -> "user1",
            "total" -> 20.00,
            "orderItems" -> Json.arr(
              Json.obj(
                "productId" -> "product1",
                "qty" -> 1,
                "price" -> 10.00
              ),
              Json.obj(
                "productId" -> "product2",
                "qty" -> 2,
                "price" -> 5.00
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
