package com.chumcraft.usefulwanderingtrader;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Miniblock {
    public String name;
    public String texture;
    public Material block;
    public int block_quantity;
    public boolean enabled;
    public ItemStack skull;

    public Miniblock(String name, String texture, Material block, int block_quantity, boolean enabled){
        this.name = name;
        this.texture = texture;
        this.block = block;
        this.block_quantity = block_quantity;
        this.enabled = enabled;
    }
}