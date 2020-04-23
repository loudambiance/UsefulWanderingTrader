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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

import com.chumcraft.usefulwanderingtrader.heads.Head;
import com.chumcraft.usefulwanderingtrader.heads.Heads;
import com.chumcraft.usefulwanderingtrader.heads.Miniblock;

import java.util.ArrayList;
import java.util.Arrays;

public class WanderingTraderListener implements Listener
{
     private UWTPlugin plugin;

     public WanderingTraderListener(){
          this.plugin = UWTPlugin.getInstance();
     }

     private List<MerchantRecipe> trades(List<MerchantRecipe> recipes, Heads headsitem, Heads headsettings)
     {
          int price = headsettings.getIntSetting("price");
          int quantity = headsettings.getIntSetting("quantity");
          int restock = headsettings.getIntSetting("restock");
          String payment = headsettings.getStringSetting("payment");
          boolean blockrequired = headsettings.getBooleanSetting("requireblock");
          ArrayList<Head> headslist = headsitem.getRandomHeads();
          List<MerchantRecipe> newRecipes = new ArrayList<MerchantRecipe>();
          for(Head head : headslist){
               MerchantRecipe recipe = new MerchantRecipe(head.skull, quantity);
               recipe.addIngredient(new ItemStack(Material.getMaterial(payment), price));
               if(head instanceof Miniblock){
                    if(blockrequired && ((Miniblock)head).block_quantity > 0){
                         try{
                              recipe.addIngredient(new ItemStack(((Miniblock)head).block, ((Miniblock)head).block_quantity));
                         }
                         catch(Exception e){
                              this.plugin.getLogger().warning(((Miniblock)head).name);
                              this.plugin.getLogger().warning(e.getStackTrace().toString());
                         }
                    }
               }
               //recipe.setMaxUses(restock);
               newRecipes.add(recipe);
          }
          newRecipes.addAll(recipes);
          return newRecipes;
     }

     private List<MerchantRecipe> trades(List<MerchantRecipe> recipes, Heads heads, boolean enabled)
     {
          Random r = new Random();
          int chance = r.nextInt(100)+1;
          int percentage = heads.getIntSetting("percentchance");
          if(enabled && (chance <= percentage)){
               return trades(recipes, heads, heads);
          }else{
               return recipes;
          }
     }

     private List<MerchantRecipe> combinedTrades(List<MerchantRecipe> recipes){
          int quantity = this.plugin.getConfiguration().getIntSetting("combined", "quantity");
          int max = this.plugin.getConfiguration().getIntSetting("combined", "max");
          int min = this.plugin.getConfiguration().getIntSetting("combined", "min");
          List<MerchantRecipe> ret = new ArrayList<MerchantRecipe>();
          Random rand = new Random();
          int numrecipes = rand.nextInt(max-min+1)+min;
          int recipeListSize = recipes.size()-1;
          if (recipeListSize < numrecipes ) {
               return recipes;
          }
          int[] triedindexes = new int[numrecipes];
          for(int k = 0; k<numrecipes&&k<recipeListSize; k++){
               int newIndex = rand.nextInt(recipeListSize+1);
               if(!Arrays.stream(triedindexes).anyMatch(i -> i == newIndex)){
                    ret.add(recipes.get(newIndex));
                    triedindexes[k] = newIndex;
               }
          }
          for(MerchantRecipe recipe : ret){
               recipe.setMaxUses(quantity);
          }

          return ret;
          
     }

     @EventHandler
     public void onSpawn(CreatureSpawnEvent event)
     {
          if(event.getEntityType() == EntityType.WANDERING_TRADER){
               boolean headsenabled = this.plugin.getPlayerHeads().getBooleanSetting("enabled");
               boolean miniblocksenabled = this.plugin.getMiniblocks().getBooleanSetting("enabled");
               boolean passivesenabled = this.plugin.getPassiveMobHeads().getBooleanSetting("enabled");
               boolean hostileenabled = this.plugin.getHostileMobHeads().getBooleanSetting("enabled");
               boolean neutralenabled = this.plugin.getNeutralMobHeads().getBooleanSetting("enabled");
               int combined = this.plugin.getConfiguration().getIntSetting("combined", "combine");
               WanderingTrader trader = (WanderingTrader)event.getEntity();
               List<MerchantRecipe> recipes = trader.getRecipes();
               List<MerchantRecipe> recipescombined = new ArrayList<MerchantRecipe>();
               recipes = trades(recipes,this.plugin.getMiniblocks(), miniblocksenabled);
               switch(combined)
               {
                    default:
                    case 0:
                         recipes = trades(recipes,this.plugin.getPassiveMobHeads(), passivesenabled);
                         recipes = trades(recipes,this.plugin.getNeutralMobHeads(), neutralenabled);
                         recipes = trades(recipes,this.plugin.getHostileMobHeads(), hostileenabled);
                         recipes = trades(recipes,this.plugin.getPlayerHeads(), headsenabled);
                         break;
                    case 1:
                         recipescombined = trades(recipescombined,this.plugin.getPassiveMobHeads(), passivesenabled);
                         recipescombined = trades(recipescombined,this.plugin.getNeutralMobHeads(), neutralenabled);
                         recipescombined = trades(recipescombined,this.plugin.getHostileMobHeads(), hostileenabled);
                         recipescombined = trades(recipescombined,this.plugin.getPlayerHeads(), headsenabled);
                         recipescombined = combinedTrades(recipescombined);
                         recipescombined.addAll(recipes);
                         recipes = recipescombined;
                         break;
                    case 2:
                         recipescombined = trades(recipescombined,this.plugin.getPassiveMobHeads(), passivesenabled);
                         recipescombined = trades(recipescombined,this.plugin.getNeutralMobHeads(), neutralenabled);
                         recipescombined = trades(recipescombined,this.plugin.getHostileMobHeads(),hostileenabled);
                         recipescombined = combinedTrades(recipescombined);
                         recipescombined.addAll(recipes);
                         recipes = recipescombined;
                         recipes = trades(recipes,this.plugin.getPlayerHeads(), headsenabled);
                         break;
               }
               trader.setRecipes(recipes);

          }
     }
}