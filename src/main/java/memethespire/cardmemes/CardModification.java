package memethespire.cardmemes;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import memethespire.PlayerConditions;
import memethespire.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * A modification that is applied to cards seen in card reward screens and
 * shop screens based on the player's current situation.
 */
public class CardModification {
    /**
     * The conditions of the player where the modification becomes applicable.
     */
    PlayerConditions conditions;
    /**
     * The name of the card that the modification changes.
     */
    String cardName;
    /**
     * The new name of the card. If left as null, the card's name will not be
     * changed.
     */
    String modifiedName;
    /**
     * The new name of the card if it is upgraded. If left as null. the
     * modification will use the unupgraded name instead.
     */
    String modifiedUpgradedName;
    /**
     * The new description of the card. If left as null, the card's description
     * will not be changed.
     */
    String modifiedDescription;
    /**
     * The new upgraded description of the card. If left as null, it will use
     * the modified description instead.
     */
    String modifiedUpgradedDescription;

    public CardModification() {

    }

    public boolean applicableOnPlayer(AbstractPlayer player) {
        return conditions.applicableOnPlayer(player);
    }

    public boolean applicableOnCard(AbstractCard card) {
        return getCardStrings(card).NAME.equalsIgnoreCase(cardName);
    }

    public void modify(AbstractCard card) {
        if (modifiedName != null) card.name =
                card.upgraded ? (modifiedUpgradedName == null ?
                        modifiedName + "+" : modifiedUpgradedName) :
                        modifiedName;
        if (modifiedDescription != null) {
            card.rawDescription = card.upgraded ?
                    (modifiedUpgradedDescription == null ?
                            modifiedDescription : modifiedUpgradedDescription) :
                    modifiedDescription;
            card.initializeDescription();
        }
    }

    // Restore the card to its original name and description.
    public static void restore(AbstractCard card) {
        CardStrings cardStrings = getCardStrings(card);
        // Revert the name to the original name.
        card.name = cardStrings.NAME;
        // Revert the description to the original description.
        card.rawDescription = card.upgraded ?
                (cardStrings.UPGRADE_DESCRIPTION == null ?
                        cardStrings.DESCRIPTION : cardStrings.UPGRADE_DESCRIPTION) :
                cardStrings.DESCRIPTION;
        card.initializeDescription();
    }

    // Used to fetch the original card names and descriptions.
    public static CardStrings getCardStrings(AbstractCard card) {
        // Potential bug with modded cards.
        return (CardStrings) ReflectionUtils.getPrivateStatic(
                card.getClass(), "cardStrings");
    }
}
