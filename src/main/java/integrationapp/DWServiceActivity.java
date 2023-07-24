package integrationapp;

// @@@SNIPSTART money-transfer-project-template-java-activity-interface

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface DWServiceActivity {

    @ActivityMethod
    void registerChannel(String pluginId);

    @ActivityMethod
    void deregisterChannel(String pluginId);

    @ActivityMethod
    void sendLog(AppCafe24 appCafe24, String pluginId, ManagerAbility who);
}
// @@@SNIPEND
