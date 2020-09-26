package io.github.mvillafuertem;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.workflow.steps.*;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SayHelloFromJava extends Step {

    @DataBoundConstructor
    public SayHelloFromJava() {
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new Execution(context);
    }

    @Extension
    public static class DescriptorImpl extends StepDescriptor {

        @Override
        public Set<? extends Class<?>> getRequiredContext() {
            return new HashSet<>(Arrays.asList(EnvVars.class, TaskListener.class));
        }

        @Override
        public String getFunctionName() {
            return "sayHelloFromJava";
        }

        @Override
        @Nonnull
        public String getDisplayName() {
            return "Say Hello From Java";
        }
    }


    public static class Execution extends SynchronousNonBlockingStepExecution<String> {


        public Execution(StepContext context) {
            super(context);
        }

        @Override
        protected String run() throws Exception {
            TaskListener listener = Execution.this.getContext().get(TaskListener.class);
            listener.getLogger().println("Running... ");
            listener.getLogger().println("Step Completed");
            return "completed";
        }
    }


}
