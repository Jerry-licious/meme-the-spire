package memethespire.tooltipmemes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

@SpirePatch(clz = CardRewardScreen.class, method = "reopen")
public class CombatRewardScreenReopenPatch {
    // When there are multiple consecutive tooltips to be shown (because one
    // tooltip is too long to fit in the textbox), there will be a bug in
    // boss reward screens where skipping or picking the card will eject the
    // player out of the reward screen menu and leaves the player with an
    // empty room and a "Skip Rewards" button that does not do anything when
    // clicked.
    // This turns out to be a product of repeatedly calling
    // CombatRewardScreen.open, since when a sequence of tooltips are shown,
    // all of them will be closed and all of them will call
    // CombatRewardScreen.open. What this exactly does is beyond my knowledge
    // to know, however it did happen to become the cause of this issue.
    // To fix this problem, a prefix patch is added to prevent the reopen
    // method from being called *at all* while there are still more tooltips
    // queued.
    @SpirePrefixPatch
    public static SpireReturn preventReopen(CardRewardScreen _instance) {
        if (!TooltipDismissalPatch.tooltipQueue.isEmpty()) {
            return SpireReturn.Return(null);
        } else {
            // However, this fix caused more problems:
            // When opening a card reward screen and seeing a tooltip, the
            // previous screen is set to the card reward screen. However,
            // opening the card reward screen after seeing consecutive
            // tooltip screens will cause the previous screen to be set to
            // the tooltip screen. Thus, when the reward screen is dismissed,
            // the game returns to the tooltip screen and sets the previous
            // screen back to the reward screen, causing a flash in and out of
            // the tooltip screen. What happened here exactly is unclear to me,
            // however this causes the card reward screen to glitch and
            // return to itself, if the player clicks a card, the card goes
            // in and then out of the deck. At this time, if the player
            // clicks another card, both cards will be added, but if the
            // player clicks the card that they previously selected, that
            // card will only be added to the deck once (as there is only one
            // card object).
            // The first attempt to fix this was to set the previous screen to null
            // after selecting, however this will again cause the problem mentioned
            // in CombatRewardScreenReopenPatch, where the lack of a screen to return
            // to causes the game to return the player to an empty room with an
            // unusable skip rewards button.
            //The second attempt to fix this was to set the previous screen to
            // COMBAT_REWARD after skipping, however this causes the screen to lose
            // its proceed button for some reason, thus it has to be closed. Closing
            // it will return the player to the same empty room with the unusable skip
            // rewards button. Though turns out that the proceed button is lost because
            // I was using orrery to test it, thus this will become the preferred
            // solution.

            // However, this causes new bugs with card reward screens in
            // combat as the mod  makes the player open the combat reward
            // screen during combat, likely because Nilry calls the re-open
            // function. Thus an additional condition is added to make sure
            // that this does not happen during combat.
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMPLETE){
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
            }
            return SpireReturn.Continue();
        }
    }
}
