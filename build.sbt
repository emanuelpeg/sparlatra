val ScalatraVersion = "3.0.0"

ThisBuild / scalaVersion := "2.13.11"
ThisBuild / organization := "com.hexacta"

lazy val hello = (project in file("."))
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

enablePlugins(SbtTwirl)
enablePlugins(JettyPlugin)

Jetty / containerLibs := Seq("org.eclipse.jetty" % "jetty-runner" % "11.0.17" intransitive())
