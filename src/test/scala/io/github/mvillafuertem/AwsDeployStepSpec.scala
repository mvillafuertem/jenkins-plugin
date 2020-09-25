package io.github.mvillafuertem

import org.junit.runner.RunWith
import org.jvnet.hudson.test.RestartableJenkinsRule
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class AwsDeployStepSpec extends AnyFlatSpecLike with  Matchers {

  behavior of ""

  it should "" in {
    new RestartableJenkinsRule();

  }

}
