package com.hexacta.app.data

import slick.jdbc.H2Profile.api._

object Tables {

  // Definition of the Profile table
  class Profiles(tag: Tag) extends Table[(Int, String, String, String, String, String)](tag, "PROFILES") {
    def id = column[Int]("PROFILE_ID", O.PrimaryKey) // This is the primary key column

    def name = column[String]("PROFILE_NAME")

    def street = column[String]("STREET")

    def city = column[String]("CITY")

    def state = column[String]("STATE")

    def zip = column[String]("ZIP")

    // Every table needs a * projection with the same type as the table's type parameter
    def * = (id, name, street, city, state, zip)
  }

  // Table query for the PROFILERS table, represents all tuples of that table
  private val profiles = TableQuery[Profiles]

  // Query profiles return their names
  val findProfilesNames = {
    for {
      s <- profiles
    } yield (s.name)
  }

  // DBIO Action which runs several queries inserting sample data
  val insertProfiles = DBIO.seq(
    profiles += (101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
    profiles += (49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
    profiles += (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"),
  )

  // DBIO Action which creates the schema
  val createSchemaAction = (profiles.schema).create

  // DBIO Action which drops the schema
  val dropSchemaAction = (profiles.schema).drop

  // Create database, composing create schema and insert sample data actions
  val createDatabase = DBIO.seq(createSchemaAction, insertProfiles)

}
