package me.afek.orefinder.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Settings extends Config {

    public static void save(File file) {
        save(file, Settings.class);
    }

    public static void load(File file) {
        load(file, Settings.class);
    }

    public static void reload(File file) {
        if (!file.exists()) {
            save(file);
        } else {
            convertLegacy(file);
            load(file);
            save(file);
        }
    }

    public static boolean convertLegacy(File file) {
        if (!file.exists()) {
            return false;
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        // Main settings
        PREFIX = config.getString("prefix");
        COOLDOWN_MESSAGE = config.getString("cooldown_message");
        FORMAT_FIND_MESSAGE = config.getString("format_find_message");
        FORMAT_NOFIND_MESSAGE = config.getString("format_nofind_message");
        RANGE = config.getInt("range");

        // DIAMOND_PICKAXE
        DIAMOND_PICKAXE.ENABLE = config.getBoolean("diamond_pickaxe.enable");
        DIAMOND_PICKAXE.COOLDOWN = config.getInt("diamond_pickaxe.cooldown");

        // WOOD_PICKAXE
        WOOD_PICKAXE.ENABLE = config.getBoolean("wood_pickaxe.enable");
        WOOD_PICKAXE.COOLDOWN = config.getInt("wood_pickaxe.cooldown");

        // IRON_PICKAXE
        IRON_PICKAXE.ENABLE = config.getBoolean("iron_pickaxe.enable");
        IRON_PICKAXE.COOLDOWN = config.getInt("iron_pickaxe.cooldown");

        // GOLD_PICKAXE
        GOLD_PICKAXE.ENABLE = config.getBoolean("gold_pickaxe.enable");
        GOLD_PICKAXE.COOLDOWN = config.getInt("gold_pickaxe.cooldown");
        return true;
    }

    @Comment("Префикс.")
    public static String PREFIX = "&f[&cOreFinder&f] ";
    @Comment("Сообщение при обнаружение ресурсов. %type% - блок.")
    public static String FORMAT_FIND_MESSAGE = "Вы нашли &e%type%";
    @Comment("Сообщение когда вы не обнаружили ресурсы.")
    public static String FORMAT_NOFIND_MESSAGE = "Вы ничего не нашли :(";
    @Comment("Сообщение когда вы не обнаружили ресурсы.")
    public static String COOLDOWN_MESSAGE = "Вы не можете использовать &e%type%&f. Подождите &6%time% &f%formated-sec%.";
    @Comment({"Диапазон обнаружения на которой кирка будет сканировать блоки",
            "Не советую ставить менше 4"})
    public static int RANGE = 7;

    @Comment("Настройки для поиска ресурсов с помощью алмазной кирки. orefinder.use.diamond_pickaxe")
    public static class DIAMOND_PICKAXE {
        public static boolean ENABLE = true;
        @Comment({"Задержка для алмазной кирки.",
                "Значение 0 отключает задержку."})
        public static int COOLDOWN = 30;
    }

    @Comment("Настройки для поиска ресурсов с помощью деревянной кирки. orefinder.use.wood_pickaxe")
    public static class WOOD_PICKAXE {
        public static boolean ENABLE = true;
        @Comment({"Задержка для деревянной кирки.",
                "Значение 0 отключает задержку."})
        public static int COOLDOWN = 30;
    }

    @Comment("Настройки для поиска ресурсов с помощью железной кирки. orefinder.use.iron_pickaxe")
    public static class IRON_PICKAXE {
        public static boolean ENABLE = true;
        @Comment({"Задержка для железной кирки.",
                "Значение 0 отключает задержку."})
        public static int COOLDOWN = 30;
    }

    @Comment("Настройки для поиска ресурсов с помощью золотой кирки. orefinder.use.gold_pickaxe")
    public static class GOLD_PICKAXE {
        public static boolean ENABLE = true;
        @Comment({"Задержка для золотой кирки.",
                "Значение 0 отключает задержку."})
        public static int COOLDOWN = 30;
    }
}
