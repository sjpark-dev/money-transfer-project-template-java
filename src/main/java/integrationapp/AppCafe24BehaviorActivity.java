package integrationapp;

// @@@SNIPSTART money-transfer-project-template-java-activity-interface

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface AppCafe24BehaviorActivity {

    @ActivityMethod
    void registerAppCafe24(AppCafe24 appCafe24);

    @ActivityMethod
    void deregisterAppCafe24(AppCafe24 appCafe24);
}
// @@@SNIPEND
