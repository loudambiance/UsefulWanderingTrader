package com.chumcraft.usefulwanderingtrader;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;

public class Configuration {
    private FileConfiguration mainConfig;
    private FileConfiguration miniblocksConfig;
    private FileConfiguration playerheadsConfig;
    File mainConfigFile;
    File miniblocksFile;
    File playerheadsFiles;
    private UWTPlugin plugin;

    public Configuration(UWTPlugin plugin){
        this.plugin = plugin;
        this.mainConfig = loadConfig(this.mainConfigFile, "config.yml");
    }

    private FileConfiguration loadConfig(File filevar, String filename){
        filevar = new File(this.plugin.getDataFolder(), filename);
        fileConfig(filevar, filename);
        FileConfiguration outConfigvar = new YamlConfiguration();
        try{
            outConfigvar.load(filevar);
        }catch(Exception e){
            this.plugin.getLogger().warning(e.getStackTrace().toString());
        }
        return outConfigvar;
    }

    public ArrayList<Miniblock> loadminiblocksConfig()
    {
        this.miniblocksConfig = loadConfig(this.miniblocksFile, "miniblocks.yml");
        Set<String> keys = this.miniblocksConfig.getKeys(false);
        ArrayList<Miniblock> ret = new ArrayList<Miniblock>();
        for(String key : keys){
            ConfigurationSection miniblock = this.miniblocksConfig.getConfigurationSection(key);
            ret.add(new Miniblock(
                    miniblock.getString("name"),
                    miniblock.getString("texture"),
                    Material.getMaterial(miniblock.getString("block")),
                    miniblock.getInt("block_quantity"),
                    miniblock.getBoolean("enabled")
            ));
        }
        return ret;
    }

    public ArrayList<Playerhead> loadPlayerheadConfig()
    {
        this.playerheadsConfig = loadConfig(this.playerheadsFiles, "playerheads.yml");
        Set<String> keys = this.playerheadsConfig.getKeys(false);
        ArrayList<Playerhead> ret = new ArrayList<Playerhead>();
        for(String key : keys){
            ConfigurationSection Playerhead = this.playerheadsConfig.getConfigurationSection(key);
            ret.add(new Playerhead(
                    Playerhead.getString("name"),
                    Playerhead.getString("texture"),
                    Playerhead.getBoolean("enabled")
            ));
        }
        return ret;
    }

    private void fileConfig(File filevar, String filename){
        if(!filevar.exists()){
            filevar.getParentFile().mkdirs();
            try {
                InputStream in = this.plugin.getResource(filename);
                OutputStream out = new FileOutputStream(filevar);
                byte[] buf = new byte[1024];
                int len;
                while((len=in.read(buf))>0){
                    out.write(buf,0,len);
                }
                out.close();
                in.close();
            } catch (Exception e) {
                this.plugin.getLogger().warning(e.getStackTrace().toString());
            }
        }
    }

    public String getStringSetting(String section, String setting)
    {
        ConfigurationSection configsection = this.mainConfig.getConfigurationSection(section);
        return configsection.getString(setting);
    }

    public void setSetting(String section, String setting, String value)
    {
        ConfigurationSection configsection = this.mainConfig.getConfigurationSection(section);
        configsection.set(setting, value);
        this.plugin.saveConfig();
    }

    public int getIntSetting(String section, String setting){
        ConfigurationSection configsection = this.mainConfig.getConfigurationSection(section);
        return configsection.getInt(setting);
    }

    public boolean getBooleanSetting(String section, String setting){
        ConfigurationSection configsection = this.mainConfig.getConfigurationSection(section);
        return configsection.getBoolean(setting); 
    }
}