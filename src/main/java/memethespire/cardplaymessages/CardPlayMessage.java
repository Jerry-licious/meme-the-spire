package memethespire.cardplaymessages;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import memethespire.cardmemes.CardModification;

import java.util.Random;

/**
 * A textbox on the player when they play a specific card.
 */
public class CardPlayMessage {
    /**
     * The name of the card that the player plays to trigger the textbox.
     */
    String cardName;
    /**
     * The lines that the player can say, when the message is to be shown, a
     * random line will be chosen.
     */
    String[] lines;

    private static final Random cardPlayRng = new Random();

    public CardPlayMessage() {

    }

    public boolean applicableOnCard(AbstractCard card) {
        return CardModification.getCardStrings(card).NAME.equals(cardName);
    }

    public void showTextbox() {
        AbstractDungeon.actionManager.addToBottom(new
                TalkAction(true, this.lines[cardPlayRng.nextInt(this.lines.length)],
                0.8F, 1.0F));
    }
}
