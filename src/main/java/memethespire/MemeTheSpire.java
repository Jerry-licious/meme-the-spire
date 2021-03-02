package memethespire;

import basemod.BaseMod;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.RelicGetSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import memethespire.relicmemes.RelicModification;

@SpireInitializer
public class MemeTheSpire implements PostInitializeSubscriber,
        RelicGetSubscriber, EditKeywordsSubscriber {
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

    @Override
    public void receiveRelicGet(AbstractRelic abstractRelic) {
        RelicModification.restore(abstractRelic);
    }

    @Override
    public void receiveEditKeywords() {
        BaseMod.addKeyword(new String[]{"Meme"}, "The name and/or " +
                "description of this card has been modified by a meme! " +
                "Right-click it to view its original state.");
    }
}
