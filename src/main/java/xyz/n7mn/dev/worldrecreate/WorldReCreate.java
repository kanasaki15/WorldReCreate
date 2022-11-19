package xyz.n7mn.dev.worldrecreate;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldReCreate extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        PluginCommand command = getCommand("rc");
        command.setExecutor(new ReCreateCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
