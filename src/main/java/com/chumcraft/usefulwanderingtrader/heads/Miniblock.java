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

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.Material;

public class Miniblock extends Head{
    public Material block;
    public int block_quantity;

    public Miniblock(String name, String texture, Material block, int block_quantity, boolean enabled, int stacksize){
        super(name, texture, enabled,stacksize);
        this.block = block;
        this.block_quantity = block_quantity;
    }

    public Miniblock(ConfigurationSection section){
        super(section);
        this.block = Material.getMaterial(section.getString("block"));
        this.block_quantity = section.getInt("block_quantity");
    }
}