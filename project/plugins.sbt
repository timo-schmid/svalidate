resolvers += Resolver.url("tpolecat-sbt-plugin-releases", url("http://dl.bintray.com/content/tpolecat/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

addSbtPlugin("org.tpolecat" % "tut-plugin" % "0.4.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "0.8.1")

