package data

import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuiteLike
import slick.jdbc.H2Profile.api.*

import scala.concurrent.Await
import scala.concurrent.duration.*
import scala.language.postfixOps

class UserH2InMemoryTest extends AnyFunSuiteLike with BeforeAndAfterEach {
  val db = Database.forConfig("mydb")

  override def beforeEach(): Unit = {
    // I use await because I need to be sure the schema creation is ready.
    Await.result(db.run(DBIO.seq(
      users.schema.create
    )), 1 seconds)
    // TODO clean tables after tests
    // Create schema
    println("Iniciando una prueba...")
  }

  test("testCreate") {

    val repo = new UserH2InMemory
    val id = Some(1) // we could improve using uuid
    val juan = User(id, "juan", 37)
    val juanId = Await.result(repo.create(juan), 5 seconds)
    assert(id.get == juanId)
  }

}
