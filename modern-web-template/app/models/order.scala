package models

import java.util.Date

case class Order(orderId: String,
                 userId: String,
                 total: Double,
                 createDate: Date,
                 orderItems: List[OrderItem])