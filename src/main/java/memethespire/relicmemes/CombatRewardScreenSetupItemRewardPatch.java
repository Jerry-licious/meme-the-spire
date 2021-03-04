package memethespire.relicmemes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import memethespire.MemeCollection;

@SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward")
public class CombatRewardScreenSetupItemRewardPatch {
    @SpirePostfixPatch
    public static void modifyRelics(CombatRewardScreen instance) {
        instance.rewards.forEach((reward) -> {
            if (reward.type == RewardItem.RewardType.RELIC) {
                MemeCollection.applyFirstApplicableRelicModificationFromAllCollections(
                        reward.relic, AbstractDungeon.player, false);
                // Update the name on the reward item as well.
                reward.text = reward.relic.name;
            }
        });
    }
}
