plugins {
    scala
    groovy
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
    scalaCompileOptions.additionalParameters = listOf("-Yresolve-term-conflict:object") // https://stackoverflow.com/questions/8984730/package-contains-object-and-package-with-same-name
}


sourceSets.main {
    output.classesDirs.plus(File(buildDir, "classes/main"))
}

sourceSets.test {
    output.classesDirs.plus(File(buildDir, "classes/main"))
}

tasks.compileScala {
    dependsOn(tasks.compileGroovy)
}

tasks.compileTestScala {
    dependsOn(tasks.compileTestGroovy)
}

dependencies {
    implementation("org.codehaus.groovy:groovy-all:3.0.5")
    implementation("org.scala-lang:scala-library:2.13.3")

    //implementation("software.amazon.awssdk:aws-sdk-java:2.14.25")

    implementation(platform("org.jenkins-ci.plugins:plugin:4.7"))
    implementation("org.jenkins-ci.main:jenkins-core")
    implementation("org.jenkins-ci.plugins.workflow:workflow-step-api:2.14")

    testImplementation("org.jenkins-ci.main:jenkins-test-harness")
    testImplementation("org.kohsuke:access-modifier-annotation")
    testImplementation("net.jcip:jcip-annotations")
    testImplementation("com.github.spotbugs:spotbugs-annotations")
    testImplementation("commons-logging:commons-logging")

//    implementation("org.jenkins-ci.plugins:credentials:2.1.5@jar")
//    implementation("org.jenkins-ci.plugins:aws-credentials:1.23@jar")
//    implementation("org.jenkins-ci.main:jenkins-core:2.258")

    testImplementation("org.jenkins-ci.main:jenkins-test-harness")
//    testImplementation("org.jenkins-ci.plugins.workflow:workflow-api:2.26")
//    testImplementation("org.jenkins-ci.plugins.workflow:workflow-basic-steps:2.6")
//    testImplementation("org.jenkins-ci.plugins.workflow:workflow-cps:2.45")
//    testImplementation("org.jenkins-ci.plugins.workflow:workflow-durable-task-step:2.19")
//    testImplementation("org.jenkins-ci.plugins.workflow:workflow-job:2.11.2")
//    testImplementation("org.jenkins-ci.plugins.workflow:workflow-support:2.17")
    //testImplementation("org.jenkins-ci.plugins.plugins:cloudbees-folder:6.3")
    testImplementation("junit:junit:4.13")
    testImplementation("org.scalatest:scalatest-flatspec_2.13:3.2.2")
    testImplementation("org.scalatest:scalatest-shouldmatchers_2.13:3.2.2")
    testImplementation("org.scalatestplus:junit-4-13_2.13:3.2.2.0")
    testImplementation("com.lesfurets:jenkins-pipeline-unit:1.7")
}

tasks.withType<Test> {
    //useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        //showStandardStreams = true
    }
}

tasks.withType<JacocoReport> {
//    classDirectories.setFrom(
//            sourceSets.main.get().output.asFileTree.matching {
//                exclude("org/example/B.class")
//            }
//    )
    reports {
        xml.isEnabled = true
        html.isEnabled = true
        //html.destination file("${buildDir}/jacocoHtml")
    }
}

// ./gradlew wrapper --gradle-version=6.6.1 --distribution-type=bin
tasks.withType<Wrapper> {
    gradleVersion = "6.6.1"
    distributionType = Wrapper.DistributionType.BIN
}
