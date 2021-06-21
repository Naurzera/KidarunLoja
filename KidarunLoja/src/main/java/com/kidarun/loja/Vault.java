package com.kidarun.loja;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Vault {
    public static Economy getEconomy() {
        if (!(Bukkit.getServer().getPluginManager().isPluginEnabled("Vault"))) return null;
        RegisteredServiceProvider<Economy> response = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (response == null) return null;
        return response.getProvider();
    }
}
