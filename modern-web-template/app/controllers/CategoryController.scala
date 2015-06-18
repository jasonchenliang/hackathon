package controllers

import org.slf4j.{LoggerFactory, Logger}
import play.api.libs.json.{JsValue, Writes, JsArray, Json}
import play.api.mvc.{Action, Controller}
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.Cursor

import play.api.libs.concurrent.Execution.Implicits._

import scala.concurrent.Future

/**
 * Created by gavin.liu on 6/17/2015.
 */
class CategoryController extends Controller with MongoController {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[CategoryController])

  def collection: JSONCollection = db.collection[JSONCollection]("category")

  import models.Category

  implicit val categoryFormat = Json.format[Category]

  def createCategory = Action.async(parse.json) {
    request =>
      request.body.validate[Category].map {
        category =>
          collection.insert(category).map {
            lastError =>
              logger.debug(s"Successfully inserted with LastError: $lastError")
              Created(s"Category Created")
          }
      }.getOrElse(Future.successful(BadRequest("invalid json")))
  }

//  def findCategories = Action.async {
//    val query = Json.obj("parentId" -> "0")
//    val cursor: Cursor[Category] = collection.
//      find(query).
//      // perform the query and get a cursor of JsObject
//      cursor[Category]
//
//    // gather all the JsObjects in a list
//    val futureCategoriesList: Future[List[Category]] = cursor.collect[List]()
//
//    // transform the list into a JsArray
//    val futureCategoriesJsonArray: Future[JsArray] = futureCategoriesList.map { categories =>
//      Json.arr(categories)
//    }
//    // everything's ok! Let's reply with the array
//    futureCategoriesJsonArray.map {
//      categories =>
//        Ok(categories(0))
//    }
//  }

  def findCategories(parentId: String) = Action.async {
    val query = Json.obj("parentId" -> parentId)
    val cursor: Cursor[Category] = collection.
      find(query).
      // perform the query and get a cursor of JsObject
      cursor[Category]

    // gather all the JsObjects in a list
    val futureCategoriesList: Future[List[Category]] = cursor.collect[List]()

    // transform the list into a JsArray
    val futureCategoriesJsonArray: Future[JsArray] = futureCategoriesList.map { categories =>
      Json.arr(categories)
    }
    // everything's ok! Let's reply with the array
    futureCategoriesJsonArray.map {
      categories =>
        Ok(categories(0))
    }
  }

}
