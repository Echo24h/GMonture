package fr.aang.gmonture.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

    public static ItemStack getItem(Material material, String custom_name, List<String> lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(custom_name);
        if (lore != null && !lore.isEmpty())
            itemM.setLore(lore);
        item.setItemMeta(itemM);;
        return item;
    }

    public static EntityType getEntityByName(String name) {
        for (EntityType type : EntityType.values()) {
            if(type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    public static Material getMaterialByName(String name) {
        for (Material type : Material.values()) {
            if(type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByMaterial(Material material) {

        for (int i = 0; i < Material.values().length; i++) {
            if(Material.values()[i].equals(material)) {
                return Material.values()[i].name().toLowerCase();
            }
        }
        return null;
    }
}
