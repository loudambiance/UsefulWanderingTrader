/**
 * Copyright (C) 2020 Daniel Baucom
 * 
 * This program is free software: you can redistribute it and/or modify it 
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/license
 * 
 */

package com.chumcraft.usefulwanderingtrader.heads;

import com.chumcraft.usefulwanderingtrader.utils.SkullCreator;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public abstract class Head {
    public String name;
    public String texture;
    public boolean enabled;
    public ItemStack skull;

    public Head(String name, boolean enabled){
        this.name = name;
        this.enabled = enabled;
    }

    public Head(String name, String texture, boolean enabled, int stacksize) {
        this.name = name;
        this.texture = texture;
        this.enabled = enabled;
        this.skull = createSkull(stacksize);
    }

    public Head(ConfigurationSection section){
        this(section.getString("name"), section.getString("texture"), 
            section.getBoolean("enabled"), section.getInt("stacksize"));
    }

    protected ItemStack createSkull(int stacksize){
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, stacksize);
        return SkullCreator.itemWithUrl(head, this.texture, this.name);
    }
}