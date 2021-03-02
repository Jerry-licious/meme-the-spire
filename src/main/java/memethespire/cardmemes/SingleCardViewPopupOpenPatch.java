package memethespire.cardmemes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import memethespire.ReflectionUtils;

// Since the mod intends to be less disruptive, it would be best to make sure
// that the player has a way to view the card's original name and description.
// When the player right-clicks on a card at a reward screen or at a shop,
// a larger copy of the card is shown through a SingleCardViewPopup. The card
// will make a copy of itself and let it be shown on the screen.
// Cards shown in their popup will have their descriptions re-rendered (I
// still have not found where this happens), however their names are copied
// from the original card. This means that the cards keep their memed names
// when the player wants to look at the original name and descriptions. Thus,
// when a card view popup is opened, the copy's name will have to be manually
// restored.
@SpirePatch(clz = SingleCardViewPopup.class, method = "open",
        paramtypez = {AbstractCard.class})
public class SingleCardViewPopupOpenPatch {
    @SpirePostfixPatch
    public static void restoreCardName(SingleCardViewPopup popup,
                                       AbstractCard _card) {
        AbstractCard card = (AbstractCard) ReflectionUtils.getPrivate(popup,
                SingleCardViewPopup.class, "card");
        CardStrings cardStrings = CardModification.getCardStrings(card);
        if (cardStrings != null) {
            card.name = cardStrings.NAME;
        }
    }
}
