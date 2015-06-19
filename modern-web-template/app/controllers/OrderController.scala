package controllers

import java.util.{Date, UUID}

import org.slf4j.{LoggerFactory, Logger}
import play.api.libs.json._
import play.api.mvc.{Controller, Action}
import play.api.libs.functional.syntax._
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import play.api.libs.concurrent.Execution.Implicits._

import scala.concurrent.Future

/**
 * Created by gavin.liu on 6/18/2015.
 */
class OrderController extends Controller with MongoController {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[CategoryController])

  def collectionOrder: JSONCollection = db.collection[JSONCollection]("order")

  import models.Order
  import models.OrderItem

  implicit val orderItemFormat = Json.format[OrderItem]
  implicit val orderFormat = Json.format[Order]
  case class TransientOrder(userId: String, total: Double, orderItem: List[OrderItem])

  def createOrder = Action.async(parse.json) {
    request =>
      implicit val orderItemReads: Reads[OrderItem] = (
        (JsPath \ "productId").read[String] and
          (JsPath \ "qty").read[Int] and
          (JsPath \ "price").read[Double]
        )(OrderItem.apply _)

      implicit val orderReads: Reads[TransientOrder] = (
        (JsPath \ "userId").read[String] and
          (JsPath \ "total").read[Double] and
          (JsPath \ "orderItems").read[List[OrderItem]]
        )(TransientOrder.apply _)

      implicit val orderItemWrites: Writes[OrderItem] = (
        (JsPath \ "productId").write[String] and
          (JsPath \ "qty").write[Int] and
          (JsPath \ "price").write[Double]
        )(unlift(OrderItem.unapply))

      implicit val orderWrites: Writes[Order] = (
        (JsPath \ "orderId").write[String] and
        (JsPath \ "userId").write[String] and
          (JsPath \ "total").write[Double] and
          (JsPath \ "createDate").write[Date] and
          (JsPath \ "orderItems").write[List[OrderItem]]
        )(unlift(Order.unapply))

      request.body.validate[TransientOrder] match {
        case s: JsSuccess[TransientOrder] => {
          val transientOrder: TransientOrder = s.get
          val orderId = UUID.randomUUID().toString()
          var order = Order(orderId, transientOrder.userId, transientOrder.total, new Date(), transientOrder.orderItem)
          collectionOrder.insert(order).map {
            lastError =>
              logger.debug(s"Successfully inserted with LastError: $lastError")
              Created(s"Order Created")
          }
        }
        case e: JsError => {
          Future.successful(BadRequest("invalid json"))
        }
      }
  }
}
