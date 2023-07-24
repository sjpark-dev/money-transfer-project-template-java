package integrationapp;

// @@@SNIPSTART money-transfer-project-template-java-activity-implementation
public class DWServiceActivityImpl implements DWServiceActivity {

    @Override
    public void registerChannel(String pluginId) {

        System.out.printf(
            "\nDW server channel registered. PluginId %s\n",
            pluginId
        );
        // Uncomment the following line to simulate an Activity error.
        // throw new RuntimeException("simulated");
    }

    @Override
    public void deregisterChannel(String pluginId) {

        System.out.printf(
            "\nDW server channel deregistered. PluginId %s\n",
            pluginId
        );
    }

    @Override
    public void sendLog(AppCafe24 appCafe24, String pluginId, ManagerAbility who) {
        System.out.printf(
            "\nRegister complete. AppCafe24Id %s, PluginId %s, ManagerAbilityId %s\n",
            appCafe24.getId(),
            pluginId,
            who.getId()
        );
    }
}
// @@@SNIPEND
