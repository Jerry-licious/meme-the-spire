package memethespire;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import memethespire.cardmemes.CardModification;
import memethespire.cardplaymessages.CardPlayMessage;
import memethespire.relicmemes.RelicModification;
import memethespire.tooltipmemes.CardRewardTooltip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MemeCollection {
    private static final Logger logger =
            LogManager.getLogger(MemeCollection.class.getName());

    private static Gson gson = new Gson();
    public static ArrayList<MemeCollection> collections = new ArrayList<>();

    private static MemeCollection loadCollectionFromResources(String path) {
        Reader reader = new InputStreamReader(
                CardModification.class.getResourceAsStream(path));
        return gson.fromJson(reader, MemeCollection.class);
    }

    // The base collections include the genuine tips collection, the ironclad
    // collection and the silent collection.

    // If a base collection file does not exist in the meme collections
    // folder, copy the default collection there.
    private static void updateBaseMemeCollection(String name) {
        try {
            logger.info("Looking for base meme collection: " + name + ".");
            File collectionFile = new File("./meme_collections/" + name + ".json");
            if (collectionFile.exists()) {
                logger.info("Base meme collection found: " + name + ".");
                try {
                    MemeCollection collection = gson.fromJson(
                            new FileReader(collectionFile), MemeCollection.class);
                    if (collection.receiveUpdates) {
                        logger.info("Giving collection update: " + name + ".");
                        copyBaseMemeCollectionFromDefault(name);
                    }
                } catch (JsonSyntaxException e) {
                    logger.info("Base meme collection \"" + name +
                            "\" is invalid, copying from default.");

                    copyBaseMemeCollectionFromDefault(name);
                }
            } else {
                logger.info("Base meme collection \"" + name +
                        "\" not found, copying from default.");

                collectionFile.createNewFile();

                copyBaseMemeCollectionFromDefault(name);
            }
        } catch (IOException e) {
            logger.error("Failed to create default meme collection: " + name + ".");
        }
    }

    private static void copyBaseMemeCollectionFromDefault(String name) {
        File collectionFile = new File("./meme_collections/" + name + ".json");
        // Load the default collection
        String defaultFileContent = new BufferedReader(
                new InputStreamReader(CardModification.class
                        .getResourceAsStream("/meme_the_spire/meme_collections/"
                                + name + ".json")))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        try {
            // And write it into the corresponding collection file.
            FileWriter writer = new FileWriter(collectionFile);
            writer.write(defaultFileContent);
            writer.flush();
            writer.close();
            logger.info("Finished copying default meme collection: " + name + ".");
        } catch (IOException e) {
            logger.error("Failed to copy default meme collection: " + name + ".");
        }
    }

    public static void loadMemeCollections(){
        // Make the collection folder.
        File collectionFolder = new File("./meme_collections");
        if (!collectionFolder.exists()) {
            collectionFolder.mkdir();
        }

        logger.info("Filling base collections.");
        updateBaseMemeCollection("genuine_tips");
        updateBaseMemeCollection("ironclad");
        updateBaseMemeCollection("silent");
        updateBaseMemeCollection("defect");
        updateBaseMemeCollection("watcher");
        logger.info("Finished filling base collections.");

        logger.info("Loading collections from meme collections folder.");
        for (File file : collectionFolder.listFiles()) {
            try {
                logger.info("Loading meme collection: " + file.getName() + ".");
                MemeCollection collection = gson.fromJson(
                        new FileReader(file), MemeCollection.class);
                if (collection.enabled) {
                    collections.add(collection);
                    logger.info("Meme collection loaded successfully: " + file.getName() + ".");
                } else {
                    logger.info("Meme collection is disabled: " + file.getName() + ".");
                }
            } catch (FileNotFoundException e) {
                logger.error("Failed loading meme collection: " + file.getName() + ".");
            } catch (JsonSyntaxException e) {
                logger.error("Meme collection " + file.getName() + " is invalid, skipping.");
            }
        }
        logger.info("Finished loading all meme collections. ");
    }

    /**
     * Determines whether the collection will be added after it is loaded.
     */
    boolean enabled = true;
    /**
     * Determines whether the collection will be updated (overridden) when
     * the game starts, only applicable for base collections.
     */
    boolean receiveUpdates = false;
    CardModification[] modifications = {};
    CardRewardTooltip[] tooltips = {};
    CardPlayMessage[] cardPlayMessages = {};
    RelicModification[] relicModifications = {};

    public MemeCollection() { }

    public static void applyFirstApplicableCardModificationFromAllCollections
            (AbstractCard card, AbstractPlayer player) {
        // Placing the config check here because this function is called from
        // multiple places.
        if (MemeTheSpire.config.enableCardModifications){
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
    }

    public static void showFirstApplicableTooltipFromAllCollections(
            Iterable<AbstractCard> cards, AbstractPlayer player) {
        if (MemeTheSpire.config.enableTooltipMemes){
            for (AbstractCard card : cards) {
                for (MemeCollection collection : collections){
                    for (CardRewardTooltip tooltip : collection.tooltips) {
                        if (!tooltip.shown && tooltip.applicableOnCard(card) &&
                                tooltip.applicableOnPlayer(player)) {
                            tooltip.show(card);
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void showFirstApplicableCardPlayMessage(AbstractCard card) {
        if (MemeTheSpire.config.enableCardPlayMessages) {
            for (MemeCollection collection : collections){
                for (CardPlayMessage message : collection.cardPlayMessages) {
                    if (message.applicableOnCard(card)) {
                        message.showTextbox();
                        return;
                    }
                }
            }
        }
    }

    public static void applyFirstApplicableRelicModification
            (AbstractRelic relic, AbstractPlayer player) {
        if (MemeTheSpire.config.enableRelicModifications) {
            for (MemeCollection collection : collections) {
                for (RelicModification modification : collection.relicModifications) {
                    if (modification.applicableOnRelic(relic) &&
                            modification.applicableOnPlayer(player)) {
                        modification.modify(relic);
                        return;
                    }
                }
            }
        }
    }
}
