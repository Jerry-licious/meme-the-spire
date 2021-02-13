package dynamic_card.tooltip_memes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.ui.FtueTip;
import com.megacrit.cardcrawl.ui.buttons.GotItButton;

import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * Some tooltips are too long to display, thus they are displayed over
 * multiple tooltip screens.
 * dismissed.
 */
@SpirePatch(clz = FtueTip.class, method = "update")
public class TooltipDismissalPatch {
    private static LinkedList<TooltipSkeleton> tooltipQueue = new LinkedList<>();

    public static void queueTooltip(TooltipSkeleton tooltip) {
        tooltipQueue.add(tooltip);
    }

    @SpirePostfixPatch
    public static void showNextTooltip(FtueTip instance) {
        Class<FtueTip> ftueTipClass = (Class<FtueTip>) instance.getClass();
        try {
            Field gotItButtonField = ftueTipClass.getDeclaredField("button");
            gotItButtonField.setAccessible(true);
            GotItButton gotItButton = (GotItButton) gotItButtonField.get(instance);
            // When the button is pressed.
            if (gotItButton.hb.clicked) {
                if (!tooltipQueue.isEmpty()) {
                    AbstractDungeon.ftue = tooltipQueue.poll().makeTooltip();
                }
            }
            if (CInputActionSet.proceed.isJustPressed()) {
                CInputActionSet.proceed.unpress();
            }
        } // This shouldn't happen.
        catch (NoSuchFieldException | IllegalAccessException ignored) { }
    }
}
