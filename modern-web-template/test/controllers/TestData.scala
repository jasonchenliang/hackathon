package controllers

import java.util.{Date, UUID}
import java.util.concurrent.TimeUnit

import org.specs2.mutable.Specification
import play.api.libs.json.Json
import play.api.test.{FakeRequest, FakeApplication}
import play.api.test.Helpers._

import scala.concurrent.Await
import controllers._

import scala.concurrent.duration.FiniteDuration


class TestData extends Specification {

  val timeout: FiniteDuration = FiniteDuration(5, TimeUnit.SECONDS)

  "Test Data" should {

    //    "insert a categories" in {
    //      running(FakeApplication()) {
    //        var request = FakeRequest.apply(POST, "/category").withJsonBody(Json.obj(
    //          "cid" -> "d4e6641f-e88d-4722-b056-f304a5f95bd1", //UUID.randomUUID().toString(),
    //          "parentId" -> "0",
    //          "name" -> "Breakfast",
    //          "description" -> "Healthy breakfast")
    //        )
    //        var response = route(request)
    //        response.isDefined mustEqual true
    //        var result = Await.result(response.get, timeout)
    //        result.header.status must equalTo(CREATED)
    //
    //        request = FakeRequest.apply(POST, "/category").withJsonBody(Json.obj(
    //          "cid" -> "3a771e5c-02cc-4ea2-a5e0-f178f0766759", //UUID.randomUUID().toString(),
    //          "parentId" -> "0",
    //          "name" -> "Frozen Foods",
    //          "description" -> "Frozen goodies")
    //        )
    //        response = route(request)
    //        response.isDefined mustEqual true
    //        result = Await.result(response.get, timeout)
    //        result.header.status must equalTo(CREATED)
    //
    //        request = FakeRequest.apply(POST, "/category").withJsonBody(Json.obj(
    //          "cid" -> "58763fbc-7408-476f-8d0d-258d7619a079", //UUID.randomUUID().toString(),
    //          "parentId" -> "0",
    //          "name" -> "Beverages",
    //          "description" -> "Assorted beverages")
    //        )
    //        response = route(request)
    //        response.isDefined mustEqual true
    //        result = Await.result(response.get, timeout)
    //        result.header.status must equalTo(CREATED)
    //
    //        request = FakeRequest.apply(POST, "/category").withJsonBody(Json.obj(
    //          "cid" -> "920435fe-34d3-43db-97a1-44b5d536e6fa", //UUID.randomUUID().toString(),
    //          "parentId" -> "0",
    //          "name" -> "Snacks",
    //          "description" -> "Snacks, cookies, & candy")
    //        )
    //        response = route(request)
    //        response.isDefined mustEqual true
    //        result = Await.result(response.get, timeout)
    //        result.header.status must equalTo(CREATED)
    //      }
    //    }

    "insert breakfast products" in {
      running(FakeApplication()) {
        var request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "38b2117f-65ab-4b72-85ca-cdd45b5678c7", //UUID.randomUUID().toString(),
          "name" -> "Honey Nut Cheerios Cereal",
          "categoryId" -> "d4e6641f-e88d-4722-b056-f304a5f95bd1",
          "price" -> 3.99,
          "description" -> "Sweetened whole grain oat cereal with real honey and natural almond flavor",
          "qty" -> 5,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/91vmSXZU+6L._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        var response = route(request)
        response.isDefined mustEqual true
        var result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)

        request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "32cc5a46-3b57-4927-b94a-c9a9c4e7d87f", //UUID.randomUUID().toString(),
          "name" -> "Rice Krispies",
          "categoryId" -> "d4e6641f-e88d-4722-b056-f304a5f95bd1",
          "price" -> 3.38,
          "description" -> "The original crisped rice cereal has become one of life's simple pleasures.",
          "qty" -> 15,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/91S6c8hgp4L._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        response = route(request)
        response.isDefined mustEqual true
        result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)

        request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "b5230bff-bb86-4567-8bd9-20f0a3cf3fda", //UUID.randomUUID().toString(),
          "name" -> "Odwalla Chocolate Peanut Butter Protein Bar",
          "categoryId" -> "d4e6641f-e88d-4722-b056-f304a5f95bd1",
          "price" -> 1.00,
          "description" -> "Made with whole cereal grains",
          "qty" -> 6,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/81SzkOdi+aL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        response = route(request)
        response.isDefined mustEqual true
        result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)

        request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "ad5f5d1c-3750-491b-b527-91877f9c2245", //UUID.randomUUID().toString(),
          "name" -> "Quaker Chewy Granola Bars",
          "categoryId" -> "d4e6641f-e88d-4722-b056-f304a5f95bd1",
          "price" -> 2.19,
          "description" -> "Give your family the taste Chewy Chocolate Chip taste they love",
          "qty" -> 12,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/A1EyOd9A0yL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        response = route(request)
        response.isDefined mustEqual true
        result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)
      }
    }

    "insert frozen products" in {
      running(FakeApplication()) {
        var request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "6e966df2-df0f-4e0c-9487-91c652113d97", //UUID.randomUUID().toString(),
          "name" -> "Amy's Bean Rice and Cheese Burrito",
          "categoryId" -> "3a771e5c-02cc-4ea2-a5e0-f178f0766759",
          "price" -> 2.39,
          "description" -> "Organic flour tortilla wrapped around organic pinto beans",
          "qty" -> 8,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/71U7q2qRNTL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        var response = route(request)
        response.isDefined mustEqual true
        var result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)

        request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "d4a0a9cc-5739-4484-93ac-4f5e0ea357e8", //UUID.randomUUID().toString(),
          "name" -> "Amy's Macaroni & Cheese",
          "categoryId" -> "3a771e5c-02cc-4ea2-a5e0-f178f0766759",
          "price" -> 2.99,
          "description" -> "Organic elbow macaroni in a creamy cheese sauce.",
          "qty" -> 5,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/91C3I-4w2yL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        response = route(request)
        response.isDefined mustEqual true
        result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)

        request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "0e23b705-f830-4cfd-ae45-52a680b7a76e", //UUID.randomUUID().toString(),
          "name" -> "Lean Cuisine Sesame Chicken with Pasta Dinner",
          "categoryId" -> "3a771e5c-02cc-4ea2-a5e0-f178f0766759",
          "price" -> 2.89,
          "description" -> "Sesame breaded chicken tenderloins in a tangy Asian-style plum sauce accented with ginger and topped with roasted sesame seeds.",
          "qty" -> 5,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/61iXzTBqprL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        response = route(request)
        response.isDefined mustEqual true
        result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)

        request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "aa6da295-18a0-48d9-af6e-ba35c8b4df7b", //UUID.randomUUID().toString(),
          "name" -> "Totino's, Pizza, Three Cheese",
          "categoryId" -> "3a771e5c-02cc-4ea2-a5e0-f178f0766759",
          "price" -> 1.79,
          "description" -> "2 servings per package",
          "qty" -> 3,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/A10CTdMZ-VL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        response = route(request)
        response.isDefined mustEqual true
        result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)
      }
    }

    "insert beverage products" in {
      running(FakeApplication()) {
        var request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "4a37c795-8b65-4748-ad9d-1833e6e686f4", //UUID.randomUUID().toString(),
          "name" -> "San Pellegrino Sparkling Natural Mineral Water",
          "categoryId" -> "58763fbc-7408-476f-8d0d-258d7619a079",
          "price" -> 1.49,
          "description" -> "Refreshing sparkling water",
          "qty" -> 30,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/71GnrB+cixL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        var response = route(request)
        response.isDefined mustEqual true
        var result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)

        request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "f0498349-948d-49ff-a669-6d4d8833998f", //UUID.randomUUID().toString(),
          "name" -> "vitaminwater zero",
          "categoryId" -> "58763fbc-7408-476f-8d0d-258d7619a079",
          "price" -> 1.00,
          "description" -> "Zero Calories. Nutrient enhanced water beverage.",
          "qty" -> 10,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/41pyJ39eivL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        response = route(request)
        response.isDefined mustEqual true
        result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)

        request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "509e0a5b-9532-4953-ab6c-3872fe692296", //UUID.randomUUID().toString(),
          "name" -> "Simply, Pulp Free Orange Juice with Calcium",
          "categoryId" -> "58763fbc-7408-476f-8d0d-258d7619a079",
          "price" -> 2.89,
          "description" -> "Fresh taste guranteed. Note from concentrate.",
          "qty" -> 8,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/51HGIuFYEDL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        response = route(request)
        response.isDefined mustEqual true
        result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)

        request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "aa6da295-18a0-48d9-af6e-ba35c8b4df7b", //UUID.randomUUID().toString(),
          "name" -> "Zico Natural Coconut Water",
          "categoryId" -> "58763fbc-7408-476f-8d0d-258d7619a079",
          "price" -> 3.99,
          "description" -> "Earliest expiration date: 10-26-2015",
          "qty" -> 3,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/41IXcm4eiYL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        response = route(request)
        response.isDefined mustEqual true
        result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)
      }
    }

    "insert snack products" in {
      running(FakeApplication()) {
        var request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "d73cc5c2-8f2d-41fc-a4c0-a4c5f90c4d20", //UUID.randomUUID().toString(),
          "name" -> "Stacy's Pita Chip, Simply Naked",
          "categoryId" -> "920435fe-34d3-43db-97a1-44b5d536e6fa",
          "price" -> 2.80,
          "description" -> "We bake real pita from our own special recipe, slice it into chips, then bake it again for a delicious crunch.",
          "qty" -> 10,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/91GHnijQskL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        var response = route(request)
        response.isDefined mustEqual true
        var result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)

        request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "0abbc358-b93e-4105-ad01-ff65d1b97f56", //UUID.randomUUID().toString(),
          "name" -> "Pepperidge Farm Goldfish Snack Crackers, Cheddar",
          "categoryId" -> "920435fe-34d3-43db-97a1-44b5d536e6fa",
          "price" -> 6.99,
          "description" -> "Goldfish Cheddar Crackers, fun Goldfish shape all Natural ingredients",
          "qty" -> 8,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/910agKls5dL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        response = route(request)
        response.isDefined mustEqual true
        result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)

        request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "fa55f6bb-54d2-47c1-afb0-359546f67b53", //UUID.randomUUID().toString(),
          "name" -> "Seneca Apple Sauce, 100% Natural, No Sugar Added",
          "categoryId" -> "920435fe-34d3-43db-97a1-44b5d536e6fa",
          "price" -> 1.99,
          "description" -> "Earliest expiration date: 06-24-2016",
          "qty" -> 2,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/41dKN5vIgHL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        response = route(request)
        response.isDefined mustEqual true
        result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)

        request = FakeRequest.apply(POST, "/product").withJsonBody(Json.obj(
          "productId" -> "37f53d95-bb11-43b1-aa72-98cda98cbf5a", //UUID.randomUUID().toString(),
          "name" -> "Rold Gold Pretzels, Classic Thins",
          "categoryId" -> "920435fe-34d3-43db-97a1-44b5d536e6fa",
          "price" -> 2.99,
          "description" -> "Made with all natural ingridients.",
          "qty" -> 5,
          "attribute" -> "",
          "imageUrl" -> "https://images-na.ssl-images-amazon.com/images/I/91offMtn-lL._SR280,280_.jpg",
          "createDate" -> new Date()
        ))
        response = route(request)
        response.isDefined mustEqual true
        result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)
      }
    }
  }

}
