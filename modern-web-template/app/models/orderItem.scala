package models

case class OrderItem(orderId: String,
                     productId: String,
                     qty: Int,
                     price: Double)