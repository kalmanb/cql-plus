package com.kalmanb.cql

sealed trait CqlType
object CqlType {
  case object blob extends CqlType // bytes
  case object text extends CqlType
  case object long extends CqlType
  case object int extends CqlType
}

case class Field(
  name: String,
  cqlType: CqlType,
  column: Int)

case class TableMeta(
  name: String,
  id: Seq[Field],
  fields: Seq[Field],
  sourceTable: String)

