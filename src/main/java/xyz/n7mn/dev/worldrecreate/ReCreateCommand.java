package xyz.n7mn.dev.worldrecreate;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.util.UUID;


public class ReCreateCommand implements CommandExecutor {

    private final Plugin plugin;

    public ReCreateCommand(Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)){
            return true;
        }

        if (!sender.isOp()){
            return true;
        }

        if (!sender.hasPermission("worldrecreate.command")) {
            return true;
        }

        Player player = (Player) sender;
        World oldWorld = player.getWorld();

        String folderName = DigestUtils.sha512Hex(UUID.randomUUID().toString() + new SecureRandom().nextInt());
        WorldCreator worldCreator = WorldCreator.name(folderName.substring(0, 32));
        worldCreator.environment(World.Environment.NORMAL);

        plugin.getServer().createWorld(worldCreator);
        World world = plugin.getServer().getWorld(folderName.substring(0, 32));

        for (Player p : plugin.getServer().getOnlinePlayers()) {
            p.teleport(world.getSpawnLocation());
        }

        plugin.getServer().unloadWorld(oldWorld, false);
        return true;
    }
}
