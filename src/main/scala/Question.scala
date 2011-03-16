package models

import models.ebean._

@Entity
@Table(name = questions)
class Question extends BaseEntity {

	@Id
	var id: Long = _

	@Column
	var title: String = _

	@Column
	var content: String = _

	@ManyToOne
	var user: User = _

	@OneToMany
	var answers: JList[Answer] = _

	@ManyToMany(cascade = Array[CascadeType](ALL))
	@JoinTable(name = question_tag_r)
	var tags: JList[Tag] = _

	@Column
	var createdAt = new Date

}

object Question extends EbeanDao[Question] {

	def apply(title: String, content: String, user: User): Question = {
		val question = new Question
		question.title = title
		question.content = content
		question.user = user
		question
	}

}
