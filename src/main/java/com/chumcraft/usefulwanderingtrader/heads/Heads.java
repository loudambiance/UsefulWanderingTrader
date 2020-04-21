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

import com.chumcraft.usefulwanderingtrader.configuration.Configuration;
import com.chumcraft.usefulwanderingtrader.utils.SkullCreator;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Heads {

    public ArrayList<Head> HeadList = new ArrayList<Head>();
    protected Configuration config;
    protected Configuration headsConfig;
    protected String sectionname;

    public Heads(Configuration config) 
    {
        this.config = config;
        this.headsConfig = headsConfig();
    }

    protected ArrayList<Head> getHeads()
    {
        Set<String> keys = this.headsConfig.getKeys(false);
        ArrayList<Head> ret = new ArrayList<Head>();
        for(String key : keys){
            ConfigurationSection section = this.headsConfig.getConfigurationSection(key);
            getHeadsReturnAdd(ret, section);
        }
        return ret;
    }

    protected abstract void getHeadsReturnAdd(ArrayList<Head> ret, ConfigurationSection section);

    protected abstract Configuration headsConfig();

    public ArrayList<Head> getRandomHeads(){
        int max = this.config.getIntSetting(sectionname, "max");
        int min = this.config.getIntSetting(sectionname, "min");
        Random rand = new Random();
        int numheads = rand.nextInt(max-min+1)+min;
        ArrayList<Head> ret = new ArrayList<Head>();
        int headListSize = this.HeadList.size()-1;
        if (headListSize < numheads ) {
            return this.HeadList;
        }
        int[] triedindexes = new int[numheads];
        for(int k = 0; k<numheads&&k<headListSize; k++){
            int newIndex = rand.nextInt(headListSize+1);
            if(!Arrays.stream(triedindexes).anyMatch(i -> i == newIndex)){
                ret.add(this.HeadList.get(newIndex));
                triedindexes[k] = newIndex;
            }
        }
        return ret;
    }

    protected void loadHeadsFromConfig(){
        ArrayList<Head> heads = this.getHeads();
        int stacksize = this.config.getIntSetting(sectionname, "stacksize");
        for(Head head : heads){
            ItemStack ihead = new ItemStack(Material.PLAYER_HEAD, stacksize);
            head.skull = SkullCreator.itemWithUrl(ihead, head.texture, head.name);
            this.HeadList.add(head);
        }
    }

    protected void loadHeads()
    {
        if(this.config.getBooleanSetting(this.sectionname, "enabled"))
            loadHeadsFromConfig();
    }
}