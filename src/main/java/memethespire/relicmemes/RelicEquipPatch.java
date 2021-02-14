package memethespire.relicmemes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;

@SpirePatch(clz = AbstractRelic.class, method = "onEquip")
public class RelicEquipPatch {
    @SpirePostfixPatch
    public static void restoreRelic(AbstractRelic instance) {
        RelicModification.restore(instance);
    }
}
