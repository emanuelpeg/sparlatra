package data

import slick.jdbc.H2Profile.api.*

import scala.concurrent.Future

case class User(id: Option[Int], name: String, age: Int)

class UserTable(tag: Tag) extends Table[User](tag, "USER") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME")

  def age = column[Int]("age")

  def * = (id.?, name, age) <> ((User.apply _).tupled, User.unapply)
}

val users = TableQuery[UserTable]

trait UserRepository extends Repository[User, Int]


// In Memory H2 DB
class UserH2InMemory extends UserRepository {

  private val db = Database.forConfig("mydb")

  override def create(user: User): Future[Int] = db.run(users += user)

  override def update(id: Int, user: User): Future[Int] = db.run(users.filter(_.id === id).update(user))

  override def findById(id: Int): Future[Option[User]] = db.run(users.filter(_.id === id).result.headOption)

  override def deleteById(id: Int): Future[Int] = db.run(users.filter(_.id === id).delete)

  override def findAll(): Future[Seq[User]] = db.run(users.result)
}