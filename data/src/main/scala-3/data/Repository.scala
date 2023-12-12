package data

import scala.concurrent.Future

/**
 * General abstraction for common db operations
 *
 * @tparam T type of the entity
 * @tparam ID key type of the entity
 */
trait Repository[T, ID] {

  // add or update
  def create(entity: T): Future[ID]

  def update(id: ID, entity: T): Future[ID]

  def findAll(): Future[Seq[T]]

  def findById(id: ID): Future[Option[T]]

  def deleteById(id: ID): Future[ID]

}




