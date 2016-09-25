lazy val root = (project in file(".")).
  settings(
    name := "monzo-scala",
    version := "0.1",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "org.scalaj" %% "scalaj-http" % "2.3.0",
      "com.typesafe.play" %% "play-json" % "2.5.8",
      "org.specs2" %% "specs2-core" % "3.8.5" % "test"
    )
  )
