package memethespire;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import memethespire.cardmemes.CardModification;
import memethespire.relicmemes.RelicModification;

import java.util.Arrays;

/**
 * The conditions of the player in which a modification can be applied.
 * Intended to be used by other modifications to check the conditions of the
 * player.
 */
public class RunConditions {
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

    /**
     * The player's deck must contain a card whose description contains one
     * of these words. If left as empty, this criterion will automatically pass.
     */
    String[] cardsContain = {};

    public boolean applicableOnPlayer(AbstractPlayer player) {
        return relicMatchesCheck(player) && cardMatchesCheck(player) &&
            actNumberCheck() && deckSizeCheck(player) && cardsContainCheck(player);
    }

    private boolean relicMatchesCheck(AbstractPlayer player) {
        // If there are no relic requirements
        return relicMatches.length == 0 ||
                // Or if the player has one of the relics.
                Arrays.stream(relicMatches).anyMatch((relicName) ->
                        player.relics.stream().anyMatch((relic) -> {
                            RelicStrings relicStrings =
                                    RelicModification.getRelicStrings(relic);
                            return relicStrings != null &&
                                    relicStrings.NAME.equalsIgnoreCase(relicName);
                        }));
    }

    private boolean cardMatchesCheck(AbstractPlayer player) {
        // If there are no card requirements
        return cardMatches.length == 0 ||
                // Or if the player has one of the cards.
                Arrays.stream(cardMatches).anyMatch((cardName) ->
                        player.masterDeck.group.stream().anyMatch((card) ->  {
                            CardStrings cardStrings =
                                    CardModification.getCardStrings(card);
                            return cardStrings != null &&
                                    cardStrings.NAME.equalsIgnoreCase(cardName);
                        }));
    }

    private boolean actNumberCheck() {
        // If there are no act number requirements
        return actNumbers.length == 0 ||
                // Or if the player is in one of the acts.
                Arrays.stream(actNumbers).anyMatch(
                        (actNumber) -> actNumber == AbstractDungeon.actNum);
    }

    private boolean deckSizeCheck(AbstractPlayer player) {
        // If the deck size requirements are undefined or if they meet the
        // requirements.
        return (minDeckSize == -1 || player.masterDeck.size() >= minDeckSize) &&
                (maxDeckSize == -1 || player.masterDeck.size() <= maxDeckSize);
    }

    private boolean cardsContainCheck(AbstractPlayer player) {
        return cardsContain.length == 0 ||
                Arrays.stream(cardsContain).anyMatch((word) ->
                        player.masterDeck.group.stream().anyMatch((card) -> {
                            String description =
                                    getCardDescriptionFromCardStrings(card);
                            return description != null && description
                                    .toLowerCase().contains(word.toLowerCase());
                        }));
    }

    private String getCardDescriptionFromCardStrings(AbstractCard card) {
        CardStrings cardStrings = CardModification.getCardStrings(card);
        if (cardStrings != null) {
            if (card.upgraded) {
                if (cardStrings.UPGRADE_DESCRIPTION != null) {
                    return cardStrings.UPGRADE_DESCRIPTION;
                } else {
                    return cardStrings.DESCRIPTION;
                }
            } else {
                return cardStrings.DESCRIPTION;
            }
        } else {
            return null;
        }
    }
}
