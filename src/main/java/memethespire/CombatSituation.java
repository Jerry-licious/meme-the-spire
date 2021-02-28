package memethespire;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import memethespire.cardmemes.CardModification;

import java.util.Arrays;

/**
 * The conditions of the combat in which a dialog can be said. Intended to be
 * used by other dialogs to check the situations of a combat.
 */
public class CombatSituation {
    /**
     * The player must have at least one of the cards named in this list. If
     * left as empty or undefined, this criterion will automatically pass.
     */
    String[] handContains = {};
    /**
     * The description of the cards in the player's hand must contain at
     * least one of these words. If left as empty or undefined, this
     * criterion will automatically pass.
     */
    String[] handSays = {};
    /**
     * The description of the cards in the player's hand must not contain any
     * of these words. If left as empty or undefined, this criterion will
     * automatically pass.
     */
    String[] handDoesNotSay = {};

    public CombatSituation() {}

    public boolean applicableOnPlayer(AbstractPlayer player) {
        return handContainsCheck(player) && handSaysCheck(player)
                && handDoesNotSayCheck(player);
    }

    private boolean handContainsCheck(AbstractPlayer player) {
        return handContains.length == 0 ||
                Arrays.stream(handContains).anyMatch((cardName) ->
                    player.hand.group.stream().anyMatch((card) -> {
                        CardStrings cardStrings =
                                CardModification.getCardStrings(card);
                        return cardStrings != null &&
                                cardStrings.NAME.equalsIgnoreCase(cardName);
                    }));
    }

    private boolean handSaysCheck(AbstractPlayer player) {
        return handSays.length == 0 ||
                Arrays.stream(handSays).anyMatch((word) ->
                    player.hand.group.stream().anyMatch((card) -> {
                        CardStrings cardStrings =
                                CardModification.getCardStrings(card);
                        return cardStrings != null &&
                                cardStrings.DESCRIPTION.toLowerCase()
                                        .contains(word.toLowerCase());
                    }));
    }

    private boolean handDoesNotSayCheck(AbstractPlayer player) {
        return handSays.length == 0 ||
                Arrays.stream(handDoesNotSay).noneMatch((word) ->
                        player.hand.group.stream().anyMatch((card) -> {
                            CardStrings cardStrings =
                                    CardModification.getCardStrings(card);
                            return cardStrings != null &&
                                    cardStrings.DESCRIPTION.toLowerCase()
                                            .contains(word.toLowerCase());
                        }));
    }
}
