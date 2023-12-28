package com.hexacta.app.data

import com.mchange.v2.c3p0.ComboPooledDataSource
import slick.jdbc.H2Profile.api._
object DBConfig {
   def h2Db = {
    val cpds = new ComboPooledDataSource

    Database.forDataSource(cpds, None)
  }
}
