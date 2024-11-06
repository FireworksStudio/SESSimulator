package io.github.fireworksstudio.sessimulator.listeners;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import io.github.fireworksstudio.sessimulator.SESSimulator;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
public final class PlayerListener implements Listener {
    private static final double BOUNDING_BOX_EXPANSION = 1D / 1024;
    private static final PlayerListener instance = new PlayerListener();
    private PlayerListener() {}
    public static PlayerListener getInstance() {
        return instance;
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final int[] electrocutionTimer = {0};
        player.getScheduler().runAtFixedRate(SESSimulator.getInstance(), task -> {
            if(electrocutionTimer[0] > 0) {
                electrocutionTimer[0]--;
            }
            final Block block = player.getLocation().getBlock();
            if((block.getType() == Material.REDSTONE_WIRE || block.getType() == Material.REDSTONE_TORCH || block.getType() == Material.REPEATER) && block.getCollisionShape().overlaps(player.getBoundingBox().clone().expand(BOUNDING_BOX_EXPANSION))) {
                player.damage(block.getBlockPower(), DamageSource.builder(DamageType.CACTUS).build());
            }
        }, null, 1, 1);
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerJump(PlayerJumpEvent event) {
        if(event.getPlayer().isSneaking()) {
            event.getPlayer().setPose(Pose.FALL_FLYING, true);
        }
    }
}
