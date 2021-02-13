package memethespire.tooltipmemes;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.FtueTip;

/**
 * A tooltip is only shown when it is opened, and a tooltip is only opened
 * when its constructor is called, thus a skeleton is used to queue tooltips
 * and construct them when they are used.
 */
public class TooltipSkeleton {
    String title;
    String content;
    AbstractCard card;

    public TooltipSkeleton(String title, String content, AbstractCard card) {
        this.title = title;
        this.content = content;
        this.card = card;
    }

    public FtueTip makeTooltip() {
        FtueTip tooltip = new FtueTip(title, content,
                (float) Settings.WIDTH / 2.0F - 500.0F * Settings.scale,
                (float) Settings.HEIGHT / 2.0F, card);
        // Make the type Power so the game returns to the card reward screen
        // when it is dismissed.
        tooltip.type = FtueTip.TipType.POWER;
        return tooltip;
    }
}
