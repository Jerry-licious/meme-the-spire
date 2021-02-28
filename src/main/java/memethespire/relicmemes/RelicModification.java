package memethespire.relicmemes;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import memethespire.PlayerConditions;
import memethespire.ReflectionUtils;

/**
 * A modification that is applied to relics seen in combat reward screens and
 * shops based on the player's current situation.
 */
public class RelicModification {
    /**
     * The conditions of the player where the modification becomes applicable.
     */
    PlayerConditions conditions;
    /**
     * The name of the relic that the modification changes.
     */
    String relicName;
    /**
     * The new name of the relic. If left as null, the relic's name will not be
     * changed.
     */
    String modifiedName;
    /**
     * The new description of the relic. If left as null, the relic's
     * description will not be changed.
     */
    String modifiedDescription;

    public RelicModification(){

    }

    public boolean applicableOnPlayer(AbstractPlayer player) {
        return conditions.applicableOnPlayer(player);
    }

    public boolean applicableOnRelic(AbstractRelic relic) {
        RelicStrings relicStrings = getRelicStrings(relic);
        return relicStrings != null && relicStrings.NAME.equalsIgnoreCase(relicName);
    }

    public void modify(AbstractRelic relic) {
        RelicStrings relicStrings = getRelicStrings(relic);
        String newName = modifiedName != null ? modifiedName : relicStrings.NAME;
        String newDescription = modifiedDescription != null ?
                modifiedDescription : relic.getUpdatedDescription();

        // Update the power tip.
        relic.tips.clear();
        relic.tips.add(new PowerTip(newName, newDescription));

        ReflectionUtils.invokePrivate(relic, AbstractRelic.class, "initializeTips");
    }

    public static void restore(AbstractRelic relic) {
        RelicStrings relicStrings = getRelicStrings(relic);
        if (relicStrings != null) {
            // Update the power tip.
            relic.tips.clear();
            relic.tips.add(new PowerTip(getRelicStrings(relic).NAME, relic.getUpdatedDescription()));

            ReflectionUtils.invokePrivate(relic, AbstractRelic.class, "initializeTips");
        }
    }

    public static RelicStrings getRelicStrings(AbstractRelic relic) {
        return (RelicStrings) ReflectionUtils.getPrivate(
                relic, AbstractRelic.class, "relicStrings");
    }
}
