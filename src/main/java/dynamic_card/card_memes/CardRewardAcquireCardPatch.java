package dynamic_card.card_memes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

@SpirePatch(clz = CardRewardScreen.class, method = "acquireCard")
public class CardRewardAcquireCardPatch {
    @SpirePostfixPatch
    // Cards stay changed after they get added to the deck, thus their names
    // and descriptions will be reset when they are selected.
    public static void revertModification(CardRewardScreen _instance,
                                          AbstractCard hoveredCard) {
        CardModification.restore(hoveredCard);
    }
}
