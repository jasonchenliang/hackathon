package models

import java.util.Date

case class Review(reviewId: String,
                  productId: String,
                  userId: String,
                  content: String,
                  rating: Int,
                  helpfulness: Int,
                  var createDate: Date)
