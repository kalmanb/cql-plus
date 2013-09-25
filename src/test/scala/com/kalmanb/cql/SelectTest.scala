package com.kalmanb.cql

import com.kalmanb.test.TestSpec
import CqlType._

class SelectTest extends TestSpec {

  describe("describe") {
    /**
     * Memeber [
     *   id : Long
     *   firstName : String
     *   dob : Long
     * ]
     */
    val memberTable = TableMeta(
      name = "member",
      id = Seq(Field("id", long, 0)),
      fields = Seq(
        Field("firstName", text, 1),
        Field("dob", long, 2)),
      sourceTable = "genericTable")

    val personTable = TableMeta(
      name = "person",
      id = Seq(Field("id", long, 0)),
      fields = Seq(
        Field("firstName", text, 1),
        Field("dob", long, 2)),
      sourceTable = "genericTable")

    it("should return cql if not prepended by a plus sign") {
      val cql = "select key, f1, f2 from genericTable"
      Parser.get(cql, memberTable) should be(cql)
    }

    it("simple select query") {
      val cqlp = "+select * from member where id = 123"
      val cql = "select key,f1,f2 from genericTable where key = 'client1|member|123'"
      Parser.get(cqlp, memberTable).toLowerCase should be(cql.toLowerCase)
    }

    it("simple select with updated where clause") {
      val cqlp = "+select * from member where id = 1234"
      val cql = "select key,f1,f2 from genericTable where key = 'client1|member|1234'"
      Parser.get(cqlp, memberTable).toLowerCase should be(cql.toLowerCase)
    }

    it("should support selecting some columns") {
      val cqlp = "+select firstName, dob from member where id = 1234"
      val cql = "select f1,f2 from genericTable where key = 'client1|member|1234'"
      Parser.get(cqlp, memberTable).toLowerCase should be(cql.toLowerCase)
    }

    it("should support selecting some columns including an id field") {
      val cqlp = "+select id, dob from member where id = 1234"
      val cql = "select key,f2 from genericTable where key = 'client1|member|1234'"
      Parser.get(cqlp, memberTable).toLowerCase should be(cql.toLowerCase)
    }

    it("simple select should support configured tables") {
      val cqlp = "+select * from person where id = 1234"
      val cql = "select key,f1,f2 from genericTable where key = 'client1|person|1234'"
      Parser.get(cqlp, personTable).toLowerCase should be(cql.toLowerCase)
    }
  }

  object Parser {
    def get(cql: String, table: TableMeta): String = {
      def select(cql: String): String = {
        val splitOnWhere = cql.split(" from ")
        val fieldClause = splitOnWhere(0).split(',').map(_.trim)
        val whereClause = splitOnWhere(1)

        val fields = if (cql.startsWith("*"))
          ("key" +: table.fields.map (field ⇒ "f" + field.column)).mkString(",")
        else
          getSpecifiedFields(fieldClause, table).mkString(",")

        val rowKey = whereClause.split("= ")(1)
        s"SELECT $fields FROM ${table.sourceTable} where key = 'client1|${table.name}|$rowKey'"
      }
      if (cql.startsWith("+select"))
        select(cql.drop(8))
      else
        cql
    }

    def getSpecifiedFields(fields: Seq[String], table: TableMeta):Seq[String] = {
      case class Result(keys: Seq[String], fields: Seq[String])
       val lookup = table.fields.map(f => (f.name, f)).toMap
       val idFields = if (fields.contains(table.id(0).name)) 
        Result(Seq("key"), fields diff table.id.map(_.name)) 
       else 
         Result(Seq.empty, fields)
       
       idFields.keys ++ idFields.fields.map(f => "f" + lookup(f).column)
    }
  }
}
