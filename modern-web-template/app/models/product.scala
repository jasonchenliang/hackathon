package models

import java.util.Date

/*comments*/
case class Product(productId: String,
                   name: String,
                   categoryId: String,
                   price: Double,
                   description: String,
                   qty: Int,
                   attribute: String,
                   imageUrl: String,
                   createDate: Date)


//object ProductJsonFormats {
//
//  import play.api.libs.json.Json
//
//  // Generates Writes and Reads for Feed and User thanks to Json Macros
//  implicit val userFormat = Json.format[Product]
//}