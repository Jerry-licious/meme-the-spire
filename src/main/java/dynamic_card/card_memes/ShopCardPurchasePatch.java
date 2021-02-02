package dynamic_card.card_memes;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.shop.ShopScreen;

@SpirePatch(clz = ShopScreen.class, method = "purchaseCard")
public class ShopCardPurchasePatch {
    // The revert has to be a prefix patch as the method is called when the
    // player *attempts* to purchase a card, thus the price is not checked.
    // The method also deducts gold and sets the hoveredCard to null, so a
    // postfix is impossible, as the card and the gold has to be checked
    // beforehand.
    @SpirePrefixPatch
    // Cards stay changed after they get added to the deck, thus their names
    // and descriptions will be reset when they are selected.
    public static void revertAndUpdate(ShopScreen _instance,
                                       AbstractCard hoveredCard) {
        if (AbstractDungeon.player.gold >= hoveredCard.price) {
            CardModification.restore(hoveredCard);
        }
    }

    // When a card is purchased, instead of directly adding it to the deck
    // instantly, the instruction is carried by a FastCardObtainEffect, which
    // adds the card to the deck after a very short delay. However, due to
    // the delay it is not possible to update the shop immediately after the
    // method is called. Instead, the current instance of the shop will be
    // stored as a static variable which will be accessed by the patch to
    // FastCardObtainEffect.
    public static ShopScreen currentScreen;

    @SpirePostfixPatch
    public static void storeShopScreen(ShopScreen instance,
                                       AbstractCard _hoveredCard) {
        currentScreen = instance;
    }
}
