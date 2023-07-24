package integrationapp;

import io.temporal.activity.ActivityOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import io.temporal.common.RetryOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

// @@@SNIPSTART money-transfer-project-template-java-workflow-implementation
public class AppCafe24RegisterWorkflowImpl implements AppCafe24RegisterWorkflow {
    private static final String WITHDRAW = "Withdraw";
    // RetryOptions specify how to automatically handle retries when Activities fail.
    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(1)
            .build();
    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            // Timeout options specify when to automatically timeout Activities if the process is taking too long.
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            // Optionally provide customized RetryOptions.
            // Temporal retries failures by default, this is simply an example.
            .setRetryOptions(retryoptions)
            .build();
    // ActivityStubs enable calls to methods as if the Activity object is local, but actually perform an RPC.
    private final Map<String, ActivityOptions> perActivityMethodOptions = new HashMap<String, ActivityOptions>(){{
        put(WITHDRAW, ActivityOptions.newBuilder().setHeartbeatTimeout(Duration.ofSeconds(5)).build());
    }};
    private final Cafe24ServiceActivity cafe24Service = Workflow.newActivityStub(Cafe24ServiceActivity.class, defaultActivityOptions, perActivityMethodOptions);
    private final DWServiceActivity dwService = Workflow.newActivityStub(DWServiceActivity.class, defaultActivityOptions, perActivityMethodOptions);
    private final AppCafe24BehaviorActivity appCafe24Behavior = Workflow.newActivityStub(AppCafe24BehaviorActivity.class, defaultActivityOptions, perActivityMethodOptions);

    // The transfer method is the entry point to the Workflow.
    // Activity method executions can be orchestrated here or from within other Activity methods.
    @Override
    public void register(AppCafe24 appCafe24, String pluginId, ManagerAbility who) {
        // Configure SAGA to run compensation activities in parallel
        Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(false).build();
        Saga saga = new Saga(sagaOptions);
        try {
            // Install script to Cafe24
            cafe24Service.registerPlugin(appCafe24.getId());
            saga.addCompensation(cafe24Service::deregisterPlugin, appCafe24.getId());

            // Update channel in DW
            dwService.registerChannel(pluginId);
            saga.addCompensation(dwService::deregisterChannel, pluginId);

            // Update AppCafe24 in AC
            appCafe24Behavior.registerAppCafe24(appCafe24);
            saga.addCompensation(appCafe24Behavior::deregisterAppCafe24, appCafe24);

            // Send log to DW
            try {
                dwService.sendLog(appCafe24, pluginId, who);
            } catch (Exception ignored) {
            }
        } catch (ActivityFailure cause) {
            saga.compensate();
            throw cause;
        }
    }
}
// @@@SNIPEND
