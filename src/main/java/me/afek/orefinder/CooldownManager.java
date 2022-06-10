package me.afek.orefinder;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

public class CooldownManager {

    private final Table<Player, CooldownManager.CooldownType, Long> cooldowns = HashBasedTable.create();

    public void addCooldown(Player player, CooldownManager.CooldownType type) {
        cooldowns.put(player, type, System.currentTimeMillis());
    }

    public boolean hasCooldown(Player player, CooldownManager.CooldownType type, long duration) {
        Long added = cooldowns.get(player, type);
        if (added == null)
            return false;

        return (System.currentTimeMillis() - added) < duration;
    }

    public long timeLeft(Player player, CooldownManager.CooldownType type, long duration) {
        Long added = cooldowns.get(player, type);
        return duration - (System.currentTimeMillis() - added);
    }

    public void removeCooldown(Player player) {
        cooldowns.row(player).clear();
    }

    public void clearAll() {
        cooldowns.clear();
    }

    @Getter
    @AllArgsConstructor
    public enum CooldownType {
        DIAMOND_PICKAXE("Алмазная кирка", "Алмазную кирку",
                "Алмазную руду"), WOOD_PICKAXE("Деревянная кирка",
                "Деревянную кирку", "Угольную руду"), IRON_PICKAXE(
                "Железная кирка", "Железную кирку",
                "Железную руду"), GOLD_PICKAXE("Золотая кирка",
                "Золотую кирку", "Золотую руду");

        private final String name, padezh, ore;
    }
}