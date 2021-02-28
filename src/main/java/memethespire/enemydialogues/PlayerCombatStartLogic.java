package memethespire.enemydialogues;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfCombatLogic")
public class PlayerCombatStartLogic {
    @SpirePostfixPatch
    public static void showDialogues(AbstractPlayer player) {
        AbstractDungeon.actionManager.addToBottom(new CheckAndQueueAllDialoguesAction());
    }
}
