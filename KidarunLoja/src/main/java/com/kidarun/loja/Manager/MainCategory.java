package com.kidarun.loja.Manager;

import com.kidarun.loja.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MainCategory {

    public static Inventory createMainInventory(List<CategoryItem> itens, int rows, String titulo){
        Inventory inv = Bukkit.createInventory(null, rows*9, titulo.replaceAll("&", "§"));
        for (CategoryItem shopItem : itens){
            int slot = shopItem.getSlot();
            ItemStack item = shopItem.getItemStack();
            inv.setItem(slot, item);
        }
        return inv;
    }

}
