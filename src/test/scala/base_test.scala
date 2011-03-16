package test

import org.scalatest.{ FlatSpec, BeforeAndAfterEach }
import org.scalatest.matchers.ShouldMatchers
import com.avaje.ebean._, annotation._
import models._

class BaseTest extends FlatSpec with ShouldMatchers with BeforeAndAfterEach {

	System.setProperty("ebean.props.file", "ebean-test.properties")

	override def beforeEach() {
		val tran = Ebean.beginTransaction()
		try {
			val conn = tran.getConnection()
			val sql = "truncate users, questions, articles, answers, comments, tags cascade"
			conn.createStatement().executeUpdate(sql)
			Ebean.commitTransaction()
		} finally {
			Ebean.endTransaction();
		}
		Ebean.getServerCacheManager().clearAll()
	}
}
