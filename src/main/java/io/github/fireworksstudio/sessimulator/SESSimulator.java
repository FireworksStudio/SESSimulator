package io.github.fireworksstudio.sessimulator;
import io.github.fireworksstudio.sessimulator.listeners.PlayerListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
public final class SESSimulator extends JavaPlugin {
    private static ComponentLogger logger;
    private static SESSimulator instance;
    public static SESSimulator getInstance() {
        return instance;
    }
    public static void log(final String msg) {
        log(Component.text(msg));
    }
    public static void log(final Component msg) {
        logger.info(msg);
    }
    public static void warn(final String msg) {
        warn(Component.text(msg));
    }
    public static void warn(final Component msg) {
        logger.warn(msg);
    }
    public static void error(final String msg) {
        error(Component.text(msg));
    }
    public static void error(final Component msg) {
        logger.error(msg);
    }
    @Override
    public void onEnable() {
        instance = this;
        logger = getComponentLogger();
        Bukkit.getPluginManager().registerEvents(PlayerListener.getInstance(), getInstance());
        log(Component.text("SESSimulator initialized."));
    }
    @Override
    public void onDisable() {
        log(Component.text("SESSimulator finalized."));
        logger = null;
        instance = null;
    }
}
