package com.kalmanb.cql

import com.kalmanb.test.TestSpec

class TableConfigTest extends TestSpec {

  describe("describe") {
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

    it("should") {
      /* cursor */
    }
  }
}
