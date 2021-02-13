package memethespire.tooltipmemes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import memethespire.MemeCollection;

import java.util.ArrayList;

@SpirePatch(clz = CardRewardScreen.class, method = "open")
public class CardRewardScreenOpenPatch {
    @SpirePostfixPatch
    public static void showTooltips(CardRewardScreen _instance,
                                    ArrayList<AbstractCard> cards,
                                    RewardItem _rItem, String _header) {
        MemeCollection.showFirstApplicableTooltipFromAllCollections(
                cards, AbstractDungeon.player);
    }
}
