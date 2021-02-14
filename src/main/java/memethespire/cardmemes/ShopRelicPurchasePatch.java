package memethespire.cardmemes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StoreRelic;
import memethespire.ReflectionUtils;

import java.lang.reflect.Field;

@SpirePatch(clz = StoreRelic.class, method = "purchaseRelic")
public class ShopRelicPurchasePatch {
    @SpirePostfixPatch
    // When a relic is purchased, update the card descriptions as new
    // modifications may be applicable.
    public static void updateCards(StoreRelic instance) {
        ShopScreen shopScreen = (ShopScreen) ReflectionUtils.getPrivate(
                instance, StoreRelic.class, "shopScreen");
        ShopInitCardsPatch.modifyCards(shopScreen);
    }
}
