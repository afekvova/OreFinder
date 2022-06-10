package me.afek.orefinder.listener;

import lombok.RequiredArgsConstructor;
import me.afek.orefinder.CooldownManager;
import me.afek.orefinder.config.Settings;
import me.afek.orefinder.utils.StringHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class FinderListener implements Listener {

    private final CooldownManager cooldownManager;

    @EventHandler
    public void onInteractGold(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!this.checkHand(event.getAction()))
            return;

        if (!Settings.GOLD_PICKAXE.ENABLE || !player.hasPermission("orefinder.use.gold_pickaxe") || event.getHand() != EquipmentSlot.HAND || !event.hasItem() || event.getItem().getType() != Material.GOLDEN_PICKAXE)
            return;

        if (Settings.GOLD_PICKAXE.COOLDOWN > 0 && checkCooldown(event.getPlayer(), CooldownManager.CooldownType.GOLD_PICKAXE, Settings.GOLD_PICKAXE.COOLDOWN * 1000)) {
            event.setCancelled(true);
            return;
        }

        if (findblock(player, Material.GOLD_ORE)) {
            player.sendMessage(StringHelper.color(
                    Settings.PREFIX + Settings.FORMAT_FIND_MESSAGE.replace(
                            "%type%", CooldownManager.CooldownType.GOLD_PICKAXE.getOre())));
            cooldownManager.addCooldown(event.getPlayer(),
                    CooldownManager.CooldownType.GOLD_PICKAXE);
            event.setCancelled(true);
        } else {
            player.sendMessage(StringHelper
                    .color(Settings.PREFIX + Settings.FORMAT_NOFIND_MESSAGE));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractWood(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!checkHand(event.getAction())) {
            return;
        }

        if (!Settings.WOOD_PICKAXE.ENABLE
                || !player.hasPermission("orefinder.use.wood_pickaxe")
                || event.getHand() != EquipmentSlot.HAND || !event.hasItem()
                || event.getItem().getType() != Material.WOODEN_PICKAXE) {
            return;
        }

        if (Settings.WOOD_PICKAXE.COOLDOWN > 0
                && checkCooldown(event.getPlayer(), CooldownManager.CooldownType.WOOD_PICKAXE,
                Settings.WOOD_PICKAXE.COOLDOWN * 1000)) {
            event.setCancelled(true);
            return;
        }

        if (findblock(player, Material.COAL_ORE)) {
            player.sendMessage(StringHelper.color(
                    Settings.PREFIX + Settings.FORMAT_FIND_MESSAGE.replace(
                            "%type%", CooldownManager.CooldownType.WOOD_PICKAXE.getOre())));
            cooldownManager.addCooldown(event.getPlayer(),
                    CooldownManager.CooldownType.WOOD_PICKAXE);
            event.setCancelled(true);
        } else {
            player.sendMessage(StringHelper
                    .color(Settings.PREFIX + Settings.FORMAT_NOFIND_MESSAGE));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractIron(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!checkHand(event.getAction())) {
            return;
        }

        if (!Settings.IRON_PICKAXE.ENABLE
                || !player.hasPermission("orefinder.use.iron_pickaxe")
                || event.getHand() != EquipmentSlot.HAND || !event.hasItem()
                || event.getItem().getType() != Material.IRON_PICKAXE) {
            return;
        }

        if (Settings.IRON_PICKAXE.COOLDOWN > 0
                && checkCooldown(event.getPlayer(), CooldownManager.CooldownType.IRON_PICKAXE,
                Settings.IRON_PICKAXE.COOLDOWN * 1000)) {
            event.setCancelled(true);
            return;
        }

        if (findblock(player, Material.IRON_ORE)) {
            player.sendMessage(StringHelper.color(
                    Settings.PREFIX + Settings.FORMAT_FIND_MESSAGE.replace(
                            "%type%", CooldownManager.CooldownType.IRON_PICKAXE.getOre())));
            cooldownManager.addCooldown(event.getPlayer(),
                    CooldownManager.CooldownType.IRON_PICKAXE);
            event.setCancelled(true);
        } else {
            player.sendMessage(StringHelper
                    .color(Settings.PREFIX + Settings.FORMAT_NOFIND_MESSAGE));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractDiamond(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!checkHand(event.getAction())) {
            return;
        }

        if (!Settings.DIAMOND_PICKAXE.ENABLE
                || !player.hasPermission("orefinder.use.diamond_pickaxe")
                || event.getHand() != EquipmentSlot.HAND || !event.hasItem()
                || event.getItem().getType() != Material.DIAMOND_PICKAXE) {
            return;
        }

        if (Settings.DIAMOND_PICKAXE.COOLDOWN > 0 && checkCooldown(
                event.getPlayer(), CooldownManager.CooldownType.DIAMOND_PICKAXE,
                Settings.DIAMOND_PICKAXE.COOLDOWN * 1000)) {
            event.setCancelled(true);
            return;
        }

        if (findblock(player, Material.DIAMOND_ORE)) {
            player.sendMessage(StringHelper.color(
                    Settings.PREFIX + Settings.FORMAT_FIND_MESSAGE.replace(
                            "%type%", CooldownManager.CooldownType.DIAMOND_PICKAXE.getOre())));
            cooldownManager.addCooldown(event.getPlayer(),
                    CooldownManager.CooldownType.DIAMOND_PICKAXE);
            event.setCancelled(true);
        } else {
            player.sendMessage(StringHelper
                    .color(Settings.PREFIX + Settings.FORMAT_NOFIND_MESSAGE));
            event.setCancelled(true);
        }
    }

    private boolean checkHand(Action action) {
        if (action == Action.RIGHT_CLICK_AIR
                || action == Action.RIGHT_CLICK_BLOCK) {
            return true;
        }
        return false;
    }

    private boolean findblock(Player player, Material material) {
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        int range = Settings.RANGE;
        for (int ix = x - range; ix <= x + range; ++ix) {
            for (int iy = y - range; iy <= y + range; ++iy) {
                for (int iz = z - range; iz <= z + range; ++iz) {
                    List<Material> listblock = new ArrayList<Material>();
                    listblock.add(Bukkit.getServer()
                            .getWorld(player.getWorld().getName())
                            .getBlockAt(ix, iy, iz).getType());
                    Location block = new Location(player.getWorld(),
                            (double) ix, (double) iy, (double) iz);
                    if (listblock.contains(material)) {
                        player.teleport(lookAt(player.getLocation(), block));
                        ix = x + range;
                        iy = y + range;
                        iz = z + range;
                        return true;
                    }

                    if (!listblock.contains(material) && ix == x + range - 1) {
                        ix = x + range;
                        iy = y + range;
                        iz = z + range;
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private Location lookAt(Location loc, Location lookat) {
        loc = loc.clone();
        double doublex = lookat.getX() - loc.getX();
        double doubley = lookat.getY() - loc.getY() - 1.0;
        double doublez = lookat.getZ() - loc.getZ();
        if (doublex != 0.0) {
            if (doublex < 0.0) {
                loc.setYaw(4.712389f);
            } else {
                loc.setYaw(1.5707964f);
            }
            loc.setYaw(loc.getYaw() - (float) Math.atan(doublez / doublex));
        } else if (doublez < 0.0) {
            loc.setYaw(3.1415927f);
        }
        double doublexz = Math
                .sqrt(Math.pow(doublex, 2.0) + Math.pow(doublez, 2.0));
        loc.setPitch((float) (-Math.atan(doubley / doublexz)));
        loc.setYaw(-loc.getYaw() * 180.0f / 3.1415927f);
        loc.setPitch(loc.getPitch() * 180.0f / 3.1415927f);
        return loc;
    }

    private boolean checkCooldown(Player player, CooldownManager.CooldownType cooldownType,
                                  long cooldownTime) {
        boolean cooldownActive = cooldownTime > 0;
        if (cooldownActive && cooldownManager.hasCooldown(player, cooldownType,
                cooldownTime)) {
            long remaining = cooldownManager.timeLeft(player, cooldownType,
                    cooldownTime);
            int remainingInt = (int) TimeUnit.MILLISECONDS.toSeconds(remaining);
            String message = Settings.COOLDOWN_MESSAGE;
            if (!message.isEmpty()) {
                player.sendMessage(
                        StringHelper
                                .color(Settings.PREFIX
                                        + StringHelper.replaceTime(message
                                                .replace("%time%",
                                                        Math.round(remaining
                                                                / 1000) + "")
                                                .replace("%type%",
                                                        cooldownType.getPadezh()),
                                        remainingInt)));
            }
            return true;
        }
        return false;
    }
}
