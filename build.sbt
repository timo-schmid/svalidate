
lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      "org.specs2"        %%  "specs2-core"       % "3.7"    % "test",
      "org.specs2"        %%  "specs2-mock"       % "3.7"    % "test"
    )
  )
//  .settings(site.addMappingsToSiteDir(tut, "tut"))
  .settings(tutSettings)

// enablePlugins(GitBookPlugin)

