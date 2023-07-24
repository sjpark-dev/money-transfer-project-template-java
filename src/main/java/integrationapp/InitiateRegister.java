package integrationapp;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.util.UUID;

// @@@SNIPSTART money-transfer-project-template-java-workflow-initiator
public class InitiateRegister {

    public static void main(String[] args) throws Exception {

        // WorkflowServiceStubs is a gRPC stubs wrapper that talks to the local Docker instance of the Temporal server.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(Shared.APP_COMMERCE_TASK_QUEUE)
                // A WorkflowId prevents this it from having duplicate instances, remove it to duplicate.
                .setWorkflowId("register-workflow")
                .build();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        AppCafe24RegisterWorkflow workflow = client.newWorkflowStub(AppCafe24RegisterWorkflow.class, options);
        AppCafe24 appCafe24 = new AppCafe24("123");
        String pluginId = "111";
        ManagerAbility managerAbility = new ManagerAbility("10");
        // Asynchronous execution. This process will exit after making this call.
        WorkflowExecution we = WorkflowClient.start(workflow::register, appCafe24, "111", managerAbility);
        System.out.println("Workflow complete.");
        System.exit(0);
    }
}
// @@@SNIPEND
