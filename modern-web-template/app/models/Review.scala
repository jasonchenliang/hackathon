package models

import java.util.Date

case class Review(reviewId: Int,
                  productId: Int,
                  userId: Int,
                  content: String,
                  rating: Int,
                  helpfulness: Int,
                  createDate: Date)

//object JsonFormats {
//
//  import play.api.libs.json.Json
//
//  // Generates Writes and Reads for Feed and User thanks to Json Macros
//  implicit val userFormat = Json.format[Review]
//}