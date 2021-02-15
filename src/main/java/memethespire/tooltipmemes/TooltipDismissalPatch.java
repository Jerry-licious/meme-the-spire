package memethespire.tooltipmemes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.ui.FtueTip;
import com.megacrit.cardcrawl.ui.buttons.GotItButton;
import memethespire.ReflectionUtils;

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
        GotItButton gotItButton = (GotItButton) ReflectionUtils.getPrivate(
                instance, FtueTip.class, "button");
        // When the button is pressed.
        if (gotItButton.hb.clicked) {
            if (!tooltipQueue.isEmpty()) {
                AbstractDungeon.ftue = tooltipQueue.poll().makeTooltip();
            }
        }
        if (CInputActionSet.proceed.isJustPressed()) {
            CInputActionSet.proceed.unpress();
        }
    }
}
