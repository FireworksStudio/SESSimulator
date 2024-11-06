package io.github.fireworksstudio.sessimulator.listeners;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
public final class GameListener implements Listener {
    private static final GameListener instance = new GameListener();
    private GameListener() {}
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof final LivingEntity entity) {

        }
    }
}
