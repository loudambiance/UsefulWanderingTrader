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
