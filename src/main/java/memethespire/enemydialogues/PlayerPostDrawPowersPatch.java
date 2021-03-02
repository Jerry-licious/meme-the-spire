package memethespire.enemydialogues;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import memethespire.MemeCollection;

import java.util.ArrayList;

// Interestingly enough, the game does not have a specific start of turn hook
// or start of turn action, so instead a start of turn action is queued after
// the start of turn powers trigger, as they are the last actions triggered
// at the start of each player turn.
@SpirePatch(clz = AbstractCreature.class, method = "applyStartOfTurnPostDrawPowers")
public class PlayerPostDrawPowersPatch {
    @SpirePostfixPatch
    public static void showEnemyDialogues(AbstractCreature creature) {
        // The method is defined in the creature class, not the player class
        // (let's hope that other mods don't override that method).
        if (creature instanceof AbstractPlayer) {
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    ArrayList<AbstractMonster> monsters =
                            AbstractDungeon.getCurrRoom().monsters.monsters;

                    monsters.forEach((monster) -> {
                        MemeCollection.queueFirstApplicableStartOfTurnDialogueFromAllCollections(
                                AbstractDungeon.player, monster);
                    });

                    this.isDone = true;
                }
            });
        }
    }
}
