package integrationapp;

// @@@SNIPSTART money-transfer-project-template-java-activity-implementation
public class Cafe24ServiceActivityImpl implements Cafe24ServiceActivity {

    @Override
    public void registerPlugin(String appCafe24Id) {

        System.out.printf(
            "\nCafe24 server script installed. AppCafe24Id %s\n",
            appCafe24Id
        );
        // Uncomment the following line to simulate an Activity error.
//         throw new RuntimeException("simulated");
    }

    @Override
    public void deregisterPlugin(String appCafe24Id) {

        System.out.printf(
            "\nCafe24 server script uninstalled. AppCafe24Id %s\n",
            appCafe24Id
        );
    }
}
// @@@SNIPEND
