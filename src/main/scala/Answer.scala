package models

import models.ebean._

@Entity
@Table(name = answers)
class Answer extends BaseEntity {

	@Id
	var id: Long = _

	@Column
	var content: String = _

	@ManyToOne
	var question: Question = _

	@ManyToOne
	var user: User = _

	@Column
	var createdAt = new Date

}

object Answer extends EbeanDao[Answer] {

	def apply(content: String, question: Question, user: User): Answer = {
		val answer = new Answer
		answer.content = content
		answer.question = question
		answer.user = user
		answer
	}

}

