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
import java.util.ArrayList;
import java.util.Arrays;

public class WanderingTraderListener implements Listener
{
     private UWTPlugin plugin;

     public WanderingTraderListener(UWTPlugin plugin){
          this.plugin = plugin;
     }


     private List<MerchantRecipe> headTrades(List<MerchantRecipe> recipes)
     {
          int price = this.plugin.getConfiguration().getIntSetting("heads", "price");
          int quantity = this.plugin.getConfiguration().getIntSetting("heads", "quantity");
          String payment = this.plugin.getConfiguration().getStringSetting("heads", "payment");
          ArrayList<ItemStack> heads = this.plugin.getPlayerHeads().getRandomHeads();
          List<MerchantRecipe> newRecipes = new ArrayList<MerchantRecipe>();
          for(ItemStack head : heads){
               MerchantRecipe recipe = new MerchantRecipe(head, quantity);
               List<ItemStack> newBuys = Arrays.asList(new ItemStack(Material.getMaterial(payment), price));
               recipe.setIngredients(newBuys);
               newRecipes.add(recipe);
          }
          newRecipes.addAll(recipes);
          return newRecipes;
     }

     private List<MerchantRecipe> miniBlockTrades(List<MerchantRecipe> recipes)
     {
          int price = this.plugin.getConfiguration().getIntSetting("miniblocks", "price");
          int quantity = this.plugin.getConfiguration().getIntSetting("miniblocks", "quantity");
          String payment = this.plugin.getConfiguration().getStringSetting("miniblocks", "payment");
          boolean blockrequired = this.plugin.getConfiguration().getBooleanSetting("miniblocks", "requireblock");
          ArrayList<Miniblock> miniblocks = this.plugin.getMiniblocks().getRandomMiniBlock();
          List<MerchantRecipe> newRecipes = new ArrayList<MerchantRecipe>();
          for(Miniblock miniblock : miniblocks){
               MerchantRecipe recipe = new MerchantRecipe(miniblock.skull, quantity);
               //List<ItemStack> newBuys = Arrays.asList(new ItemStack(Material.getMaterial(payment), price));
               //newBuys.add(new ItemStack(miniblock.block, miniblock.block_quantity));
               //recipe.setIngredients(newBuys);
               recipe.addIngredient(new ItemStack(Material.getMaterial(payment), price));
               if(blockrequired && miniblock.block_quantity > 0){
                    recipe.addIngredient(new ItemStack(miniblock.block, miniblock.block_quantity));
               }
               newRecipes.add(recipe);
          }
          newRecipes.addAll(recipes);
          return newRecipes;
     }

     @EventHandler
     public void onSpawn(CreatureSpawnEvent event)
     {
          boolean headsenabled = this.plugin.getConfiguration().getBooleanSetting("heads", "enabled");
          boolean miniblocksenabled = this.plugin.getConfiguration().getBooleanSetting("miniblocks", "enabled");
          if(event.getEntityType() == EntityType.WANDERING_TRADER){
               WanderingTrader trader = (WanderingTrader)event.getEntity();
               List<MerchantRecipe> recipes = trader.getRecipes();
               if(miniblocksenabled)
               {
                    recipes = miniBlockTrades(recipes);
               }
               if(headsenabled){
                    recipes = headTrades(recipes);
               }
               trader.setRecipes(recipes);
          }
     }
}