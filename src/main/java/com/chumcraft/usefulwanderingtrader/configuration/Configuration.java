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

package com.chumcraft.usefulwanderingtrader.configuration;

import com.chumcraft.usefulwanderingtrader.UWTPlugin;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

public abstract class Configuration {
    protected FileConfiguration config;
    protected File configFile;

    public Configuration(String file){
        this.config = loadConfig(file);
    }

    protected FileConfiguration loadConfig(String filename){
        this.configFile = new File(UWTPlugin.getInstance().getDataFolder(), filename);
        fileConfig(this.configFile, filename);
        FileConfiguration outConfigvar = new YamlConfiguration();
        try{
            outConfigvar.load(this.configFile);
        }catch(Exception e){
            UWTPlugin.getInstance().getLogger().warning(e.getStackTrace().toString());
        }
        return outConfigvar;
    }

    protected void fileConfig(File filevar, String filename){
        if(!filevar.exists()){
            filevar.getParentFile().mkdirs();
            try {
                InputStream in = UWTPlugin.getInstance().getResource(filename);
                OutputStream out = new FileOutputStream(filevar);
                byte[] buf = new byte[1024];
                int len;
                while((len=in.read(buf))>0){
                    out.write(buf,0,len);
                }
                out.close();
                in.close();
            } catch (Exception e) {
                UWTPlugin.getInstance().getLogger().warning(e.getStackTrace().toString());
            }
        }
    }

    public String getStringSetting(String section, String setting)
    {
        ConfigurationSection configsection = this.config.getConfigurationSection(section);
        return configsection.getString(setting);
    }

    public void setSetting(String section, String setting, String value)
    {
        ConfigurationSection configsection = this.config.getConfigurationSection(section);
        configsection.set(setting, value);
        UWTPlugin.getInstance().saveConfig();
    }

    public int getIntSetting(String section, String setting){
        ConfigurationSection configsection = this.config.getConfigurationSection(section);
        return configsection.getInt(setting);
    }

    public boolean getBooleanSetting(String section, String setting){
        ConfigurationSection configsection = this.config.getConfigurationSection(section);
        return configsection.getBoolean(setting); 
    }

    public ConfigurationSection getConfigurationSection(String section){
        return this.config.getConfigurationSection(section);
    }

    public Set<String> getKeys(boolean deep){
        return this.config.getKeys(deep);
    }

    public Set<String> getKeys(){
        return this.config.getKeys(false);
    }

}