organization := "com.kalmanb"

name := "cql-plus"

libraryDependencies ++= Seq(
  //"com.datastax.cassandra" % "cassandra-driver-core" % "2.0.0-beta2" excludeAll (
  "com.datastax.cassandra" % "cassandra-driver-core" % "1.0.4" excludeAll (
    ExclusionRule(organization = "javax.servlet"),
    ExclusionRule(organization = "org.slf4j")
    //ExclusionRule(organization = "org.apache.cassandra"),
    //ExclusionRule(organization = "io.netty")),
    ),
  "org.slf4j" % "slf4j-api" % "1.7.2",
  "org.scalatest" %% "scalatest" % "2.0.M6-SNAP33" % "test",
  "junit" % "junit" % "4.11" % "test",
  "org.mockito" % "mockito-all" % "1.9.5" % "test" 
)




