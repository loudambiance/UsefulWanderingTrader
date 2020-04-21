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

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;

import com.chumcraft.usefulwanderingtrader.configuration.Configuration;
import com.chumcraft.usefulwanderingtrader.configuration.playerheadConfiguration;

import java.util.ArrayList;

public class PlayerHeads extends Heads{
    private ArrayList<String> playernames = new ArrayList<String>();

    public PlayerHeads(Configuration config) 
    {
        super(config);
        this.sectionname = "heads";
        this.headsConfig = headsConfig();
        loadHeads();
    }

    @Override
    protected Configuration headsConfig(){
        return new playerheadConfiguration();
    }

    private void updateHeadList(OfflinePlayer[] playerlist)
    {
        int stacksize = this.config.getIntSetting(this.sectionname, "stacksize");
        for (OfflinePlayer o : playerlist) {
            if(!this.playernames.contains(o.getUniqueId().toString())){
                this.playernames.add(o.getUniqueId().toString());
                Playerhead phead = new Playerhead(o,true,stacksize);
                this.HeadList.add(phead);
            }
        }
    }

    @Override
    protected void loadHeads(){
        loadHeadsFromServer();
    }

    @Override
    protected void getHeadsReturnAdd(ArrayList<Head> ret, ConfigurationSection section)
    {
        ret.add(new Playerhead(section));
    }

    private void loadHeadsFromServer(){
        boolean extraheads = this.config.getBooleanSetting(this.sectionname, "extraheads");
        this.updateHeadList(Bukkit.getOfflinePlayers());
        if (Bukkit.hasWhitelist()) {
            this.updateHeadList(Bukkit.getWhitelistedPlayers().toArray(
                new OfflinePlayer[Bukkit.getWhitelistedPlayers().size()]));    
        }
        this.updateHeadList(Bukkit.getOperators().toArray(
                new OfflinePlayer[Bukkit.getOperators().size()]));
        if(extraheads){
            this.loadHeadsFromConfig();
        }
    }

}