package dynamic_card;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import dynamic_card.card_memes.CardModification;

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
     * The relics that the player need to satisfy the condition. The player
     * must have at least one of the relics named in this list. If left as
     * empty, this criterion will automatically pass.
     */
    String[] relicMatches = {};
    /**
     * The cards that the player need to satisfy the condition. The player
     * must have at least one of the cards named in this list. If left as
     * empty, this criterion will automatically pass.
     */
    String[] cardMatches = {};
    /**
     * The acts that the player must be in to satisfy the condition. The
     * player must be in one of the acts numbered in this list. If left as
     * empty, this criterion will automatically pass.
     */
    int[] actNumbers = {};

    /**
     * The minimum amount of cards that the player must have in their deck to
     * satisfy the condition. If left as null, this criterion will
     * automatically pass.
     */
    int minDeckSize = -1;
    /**
     * The maximum amount of cards that the player can have in their deck to
     * satisfy the condition. If left as null, this criterion will
     * automatically pass.
     */
    int maxDeckSize = -1;

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
                        (card) -> CardModification.getCardStrings(card).NAME)
                        .collect(Collectors.toList()).contains(cardName))) &&
            (actNumbers.length == 0 || Arrays.stream(actNumbers).anyMatch(
                    (actNumber) -> actNumber == AbstractDungeon.actNum)) &&
            (minDeckSize == -1 || player.masterDeck.size() >= minDeckSize) &&
            (maxDeckSize == -1 || player.masterDeck.size() <= maxDeckSize);
    }
}
