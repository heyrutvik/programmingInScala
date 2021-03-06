name := "programmingInScala"
version := "0.0.1"

inThisBuild(Seq(
  scalaOrganization := "org.typelevel",
  scalaVersion      := "2.12.4-bin-typelevel-4"
))

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6",
  "com.chuusai" %% "shapeless" % "2.3.3"
)

scalacOptions += "-Yliteral-types"