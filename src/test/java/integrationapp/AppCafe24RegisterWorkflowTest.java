package integrationapp;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.worker.Worker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppCafe24RegisterWorkflowTest {

    private TestWorkflowEnvironment testEnv;
    private Worker worker;
    private WorkflowClient workflowClient;

    @Before
    public void setUp() {
        testEnv = TestWorkflowEnvironment.newInstance();
        worker = testEnv.newWorker(Shared.APP_COMMERCE_TASK_QUEUE);
        worker.registerWorkflowImplementationTypes(AppCafe24RegisterWorkflowImpl.class);
        workflowClient = testEnv.getWorkflowClient();
    }

    @After
    public void tearDown() {
        testEnv.close();
    }

    @Test
    public void register() {
        Cafe24ServiceActivity cafe24ServiceActivity = mock(Cafe24ServiceActivityImpl.class);
        DWServiceActivity dwServiceActivity = mock(DWServiceActivityImpl.class);
        AppCafe24BehaviorActivity appCafe24BehaviorActivity= mock(AppCafe24BehaviorActivityImpl.class);
        worker.registerActivitiesImplementations(cafe24ServiceActivity, dwServiceActivity, appCafe24BehaviorActivity);
        testEnv.start();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(Shared.APP_COMMERCE_TASK_QUEUE)
                .build();
        AppCafe24RegisterWorkflow workflow = workflowClient.newWorkflowStub(
            AppCafe24RegisterWorkflow.class, options);
        AppCafe24 appCafe24 = new AppCafe24("123");
        String pluginId = "111";
        ManagerAbility who = new ManagerAbility("10");
        workflow.register(appCafe24, pluginId, who);
        verify(cafe24ServiceActivity).registerPlugin(appCafe24.getId());
        verify(dwServiceActivity).registerChannel(pluginId);
        verify(appCafe24BehaviorActivity).registerAppCafe24(appCafe24);
        verify(dwServiceActivity).sendLog(appCafe24, pluginId, who);
    }
}
