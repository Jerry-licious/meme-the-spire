package dynamiccard;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;

@SpirePatch(clz = FastCardObtainEffect.class, method = "update")
public class FastCardObtainEffectUpdatePatch {
    // See ShopCardPurchase patch: a separate patch on FastCardObtainEffect
    // is used to update the shop screen.
    @SpirePostfixPatch
    public static void update(FastCardObtainEffect instance) {
        // The card is added to the deck after the animation is complete.
        if (instance.isDone && ShopCardPurchasePatch.currentScreen != null) {
            // Update the cards.
            ShopInitCardsPatch.modifyCards(ShopCardPurchasePatch.currentScreen);
            // The FastCardObtainEffect is likely used in other places as
            // well, thus it is better to make sure that the other uses do
            // not interfere with a shop screen that has been left behind.

            // It is safe* to assign the screen to null as it will be
            // re-assigned when another card is purchased.
            // *If the player clicks fast enough between card purchases, it
            // is possible that one animation completes when another card
            // is being purchased, causing the second card to not have access
            // to the variable. However, since this case is relatively
            // unlikely, I will be lazy here.
            ShopCardPurchasePatch.currentScreen = null;
        }
    }
}
