package com.kidarun.loja.Manager;

import com.kidarun.loja.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SinglePageCategory {

    public static Inventory createSinglePageCategory(List<ShopItem> itens, String titulo){
        Inventory inv = Bukkit.createInventory(null, 54, titulo.replaceAll("&", "§"));
        for (ShopItem shopItem : itens) {
            Double cost = shopItem.getCost();
            String custo = NumberFormat.humanBalance(cost);
            ItemStack itemStack = shopItem.getItemStack();
            ItemMeta itemMeta = itemStack.getItemMeta();
            List<String> lore = itemMeta.getLore();
            for (int lineid = 0 ; lineid<lore.size() ; lineid++)
            {
                String line = lore.get(lineid);
                line = line.replaceAll("%custo%",""+custo);
                lore.set(lineid ,line);
            }
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            int slot = shopItem.getSlot();
            inv.setItem(slot, itemStack);
        }
        inv.setItem(49,addBack());
        return inv;
    }

    public static ItemStack addBack() {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cVoltar");
        item.setItemMeta(meta);

        return item;
    }
}
