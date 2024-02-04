package play.mcdev.tv.ultimatefeatues.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import play.mcdev.tv.ultimatefeatues.Main;

import java.util.Arrays;

public class ItemBuilder {
    private ItemMeta itemMeta;
    private ItemStack itemStack;
    public ItemBuilder(Material material){
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }
    public ItemBuilder setDisplayName(String displayName){
        itemMeta.setDisplayName(displayName);
        return this;
    }
    public ItemBuilder setLocalizedName(String localizedName){
        itemMeta.setLocalizedName(localizedName);
        return this;
    }
    public ItemBuilder setLore(String... lore){
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }
    public ItemBuilder setGlow(){
        itemMeta.addEnchant(Enchantment.ARROW_FIRE, 0, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }
    public ItemBuilder addEnchantment(Enchantment enchantment, int level, boolean ignore){
        itemMeta.addEnchant(enchantment, level, ignore);
        return this;
    }
    public ItemBuilder setUnbreakable(){
        itemMeta.setUnbreakable(true);
        return this;
    }
    public ItemBuilder addItemFlag(ItemFlag... itemFlags){
        itemMeta.addItemFlags(itemFlags);
        return this;
    }
    public ItemStack build(){
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
