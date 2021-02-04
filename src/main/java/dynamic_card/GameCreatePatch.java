package dynamic_card;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;

@SpirePatch(clz = CardCrawlGame.class, method = "create")
public class GameCreatePatch {
    @SpirePostfixPatch
    // CardCrawlGame.create is the one of the first methods called when the
    // game starts. Since using static block to load the json files makes the
    // game lag for a bit (and definitely more in the foreseeable future),
    // the loading procedure is changed to be from the static block to here -
    // where the game starts.
    public static void onGameStart(CardCrawlGame _instance) {
        MemeCollection.loadMemeCollections();
    }
}
