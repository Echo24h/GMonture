package fr.aang.gmonture;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import fr.aang.gmonture.packet.WASD;
import org.bukkit.plugin.java.JavaPlugin;

import fr.aang.gmonture.commands.Commands;
import fr.aang.gmonture.config.Config;
import fr.aang.gmonture.data.Data;
import fr.aang.gmonture.events.MontureGuyListener;
import fr.aang.gmonture.events.MontureListener;
import fr.aang.gmonture.events.ShopGuyListener;

public class Main extends JavaPlugin {

    private Config					_config;
    private Data					_data;
    private List<Monture>			_montures = new ArrayList<Monture>();

    private VehicleManager         _vehicles;

    private WASD                    _control;

    @Override
    public void onEnable() {

        this._config = new Config(this, "config.yml");
        this._data = new Data(this, "data.yml");

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

        this._vehicles = new VehicleManager(this);

        this._control = new WASD(this, manager);

        getServer().getPluginManager().registerEvents(new MontureListener(this), this);
        getServer().getPluginManager().registerEvents(new MontureGuyListener(this), this);
        getServer().getPluginManager().registerEvents(new ShopGuyListener(this), this);

        getCommand("monture").setExecutor(new Commands(this));
    }

    public void reload() {
        _montures.clear();
        this._config = new Config(this, "config.yml");
        this._data = new Data(this, "data.yml");
    }

    public File getDirectory() {
        return getDataFolder();
    }

    public Config getConf() {
        return _config;
    }

    public Data getData() {
        return _data;
    }

    public List<Monture> getMontures() {
        return _montures;
    }

    public VehicleManager getVehicles() {
        return _vehicles;
    }


}
