import bintray._

lazy val root = (project in file("."))
  .settings(
    name := "svalidate",
    organization := "svalidate",
    libraryDependencies ++= Seq(
      "org.specs2"        %%  "specs2-core"       % "3.7"    % "test",
      "org.specs2"        %%  "specs2-mock"       % "3.7"    % "test"
    ),
    publishMavenStyle := false,
    bintrayRepository := "sbt-plugins",
    bintrayOrganization := None,
    licenses += ("Apache-2.0", url("http://opensource.org/licenses/Apache-2.0"))
  )
//  .settings(site.addMappingsToSiteDir(tut, "tut"))
  .settings(tutSettings)
  .settings(
    tutTargetDirectory := file(".") / "docs"
  )

// enablePlugins(GitBookPlugin)

