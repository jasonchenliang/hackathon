package controllers

import java.util.{UUID, Calendar}
import javax.inject.Singleton

import models.{Review, Product}
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsArray, Json}
import play.api.mvc._
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.Cursor

import scala.concurrent.Future


@Singleton
class ReviewController extends Controller with MongoController {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[ReviewController])

  def collection: JSONCollection = db.collection[JSONCollection]("review")

  implicit val reviewFormat = Json.format[Review]

  def addReview(productId: String, userId: String, content: String, rating: Int) = Action.async {
      val review = Review(UUID.randomUUID().toString(), productId, userId, content, rating, 0, Calendar.getInstance().getTime())
      collection.insert(review).map {
        lastError =>
          logger.debug(s"Successfully inserted with LastError: $lastError")
          Created(s"Review Created")
      }
  }


  def getReview(productId: String) = Action.async {
      val cursor: Cursor[Review] = collection.find(Json.obj("productId" -> productId)).sort(Json.obj("createDate" -> -1)).cursor[Review]
      val futureList: Future[List[Review]] = cursor.collect[List]()
      val futureJsonArray: Future[JsArray] = futureList.map { items =>
        Json.arr(items)
      }

      futureJsonArray.map {
        items =>
          Ok(items(0))
      }
  }
}
