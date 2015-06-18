package controllers

import java.util.{Date, Calendar}
import javax.inject.Singleton

import com.google.gson.Gson
import models.Product
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.json.{JsValue, JsObject, Json}
import play.api.mvc._
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection

import scala.concurrent.Future

/**
 * Created by eric.lin on 6/17/2015.
 */
@Singleton
class ProductController extends Controller with MongoController {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[ProductController])

  def collection: JSONCollection = db.collection[JSONCollection]("products")

  //implicit val dateFormat = Json.format[Date]
  implicit val productFormat = Json.format[Product]
  //implicit val productWrites = Json.writes[Product]

  def getProduct(productId: Int) = Action {
    request =>
      val product1 = new Product(0, "Product A", 1, 10.5, "Some description", 10, null, "https://www.google.ca/images/srpr/logo11w.png", Calendar.getInstance().getTime())
      val gson = new Gson()
      Ok(gson.toJson(product1))
  }

  def search(sortBy: String, keyword: String, categoryId: Int) = Action {
    request =>
      val product1 = Product(0, "Product A", 1, 10.5, "Some description", 10, null, "https://www.google.ca/images/srpr/logo11w.png", Calendar.getInstance().getTime())
      val product2 = Product(1, "Product B", 2, 20.5, "Other description", 9, null, "https://www.google.ca/images/srpr/logo11w.png", Calendar.getInstance().getTime())
      val gson = new Gson()
      Ok(gson.toJson(Array(product1, product2)))
  }

  def createUser = Action.async(parse.json) {
      request =>
      /*
       * request.body is a JsValue.
       * There is an implicit Writes that turns this JsValue as a JsObject,
       * so you can call insert() with this JsValue.
       * (insert() takes a JsObject as parameter, or anything that can be
       * turned into a JsObject using a Writes.)
       */
        request.body.validate[User].map {
          user =>
          // `user` is an instance of the case class `models.User`
            collection.insert(user).map {
              lastError =>
                logger.debug(s"Successfully inserted with LastError: $lastError")
                Created(s"User Created")
            }
        }.getOrElse(Future.successful(BadRequest("invalid json")))
    }
}
