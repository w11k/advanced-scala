import sbt._

class Project(info: ProjectInfo) extends DefaultProject(info) with AutoCompilerPlugins {

  // Repositories
  val scalaz51ModuleConfig = ModuleConfiguration("com.googlecode.scalaz", ScalaToolsSnapshots) // For scalaz 5.1-SNAPSHOT needed by specs2

  // Compile dependencies
//  val scalaz = "org.scalaz" %% "scalaz-core" % "6.0-SNAPSHOT" withSources

  // Test dependencies
  val specs2 = "org.specs2" %% "specs2" % "1.1" % "test" withSources

  // Continuations
  val cont = compilerPlugin("org.scala-lang.plugins" % "continuations" % buildScalaVersion)
  override def compileOptions = super.compileOptions ++ compileOptions("-P:continuations:enable")
}
