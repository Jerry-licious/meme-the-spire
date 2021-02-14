package memethespire;

import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class MemeTheSpire implements PostInitializeSubscriber {
    public static void initialize() { new MemeTheSpire(); }

    public static Config config;

    public MemeTheSpire() {
        BaseMod.subscribe(this);
    }

    @Override
    public void receivePostInitialize() {
        config = Config.loadConfig();
        Config.setupConfigMenu();
        MemeCollection.loadMemeCollections();
    }
}
