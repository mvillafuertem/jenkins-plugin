package io.github.mvillafuertem

import org.junit.runner.RunWith
import org.jvnet.hudson.test.RestartableJenkinsRule
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner
import org.junit.Rule
import org.jvnet.hudson.test.JenkinsRule
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition
import org.jenkinsci.plugins.workflow.job.WorkflowRun
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.job.WorkflowRun

@RunWith(classOf[JUnitRunner])
final class SayHelloFromJavaSpec extends AnyFlatSpecLike with Matchers {

  @Rule
  val j = new JenkinsRule

  behavior of ""

  it should "test" in {
    //val p = j.jenkins.createProject(classOf[WorkflowJob], "p")
    //p.setDefinition(new CpsFlowDefinition("node {\n" + "echo 'First message'\n" + "syncnonblocking 'wait'\n" + "echo 'Second message'\n" + "}", true))
    //val b = p.scheduleBuild2(0).getStartCondition.get
    // Wait for syncnonblocking to be started
    System.out.println("Waiting to syncnonblocking to start...");
    //SynchronousNonBlockingStep.waitForStart("wait", b);

  }

}
