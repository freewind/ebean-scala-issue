import models.ebean._

package object models {

	/** table names */
	final val users = "users"
	final val questions = "questions"
	final val answers = "answers"
	final val tags = "tags"
	final val question_tag_r = "question_tag_r"
	final val comments = "comments"
	final val articles = "articles"

	type JList[T] = java.util.List[T]

}

package models {

	abstract class BaseEntity extends EbeanEntity {
		def id: Any

		override def hashCode(): Int = {
			val prime = 31
			val result = 1
			prime * result + (if (id == null) 0 else id.hashCode())
		}

		override def equals(obj: Any): Boolean = {
			obj match {
				case other: BaseEntity => getClass == other.getClass && id == other.id
				case _ => false
			}
		}

		override def toString = this.getClass.getSimpleName+"<"+id+">"
	}
}

