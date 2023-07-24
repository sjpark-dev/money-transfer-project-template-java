package integrationapp;

// @@@SNIPSTART money-transfer-project-template-java-activity-interface

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface Cafe24ServiceActivity {

    @ActivityMethod
    void registerPlugin(String appCafe24Id);

    @ActivityMethod
    void deregisterPlugin(String appCafe24Id);
}
// @@@SNIPEND
