import bintray._

lazy val root = (project in file("."))
  .settings(
    name := "svalidate",
    organization := "svalidate",
//     javacOptions ++= Seq("-source", "1.8"),
    libraryDependencies ++= Seq(
      "org.specs2"        %%  "specs2-core"       % "3.8.6" % "test",
      "org.specs2"        %%  "specs2-mock"       % "3.8.6" % "test",
      "com.novocode"      %   "junit-interface"   % "0.11"  % "test->default"
    ),
    publishMavenStyle := true,
    bintrayRepository := "maven",
    bintrayOrganization := None,
    licenses += ("Apache-2.0", url("http://opensource.org/licenses/Apache-2.0")),
    testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")
  )
  .settings(tutSettings)
  .settings(
    tutTargetDirectory := file(".") / "docs"
  )

