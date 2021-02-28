package memethespire.enemydialogues;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import memethespire.CombatSituation;
import memethespire.ReflectionUtils;
import memethespire.RunConditions;

import java.util.Random;

public class StartOfCombatDialogue {
    /**
     * The conditions of the player where the dialogue becomes applicable.
     */
    RunConditions conditions;
    /**
     * The combat situation of the player where the dialogue becomes applicable.
     */
    CombatSituation combatSituation;
    /**
     * The name of the monster that can say this dialogue. If left undefined,
     * any monster will be able to say this dialogue.
     */
    String monsterName;
    /**
     * The chance that the dialogue will appear when the conditions are met.
     */
    float chance = 1.0f;
    /**
     * The lines that the monster can say, when the dialogue is to be shown, a
     * random line will be chosen.
     */
    String[] lines;

    private static final Random dialogueRng = new Random();

    public StartOfCombatDialogue() {}

    public boolean applicableOnPlayer(AbstractPlayer player) {
        return conditions.applicableOnPlayer(player) &&
                combatSituation.applicableOnPlayer(player);
    }

    public boolean applicableOnMonster(AbstractMonster monster) {
        MonsterStrings monsterStrings = getMonsterStrings(monster);
        return monsterName == null || (monsterStrings != null &&
                monsterStrings.NAME.equalsIgnoreCase(monsterName));
    }

    // Returns whether the dialogue is added or not.
    public boolean queueDialogue(AbstractMonster monster) {
        if (dialogueRng.nextFloat() < chance) {
            monster.addToBot(new EnemyDialogueAction(monster,
                    this.lines[dialogueRng.nextInt(this.lines.length)]));
            return true;
        }
        return false;
    }

    public static MonsterStrings getMonsterStrings(AbstractMonster monster) {
        return (MonsterStrings) ReflectionUtils.getPrivateStatic(monster.getClass(), "monsterStrings");
    }
}
