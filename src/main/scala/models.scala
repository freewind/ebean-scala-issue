package models

import com.avaje.ebean._
import javax.persistence._, annotation._
import collection.JavaConverters
import org.apache.commons.lang.RandomStringUtils

@Entity
@Table(name = "users")
class User {

	@Id
	var id: Long = _

	@Column(unique = true)
	var email: String = _

	@OneToMany
	var questions: scala.collection.mutable.Buffer[Question] = _

	@OneToMany
	var articles: java.util.List[Article] = _

}

@Entity
@Table(name = "questions")
class Question {

	@Id
	var id: Long = _

	@Column
	var content: String = _

	@ManyToOne
	var user: User = _

}

@Entity
@Table(name = "articles")
class Article {

	@Id
	var id: Long = _

	@Column
	var content: String = _

	@ManyToOne
	var user: User = _

}
