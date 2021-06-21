package com.kidarun.loja;

import com.kidarun.loja.Data.Cache;
import com.kidarun.loja.Manager.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopListener implements Listener {


    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = false)
    public static void onShopClick(InventoryClickEvent e)
    {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if (isShopInventory(e)) {
            e.setResult(Event.Result.DENY);
            Player player = (Player) e.getWhoClicked();
            if (isShopItem(e)) {
                ShopItem shopItem = getShopItem(e);
                ItemStack itemStack = shopItem.getItemStack().clone();
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setLore(null);
                itemStack.setItemMeta(itemMeta);
                Double custo = shopItem.getCost();
                if (Vault.getEconomy().has(player, custo)) {
                    if (InvCheio.quantoCabe(player, itemStack) > itemStack.getAmount()) {
                        player.getInventory().addItem(itemStack);
                        Vault.getEconomy().withdrawPlayer(player, custo);
                    } else {
                        player.sendMessage("§cO seu inventário está cheio!");
                    }
                } else {
                    player.sendMessage("§cVocê não tem saldo suficiente para comprar esse item!");
                }
            }
            if (isSimpleItem(e.getCurrentItem())) {
                SimpleItem simpleItem = getSimpleItem(e);
                ItemStack itemStack = simpleItem.getItemStack().clone();
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setLore(null);
                itemStack.setItemMeta(itemMeta);
                Double custo = simpleItem.getCost();
                if (Vault.getEconomy().has(player, custo)) {
                    if (InvCheio.quantoCabe(player, itemStack) > itemStack.getAmount()) {
                        player.getInventory().addItem(itemStack);
                        Vault.getEconomy().withdrawPlayer(player, custo);
                    } else {
                        player.sendMessage("§cO seu inventário está cheio!");
                    }
                } else {
                    player.sendMessage("§cVocê não tem saldo suficiente para comprar esse item!");
                }
            }
            try {
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Voltar")){
                    if (e.getCurrentItem().getType().equals(Material.ARROW)){
                        player.openInventory(Cache.getMainCategory());
                        player.updateInventory();
                    }
                }
            }catch (NullPointerException ignored){}
        }
        if (isCategoryItem(e)) {
            e.setResult(Event.Result.DENY);
            Player player = (Player) e.getWhoClicked();
            CategoryItem categoryItem = getCategoryItem(e);
            String category = categoryItem.getCategory();
            for (String key : Cache.getSinglePageInventories().keySet())
            {
                if (category.equals(key))
                {
                    player.openInventory(Cache.getSinglePageInventories().get(category));
                    player.updateInventory();
                }
            }
            for (String key : Cache.getMultiPageCategories().keySet())
            {
                if (category.equals(key))
                {
                    player.openInventory(Cache.getMultiPageCategories().get(category).get(0));
                    player.updateInventory();
                }
            }
        }
    }

    public static boolean isShopInventory(InventoryClickEvent thise)
    {
        for (String title : Cache.categories.values())
        {
            try
            {
                if (title.equals(thise.getClickedInventory().getTitle())) return true;
            } catch (NullPointerException ignored) {}
        }
        return false;
    }

    public static String getCategoryInventory(InventoryClickEvent thise){
        for (String title : Cache.categories.keySet())
        {
            if (Cache.categories.get(title).equals(thise.getClickedInventory().getTitle())) return title;
        }
        return null;
    }

    public static boolean isCategoryItem(InventoryClickEvent thise)
    {
        for (CategoryItem categoryItem : Cache.getCategoryItems())
        {
            try
            {
            if (categoryItem.getItemStack().equals(thise.getCurrentItem())) return true;
            } catch (NullPointerException ignored) {}
        }
        return false;
    }
    public static CategoryItem getCategoryItem(InventoryClickEvent thise)
    {
        for (CategoryItem categoryItem : Cache.getCategoryItems())
        {
            if (categoryItem.getItemStack().equals(thise.getCurrentItem())) return categoryItem;
        }
        return null;
    }

    public static boolean isShopItem(InventoryClickEvent thise)
    {
        for (ShopItem shopItem : Cache.getShopItems()) {
            try
            {
                if (shopItem.getItemStack().equals(thise.getCurrentItem())) return true;
            } catch (NullPointerException ignored) {}
        }
        return false;
    }
    public static ShopItem getShopItem(InventoryClickEvent thise)
    {
        for (ShopItem shopItem : Cache.getShopItems())
        {
            if (shopItem.getItemStack().equals(thise.getCurrentItem())) return shopItem;
        }
        return null;
    }

    public static boolean isSimpleItem(ItemStack itemStack)
    {
        for (SimpleItem simpleItem : Cache.getSimpleItems())
        {
            try {
                if (simpleItem.getItemStack().equals(itemStack)) return true;
            } catch (NullPointerException ignored) {}
        }
        return false;
    }
    public static SimpleItem getSimpleItem(InventoryClickEvent thise)
    {
        for (SimpleItem simpleItem : Cache.getSimpleItems())
        {
            if (simpleItem.getItemStack().equals(thise.getCurrentItem())) return simpleItem;
        }
        return null;
    }

}
