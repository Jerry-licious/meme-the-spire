package memethespire.cardplaymessages;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import memethespire.MemeCollection;

@SpirePatch(clz = AbstractPlayer.class, method = "playCard")
public class PlayCardPatch {
    @SpirePrefixPatch
    public static void showTextbox(AbstractPlayer instance) {
        MemeCollection.showFirstApplicableCardPlayMessageFromAllCollections(instance.hoveredCard);
    }
}
