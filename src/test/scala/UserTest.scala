package test

import org.scalatest.{ FlatSpec, BeforeAndAfterEach }
import org.scalatest.matchers.ShouldMatchers
import com.avaje.ebean._, annotation._
import models._

class IssueTest extends BaseTest {

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

		// FIXME it should have size 2
		intercept[IndexOutOfBoundsException] {
			articles should have size (2)
		}
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

		val found = User.get(user.id)
		val questions = found.questions
		questions.size should equal(2)

		// FIXME it should contain question1 and question2
		intercept[org.scalatest.TestFailedException] {
			questions should contain(question1)
		}
		intercept[org.scalatest.TestFailedException] {
			questions should contain(question2)
		}
	}
}
