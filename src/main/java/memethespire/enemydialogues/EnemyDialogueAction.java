package memethespire.enemydialogues;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class EnemyDialogueAction extends AbstractGameAction {
    String text;
    public EnemyDialogueAction(AbstractCreature enemy, String text) {
        this.target = enemy;
        this.text = text;

        this.actionType = ActionType.TEXT;
    }

    @Override
    public void update() {
        addToBot(new TalkAction(target, text));
        this.isDone = true;
    }
}
