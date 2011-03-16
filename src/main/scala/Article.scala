package models

import models.ebean._

@Entity
@Table(name = articles)
class Article extends BaseEntity {

	@Id
	var id: Long = _

	@Column
	var content: String = _

	@ManyToOne
	var user: User = _

}
