package dynamic_card;

import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dynamic_card.card_memes.CardModification;
import dynamic_card.tooltip_memes.CardRewardTooltip;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class MemeCollection {
    private static Gson gson = new Gson();
    public static ArrayList<MemeCollection> collections = new ArrayList<>();

    private static MemeCollection loadCollectionFromResources(String path) {
        Reader reader = new InputStreamReader(
                CardModification.class.getResourceAsStream(path));
        return gson.fromJson(reader, MemeCollection.class);
    }

    static {
        collections.add(loadCollectionFromResources(
                "./meme_collections/ironclad.json"));
        collections.add(loadCollectionFromResources(
                "./meme_collections/silent.json"));
    }

    CardModification[] modifications = {};
    CardRewardTooltip[] tooltips = {};

    public MemeCollection() { }

    public void applyFirstApplicableCardModification(AbstractCard card,
                                                     AbstractPlayer player) {
        for (CardModification modification : modifications) {
            if (modification.applicableOnCard(card) && modification.applicableOnPlayer(player)) {
                modification.modify(card);
                return;
            }
        }
    }

    // Accept all cards at once so only one tooltip can be shown at once.
    public void showFirstApplicableTooltip(Iterable<AbstractCard> cards) {
        for (AbstractCard card : cards) {
            for (CardRewardTooltip tooltip : tooltips) {
                if (tooltip.applicableOnCard(card)) {
                    tooltip.show(card);
                    return;
                }
            }
        }
    }

    public static void applyFirstApplicableCardModificationFromAllCollections
            (AbstractCard card, AbstractPlayer player) {
        for (MemeCollection collection : collections) {
            for (CardModification modification : collection.modifications) {
                if (modification.applicableOnCard(card) &&
                        modification.applicableOnPlayer(player)) {
                    modification.modify(card);
                    return;
                }
            }
        }
    }

    public static void showFirstApplicableTooltipFromAllCollections(Iterable<AbstractCard> cards) {
        for (AbstractCard card : cards) {
            for (MemeCollection collection : collections){
                for (CardRewardTooltip tooltip : collection.tooltips) {
                    if (tooltip.applicableOnCard(card)) {
                        tooltip.show(card);
                        return;
                    }
                }
            }
        }
    }
}
