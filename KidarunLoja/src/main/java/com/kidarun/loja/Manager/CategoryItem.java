package com.kidarun.loja.Manager;

import org.bukkit.inventory.ItemStack;

public class CategoryItem {
    private final Integer slot;
    private final ItemStack itemStack;
    private final String category;
    public CategoryItem(Integer slot, ItemStack itemStack, String category){
        if (slot == null || itemStack == null)
        {
            System.out.println("Error trying to create an item, SLOT and MATERIAL cannot be null.");
        }
        this.slot = slot;
        this.itemStack = itemStack;
        this.category = category;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
    public Integer getSlot() {
        return slot;
    }
    public String getCategory() {
        return category;
    }
}
