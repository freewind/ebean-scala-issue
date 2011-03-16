package test

import org.scalatest.{ FlatSpec, BeforeAndAfterEach }
import org.scalatest.matchers.ShouldMatchers
import com.avaje.ebean._, annotation._
import models._

class TruncateTest extends BaseTest {

	"truncate" should "clear the tables via Ebean.SqlUpdate" in {
		// insert a user first
		val user = new User
		user.email = "aaa@aaa.com"
		Ebean.save(user)

		// count
		val count = Ebean.find(classOf[User]).findRowCount()
		count should equal(1)

		// truncate
		Ebean.createSqlUpdate("truncate users cascade").execute()

		// count again
		val count2 = Ebean.find(classOf[User]).findRowCount()

		intercept[org.scalatest.TestFailedException] {
			count2 should equal(0) // FIXME
		}
	}

	"truncate" should "clear the tables via jdbc" in {
		// insert a user first
		val user = new User
		user.email = "aaa@aaa.com"
		Ebean.save(user)

		// count
		val count = Ebean.find(classOf[User]).findRowCount()
		count should equal(1)

		// trancate
		val tran = Ebean.beginTransaction()
		val conn = tran.getConnection()
		conn.createStatement().executeUpdate("truncate users cascade");
		tran.commit()

		// count again
		val count2 = Ebean.find(classOf[User]).findRowCount()

		count2 should equal(0)

	}

}
