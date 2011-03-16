package models

import models.ebean._

@Entity
@Table(name = comments)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "comment_for", discriminatorType = DiscriminatorType.STRING)
abstract class Comment extends BaseEntity {

	@Id
	var id: Long = _

	@Column
	var content: String = _

	@ManyToOne
	var user: User = _

	var createdAt = new Date

	var updateAt: Date = _
}

@Entity
@DiscriminatorValue("question")
class QuestionComment extends Comment {

	@ManyToOne
	var quesiton: Question = _

}

object QuestionComment extends EbeanDao[QuestionComment]

@Entity
@DiscriminatorValue("answer")
class AnswerComment extends Comment {

	@ManyToOne
	var answer: Answer = _

}

object AnswerComment extends EbeanDao[AnswerComment]

