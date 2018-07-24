package ru.Finder.Listener;

import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import ru.Finder.*;
import ru.Finder.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.event.*;

public class Finder implements Listener
{
    @EventHandler
    public void onUse(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK) || event.getAction().equals((Object)Action.RIGHT_CLICK_AIR)) {
            final int x = player.getLocation().getBlockX();
            final int y = player.getLocation().getBlockY();
            final int z = player.getLocation().getBlockZ();
            final String messagefind = Utils.colorize(String.valueOf(Main.prefix) + Main.formatfindmessage);
            final String messagenofind = Utils.colorize(String.valueOf(Main.prefix) + Main.formatnofindmessage);
            for (int ix = x - Main.range; ix <= x + Main.range; ++ix) {
                for (int iy = y - Main.range; iy <= y + Main.range; ++iy) {
                    for (int iz = z - Main.range; iz <= z + Main.range; ++iz) {
                        final List<Material> listblock = new ArrayList<Material>();
                        listblock.add(Bukkit.getServer().getWorld(player.getWorld().getName()).getBlockAt(ix, iy, iz).getType());
                        final Location block = new Location(player.getWorld(), (double)ix, (double)iy, (double)iz);
                        switch (event.getPlayer().getItemInHand().getType()) {
                            case DIAMOND_PICKAXE: {
                                if (!Main.getInctance().getConfig().getBoolean("working.DIAMOND_PICKAXE")) {
                                    return;
                                }
                                if (!player.hasPermission("use.diamond_pickaxe")) {
                                    return;
                                }
                                if (listblock.contains(Material.DIAMOND_ORE)) {
                                    player.teleport(Utils.lookAt(player.getLocation(), block));
                                    player.sendMessage(messagefind.replace("%type%", Material.DIAMOND_ORE.name()));
                                    ix = x + Main.range;
                                    iy = y + Main.range;
                                    iz = z + Main.range;
                                    event.setCancelled(true);
                                    return;
                                }
                                if (!listblock.contains(Material.DIAMOND_ORE) && ix == x + Main.range - 1) {
                                    player.sendMessage(messagenofind);
                                    ix = x + Main.range;
                                    iy = y + Main.range;
                                    iz = z + Main.range;
                                    event.setCancelled(true);
                                    return;
                                }
                                break;
                            }
                            case WOOD_PICKAXE: {
                                if (!Main.getInctance().getConfig().getBoolean("working.WOOD_PICKAXE")) {
                                    return;
                                }
                                if (!player.hasPermission("use.wood_pickaxe")) {
                                    return;
                                }
                                if (listblock.contains(Material.COAL_ORE)) {
                                    player.teleport(Utils.lookAt(player.getLocation(), block));
                                    player.sendMessage(messagefind.replace("%type%", Material.COAL_ORE.name()));
                                    ix = x + Main.range;
                                    iy = y + Main.range;
                                    iz = z + Main.range;
                                    event.setCancelled(true);
                                    return;
                                }
                                if (!listblock.contains(Material.COAL_ORE) && ix == x + Main.range - 1) {
                                    player.sendMessage(messagenofind);
                                    ix = x + Main.range;
                                    iy = y + Main.range;
                                    iz = z + Main.range;
                                    event.setCancelled(true);
                                    return;
                                }
                                break;
                            }
                            case IRON_PICKAXE: {
                                if (!Main.getInctance().getConfig().getBoolean("working.IRON_PICKAXE")) {
                                    return;
                                }
                                if (!player.hasPermission("use.iron_pickaxe")) {
                                    return;
                                }
                                if (listblock.contains(Material.IRON_ORE)) {
                                    player.teleport(Utils.lookAt(player.getLocation(), block));
                                    player.sendMessage(messagefind.replace("%type%", Material.IRON_ORE.name()));
                                    ix = x + Main.range;
                                    iy = y + Main.range;
                                    iz = z + Main.range;
                                    event.setCancelled(true);
                                    return;
                                }
                                if (!listblock.contains(Material.IRON_ORE) && ix == x + Main.range - 1) {
                                    player.sendMessage(messagenofind);
                                    ix = x + Main.range;
                                    iy = y + Main.range;
                                    iz = z + Main.range;
                                    event.setCancelled(true);
                                    return;
                                }
                                break;
                            }
                            case GOLD_PICKAXE: {
                                if (!Main.getInctance().getConfig().getBoolean("working.GOLD_PICKAXE")) {
                                    return;
                                }
                                if (!player.hasPermission("use.gold_pickaxe")) {
                                    return;
                                }
                                if (listblock.contains(Material.GOLD_ORE)) {
                                    player.teleport(Utils.lookAt(player.getLocation(), block));
                                    player.sendMessage(messagefind.replace("%type%", Material.GOLD_ORE.name()));
                                    ix = x + Main.range;
                                    iy = y + Main.range;
                                    iz = z + Main.range;
                                    event.setCancelled(true);
                                    return;
                                }
                                if (!listblock.contains(Material.GOLD_ORE) && ix == x + Main.range - 1) {
                                    player.sendMessage(messagenofind);
                                    ix = x + Main.range;
                                    iy = y + Main.range;
                                    iz = z + Main.range;
                                    event.setCancelled(true);
                                    return;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
