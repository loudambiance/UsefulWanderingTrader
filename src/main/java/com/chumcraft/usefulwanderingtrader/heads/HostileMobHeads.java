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

import com.chumcraft.usefulwanderingtrader.configuration.Configuration;
import com.chumcraft.usefulwanderingtrader.configuration.hostilemobConfiguration;

import java.util.ArrayList;

public class HostileMobHeads extends Heads {

    public HostileMobHeads(Configuration config) 
    {
        super(config);
        this.sectionname = "hostileheads";
        loadHeads();
        this.headsConfig = headsConfig();
    }

    @Override
    protected void getHeadsReturnAdd(ArrayList<Head> ret, ConfigurationSection section)
    {
        ret.add(new HostileMobHead(section));
    }

    @Override
    protected Configuration headsConfig(){
        return new hostilemobConfiguration();
    }
}