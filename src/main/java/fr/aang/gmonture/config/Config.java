package fr.aang.gmonture.config;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.aang.gmonture.Main;
import fr.aang.gmonture.Monture;

public class Config {

    private Main				_main;
    private File				_file;
    private YamlConfiguration	_yaml;

    private int					_dead_delay = -1;
    private	Messages			_messages = new Messages();
    private List<String>		_disable_worlds = new ArrayList<String>();

    public Config(Main main, String file_name) {

        _main = main;
        _yaml = loadConfig(file_name);
        fillConfig();
    }

    private YamlConfiguration loadConfig(String file_name) {

        if(!_main.getDirectory().exists()) {
            _main.getDirectory().mkdir();
        }

        _file = new File(_main.getDataFolder(), file_name);

        if (!_file.exists()) {
            _main.saveResource(file_name, false);
        }

        return YamlConfiguration.loadConfiguration(_file);
    }

    private void fillConfig() {

        ConfigurationSection	section;

        int i = 0;
        while ((section = _yaml.getConfigurationSection("montures." + i)) != null) {

            Monture monture = new Monture(_main, section.getString("entity"));

            monture.setName(section.getString("name"));
            monture.setPerm(section.getString("perm"));
            monture.setSlot(section.getInt("slot"));
            monture.setPrice(section.getInt("price"));
            monture.setIcon(section.getString("icon"));

            if (section.isSet("speed"));
                monture.setSpeed(section.getDouble("speed"));

            if (section.isSet("fall_damage"));
                monture.setFallDamage(section.getBoolean("fall_damage"));

            if (section.isSet("control")) {
                monture.setControl(section.getBoolean("control"));
            }
            if (section.isSet("fly")) {
                monture.setFly(true);
            }
            else {
                if (section.isSet("jump"))
                monture.setJump(section.getDouble("jump"));
            }

            _main.getMontures().add(monture);
            i++;
        }

        _dead_delay = _yaml.getInt("dead_delay");
        _disable_worlds = _yaml.getStringList("disable_worlds");

        _messages.permission = _yaml.getString("messages.permission");
        _messages.disable = _yaml.getString("messages.disable");
        _messages.dead = _yaml.getString("messages.dead");
        _messages.no_place = _yaml.getString("messages.no_place");
        _messages.money = _yaml.getString("messages.money");
        _messages.buy = _yaml.getString("messages.buy");
    }

    public int getDeadDelay() {
        return _dead_delay;
    }

    public Messages getMessages() {
        return _messages;
    }

    public boolean isDisableWorld(String world_name) {
        return _disable_worlds.contains(world_name);
    }

}
