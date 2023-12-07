val ScalatraVersion = "3.0.0"

ThisBuild / scalaVersion := "3.3.1"
ThisBuild / organization := "com.hexacta"


lazy val hello = (project in file("."))
  .settings(
    name := "Sparlatra",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "org.scalatra" %% "scalatra-jakarta" % ScalatraVersion,
      "org.scalatra" %% "scalatra-json-jakarta" % ScalatraVersion,
      "org.scalatra" %% "scalatra-forms-jakarta" % ScalatraVersion,
      "org.eclipse.jetty" % "jetty-webapp" % "11.0.15" % "container;compile",
      "ch.qos.logback" % "logback-classic" % "1.4.11" % "compile",

      
    )
  )