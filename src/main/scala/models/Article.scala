package models

import com.avaje.ebean._
import javax.persistence._, annotation._
import collection.JavaConverters

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
