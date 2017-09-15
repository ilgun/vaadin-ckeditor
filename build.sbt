name := "vaadin-ckeditor"

version in ThisBuild := "1.0"

organization in ThisBuild := "com.github.ilgun"

crossPaths in ThisBuild := false

autoScalaLibrary in ThisBuild := false

isSnapshot in ThisBuild := false

javacOptions in ThisBuild ++= Seq("-source", "1.8", "-target", "1.8")

sources in doc in Compile := List()

sonatypeProfileName in ThisBuild := "com.github.ilgun"

useGpg in ThisBuild := true

def publishSettings: Seq[Setting[_]] = Seq(
  publishMavenStyle in ThisBuild := true,
  publishArtifact in Test := false,

  publishTo in ThisBuild := {
    val nexus = "https://oss.sonatype.org/"
    if (version.value.trim.endsWith("SNAPSHOT"))
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },

  // Maven central cannot allow other repos.  We're ok here because the artifacts we
  // we use externally are *optional* dependencies.
  pomIncludeRepository in ThisBuild := { x => false }
)

pomExtra in ThisBuild := <url>https://github.com/ilgun/vaadin-ckeditor</url>
  <licenses>
    <license>
      <name>Apache-2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>https://github.com/ilgun/vaadin-ckeditor</url>
    <connection>scm:git@github.com:ilgun/vaadin-ckeditor.git</connection>
  </scm>
  <developers>
    <developer>
      <id>ilgun</id>
      <name>Ilgun Ilgun</name>
      <url>https://github.com/ilgun</url>
    </developer>
  </developers>

val vaadinVersion = "8.1.1"
libraryDependencies ++= Seq(
  "com.vaadin" % "vaadin-server" % vaadinVersion,
  "com.vaadin" % "vaadin-client" % vaadinVersion,

  "com.vaadin" % "vaadin-compatibility-client" % vaadinVersion,
  "com.vaadin" % "vaadin-compatibility-server" % vaadinVersion,

  "com.vaadin" % "vaadin-client-compiler" % vaadinVersion,
  "com.vaadin" % "vaadin-compatibility-client-compiled" % vaadinVersion,

  "com.vaadin" % "vaadin-compatibility-themes" % vaadinVersion
)