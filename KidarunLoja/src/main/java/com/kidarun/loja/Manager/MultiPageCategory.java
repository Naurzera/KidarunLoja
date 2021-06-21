package com.kidarun.loja.Manager;

import com.kidarun.loja.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MultiPageCategory {

    public static LinkedHashMap<Integer, Inventory> createMultiPageCategoryInventories
            (List<SimpleItem> itens, String titulo) {
        LinkedHashMap<Integer, Inventory> inventarios = new LinkedHashMap<>();

        int[] la = {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43};
        int[] gp = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,17,18,26,27,35,36,44,45,46,47,48,49,50,51,52,53};

        int quantidade = itens.size();
        int lojan = 0;

        double eoq = Math.ceil((double) quantidade / 28.0);

        for (int i = 0; i < eoq; i++) {

            if (inventarios.get(i - 1) != null) inventarios.get(i - 1).setItem(53, addButton(true));

            Inventory inv = Bukkit.createInventory(null, 54, titulo.replaceAll("&", "§"));

            int p = i + 1;

            for (int n : la) {
                if (itens.size() > lojan) {
                    SimpleItem simpleItem = itens.get(lojan);
                    String custo = NumberFormat.humanBalance(simpleItem.getCost());
                    ItemStack itemStack = simpleItem.getItemStack();
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    List<String> lore = new ArrayList<>();
                    lore.add(" ");
                    lore.add("§fCusto: §a$ " + custo);
                    itemMeta.setLore(lore);
                    itemStack.setItemMeta(itemMeta);
                    inv.setItem(n, itemStack);
                    lojan++;
                } else {
                    inv.setItem(n, addBlackStainedGlassPain(Material.STAINED_GLASS_PANE, 7));
                }
            }
            for (int n : gp) {
                inv.setItem(n, addBlackStainedGlassPain(Material.STAINED_GLASS_PANE, 7));
            }

            inv.setItem(4, addPagCount(p));
            inv.setItem(49,addBack());

            if (i != 0) {
                inv.setItem(45, addButton(false));
            } else {
                inv.setItem(45, addBlackStainedGlassPain(Material.STAINED_GLASS_PANE, 7));
            }

            inv.setItem(17,
                    addBlackStainedGlassPain(Material.STAINED_GLASS_PANE, 7));

            inventarios.put(i, inv);
        }
        if (inventarios.size() == 1)
            inventarios.get(0).setItem(9, addBlackStainedGlassPain(Material.STAINED_GLASS_PANE, 7));
        return inventarios;
    }


    public static ItemStack addBlackStainedGlassPain(Material material, int durability) {
        ItemStack item = new ItemStack(material);
        item.setDurability((short) durability);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack addPagCount(int i) {
        ItemStack item = new ItemStack(Material.PAPER, i);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Página "+i);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack addButton(boolean proxima) {
        ItemStack item = new ItemStack(Material.STONE_BUTTON);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a<- Página Anterior");
        if (proxima) meta.setDisplayName("§aPróxima Página ->");
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack addBack() {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cVoltar");
        item.setItemMeta(meta);

        return item;
    }
}
