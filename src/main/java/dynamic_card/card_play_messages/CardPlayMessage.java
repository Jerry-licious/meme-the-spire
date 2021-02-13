package dynamic_card.card_play_messages;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dynamic_card.card_memes.CardModification;

import java.util.Random;

/**
 * A thought bubble on the player when they play a specific card.
 */
public class CardPlayMessage {
    String cardName;
    String[] lines;

    private static Random textRng = new Random();

    public CardPlayMessage() {

    }

    public boolean applicableOnCard(AbstractCard card) {
        return CardModification.getCardStrings(card).NAME.equals(cardName);
    }

    public void showTextbox() {
        AbstractDungeon.actionManager.addToBottom(new
                TalkAction(true, this.lines[textRng.nextInt(this.lines.length)],
                1.2F, 1.2F));
    }
}
