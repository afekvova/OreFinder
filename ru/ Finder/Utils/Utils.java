package ru.Finder.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Utils {
	
    public static Location lookAt(Location loc, Location lookat) {
        loc = loc.clone();
        double doublex = lookat.getX() - loc.getX();
        double doubley = lookat.getY() - loc.getY() - 1;
        double doublez = lookat.getZ() - loc.getZ();
        if (doublex != 0.0) {
            if (doublex < 0.0) {
                loc.setYaw(4.712389f);
            }
            else {
                loc.setYaw(1.5707964f);
            }
            loc.setYaw(loc.getYaw() - (float)Math.atan(doublez / doublex));
        }
        else if (doublez < 0.0) {
            loc.setYaw(3.1415927f);
        }
        double doublexz = Math.sqrt(Math.pow(doublex, 2.0) + Math.pow(doublez, 2.0));
        loc.setPitch((float)(-Math.atan(doubley / doublexz)));
        loc.setYaw(-loc.getYaw() * 180.0f / 3.1415927f);
        loc.setPitch(loc.getPitch() * 180.0f / 3.1415927f);
        return loc;
    }
    
    public static String colorize(final String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

}
