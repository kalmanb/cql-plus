package com.kalmanb.cql

import com.kalmanb.test.TestSpec

class ParserTest extends TestSpec {

  describe("cql create table") {
    val clientId = "client1"

    val memberTableYml = """
    tableName:       member
    underlyingTable: genericTable
    idFields:
      - id
         type: long
    sortFields:
    fields:
      - firstName
         type:        text
         fieldNumber: 1
      - dob
         type:        long
         fieldNumber: 2
    """
    case class Table(
      tableName: String)



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

  object CqlParser {
    def parse(ecql: String): String = {
      ecql
    }
  }
}
