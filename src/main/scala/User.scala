package models

import models.ebean._
import collection.mutable.Buffer

@Entity
@Table(name = users)
class User extends BaseEntity {

	@Id
	var id: Long = _

	@Column(unique = true)
	var email: String = _

	@Column
	var name: String = _

	@Column
	var salt: String = _

	@Column
	var password: String = _

	@Column
	var createdAt = new Date

	@OneToMany
	var questions: JList[Question] = _

	@OneToMany
	var articles: Buffer[Question] = _

	@OneToMany
	var answers: JList[Answer] = _

	@OneToMany
	var tags: JList[Tag] = _

}

object User extends EbeanDao[User] {

	def apply(email: String, password: String, name: String): User = {
		val user = new User
		user.email = email
		user.name = name
		user.password = password
		user
	}

	def getByEmail(email: String): User = find().where().eq("email", email).findUnique()

}

