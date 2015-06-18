package models

case class Category(cid: String,
                    parentId: String,
                    name: String,
                    description: String)