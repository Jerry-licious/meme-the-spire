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

    // TODO: Consider the necessity of modifying name and description
    //  properties.
    //
    // The name and description properties seem to only be used when the
    // player right clicks (views) the relic, as the PowerTips are generated
    // separately. Modifying these properties would allow the player to see
    // the meme when they "take a close up look" at the relic as well,
    // however, it may not be desirable for them to see the memes when they
    // actually want to check what the relic does.
    public void modify(AbstractRelic relic) {
        if (modifiedName != null) {
            ReflectionUtils.setFinal(relic, AbstractRelic.class, "name", modifiedName);
        }
        if (modifiedDescription != null) {
            relic.description = modifiedDescription;
        }

        // Update the power tip.
        relic.tips.clear();
        relic.tips.add(new PowerTip(relic.name, relic.description));

        ReflectionUtils.invokePrivate(relic, AbstractRelic.class, "initializeTips");
    }

    public static void restore(AbstractRelic relic) {
        ReflectionUtils.setFinal(relic, AbstractRelic.class, "name", getRelicStrings(relic).NAME);
        relic.description = relic.getUpdatedDescription();

        // Update the power tip.
        relic.tips.clear();
        relic.tips.add(new PowerTip(relic.name, relic.description));

        ReflectionUtils.invokePrivate(relic, AbstractRelic.class, "initializeTips");
    }

    public static RelicStrings getRelicStrings(AbstractRelic relic) {
        return (RelicStrings) ReflectionUtils.getPrivate(
                relic, AbstractRelic.class, "relicStrings");
    }
}
