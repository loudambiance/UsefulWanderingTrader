# UsefulWanderingTrader

Spigot Plugin to reimplement [Vanilla Tweaks Wandering Trades](Https://www.vanillatweaks.com) datapack as spigot plugin with additional functionality.

Plugin adds three new category of trades to the Wandering Traders
- Player Heads (Players from your server)
- Player Heads (Any custom head)
- Miniblocks

# Settings
## config.yml
---
This file controls the global settings. The defaults are:
```
heads:
  enabled: true
  payment: EMERALD
  price: 1 
  max: 3 
  min: 1 
  quantity: 3
  stacksize: 1 
  restock: 0 
  extraheads: true 

miniblocks:
  enabled: true
  requireblock: true 
  payment: EMERALD 
  price: 1 
  max: 8 
  min: 5 
  quantity: 1 
  stacksize: 8 
  restock: 0 
```
The two sections control the settings for playerheads (heads) and the miniblocks.

The settings individually are:  
|Setting Name|Description|Applies to|
|---|---|---|
|enabled|Controls whether or not that type of trade is enabled|Both|
|payment|What item is used for payment, this must be an exact match to something in the [Spigot Materials list](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html)|Both|
|price|How many of the payment items are required for the trade|Both|
|max|The maximum number of potential trades that will be added of this type to a single Wandering Trader|Both|
|min|The minimum number of potential trades that will be added of this type to a single Wandering Trader|Both|
|quantity|The number of items the Wandering Trader carriers without needing to restock|Both|
|stacksize|The number of items received in a single trade|Both|
|restock|The number of times the Wandering Trader will restock the trade per day|Both|
|extraheads|Enable extra heads from the playerheads.yml config file|heads|
|requireblock|Require a full block as part of the payment to acquire a miniblock|miniblock|

The settings data types:
|Setting Name|Data type|Applies to|
|---|---|---|
|enabled|boolean|Both|
|payment| [Material](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html)|Both|
|price|integer|Both|
|max|integer|Both|
|min|integer|Both|
|quantity|integer|Both|
|stacksize|integer|Both|
|restock|integer|Both|
|extraheads|boolean|heads|
|requireblock|boolean|miniblock|

## miniblocks.yml
---
This file controls the configuration of each miniblock. You can add any number of additional blocks by adding new blocks in the following format:

```
unique_block_name:
  name: Name To Display to Players
  texture: 36d1fabdf3e342671bd9f95f687fe263f439ddc2f1c9ea8ff15b13f1e7e48b9
  block: Material
  block_quantity: 1
  enabled: true
```
The settings individually are:  
|Setting Name|Description|
|---|---|
|name|Descriptive name that displays to players|
|texture|Identified from textures.minecraft.net/texture url|
|block|Block that will need to traded as part of the purchase|
|block_quantity|Number of blocks that will be needed as part of the trade|
|enabled|Enable/disable the individual block|

The texture identified is taken from a minecraft.net textures URL. An example would be:  
http://textures.minecraft.net/texture/b575a2691b96ed113d3741af38f75780737670d8ea8c27491366e5f254882cf0  
With this example, the identifier is:  
b575a2691b96ed113d3741af38f75780737670d8ea8c27491366e5f254882cf0  
These textures can be searched for on [Minecraft-Heads.com](https://minecraft-heads.com/custom-heads). The identifier is listed as the last item on any head page.

## **I will not accept pull requests for non-vanilla blocks**
The settings data types:
|Setting Name|Data type|
|---|---|
|name|string|
|texture|string|
|block|[Material](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html)|
|block_quantity|integer|
|enabled|boolean|

## playerheads.yml
---
This file controls the configuration of extraheads. You can add any number of additional player heads by adding new heads in the following format:

```
unique_player_name:
  name: Player Name displayed to Players
  texture: ee9640186a709377881009848ec7ebda819eb18c4e5967ae1d0ac7146afb4b4e
  enabled: true
```
The settings individually are:  
|Setting Name|Description|
|---|---|
|name|Descriptive name that displays to players|
|texture|Identified from textures.minecraft.net/texture url|
|enabled|Enable/disable the individual block|

The texture identified is taken from a minecraft.net textures URL. An example would be:  
http://textures.minecraft.net/texture/b575a2691b96ed113d3741af38f75780737670d8ea8c27491366e5f254882cf0  
With this example, the identifier is:  
b575a2691b96ed113d3741af38f75780737670d8ea8c27491366e5f254882cf0  
These textures can be searched for on [Minecraft-Heads.com](https://minecraft-heads.com/custom-heads). The identifier is listed as the last item on any head page.

## **I will not accept pull requests for player heads**
The settings data types:
|Setting Name|Data type|
|---|---|
|name|string|
|texture|string|
|enabled|boolean|