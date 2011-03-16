package models

import com.avaje.ebean._
import javax.persistence._, annotation._
import collection.JavaConverters

package object ebean {

	final val ALL = CascadeType.ALL
	final val REMOVE = CascadeType.REMOVE
	final val PERSIST = CascadeType.PERSIST
	final val MERGE = CascadeType.MERGE
	final val REFRESH = CascadeType.REFRESH

	implicit def asScalaBufferConverter[A](l: java.util.List[A]): JavaConverters.AsScala[Buffer[A]] =
		JavaConverters.asScalaBufferConverter[A](l)

	type Date = java.util.Date
	type SDate = java.sql.Date
	type Timestamp = java.sql.Timestamp
	type Buffer[T] = collection.mutable.Buffer[T]

	type Query[T] = com.avaje.ebean.Query[T]

	type Id = javax.persistence.Id
	type Column = javax.persistence.Column
	type OneToMany = javax.persistence.OneToMany
	type ManyToMany = javax.persistence.ManyToMany
	type OneToOne = javax.persistence.OneToOne
	type ManyToOne = javax.persistence.ManyToOne
	type JoinTable = javax.persistence.JoinTable
	type CascadeType = javax.persistence.CascadeType
	type Entity = javax.persistence.Entity
	type Table = javax.persistence.Table
	type Inheritance = javax.persistence.Inheritance
	type DiscriminatorColumn = javax.persistence.DiscriminatorColumn
	type DiscriminatorValue = javax.persistence.DiscriminatorValue

	object InheritanceType {
		final val SINGLE_TABLE = javax.persistence.InheritanceType.SINGLE_TABLE
		final val JOINED = javax.persistence.InheritanceType.JOINED
		final val TABLE_PER_CLASS = javax.persistence.InheritanceType.TABLE_PER_CLASS
	}

	object DiscriminatorType {
		final val CHAR = javax.persistence.DiscriminatorType.CHAR
		final val INTEGER = javax.persistence.DiscriminatorType.INTEGER
		final val STRING = javax.persistence.DiscriminatorType.STRING
	}

	def defaultServer = Ebean.getServer(null)

}

import ebean.defaultServer

package ebean {

	abstract class EbeanEntity {
		def save(): this.type = { defaultServer.save(this); this }
		def delete(): Unit = defaultServer.delete(this);
		def refresh(): this.type = { defaultServer.refresh(this); this }
		def refreshMany(manyPropertyName: String): this.type = { defaultServer.refreshMany(this, manyPropertyName); this }
	}

	abstract class EbeanDao[T] {

		type M[T] = Manifest[T]

		def get(id: Any)(implicit m: M[T]): T = {
			find(id).get
		}

		def find(id: Any)(implicit m: M[T]): Option[T] = {
			defaultServer.find(m.erasure, id) match {
				case x: AnyRef => Some(x.asInstanceOf[T])
				case _ => None
			}
		}

		def find()(implicit m: M[T]): Query[T] = defaultServer.find(m.erasure).asInstanceOf[Query[T]]

	}
}
