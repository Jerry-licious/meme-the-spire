package dynamic_card.card_memes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StoreRelic;

import java.lang.reflect.Field;

@SpirePatch(clz = StoreRelic.class, method = "purchaseRelic")
public class ShopRelicPurchasePatch {
    @SpirePostfixPatch
    // When a relic is purchased, update the card descriptions as new
    // modifications may be applicable.
    public static void updateCards(StoreRelic instance) {
        Class<? extends StoreRelic> relicClass = instance.getClass();
        try {
            Field shopScreenField = relicClass.getDeclaredField("shopScreen");
            shopScreenField.setAccessible(true);
            ShopScreen shopScreen = (ShopScreen) shopScreenField.get(instance);
            ShopInitCardsPatch.modifyCards(shopScreen);
        }
        // Safe to avoid these exceptions as StoreRelic classes are
        // guaranteed to have the shopScreen field, and since the private
        // field is changed to be accessible, no IllegalAccessException
        // should happen either.
        catch (NoSuchFieldException | IllegalAccessException ignore) {}
    }
}
