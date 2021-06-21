package com.kidarun.loja.Manager;

import org.bukkit.inventory.ItemStack;

public class ShopItem {
    private Integer slot;
    private ItemStack itemStack;
    private Double cost;
    public ShopItem(Integer slot, ItemStack itemStack, Double cost){
        if (slot == null || itemStack == null || cost == null)
        {
            System.out.println("Error trying to create an item, SLOT and MATERIAL cannot be null.");
        }
        this.slot = slot;
        this.itemStack = itemStack;
        this.cost = cost;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
    public Integer getSlot() {
        return slot;
    }
    public Double getCost() {
        return cost;
    }
}
