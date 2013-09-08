package com.kalmanb.cql

import com.kalmanb.test.TestSpec

class InsestTest extends TestSpec {

  describe("insert queries") {

    it("insert query") {
      val ecql = s"+insert into member (id, firstName, dob) values (123, 'first', 123456)"
      val cql = "insert into genericTable (key, f1, f2) values ('client1|member|123', 'first', 123456)"
    }
    
  }
}
