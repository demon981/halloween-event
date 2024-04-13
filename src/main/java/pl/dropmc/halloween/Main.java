package pl.dropmc.halloween;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.dropmc.halloween.events.Events;

import java.util.Random;

public final class Main extends JavaPlugin {

    public static Main inst;

    static Random r = new Random();

    @Override
    public void onEnable() {
        inst = this;
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new Events(this), this);
    }

    public static Main getInst(){
        return inst;
    }

    public static void sendSound(Player p, Location loc, String sound, float volume, float pitch) {
        if (sound.equals("RANDOM")) {
            Sound[] all = Sound.values();
            int i = 0;
            do {
                sound = all[r.nextInt(all.length)].name();
                i++;
            } while ((sound.startsWith("RECORD_") || sound.startsWith("MUSIC_")) && i < 10);
        }
        p.playSound(loc, Sound.valueOf(sound), volume, pitch);
    }

    public static void strikeLightningEffect(Location loc) {
        loc.getWorld().strikeLightningEffect(loc);
        for (Entity ent : loc.getWorld().getNearbyEntities(loc, 32.0D, 64.0D, 32.0D)) {
            if (ent instanceof Player)
                ((Player)ent).playSound(loc, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 3.0F, 1.0F);
        }
    }

    public static Location getCenter(Location loc) {
        double x = loc.getX();
        double z = loc.getZ();
        if (x >= 0.0D) {
            x += 0.5D;
        } else {
            x += 0.5D;
        }
        if (z >= 0.0D) {
            z += 0.5D;
        } else {
            z += 0.5D;
        }
        Location lo = loc.clone();
        lo.setX(x);
        lo.setZ(z);
        return lo;
    }
}
