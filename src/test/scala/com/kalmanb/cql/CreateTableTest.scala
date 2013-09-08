package com.kalmanb.cql

import com.kalmanb.test.TestSpec

class CreateTableTest extends TestSpec  {

  describe("describe") {

    it("create table") {
      val ecql = """+create table member (
      id long,
      firstName text,
      dob long,
      primary key (id)
      )
      """
      val cql = """create table genericTable (
      key blob,
      sort blob,
      f1 blob,
      f2 blob,
      f3 blob,
      primary key (key, sort)
      )
      """

    }
  it("should") {
    /* cursor */
  }
  }
}
