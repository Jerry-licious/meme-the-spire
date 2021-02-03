package dynamic_card.tooltip_memes;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dynamic_card.PlayerConditions;

import java.util.Arrays;

/**
 * A tooltip that is shown to the player about a card when it is seen in a card
 * reward screen.
 */
public class CardRewardTooltip {
    /**
     * The conditions of the player where the tooltip becomes applicable.
     */
    PlayerConditions conditions;
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
    /**
     * Some tooltips are very long, they are broken into parts that are
     * separated by the new line character (\n) and then shown
     * separately with the help of the ToolTipDismissalPatch.
     */
    String[] contentParts;
    /**
     * Prevent tooltips from being overly obnoxious by preventing the same
     * tooltip from being shown multiple times in one session.
     */
    public transient boolean shown = false;

    private void breakContentIntoParts() {
        if (contentParts == null){
            contentParts = content.split("\n");
        }
    }

    public boolean applicableOnPlayer(AbstractPlayer player) {
        return conditions.applicableOnPlayer(player);
    }

    public boolean applicableOnCard(AbstractCard card) {
        return Arrays.stream(cardNames).anyMatch((cardName) ->
                cardName.equals(PlayerConditions.getCardStrings(card).NAME));
    }

    public void show(AbstractCard card) {
        breakContentIntoParts();

        // Show the first tooltip.
        AbstractDungeon.ftue = new TooltipSkeleton(title, contentParts[0], card)
                .makeTooltip();

        // Then queue the rest.
        for (int i = 1; i < contentParts.length; i++) {
            TooltipDismissalPatch.queueTooltip(
                    new TooltipSkeleton(title, contentParts[i], card));
        }

        shown = true;
    }
}
