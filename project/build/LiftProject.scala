import sbt._

class LiftProject(info: ProjectInfo) extends DefaultWebProject(info) {
  val liftVersion = property[Version]

  lazy val JavaNet = "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

  override def libraryDependencies = Set(
    "net.liftweb" %% "lift-webkit" % liftVersion.value.toString % "compile" withSources,
    "net.liftweb" %% "lift-mapper" % liftVersion.value.toString % "compile" withSources,
    "net.liftweb" %% "lift-db" % liftVersion.value.toString % "compile" withSources,
    "net.liftweb" %% "lift-actor" % liftVersion.value.toString % "compile" withSources,
    "net.liftweb" %% "lift-common" % liftVersion.value.toString % "compile" withSources,
    "net.liftweb" %% "lift-util" % liftVersion.value.toString % "compile" withSources,
    "net.liftweb" %% "lift-wizard" % liftVersion.value.toString % "compile" withSources,
    "org.mortbay.jetty" % "jetty" % "6.1.26" % "test",
    "junit" % "junit" % "4.7" % "test",
    "ch.qos.logback" % "logback-classic" % "0.9.26",
    "org.scala-tools.testing" %% "specs" % "1.6.6" % "test",
    "com.h2database" % "h2" % "1.2.147"
  ) ++ super.libraryDependencies
}
