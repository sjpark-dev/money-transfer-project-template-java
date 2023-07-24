package integrationapp;

// @@@SNIPSTART money-transfer-project-template-java-activity-implementation
public class AppCafe24BehaviorActivityImpl implements AppCafe24BehaviorActivity {

    @Override
    public void registerAppCafe24(AppCafe24 appCafe24) {

        System.out.printf(
            "\nAppCafe24 registered. AppCafe24Id %s\n",
            appCafe24.getId()
        );
        // Uncomment the following line to simulate an Activity error.
         throw new RuntimeException("simulated");
    }

    @Override
    public void deregisterAppCafe24(AppCafe24 appCafe24) {

        System.out.printf(
            "\nAppCafe24 deregistered. AppCafe24Id %s\n",
            appCafe24.getId()
        );
    }
}
// @@@SNIPEND
