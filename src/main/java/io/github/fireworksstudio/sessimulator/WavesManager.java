package io.github.fireworksstudio.sessimulator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import java.util.function.Consumer;
public final class WavesManager {
    private static int currentWave = -1;
    private static double totalHealth;
    private static final World world = Bukkit.getWorld("world");
    private static final MobPack[][] waves = new MobPack[][]{
        {MobPack.ZOMBIES, MobPack.ZOMBIES},
        {MobPack.ZOMBIES, MobPack.ZOMBIES, MobPack.UNDEAD}
    };
    private WavesManager() {}
    public static void init() {

    }
    public static void updateProgress() {
    }
    private static void newWave() {
        currentWave++;
        totalHealth = 0;
        for(MobPack pack : waves[currentWave]) {
            for(int i = 0; i < pack.entityTypes.length; i++) {
                final int i0 = i;
                world.spawnEntity(new Location(world, 0, 5, 0), pack.entityTypes[i], CreatureSpawnEvent.SpawnReason.CUSTOM, entity -> {
                    if(pack.spawnModifiers[i0] != null) {
                        pack.spawnModifiers[i0].accept(entity);
                    }
                    if(entity instanceof LivingEntity living) {
                        //totalHealth += living.getAttribute()
                    }
                });
            }
        }
    }
    private enum MobPack {
        ZOMBIES(new EntityType[]{}, new Consumer[2]),
        UNDEAD(new EntityType[]{EntityType.SKELETON, EntityType.SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE, EntityType.ZOMBIE}, new Consumer[5]);
        private final EntityType[] entityTypes;
        private final Consumer<? super Entity>[] spawnModifiers;
        MobPack(EntityType[] entityTypes, Consumer<? super Entity>[] spawnModifiers) {
            this.entityTypes = entityTypes;
            this.spawnModifiers = spawnModifiers;
        }
    }
}
