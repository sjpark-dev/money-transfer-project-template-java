package integrationapp;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

// @@@SNIPSTART money-transfer-project-template-java-workflow-interface
@WorkflowInterface
public interface AppCafe24RegisterWorkflow {

    // The Workflow method is called by the initiator either via code or CLI.
    @WorkflowMethod
    void register(AppCafe24 appCafe24, String pluginId, ManagerAbility who);
}
// @@@SNIPEND
