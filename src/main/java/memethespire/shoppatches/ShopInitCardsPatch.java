package memethespire.shoppatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StoreRelic;
import memethespire.MemeCollection;
import memethespire.ReflectionUtils;

import java.util.ArrayList;

@SpirePatch(clz = ShopScreen.class, method = "initCards")
public class ShopInitCardsPatch {
    @SpirePostfixPatch
    // Modify shown card names and descriptions when a shop is loaded.
    public static void modifyCardsAndRelics(ShopScreen instance) {
        instance.coloredCards.forEach((card) ->
                MemeCollection.applyFirstApplicableCardModificationFromAllCollections(card, AbstractDungeon.player));
        instance.colorlessCards.forEach((card) ->
                MemeCollection.applyFirstApplicableCardModificationFromAllCollections(card, AbstractDungeon.player));

        ((ArrayList<StoreRelic>)ReflectionUtils.getPrivate(instance,
                ShopScreen.class, "relics")).forEach((storeRelic) ->
                    MemeCollection.applyFirstApplicableRelicModificationFromAllCollections(
                            storeRelic.relic, AbstractDungeon.player, false));
    }
}
