package controllers

import models.Order
import org.slf4j.{LoggerFactory, Logger}
import play.api.libs.json.{JsArray, Json}
import play.api.mvc.{Action, Controller}
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection

import play.api.libs.concurrent.Execution.Implicits._
import reactivemongo.api.Cursor

import scala.concurrent.Future

/**
 * Created by gavin.liu on 6/19/2015.
 */
class CategoryNodeController extends Controller with MongoController {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[CategoryController])

  def collection: JSONCollection = db.collection[JSONCollection]("categoryNode")

  import models.CategoryNode

  implicit val categoryFormat = Json.format[CategoryNode]

  def createCategory = Action.async(parse.json) {
    request =>
      request.body.validate[CategoryNode].map {
        category =>
          collection.insert(category).map {
            lastError =>
              logger.debug(s"Successfully inserted with LastError: $lastError")
              Created(s"Category Created")
          }
      }.getOrElse(Future.successful(BadRequest("invalid json")))
  }

  def getCategories = Action.async {
    val query = Json.obj()
    val cursor: Cursor[CategoryNode] = collection.
      find(query).
      // perform the query and get a cursor of JsObject
      cursor[CategoryNode]
    // gather all the JsObjects in a list
    val futureList: Future[List[CategoryNode]] = cursor.collect[List]()

    // transform the list into a JsArray
    val futureJsonArray: Future[JsArray] = futureList.map { categogies =>
      Json.arr(categogies)
    }
    // everything's ok! Let's reply with the array
    futureJsonArray.map {
      categogies =>
        Ok(categogies(0))
    }
  }
}
