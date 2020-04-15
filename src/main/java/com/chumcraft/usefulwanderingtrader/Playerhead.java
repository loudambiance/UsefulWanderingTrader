package com.chumcraft.usefulwanderingtrader;

import org.bukkit.inventory.ItemStack;

public class Playerhead {
    public String name;
    public String texture;
    public boolean enabled;
    public ItemStack skull;

    public Playerhead(String name, String texture, boolean enabled){
        this.name = name;
        this.texture = texture;
        this.enabled = enabled;
    }
}