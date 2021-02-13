package dynamic_card.card_play_messages;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dynamic_card.MemeCollection;

@SpirePatch(clz = AbstractPlayer.class, method = "playCard")
public class PlayCardPatch {
    @SpirePrefixPatch
    public static void showTextbox(AbstractPlayer instance) {
        MemeCollection.showFirstApplicableCardPlayMessage(instance.hoveredCard);
    }
}
