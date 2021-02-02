package dynamic_card.tooltip_memes;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.FtueTip;
import dynamic_card.PlayerConditions;

import java.util.Arrays;

/**
 * A tooltip that is shown to the player about a card when it is seen in a card
 * reward screen.
 */
public class CardRewardTooltip {
    /**
     * The cards that the tooltip applies to. One tooltip can cover multiple
     * cards.
     */
    String[] cardNames;
    /**
     * The title of the tooltip.
     */
    String title;
    /**
     * The content of the tooltip.
     */
    String content;

    public boolean applicableOnCard(AbstractCard card) {
        return Arrays.stream(cardNames).anyMatch((cardName) ->
                cardName.equals(PlayerConditions.getCardStrings(card).NAME));
    }

    public void show(AbstractCard card) {
        AbstractDungeon.ftue = new FtueTip(title, content,
                (float) Settings.WIDTH / 2.0F - 500.0F * Settings.scale,
                (float) Settings.HEIGHT / 2.0F, card);
        // AbstractDungeon.ftue.type = FtueTip.TipType.POWER;
    }
}
