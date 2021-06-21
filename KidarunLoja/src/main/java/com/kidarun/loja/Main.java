package com.kidarun.loja;

import com.kidarun.loja.Data.DataLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.logging.Level;

public class Main extends JavaPlugin {

    private static Main instance;
    public static Main getInstance()
    {
        return instance;
    }
    public static final Logger LOG = new Logger();

    @Override
    public void onEnable() {
        long time = System.currentTimeMillis();
        sendCsl(ChatColor.GREEN, " ");
        sendCsl(ChatColor.YELLOW,"------------------------------------------");
        sendCsl(ChatColor.GREEN, " ");
        saveDefaultConfig();
        instance = this;
        if (Vault.getEconomy() == null){
            LOG.log(Level.SEVERE, "Could not find a compatible economy plugin at server,");
            LOG.log(Level.SEVERE, "You can create and navigate between shops");
            LOG.log(Level.SEVERE, "But there will have errors");
            LOG.log(Level.SEVERE, "If you try to buy some item");
            System.out.println(" ");
        }
        sendCsl(ChatColor.GREEN,"[KidarunLoja] Loading main inventory...");
        DataLoader.setMainCategory();
        sendCsl(ChatColor.GREEN,"[KidarunLoja] Loading multi-page categories...");
        DataLoader.loadMultiPageInventories();
        sendCsl(ChatColor.GREEN,"[KidarunLoja] Loading single-page categories...");
        DataLoader.loadSinglePageInventories();
        sendCsl(ChatColor.GREEN,"[KidarunLoja] Registering events...");
        getServer().getPluginManager().registerEvents(new ShopListener(), this);
        sendCsl(ChatColor.GREEN,"[KidarunLoja] Registering main command...");
        registerCommands();

        long ms = System.currentTimeMillis()-time;
        sendCsl(ChatColor.GREEN,"[KidarunLoja] KidarunLoja Loaded successful in "+ms+"ms!");
        sendCsl(ChatColor.GREEN, " ");
        sendCsl(ChatColor.YELLOW,"------------------------------------------");
        sendCsl(ChatColor.GREEN, " ");
    }

    @Override
    public void onDisable() {
        sendCsl(ChatColor.RED, "[KidarunLoja] Plugin DESABILITADO!");
    }

    void sendCsl(ChatColor color, String msg){
        getServer().getConsoleSender().sendMessage(color+msg);
    }

    private void registerCommands() {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register("loja", new LojaCommand());
        } catch (Exception e) {
            setEnabled(false);
            throw new RuntimeException("Failed to register commands", e);
        }
    }
}
