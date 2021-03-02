package memethespire.enemydialogues;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import memethespire.MemeCollection;

import java.util.ArrayList;

@SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfCombatLogic")
public class PlayerCombatStartLogic {
    @SpirePostfixPatch
    public static void showDialogues(AbstractPlayer player) {
        // Checking and queueing dialogues is packed into an AbstractGameAction instead
        // of being directly executed because the start of combat patch is executed
        // before the player's opening hand is drawn and before the start of combat
        // relics take effect. This is due to that the start of combat effects are
        // not directly executed, but queued as actions, so to view and consider
        // their effects, this action is queued after them.
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractMonster> monsters =
                        AbstractDungeon.getCurrRoom().monsters.monsters;

                monsters.forEach((monster) -> {
                    MemeCollection.queueFirstApplicableStartOfCombatDialogueFromAllCollections(
                            AbstractDungeon.player, monster);
                });

                this.isDone = true;
            }
        });
    }
}
