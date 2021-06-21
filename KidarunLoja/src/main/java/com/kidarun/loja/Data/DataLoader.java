package com.kidarun.loja.Data;

import com.kidarun.loja.Main;
import com.kidarun.loja.Manager.*;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.kidarun.loja.Manager.MultiPageCategory.createMultiPageCategoryInventories;

public class DataLoader {
    public static void setMainCategory(){
        FileConfiguration config = Main.getInstance().getConfig();
        String titulo = config.getString("categoria-principal.titulo");
        int rows = config.getInt("categoria-principal.quantidade-de-linhas");
        List<CategoryItem> categoryItems = new ArrayList<>();
        for (String category : config
                .getConfigurationSection("categoria-principal.categorias").getKeys(false)){
            try {
                String[] split = config.getString("categoria-principal.categorias."+category).split(",");
                Material material = Material.getMaterial(split[0]);
                short durability = Short.parseShort(split[1]);
                String name = split[2].replaceAll("&", "§");
                String[] splitedlore = split[3].replaceAll("&", "§").split("%%");
                List<String> lore = new ArrayList<>();
                Collections.addAll(lore, splitedlore);
                int slot = Integer.parseInt(split[4]);
                ItemStack itemStack = new ItemStack(material);
                itemStack.setDurability(durability);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                CategoryItem categoryItem = new CategoryItem(slot, itemStack, category);
                categoryItems.add(categoryItem);
                Cache.addCategoryItem(categoryItem);
            }catch (Exception e){
                System.out.println("Houve um erro ao carregar a categoria "+category);
                e.printStackTrace();
            }
        }
        Cache.setMainCategory(MainCategory.createMainInventory(categoryItems, rows, titulo));
    }
    public static void loadMultiPageInventories() {
        FileConfiguration config = Main.getInstance().getConfig();
        ConfigurationSection configurationSection = config.getConfigurationSection("loja-money.multiplas-paginas");
        for (String categoria : configurationSection.getKeys(false)) {
            String titulo = config.getString("loja-money.multiplas-paginas."+categoria+".titulo").replaceAll("&","§");
            List<SimpleItem> simpleItems = new ArrayList<>();
            for (String item : config.getStringList("loja-money.multiplas-paginas."+categoria+".itens")) {
                try {
                    String[] split = item.split(",");
                    System.out.println(item);
                    Material material = Material.getMaterial(split[0]);
                    int quantidade = Integer.parseInt(split[1]);
                    short durability = Short.parseShort(split[2]);
                    double cost = Double.parseDouble(split[3]);
                    ItemStack itemStack = new ItemStack(material);
                    itemStack.setDurability(durability);
                    itemStack.setAmount(quantidade);
                    SimpleItem simpleItem = new SimpleItem(itemStack, cost);
                    simpleItems.add(simpleItem);
                    Cache.addSimpleItem(simpleItem);
                } catch (Exception e) {
                    System.out.println("Houve um erro ao carregar a categoria " + item);
                    e.printStackTrace();
                }
            }
            Cache.addMultiPageCategoryInventories(categoria,
                    createMultiPageCategoryInventories(simpleItems, titulo));
            Cache.addCategory(categoria, titulo);
        }
    }
    public static void loadSinglePageInventories() {
        FileConfiguration config = Main.getInstance().getConfig();
        ConfigurationSection configurationSection = config.getConfigurationSection("loja-money.pagina-unica");
        for (String categoria : configurationSection.getKeys(false)) {
            String titulo = config.getString("loja-money.pagina-unica."+categoria+".titulo").replaceAll("&","§");
            List<ShopItem> shopItems = new ArrayList<>();
            for (String item : config.getConfigurationSection("loja-money.pagina-unica."+categoria+".itens").getKeys(false)) {
                try {
                    int slot = Integer.parseInt(item);
                    String itemName = config.getString("loja-money.pagina-unica."+categoria+".itens."+item+".name");
                    Material material = Material.getMaterial(config.getString("loja-money.pagina-unica."+categoria+".itens."+item+".material"));
                    int quantidade = config.getInt("loja-money.pagina-unica."+categoria+".itens."+item+".quantidade");
                    short durability = (short) config.getInt("loja-money.pagina-unica."+categoria+".itens."+item+".durabilidade");
                    double cost = config.getDouble("loja-money.pagina-unica."+categoria+".itens."+item+".valor");
                    List<String> lore = config.getStringList("loja-money.pagina-unica."+categoria+".itens."+item+".desc");
                    for (int i = 0;i<lore.size();i++){lore.set(i, lore.get(i).replaceAll("&","§"));}
                    List<String> enchantments = config.getStringList("loja-money.pagina-unica."+categoria+".itens."+item+".encantamentos");
                    ItemStack itemStack = new ItemStack(material);
                    itemStack.setDurability(durability);
                    itemStack.setAmount(quantidade);
                    for (String ench : enchantments){
                        String[] split = ench.split(":");
                        Enchantment enchantment = Enchantment.getByName(split[0]);
                        short level = Short.parseShort(split[1]);
                        itemStack.addUnsafeEnchantment(enchantment, level);
                    }
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName(itemName);
                    itemMeta.setLore(lore);
                    itemStack.setItemMeta(itemMeta);
                    ShopItem shopItem = new ShopItem(slot, itemStack, cost);
                    shopItems.add(shopItem);
                    Cache.addShopitem(shopItem);
                } catch (Exception e) {
                    System.out.println("Houve um erro ao carregar a categoria " + item);
                    e.printStackTrace();
                }
            }
            Cache.addSinglePageInventory(categoria,
                    SinglePageCategory.createSinglePageCategory(shopItems, titulo));
            Cache.addCategory(categoria, titulo);
        }
    }
}
