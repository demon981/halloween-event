package pl.dropmc.halloween.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import pl.dropmc.halloween.Main;

import java.util.ArrayList;
import java.util.List;

public class Events implements Listener {
    private static Main inst;
    public Events(Main main) {
        this.inst = inst;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (p.getInventory().getHelmet() == null)
            p.getInventory().setItem(39, new ItemStack(Material.JACK_O_LANTERN));
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent e){
        List<String> mobs = new ArrayList<>();
        mobs.add("ZOMBIE");
        mobs.add("SKELETON");

        if (mobs.contains(e.getEntityType().name())) {
            LivingEntity lv = e.getEntity();
            lv.getEquipment().setHelmet(new ItemStack(Material.JACK_O_LANTERN));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDeath(EntityDeathEvent e){
        LivingEntity ent = e.getEntity();
        if (ent.getKiller() != null && ent.getKiller() instanceof Player) {
            Player p = ent.getKiller();
            p.playSound(ent.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.0F, 1.0F);
            p.sendMessage(ChatColor.GOLD + "   Buhahaha!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Main.sendSound(p, p.getLocation(), "ENTITY_MAGMA_CUBE_SQUISH", 1.0F, 1.0F);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        Main.sendSound(p, p.getLocation(), "ENTITY_MAGMA_CUBE_SQUISH", 1.0F, 1.0F);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getPlayer();
        Main.strikeLightningEffect(Main.getCenter(p.getLocation()));
        Main.sendSound(p, p.getLocation(), "ENTITY_LIGHTNING_BOLT_THUNDER", 3.0F, 1.0F);
    }
}
