val ScalatraVersion = "3.0.0"

ThisBuild / scalaVersion := "2.13.11"
ThisBuild / organization := "com.hexacta"

val toolkitTest = "org.scala-lang" %% "toolkit-test" % "0.1.7"

lazy val hello = (project in file("."))
  .aggregate(data)
  .dependsOn(data)
  .settings(
    name := "Sparlatra",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "org.scalatra" %% "scalatra-jakarta" % ScalatraVersion,
      "org.scalatra" %% "scalatra-scalatest-jakarta" % ScalatraVersion % "test",
      "ch.qos.logback" % "logback-classic" % "1.4.11" % "runtime",
      "org.eclipse.jetty" % "jetty-webapp" % "11.0.17" % "container",
      "jakarta.servlet" % "jakarta.servlet-api" % "5.0.0" % "provided",
      "org.apache.spark" %% "spark-core" % "3.5.0",
      "org.apache.spark" %% "spark-sql" % "3.5.0"
    ),
  )

lazy val data = project
  .in(file("data"))
  .settings(scalaVersion := "3.3.1")
  .settings(
    name := "Data layer using slick",
    libraryDependencies ++= Seq(
      "org.scala-lang" %% "toolkit" % "0.2.0",
      "com.typesafe.slick" %% "slick" % "3.5.0-M5",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.5.0-M5",
      "com.h2database" % "h2" % "2.2.224",
      "org.scalatest" %% "scalatest" % "3.2.15" % Test


    ),
//    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.17",
//    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % "test",
    libraryDependencies += toolkitTest % Test
  )

enablePlugins(SbtTwirl)
enablePlugins(JettyPlugin)

Jetty / containerLibs := Seq("org.eclipse.jetty" % "jetty-runner" % "11.0.17" intransitive())
