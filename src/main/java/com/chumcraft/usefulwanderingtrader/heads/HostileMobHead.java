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

public class HostileMobHead extends Head {

    public HostileMobHead(String name, String texture, boolean enabled, int stacksize){
        super(name, texture, enabled,stacksize);
    }

    public HostileMobHead(ConfigurationSection section){
        super(section);
    }
}