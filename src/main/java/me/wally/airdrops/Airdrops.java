package me.wally.airdrops;
import de.slikey.effectlib.EffectManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Airdrops extends JavaPlugin {

    private EffectManager effectmanager;
    @Override
    public void onEnable() {

        //Register Effect Manager
        effectmanager = new EffectManager(this);

        //Register Commands
        getCommand("airdrop").setExecutor(new AirdropCommand());

        //Register Events
        this.getServer().getPluginManager().registerEvents(new AirdropEvent(this, effectmanager),this);
    }

    @Override
    public void onDisable(){
        effectmanager.dispose();
    }
}