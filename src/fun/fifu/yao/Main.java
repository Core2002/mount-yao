package fun.fifu.yao;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getLogger().info("小瑶瑶插件已启动,author: NekokeCore");
        super.onEnable();
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (!player.isInsideVehicle() && player.getInventory().getItemInMainHand().getType() == Material.ARROW) {
            //范围
            double range = 3;
            if (player.getLocation().distance(entity.getLocation()) > range)
                return;
            entity.addPassenger(player);
            player.sendMessage(player.getName() + "变成了小瑶瑶");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.getPlayer().leaveVehicle();
    }

}
