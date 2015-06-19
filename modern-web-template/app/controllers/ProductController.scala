package controllers

import java.util.{UUID, Calendar}
import javax.inject.Singleton

import com.google.gson.Gson
import models.{User, Product}
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsArray, Json}
import play.api.mvc._
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.Cursor
import reactivemongo.bson.BSONRegex

import scala.concurrent.Future


/**
 * Created by eric.lin on 6/17/2015.
 */
@Singleton
class ProductController extends Controller with MongoController {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[ProductController])

  def collection: JSONCollection = db.collection[JSONCollection]("product")

  implicit val productFormat = Json.format[Product]


  def getProduct(productId: String) = Action.async {
    request =>
      val cursor: Cursor[Product] = collection.find(Json.obj("productId" -> productId)).cursor[Product]
      val futureList: Future[List[Product]] = cursor.collect[List]()
      val futureJsonArray: Future[JsArray] = futureList.map { products =>
        Json.arr(products)
      }

      futureJsonArray.map {
        products =>
          Ok(products(0))
      }

//      val product1 = new Product(0, "Product A", 1, 10.5, "Some description", 10, null, "https://www.google.ca/images/srpr/logo11w.png", Calendar.getInstance().getTime())
//      val gson = new Gson()
//      Ok(gson.toJson(product1))
  }

  def search(sortBy: String, keyword: String, categoryId: String) = Action.async {
    request =>
      var cursor: Cursor[Product] = null
      if ("keyword".equals(sortBy)) {
        cursor = collection.find(
          Json.obj("name" ->
            Json.obj(
              "$regex" -> (keyword),
              "$options" -> "i"
            )
          )
        )
          .sort(Json.obj("createDate" -> -1))
          .cursor[Product]
      }
      else if ("category".equals(sortBy)) {
        cursor = collection.find(Json.obj("categoryId" -> categoryId)).sort(Json.obj("createDate" -> -1)).cursor[Product]
      }
      else if ("new".equals(sortBy)) {
        cursor = collection.find(Json.obj()).sort(Json.obj("createDate" -> -1)).cursor[Product]
      }
      else if ("hot".equals(sortBy)) {

      }
      else if ("sold".equals(sortBy)) {

      }
      val futureList: Future[List[Product]] = cursor.collect[List]()
      val futureJsonArray: Future[JsArray] = futureList.map { products =>
        Json.arr(products)
      }

      futureJsonArray.map {
        products =>
          Ok(products(0))
      }


    //      val product1 = Product(UUID.randomUUID().toString(), "Product A", 1, 10.5, "Some description", 10, null, "https://www.google.ca/images/srpr/logo11w.png", Calendar.getInstance().getTime())
    //      val product2 = Product(UUID.randomUUID().toString(), "Product B", 2, 20.5, "Other description", 9, null, "https://www.google.ca/images/srpr/logo11w.png", Calendar.getInstance().getTime())
    //      val gson = new Gson()
    //      Ok(gson.toJson(Array(product1, product2)))
  }

  def createProduct = Action.async(parse.json) {
    request =>
      request.body.validate[Product].map {
        product =>
          collection.insert(product).map {
            lastError =>
              logger.debug(s"Successfully inserted with LastError: $lastError")
              Created(s"Product Created")
          }
      }.getOrElse(Future.successful(BadRequest("invalid json")))
  }
}
