package com.meteor.blockinPractice.Utils;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemStackFactory {
    ItemStack item;

    private ItemStackFactory() {
    }

    public ItemStackFactory(ItemStack is) {
        if (is != null) this.item = is.clone();
    }

    public ItemStackFactory(Material type) {
        this(type, 1);
    }

    public ItemStackFactory(Material type, int amount) {
        this.item = new ItemStack(type, amount);
    }

    public ItemStack toItemStack() {
        return this.item;
    }

    public ItemStackFactory setType(Material type) {
        this.item.setType(type);
        return this;
    }

    public ItemStackFactory setDurability(int i) {
        ItemMeta im = this.item.getItemMeta();
        if (im instanceof Damageable) {
            ((Damageable) im).setDamage(i);
            this.item.setItemMeta(im);
        }
        return this;
    }

    public ItemStackFactory setAmount(int a) {
        this.item.setAmount(a);
        return this;
    }

    public ItemStackFactory setDisplayName(String name) {
        ItemMeta im = this.item.getItemMeta();
        if (im != null) {
            im.setDisplayName(ColorParser.parse(name));
            this.item.setItemMeta(im);
        }
        return this;
    }

    public ItemStackFactory setLore(List<String> loreList) {
        ItemMeta im = this.item.getItemMeta();
        if (im != null) {
            im.setLore(
                    loreList.stream()
                            .map(ColorParser::parse)
                            .collect(Collectors.toList())
            );
            this.item.setItemMeta(im);
        }
        return this;
    }

    public ItemStackFactory addLore(String s) {
        ItemMeta im = this.item.getItemMeta();
        if (im != null) {
            List<String> lore = im.getLore() != null ? im.getLore() : new ArrayList<>();
            lore.add(ColorParser.parse(s));
            im.setLore(lore);
            this.item.setItemMeta(im);
        }
        return this;
    }

    public ItemStackFactory addEnchant(Enchantment enchant, int level, boolean ignoreLevelRestriction) {
        ItemMeta im = this.item.getItemMeta();
        if (im != null) {
            im.addEnchant(enchant, level, ignoreLevelRestriction);
            this.item.setItemMeta(im);
        }

        return this;
    }

    public ItemStackFactory removeEnchant(Enchantment enchant) {
        ItemMeta im = this.item.getItemMeta();
        if (im != null) {
            im.removeEnchant(enchant);
            this.item.setItemMeta(im);
        }
        return this;
    }

    public ItemStackFactory addFlag(ItemFlag flag) {
        ItemMeta im = this.item.getItemMeta();
        if (im != null) {
            im.addItemFlags(flag);
            this.item.setItemMeta(im);
        }

        return this;
    }

    public ItemStackFactory removeFlag(ItemFlag flag) {
        ItemMeta im = this.item.getItemMeta();
        if (im != null) {
            im.removeItemFlags(flag);
            this.item.setItemMeta(im);
        }
        return this;
    }

    public ItemStackFactory setUnbreakable(boolean unbreakable) {
        ItemMeta im = this.item.getItemMeta();
        if (im != null) {
            im.setUnbreakable(unbreakable);
            this.item.setItemMeta(im);
        }
        return this;
    }
}