package dynamiccard;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * The conditions of the player in which a modification can be applied.
 * Intended to be used by other modifications to check the conditions of the
 * player.
 */
public class PlayerConditions {
    /**
     * The relics that the modification looks for. The modification will only
     * be applied if the player has one of the relics in the list. If left as
     * empty, the modification will not consider the player's relic bar.
     */
    String[] relicMatches = {};
    /**
     * The cards that the modification looks for. The modification will only
     * be applied if the player has on eof the cards in the list. If left as
     * empty, the modification will not consider the player's deck.
     */
    String[] cardMatches = {};

    // Used to fetch the original card names and descriptions.
    public static CardStrings getCardStrings(AbstractCard card) {
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
}
