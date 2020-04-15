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

package com.chumcraft.usefulwanderingtrader;

import org.bukkit.plugin.java.JavaPlugin;

public class UWTPlugin extends JavaPlugin {
    private Configuration config;
    private PlayerHeads playerheads;
    private Miniblocks miniblocks;

    @Override
    public void onDisable() {
        // Don't log disabling, Spigot does that for you automatically!
    }

    @Override
    public void onEnable() {
        // Don't log enabling, Spigot does that for you automatically!
        this.config = new Configuration(this);
        this.playerheads = new PlayerHeads(this);
        this.miniblocks = new Miniblocks(this);

        // Commands enabled with following method must have entries in plugin.yml
        getServer().getPluginManager().registerEvents(new WanderingTraderListener(this), this);
    }

    public Configuration getConfiguration()
    {
        return this.config;
    }

    public PlayerHeads getPlayerHeads(){
        return this.playerheads;
    }

    public Miniblocks getMiniblocks(){
        return this.miniblocks;
    }
}
