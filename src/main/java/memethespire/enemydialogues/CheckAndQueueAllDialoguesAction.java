package memethespire.enemydialogues;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import memethespire.MemeCollection;

import java.util.ArrayList;

// Checking and queueing dialogues is packed into an AbstractGameAction instead
// of being directly executed because the start of combat patch is executed
// before the player's opening hand is drawn and before the start of combat
// relics take effect. This is due to that the start of combat effects are
// not directly executed, but queued as actions, so to view and consider
// their effects, this action is queued after them.
public class CheckAndQueueAllDialoguesAction extends AbstractGameAction {
    public CheckAndQueueAllDialoguesAction() {}
    @Override
    public void update() {
        ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;

        monsters.forEach((monster) -> {
            MemeCollection.queueFirstApplicableStartOfCombatDialogueFromAllCollections(AbstractDungeon.player, monster);
        });

        this.isDone = true;
    }
}
