package com.hexacta.app
import org.scalatra._

import scala.collection.mutable.ListBuffer

class MyScalatraServlet extends ScalatraServlet {

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

}
