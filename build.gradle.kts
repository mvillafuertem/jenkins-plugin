plugins {
    scala
    jacoco
    idea
    id("org.jenkins-ci.jpi").version("0.39.0")
}

repositories {
    maven { url = uri("https://repo.jenkins-ci.org/releases/") }
    maven { url = uri("https://repo.jenkins-ci.org/public/") }
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<ScalaCompile> {
    dependsOn(tasks.compileGroovy)
    dependsOn("localizer")

    scalaCompileOptions.additionalParameters = listOf("-Yresolve-term-conflict:object") // https://stackoverflow.com/questions/8984730/package-contains-object-and-package-with-same-name
}

sourceSets.main {
    output.classesDirs.plus(File(buildDir, "classes/main"))
}

sourceSets.test {
    output.classesDirs.plus(File(buildDir, "classes/main"))
}

jenkinsPlugin {
    coreVersion = "2.257"                                               // Version of Jenkins core this plugin depends on.
    displayName = "Hello World plugin built with Scala"                 // Human-readable name of plugin.
    url = "http://wiki.jenkins-ci.org/display/JENKINS/scala-hello-world-plugin"   // URL for plugin on Jenkins wiki or elsewhere.
    gitHubUrl = "https://wiki.jenkins-ci.org/display/JENKINS/Scala+Hello+World+Plugin" // Plugin URL on GitHub. Optional.
    shortName = "scala-hello-world"                                   // Plugin ID, defaults to the project name without trailing '-plugin'
    fileExtension = "jpi"
    pluginFirstClassLoader = true
}

dependencies {
    implementation("org.scala-lang:scala-library:2.13.3")

    implementation("org.jenkins-ci.plugins.workflow:workflow-step-api:2.22@jar")
    implementation("org.jenkins-ci.plugins:structs:1.14@jar")

    // SezPoz is used to process @hudson.Extension and other annotations
    implementation("net.java.sezpoz:sezpoz:1.13")


    testImplementation("org.jenkins-ci.plugins.workflow:workflow-cps:2.83")
    testImplementation("org.jenkins-ci.plugins.workflow:workflow-job:2.40")
    testImplementation("org.jenkins-ci.plugins.workflow:workflow-basic-steps:2.21")
    testImplementation("org.jenkins-ci.plugins.workflow:workflow-durable-task-step:2.36")
    testImplementation("org.jenkins-ci.plugins.workflow:workflow-support")

    testImplementation("junit:junit:4.13")
    testImplementation("org.scalatest:scalatest-flatspec_2.13:3.2.2")
    testImplementation("org.scalatest:scalatest-shouldmatchers_2.13:3.2.2")
    testImplementation("org.scalatestplus:junit-4-13_2.13:3.2.2.0")
    testImplementation("com.lesfurets:jenkins-pipeline-unit:1.7")
}


tasks.withType<Test> {
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

tasks.withType<JacocoReport> {
    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}

// ./gradlew wrapper --gradle-version=6.6.1 --distribution-type=bin
tasks.withType<Wrapper> {
    gradleVersion = "6.6.1"
    distributionType = Wrapper.DistributionType.BIN
}


//tasks.register<Exec>("annotate") {
//
//    doFirst{
//        args("-proc:only")
//        args("-d")
//        args(tasks.compileJava.get().destinationDir)
//        args( "-classpath")
//        args(tasks.compileJava.get().classpath.getAsPath() + "/" + tasks.compileJava.get().destinationDir)
//
//        fileTree(tasks.compileScala.get()).forEach {
//            if(it.path.contains("META-INF")) {
//                return@forEach
//            }
//            if(!it.path.endsWith("class")) {
//                return@forEach
//            }
//            args((it.path.getfile - tasks.compileScala.get().destinationDir - (".class").substring(1)).replaceAll("/",'.'))
//        }
//
//    }
//
//    executable("javac")
//}
//
//tasks.compileJava.get().doLast {tasks.named<Exec>("annotate").exec()}