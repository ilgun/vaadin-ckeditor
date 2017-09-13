import org.vaadin.sbt.VaadinPlugin._

name := "Vaadin CkEditor"

version in ThisBuild := "1.0-SNAPSHOT"

organization in ThisBuild := "com.github.ilgun"

crossPaths in ThisBuild := false

autoScalaLibrary in ThisBuild := false

isSnapshot in ThisBuild := true

javacOptions in ThisBuild ++= Seq("-source", "1.8", "-target", "1.8")
lazy val root = project.in(file(".")).aggregate(addon)

lazy val addon = project.settings(vaadinAddOnSettings: _*).settings(
  name := "vaadin-ckeditor",
  libraryDependencies := Dependencies.addonDeps,
  // Javadoc generation causes problems so disabling it for now
  mappings in packageVaadinDirectoryZip <<= (packageSrc in Compile) map {
    (src) => Seq((src, src.name))
  },
  javaOptions in compileVaadinWidgetsets := Seq("-Xss8M", "-Xmx512M", "-XX:MaxPermSize=512M"),
  vaadinOptions in compileVaadinWidgetsets := Seq("-strict", "-draftCompile"),
  skip in compileVaadinWidgetsets in resourceGenerators := true,
  sources in doc in Compile := List()
)

homepage := Some(url("https://github.com/ilgun/vaadin-ckeditor"))
scmInfo := Some(ScmInfo(url("https://github.com/ilgun/vaadin-ckeditor"),
  "scm:git@github.com:ilgun/vaadin-ckeditor.git"))
developers := List(Developer("ilgun", "Ilgun Ilgun", "ilgun.ilgun@gmail.com",
  url("https://github.com/ilgun")))
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
pomIncludeRepository := (_ => false)

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

useGpg in ThisBuild := true
pgpReadOnly in ThisBuild := false
