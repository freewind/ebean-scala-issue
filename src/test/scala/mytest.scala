package test

import org.scalatest.{ FlatSpec, BeforeAndAfterEach }
import org.scalatest.matchers.ShouldMatchers
import com.avaje.ebean._, annotation._
import models._

class IssueTest extends FlatSpec with ShouldMatchers with BeforeAndAfterEach {

	System.setProperty("ebean.props.file", "ebean-test.properties")

	override def beforeEach() {
		val tran = Ebean.beginTransaction()
		try {
			val conn = tran.getConnection()
			val sql = "truncate users, questions, articles cascade"
			conn.createStatement().executeUpdate(sql)
			Ebean.commitTransaction()
		} finally {
			Ebean.endTransaction();
		}
		Ebean.getServerCacheManager().clearAll()
	}

	"user" should "be inserted correctly" in {
		val user = new User
		user.email = "aaa@aaa.com"
		Ebean.save(user)

		val found = Ebean.find(classOf[User], user.id)
		found should not be null
		found.email should equal("aaa@aaa.com")
	}

	"user" should "be able to have some articles" in {
		val user = new User
		user.email = "aaa@aaa.com"
		Ebean.save(user)

		val article1 = new Article
		article1.user = user
		Ebean.save(article1)

		val article2 = new Article
		article2.user = user
		Ebean.save(article2)

		val found = Ebean.find(classOf[User], user.id)
		val articles = found.articles
		articles should have size (2)
	}

	"user" should "be able to have some questions" in {
		val user = new User
		user.email = "aaa@aaa.com"
		Ebean.save(user)

		val question1 = new Question
		question1.user = user
		Ebean.save(question1)

		val question2 = new Question
		question2.user = user
		Ebean.save(question2)

		val found = Ebean.find(classOf[User], user.id)
		val questions = found.questions
		intercept[IndexOutOfBoundsException] {
			questions.size should equal(2)
		}
	}
}
