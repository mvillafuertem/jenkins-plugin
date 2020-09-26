<p align="center"><img width="200" src="https://raw.githubusercontent.com/mvillafuertem/jenkins-plugin/master/jenkins-icon.svg"/></p>
<h1 align="center">Jenkins Plugin</h1>
<p align="center">
  <a href="https://github.com/scala/scala/releases">
    <img src="https://img.shields.io/badge/scala-2.13.3-red.svg?logo=scala&logoColor=red"/>
  </a>  
  <a href="https://www.oracle.com/technetwork/java/javase/11all-relnotes-5013287.html">
    <img src="https://img.shields.io/badge/jdk-11.0.8-orange.svg?logo=java&logoColor=white"/>
  </a>
  <a href="https://github.com/mvillafuertem/jenkins-plugin/actions?query=workflow%3A%22scalaci%22">
    <img src="https://github.com/mvillafuertem/jenkins-plugin/workflows/scalaci/badge.svg"/>
  </a>
</p> 

****

The Project "jenkins-plugin" offers...

****

```shell script

./gradlew server

```

## Simple Pipeline

```groovy

pipeline {
    agent any

    stages {
        stage('Hello Java') {
            steps {
                sayHelloFromJava()
            }
        }
        stage('Hello Scala') {
            steps {
                sayHelloFromScala()
            }
        }
    }
}

```
