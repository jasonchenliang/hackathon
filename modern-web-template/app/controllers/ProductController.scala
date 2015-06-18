package controllers

import play.api.mvc.Controller
import play.modules.reactivemongo.MongoController

/**
 * Created by eric.lin on 6/17/2015.
 */
class ProductController extends Controller with MongoController {

  //  private final val logger: Logger = LoggerFactory.getLogger(classOf[ProductController])
  //
  //  def collection: JSONCollection = db.collection[JSONCollection]("products")
  //
  //  import models.JsonFormats._
  //  import models._
  //
  //  def getProduct = Action.async(parse.json) {
  //    request =>
  //
  //      // let's do our query
  //      val cursor: Cursor[Product] = collection.
  //        // find all
  //        find(Json.obj("active" -> true)).
  //        // sort them by creation date
  //        sort(Json.obj("created" -> -1)).
  //        // perform the query and get a cursor of JsObject
  //        cursor[Product]
  //
  //      // gather all the JsObjects in a list
  //      val futureUsersList: Future[List[User]] = cursor.collect[List]()
  //
  //      // transform the list into a JsArray
  //      val futurePersonsJsonArray: Future[JsArray] = futureUsersList.map { users =>
  //        Json.arr(users)
  //      }
  //      // everything's ok! Let's reply with the array
  //      futurePersonsJsonArray.map {
  //        users =>
  //          Ok(users(0))
  //      }
  //  }
}
