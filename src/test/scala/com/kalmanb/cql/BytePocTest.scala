package com.kalmanb.cql

import com.kalmanb.test.TestSpec
import com.datastax.driver.core._
import scala.collection.JavaConversions._

class BytePocTest extends TestSpec {

  describe("getting data from cassandra") {

    it("should get simple cql") {
      val cluster = Cluster.builder.addContactPoints("localhost").build
      val meta = cluster.getMetadata
      val sess = cluster.connect

      sess.execute(s"create keyspace if not exists kal with replication = {'class':'SimpleStrategy', 'replication_factor':1};")

      sess.execute(s"""create table if not exists kal.one (
         id blob,
         f1 blob,
         f2 blob,
         primary key (id, f1)
       )
        """)

      sess.execute("insert into kal.one (id, f1, f2) values (textAsBlob('1'), textAsBlob('a'), textAsBlob('aa'));")

      val res: ResultSet = sess.execute(s"select blobAsText(f2) from kal.one")

      res.all.foreach(println)

      cluster.shutdown
    }
  }
}
