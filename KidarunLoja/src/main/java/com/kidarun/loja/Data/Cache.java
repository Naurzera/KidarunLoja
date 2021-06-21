package com.kidarun.loja.Data;

import com.kidarun.loja.Manager.CategoryItem;
import com.kidarun.loja.Manager.ShopItem;
import com.kidarun.loja.Manager.SimpleItem;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Cache {

    public static List<CategoryItem> categoryItems = new ArrayList<>();
    public static List<ShopItem> shopItems = new ArrayList<>();
    public static List<SimpleItem> simpleItems = new ArrayList<>();
    public static LinkedHashMap<String, String> categories = new LinkedHashMap<>();
    public static LinkedHashMap<String, LinkedHashMap<Integer, Inventory>> multiPageCategories = new LinkedHashMap<>();
    public static LinkedHashMap<String, Inventory> singlePageInventories = new LinkedHashMap<>();
    public static Inventory mainCategory = null;

    public static List<CategoryItem> getCategoryItems(){return categoryItems;}
    public static List<ShopItem> getShopItems(){return shopItems;}
    public static List<SimpleItem> getSimpleItems(){return simpleItems;}

    public static void addCategoryItem(CategoryItem categoryItem){
        categoryItems.add(categoryItem);
    }
    public static void addShopitem(ShopItem shopItem){
        shopItems.add(shopItem);
    }
    public static void addSimpleItem(SimpleItem simpleItem){
        simpleItems.add(simpleItem);
    }

    public static void addCategory(String categoryName, String categoryTitle){
        categories.put(categoryName, categoryTitle);
    }

    public static void setMainCategory(Inventory inventory){
        mainCategory = inventory;
    }
    public static Inventory getMainCategory(){
        return mainCategory;
    }


    public static void addMultiPageCategoryInventories(String categoria, LinkedHashMap<Integer, Inventory> inventarios){
        multiPageCategories.put(categoria, inventarios);
    }
    public static LinkedHashMap<String, LinkedHashMap<Integer,Inventory>> getMultiPageCategories(){
        return multiPageCategories;
    }
    public static Inventory getSpecificPage(String multiPageCategory, int pageID){
        LinkedHashMap<Integer, Inventory> linkedHashMap = multiPageCategories.get(multiPageCategory);
        return linkedHashMap.get(pageID);
    }


    public static void addSinglePageInventory(String categoria, Inventory inventario){
        singlePageInventories.put(categoria, inventario);
    }
    public static LinkedHashMap<String, Inventory> getSinglePageInventories(){
        return singlePageInventories;
    }
    public static Inventory getCategoryInventory(String singlePageCategory){
        return singlePageInventories.get(singlePageCategory);
    }

}
