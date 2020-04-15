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

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Miniblocks {

    public ArrayList<Miniblock> MiniblocksList = new ArrayList<Miniblock>();
    private UWTPlugin plugin;

    public Miniblocks(UWTPlugin plugin) 
    {
        this.plugin = plugin;
        if(this.plugin.getConfiguration().getBooleanSetting("miniblocks", "enabled"))
        {
            updateMiniblocksList();
        }
        
    }

    public void updateMiniblocksList() {
        ArrayList<Miniblock> miniblocks = this.plugin.getConfiguration().loadminiblocksConfig();
        int stacksize = this.plugin.getConfiguration().getIntSetting("miniblocks", "stacksize");
        for(Miniblock miniblock : miniblocks){
            if(miniblock.enabled){
                ItemStack head = new ItemStack(Material.PLAYER_HEAD, stacksize);
                miniblock.skull = SkullCreator.itemWithUrl(head, miniblock.texture, miniblock.name);
                this.MiniblocksList.add(miniblock);
            }
        }
    }

    public ArrayList<Miniblock> getRandomMiniBlock(){
        int max = this.plugin.getConfiguration().getIntSetting("miniblocks", "max");
        int min = this.plugin.getConfiguration().getIntSetting("miniblocks", "min");
        Random rand = new Random();
        int numMiniblocks = rand.nextInt(max-min+1)+min;
        ArrayList<Miniblock> ret = new ArrayList<Miniblock>();   
        int miniblockListSize = this.MiniblocksList.size()-1;
        if (miniblockListSize < numMiniblocks ) {
            return this.MiniblocksList;
        }
        int[] triedindexes = new int[numMiniblocks];
        for(int k = 0; k<numMiniblocks&&k<miniblockListSize; k++){
            int newIndex = rand.nextInt(miniblockListSize+1);
            if(!Arrays.stream(triedindexes).anyMatch(i -> i == newIndex)){
                ret.add(this.MiniblocksList.get(newIndex));
                triedindexes[k] = newIndex;
            }
        }
        return ret;
    }
}