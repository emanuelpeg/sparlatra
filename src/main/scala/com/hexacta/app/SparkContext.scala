package com.hexacta.app

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkContext {

  //Create a SparkContext to initialize Spark
  private val conf = new SparkConf()
  conf.setMaster("local[*]")

  //conf.setMaster("spark://10.60.1.26:7077")
  //conf.setJars(Seq("/home/emanuel/Projects/scala-spark-scalatra/spark-scalatra/sparklatra/target/scala-2.11/sparklatra_2.11-0.1.0-SNAPSHOT.jar"))

  conf.setAppName("Hexacta")

  private val sc = new org.apache.spark.SparkContext(conf)
  private val sparkSession = SparkSession.builder.config(conf).getOrCreate()
  def getSc = {
    sc
  }

  def getSparkSession = {
    sparkSession
  }

}