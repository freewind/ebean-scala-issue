package models

import models.ebean._

@Entity
@Table(name = tags)
class Tag extends BaseEntity {

	@Id
	var id: Long = _

	@Column(length = 30)
	var name: String = _

	@Column
	var createdAt: Date = new Date

	@Column
	var updatedAt: Date = _

	@ManyToOne
	var user: User = _

	@ManyToMany(cascade = Array[CascadeType](ALL))
	@JoinTable(name = question_tag_r)
	var questions: JList[Question] = _

}

object Tag extends EbeanDao[Tag] {

	def apply(name: String, user: User): Tag = {
		val tag = new Tag
		tag.name = name
		tag.user = user
		tag
	}

	def getByName(tagName: String): Tag = find().where().eq("name", tagName).findUnique()
}

