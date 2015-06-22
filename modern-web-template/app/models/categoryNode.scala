package models

case class CategoryNode(id: String,
                       name: String,
                       description: String,
                       subCategories: List[CategoryNode])