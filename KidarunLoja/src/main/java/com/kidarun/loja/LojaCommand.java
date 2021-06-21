package com.kidarun.loja;

import com.kidarun.loja.Data.Cache;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LojaCommand extends BukkitCommand {
    public LojaCommand()
    {
        super("loja");
    }

    @Override
    public boolean execute(final CommandSender sender, String alias, final String[] args)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                if (sender instanceof Player)
                {
                    ((Player)sender).openInventory(Cache.getMainCategory());
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
        return false;
    }
}
