package memethespire.relicmemes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.select.BossRelicSelectScreen;
import memethespire.MemeCollection;

import java.util.ArrayList;

@SpirePatch(clz = BossRelicSelectScreen.class, method = "open")
public class BossRelicSelectScreenOpenPatch {
    @SpirePostfixPatch
    public static void modifyBossRelics(BossRelicSelectScreen _instance,
                                    ArrayList<AbstractRelic> relics) {
        relics.forEach((relic) ->
            MemeCollection.applyFirstApplicableRelicModificationFromAllCollections(relic, AbstractDungeon.player));
    }
}
