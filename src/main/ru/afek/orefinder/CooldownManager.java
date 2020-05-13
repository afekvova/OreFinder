package ru.afek.orefinder;

import org.bukkit.entity.Player;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class CooldownManager {

	private final Table<Player, CooldownType, Long> cooldowns = HashBasedTable
			.create();

	public void addCooldown(Player player, CooldownType type) {
		cooldowns.put(player, type, System.currentTimeMillis());
	}

	public boolean hasCooldown(Player player, CooldownType type,
			long duration) {
		Long added = cooldowns.get(player, type);
		if (added == null) {
			return false;
		}
		return (System.currentTimeMillis() - added) < duration;
	}

	public long timeLeft(Player player, CooldownType type, long duration) {
		Long added = cooldowns.get(player, type);
		return duration - (System.currentTimeMillis() - added);
	}

	public void removeCooldown(Player player) {
		cooldowns.row(player).clear();
	}

	public void clearAll() {
		cooldowns.clear();
	}

	public enum CooldownType {
		DIAMOND_PICKAXE("Алмазная кирка", "Алмазную кирку",
				"Алмазную руду"), WOOD_PICKAXE("Деревянная кирка",
						"Деревянную кирку", "Угольную руду"), IRON_PICKAXE(
								"Железная кирка", "Железную кирку",
								"Железную руду"), GOLD_PICKAXE("Золотая кирка",
										"Золотую кирку", "Золотую руду");

		private String name;
		private String padezh;
		private String ore;

		private CooldownType(String name, String padezh, String ore) {
			this.name = name;
			this.padezh = padezh;
			this.ore = ore;
		}

		public String getName() {
			return name;
		}

		public String getOre() {
			return ore;
		}

		public String getPadezh() {
			return padezh;
		}
	}
}