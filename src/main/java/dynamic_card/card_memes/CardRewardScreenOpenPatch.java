package dynamic_card.card_memes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipTracker;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.ui.FtueTip;
import dynamic_card.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

@SpirePatch(clz = CardRewardScreen.class, method = "open")
public class CardRewardScreenPatch {
    private static final Logger logger =
            LogManager.getLogger(CardRewardScreenPatch.class);

    @SpirePostfixPatch
    // Modify shown card names and descriptions when a card reward is seen.
    public static void modifyCards(CardRewardScreen _instance,
                                   ArrayList<AbstractCard> cards,
                                   RewardItem _rItem, String _header) {
        cards.forEach((card) ->
            Config.config.modifyWithFirstApplicableCardModification(card, AbstractDungeon.player));
        AbstractDungeon.ftue = new FtueTip(AbstractPlayer.LABEL[0],
                AbstractPlayer.MSG[0], (float) Settings.WIDTH / 2.0F - 500.0F * Settings.scale,
                (float)Settings.HEIGHT / 2.0F, cards.get(0));
        AbstractDungeon.ftue.type = FtueTip.TipType.POWER;
        TipTracker.neverShowAgain("POWER_TIP");
        // _instance.skipButton.hide();
        // _instance.bowlButton.hide();
    }
}
