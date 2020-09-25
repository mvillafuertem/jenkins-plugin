package io.github.mvillafuertem.aws

import _root_.java.beans.Introspector
import _root_.java.util

import _root_.javax.annotation.Nonnull
import _root_.org.jenkinsci.plugins.workflow.steps.{Step, StepContext, StepDescriptor, StepExecution}

final class AwsDeployStep extends Step {

  override def start(context: StepContext): StepExecution = new Execution(this, context)

  private val name: String = Introspector.decapitalize(
    this.getClass
      .getSimpleName
      .replaceAll("Step", ""
      )
  )

  class DescriptorImpl extends StepDescriptor {

    override def getRequiredContext = new util.HashSet(util.Arrays.asList())

    override def getFunctionName: String = name

    override def getDisplayName: String = "Aws Deploy"

  }

  @SerialVersionUID(1L)
  class Execution(step: AwsDeployStep, context: StepContext) extends StepExecution(context) {

    @throws[Exception]
    def start: Boolean = {

      new Thread(name) {
        override def run(): Unit = {
          try {
            //val listener: TaskListener = Execution.this.getContext.get(classOf[TaskListener])
            //listener.getLogger.println("Deploying...")
            //listener.getLogger.println("Delete complete")
            Execution.this.getContext.onSuccess(null)
          } catch {
            case e: Exception => Execution.this.getContext.onFailure(e)
          }
        }
      }.start()

      false
    }

    @throws[Exception]
    override def stop(@Nonnull cause: Throwable): Unit = {
      //
    }
  }


}