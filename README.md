# UsefulWanderingTrader

Spigot Plugin to reimplement [Vanilla Tweaks Wandering Trades](https://www.vanillatweaks.net/picker/datapacks/) datapack as spigot plugin with additional functionality.

Plugin adds three new category of trades to the Wandering Traders
- Player Heads (Players from your server)
- Player Heads (Any custom head)
- Miniblocks

By default, the Wandering Trader will add between 1 and 3 player heads for trade (assuming your server has that many players), and 5-8 miniture blocks. Each trade costs 1 Emerald. The miniblocks also require the full size block as part of the payment. For the trade, the player will receive 1 player head per trade, maxing out at 3, and 8 miniblocks per trade, with only 1 trade per available trade possible.

The player heads available for trade are taken from 4 places (in the default configuration, only 1 is able to be turned off), in the following order:
- Offline Players (all players who have ever logged into your server
- Whitelist Players (if your server has an enabled whitelist)
- OPs List (if your server has enabled ops)
- playerheads.yml (extraheads, this can be turned off in config.yml, and individual heads can be turned on/off in playerheads.yml)

The first 3 options check for duplicates using UUID, this is not possible with the last option.

# Settings
## config.yml
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
  extraheads: true
  percentchance: 100

miniblocks:
  enabled: true
  requireblock: true
  payment: EMERALD 
  price: 1
  max: 8
  min: 5 
  quantity: 1
  stacksize: 8
  percentchance: 100

hostileheads:
  enabled: true
  payment: EMERALD 
  price: 1 
  max: 3
  min: 1 
  quantity: 3
  stacksize: 1 
  percentchance: 100

passiveheads:
  enabled: true
  payment: EMERALD
  price: 1 
  max: 3
  min: 1 
  quantity: 3 
  stacksize: 1 
  percentchance: 100 

neutralheads:
  enabled: true
  payment: EMERALD 
  price: 1 
  max: 3 
  min: 1 
  quantity: 3 
  stacksize: 1 
  percentchance: 100 

combined:
  combine: 2
  max: 3
  min: 1
  percentchance: 100
  quantity: 3
```
The sections control the settings for playerheads (heads) and the miniblocks, hostile mob heads, passive mob heads, neutral mob heads and combined (whether or not to combine mob/player heads).

The settings individually are:  
|Setting Name|Description|Applies to|
|---|---|---|
|enabled|Controls whether or not that type of trade is enabled|all|
|payment|What item is used for payment, this must be an exact match to something in the [Spigot Materials list](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html)|heads|
|price|How many of the payment items are required for the trade|heads and miniblocks|
|max|The maximum number of potential trades that will be added of this type to a single Wandering Trader|all|
|min|The minimum number of potential trades that will be added of this type to a single Wandering Trader|all|
|quantity|The number of items the Wandering Trader carriers without needing to restock|all|
|stacksize|The number of items received in a single trade|heads and miniblocks|
|extraheads|Enable extra heads from the playerheads.yml config file|heads|
|requireblock|Require a full block as part of the payment to acquire a miniblock|miniblock|
|combine|Determines if Playerheads, Hostile Mob heads, Passive Mob heads, and Neutral Mob heads are combined|miniblock|

The settings data types:
|Setting Name|Data type|Applies to|
|---|---|---|
|enabled|boolean|all|
|payment| [Material](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html)|heads|
|price|integer|heads|
|max|integer|all|
|min|integer|all|
|quantity|integer|all|
|stacksize|integer|heads|
|extraheads|boolean|heads|
|requireblock|boolean|miniblock|
|combine|integer|combine|

The combine setting can be set to:
|Value|Description|
|---|---|
|0|Do not combine any categories|
|1|Combine player heads, hostile mob heads, neutral mob heads and passive mob heads|
|2|Combine only hostile mob heads, passive mob heads and neutral mob heads|

## miniblocks.yml
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

**I will not accept pull requests for non-vanilla blocks**  
The settings data types:
|Setting Name|Data type|
|---|---|
|name|string|
|texture|string|
|block|[Material](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html)|
|block_quantity|integer|
|enabled|boolean|

## playerheads.yml, neutralmobheads.yml, passivemobheads.yml, hostilemobheads.yml
These files control the configuration of extrahead player heads and mob heads. You can add any number of additional player heads by adding new heads in the following format:

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

**I will not accept pull requests for player heads**  
The settings data types:
|Setting Name|Data type|
|---|---|
|name|string|
|texture|string|
|enabled|boolean|
