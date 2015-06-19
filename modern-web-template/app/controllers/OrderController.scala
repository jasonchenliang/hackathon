package controllers

import java.util.{Date, UUID}

import org.slf4j.{LoggerFactory, Logger}
import play.api.libs.json.{JsError, JsSuccess, JsPath, Reads}
import play.api.mvc.{Controller, Action}
import play.api.mvc.BodyParsers.parse
import play.api.libs.functional.syntax._
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection

import scala.concurrent.Future

/**
 * Created by gavin.liu on 6/18/2015.
 */
class OrderController extends Controller with MongoController {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[CategoryController])

  def collectionOrder: JSONCollection = db.collection[JSONCollection]("order")

  def collectionOrderItem: JSONCollection = db.collection[JSONCollection]("orderItem")

  case class TransientOrder(userId: String, total: Double, orderItem: TransientOrderItem)

  case class TransientOrderItem(productId: String, qty: Int, price: Double)

//  val orderId = UUID.randomUUID().toString()
//  implicit val orderItemReads: Reads[TransientOrderItem] = (
//    (JsPath \ "productId").read[String] and
//      (JsPath \ "qty").read[Int] and
//      (JsPath \ "price").read[Double]
//    )(TransientOrderItem.apply _)
//
//  implicit val orderReads: Reads[TransientOrder] = (
//    (JsPath \ "userId").read[String] and
//      (JsPath \ "total").read[Double] and
//      (JsPath \ "orderItem").read[TransientOrderItem]
//    )(TransientOrder.apply _)

  import models.Order
  import models.OrderItem

//  def createOrder = Action.async(parse.json) {
//    request =>
//      implicit val orderItemReads: Reads[TransientOrderItem] = (
//        (JsPath \ "productId").read[String] and
//          (JsPath \ "qty").read[Int] and
//          (JsPath \ "price").read[Double]
//        )(TransientOrderItem.apply _)
//
//      implicit val orderReads: Reads[TransientOrder] = (
//        (JsPath \ "userId").read[String] and
//          (JsPath \ "total").read[Double] and
//          (JsPath \ "orderItem").read[TransientOrderItem]
//        )(TransientOrder.apply _)
//
//      request.body.validate[TransientOrder] match {
//        case s: JsSuccess[TransientOrder] => {
//          val transientOrder: TransientOrder = s.get
//          val orderId = UUID.randomUUID().toString()
//          var order = Order(orderId, transientOrder.userId, transientOrder.total, new Date())
//          collectionOrder.insert(order)
//
//        }
//        case e: JsError => {
//          // error handling flow
//        }
//      }
//      request.body.validate[TransientOrder].map {
//        transientOrder =>
//          collection.insert(order).map {
//            lastError =>
//              logger.debug(s"Successfully inserted with LastError: $lastError")
//              Created(s"Product Created")
//          }
//      }.getOrElse(Future.successful(BadRequest("invalid json")))
//  }
}
