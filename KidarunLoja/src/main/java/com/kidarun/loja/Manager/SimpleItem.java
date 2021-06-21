package com.kidarun.loja.Manager;

import org.bukkit.inventory.ItemStack;

public class SimpleItem {
    private ItemStack itemStack;
    private Double cost;
    public SimpleItem(ItemStack itemStack, Double cost){
        if (itemStack == null || cost == null)
        {
            System.out.println("Error trying to create an item, SLOT and MATERIAL cannot be null.");
        }
        this.itemStack = itemStack;
        this.cost = cost;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
    public Double getCost() {
        return cost;
    }
}
