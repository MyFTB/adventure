plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
}

dependencies {
  val indraVersion = "2.1.0-SNAPSHOT"
  implementation("net.kyori", "indra-common", indraVersion)
  implementation("net.kyori", "indra-publishing-sonatype", indraVersion)
  implementation("net.kyori", "indra-crossdoc", indraVersion)
  implementation("com.adarshr", "gradle-test-logger-plugin", "3.1.0")
  implementation("me.champeau.jmh", "jmh-gradle-plugin", "0.6.6")
  implementation("com.diffplug.gradle", "goomph", "3.34.0")
}
