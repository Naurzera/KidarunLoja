package com.kidarun.loja;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InvCheio {

    public static boolean invCheio(Player p){

        for (int slot = 0; slot < 36; slot++) {
            if (p.getInventory().getItem(slot) == null) {
                return false;
            }
            if (p.getInventory().getItem(slot).getType().equals(Material.AIR)) {
                return false;
            }
            if (slot == 35) {
                return true;
            }
        }

        return true;

    }

    public static int quantoCabe(Player p, ItemStack i){

        int itens = 0;

        for (int slot = 0; slot < 36; slot++) {
            if (p.getInventory().getItem(slot) == null) {
                itens += 64;
                continue;
            }
            if (p.getInventory().getItem(slot).getType().equals(Material.AIR)) {
                itens += 64;
                continue;
            }
            if (p.getInventory().getItem(slot).isSimilar(i) && p.getInventory().getItem(slot).getMaxStackSize()==64) {
                itens += 64-p.getInventory().getItem(slot).getAmount();
                continue;
            }
        }

        return itens;

    }

}
