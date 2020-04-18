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

import org.bstats.bukkit.Metrics;
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
        this.updateMetrics();

        // Commands enabled with following method must have entries in plugin.yml
        getServer().getPluginManager().registerEvents(new WanderingTraderListener(this), this);
    }

    public Configuration getConfiguration() {
        return this.config;
    }

    public PlayerHeads getPlayerHeads() {
        return this.playerheads;
    }

    public Miniblocks getMiniblocks() {
        return this.miniblocks;
    }

    private void updateMetrics() {
        int bStatID = 7211;
        Metrics metrics = new Metrics(this, bStatID);

        // Optional: Add custom charts
        String playerheads = Integer.toString(this.playerheads.PlayerHeadList.size());
        String headprice = Integer.toString(this.config.getIntSetting("heads", "price"));
        String headmax = Integer.toString(this.config.getIntSetting("heads", "max"));
        String headmin = Integer.toString(this.config.getIntSetting("heads", "min"));
        String headenabled = Boolean.toString(this.config.getBooleanSetting("heads", "enabled"));
        String extraheadsenabled = Boolean.toString(this.config.getBooleanSetting("heads", "extraheads"));
        String miniblockprice = Integer.toString(this.config.getIntSetting("miniblocks", "price"));
        String miniblockmax = Integer.toString(this.config.getIntSetting("miniblocks", "max"));
        String miniblockmin = Integer.toString(this.config.getIntSetting("miniblocks", "min"));
        String miniblockenabled = Boolean.toString(this.config.getBooleanSetting("miniblocks", "enabled"));
        metrics.addCustomChart(new Metrics.SimplePie("player_heads", () -> playerheads));
        metrics.addCustomChart(new Metrics.SimplePie("head_price", () -> headprice));
        metrics.addCustomChart(new Metrics.SimplePie("head_max", () -> headmax));
        metrics.addCustomChart(new Metrics.SimplePie("head_min", () -> headmin));
        metrics.addCustomChart(new Metrics.SimplePie("heads_enabled", () -> headenabled));
        metrics.addCustomChart(new Metrics.SimplePie("extra_heads_enabled", () -> extraheadsenabled));
        metrics.addCustomChart(new Metrics.SimplePie("miniblock_price", () -> miniblockprice));
        metrics.addCustomChart(new Metrics.SimplePie("miniblock_max", () -> miniblockmax));
        metrics.addCustomChart(new Metrics.SimplePie("miniblock_min", () -> miniblockmin));
        metrics.addCustomChart(new Metrics.SimplePie("miniblocks_enabled", () -> miniblockenabled));

    }
}
