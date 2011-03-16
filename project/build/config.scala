import sbt._

class EbeanIssueProject(info: ProjectInfo) extends DefaultProject(info) {
	val slf4j_api = "org.slf4j" % "slf4j-api" % "1.6.1" % "compile->default"
	val slf4j_nop = "org.slf4j" % "slf4j-nop" % "1.6.1" % "compile->default"

	val commons_lang = "commons-lang" % "commons-lang" % "2.6" % "compile->default"
	val commons_io = "commons-io" % "commons-io" % "2.0.1" % "compile->default"
	// "commons-collections" % "commons-collections" % "3.2.1" % "compile->default",

	val cglib = "cglib" % "cglib-nodep" % "2.2"
	val ebean = "org.avaje" % "ebean" % "2.7.2" % "compile"

	val postgresql = "postgresql" % "postgresql" % "9.0-801.jdbc4" % "compile->default"

	val jetty_compile = "org.mortbay.jetty" % "jetty" % "6.1.25" % "compile->default" // 用于编译
	val jetty_test = "org.mortbay.jetty" % "jetty" % "6.1.25" % "test->default" // 用于sbt的jetty-run
	val scalatest = "org.scalatest" % "scalatest" % "1.2" % "test->default"
	val junit = "junit" % "junit" % "4.5" % "test->default"

}
