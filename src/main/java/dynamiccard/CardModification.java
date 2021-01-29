package dynamiccard;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CardModification {
    /**
     * The relics that the modification looks for. The modification will only
     * be applied if the player has one of the relics in the list. If left as
     * empty, the modification will not consider the player's relic bar.
     */
    String[] relicMatches;
    /**
     * The cards that the modification looks for. The modification will only
     * be applied if the player has on eof the cards in the list. If left as
     * empty, the modification will not consider the player's deck.
     */
    String[] cardMatches;
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
        return
                // Relic matching
                // If there are not relic requirements
                (relicMatches.length == 0 ||
                // Or if the player has one of the relics.
            Arrays.stream(relicMatches).anyMatch((relicName) ->
                player.relics.stream().map((relic) -> relic.name)
                    .collect(Collectors.toList()).contains(relicName))) &&
                // Card matching
                // If there are not relic requirements
                (cardMatches.length == 0 ||
                // Or if the player has one of the cards.
            Arrays.stream(cardMatches).anyMatch((cardName) ->
                player.masterDeck.group.stream().map(
                        (card) -> getCardStrings(card).NAME)
                        .collect(Collectors.toList()).contains(cardName)));
    }

    public boolean applicableOnCard(AbstractCard card) {
        return cardName.equals(getCardStrings(card).NAME);
    }

    public void modify(AbstractCard card) {
        if (modifiedName != null) card.name = modifiedName +
                (card.upgraded ? "+" : "");
        if (modifiedDescription != null) {
            card.rawDescription = card.upgraded ?
                    (modifiedUpgradedDescription == null ?
                            modifiedDescription : modifiedUpgradedDescription) :
                    modifiedDescription;
            card.initializeDescription();
        }
    }

    // Used to fetch the original card names and descriptions.
    private static CardStrings getCardStrings(AbstractCard card) {
        Class<? extends AbstractCard> cardClass = card.getClass();
        try {
            Field cardStringsField = cardClass.getDeclaredField("cardStrings");
            // Access the private static field.
            cardStringsField.setAccessible(true);
            return (CardStrings) cardStringsField.get(null);
        }
        // Safe to avoid these exceptions as all card classes are
        // guaranteed to have the cardStrings field, and since the private
        // field is changed to be accessible, no IllegalAccessException
        // should happen either.
        catch (NoSuchFieldException | IllegalAccessException ignored) {
            return null;
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
}
