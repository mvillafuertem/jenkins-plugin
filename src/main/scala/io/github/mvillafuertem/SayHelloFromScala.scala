package io.github.mvillafuertem

import java.util

import hudson.Extension
import hudson.model.TaskListener
import SayHelloFromScala.Execution
import org.jenkinsci.plugins.workflow.steps._
import org.kohsuke.stapler.DataBoundConstructor

final class SayHelloFromScala @DataBoundConstructor() extends Step {

  override def start(context: StepContext): StepExecution = new Execution(context)

}

object SayHelloFromScala {

  @Extension
  class DescriptorImpl extends StepDescriptor {

    override def getRequiredContext = new util.HashSet(util.Arrays.asList())

    override def getFunctionName: String = "sayHelloFromScala"

    override def getDisplayName: String = "Say Hello From Scala"

  }

  class Execution(context: StepContext) extends SynchronousNonBlockingStepExecution[String](context) {

    @throws[Exception]
    override def run: String = {
      val listener = Execution.this.getContext.get(classOf[TaskListener])
      listener.getLogger.println("Running... ");
      listener.getLogger.println("Step Completed");
      "completed"
    }
  }

}