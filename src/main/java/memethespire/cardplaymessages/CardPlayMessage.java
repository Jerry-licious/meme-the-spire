package memethespire.cardplaymessages;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import memethespire.cardmemes.CardModification;

import java.util.Random;

/**
 * A thought bubble on the player when they play a specific card.
 */
public class CardPlayMessage {
    String cardName;
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
