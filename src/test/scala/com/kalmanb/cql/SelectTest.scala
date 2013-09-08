package com.kalmanb.cql

import com.kalmanb.test.TestSpec

class SelectTest extends TestSpec {

  describe("describe") {
    it("simple select query") {
      /**
       * Memeber [
       *   id : Long
       *   firstName : String
       *   dob : Long
       * ]
       */
      val ecql = "+select * from member where id = 123"

      val cql = "select key, f1, f2 from genericTable where key = 'client1|member|123'"

      //val result = Row()
      // row.getLong("id") = 123
      // row.getString("firstName") = "first"
      // row.getLong("dob") = 123456
    }
  }
}
