package com.hexacta.app

import com.hexacta.app.data.{DBConfig, Tables}
import org.scalatra._

import scala.collection.mutable.ListBuffer
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext

class MyScalatraServlet extends ScalatraServlet with FutureSupport {

  get("/") {
    views.html.hello()
  }

  get("/hi") {
    "HOLA HX!!"
  }

  get("/hi/:str") {
    s"HOLA ${params("str")}!!"
  }

  get(s"/contarAll/:str") {
    //word count
    val counts = List({
      params("str")
    }).flatMap(line => line.split(" "))
      .map(word => (word, 1))

    val countsRdd = SparkContext.getSc.parallelize(counts).reduceByKey(_ + _) //(a,b) => a + b

    countsRdd.foreach(println)
    println(countsRdd.count())
    countsRdd.count().toString
  }


  get(s"/contar/:str") {
    //word count
    val counts = List({
      params("str")
    }).flatMap(line => line.split(" "))
      .map(word => (word, 1))

    val result = ListBuffer[(String, Int)]()
    val countsRdd = SparkContext.getSc.parallelize(counts).reduceByKey(_ + _).collect()
    countsRdd.foreach(line => result += line)

    Ok(result.toList)
  }

  get("/employees") {
    println("hola")

    val sparkSession = SparkContext.getSparkSession
    val df = sparkSession
      .read
      .option("multiline","true")
      .json("src/main/resources/employee.json")

    // Displays the content of the DataFrame to stdout
    df.show()
    df.foreach(println)

    df.select("employees.name").collectAsList().toString
  }

  get("/db/create-tables") {
    DBConfig.h2Db.run(Tables.createSchemaAction)
  }

  get("/db/load-data") {
    DBConfig.h2Db.run(Tables.insertProfiles)
  }

  get("/db/drop-tables") {
    DBConfig.h2Db.run(Tables.dropSchemaAction)
  }

  get("/db/profiles") { // run the action and map the result to something more readable
    DBConfig.h2Db.run(Tables.findProfilesNames.result) map { xs =>
      contentType = "text/plain"
      xs map (s1 => f"  $s1 profile") mkString "\n"
    }
  }

  override protected implicit def executor: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global
}
