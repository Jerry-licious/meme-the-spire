package memethespire;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Texture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class Config {
    private static final Logger logger = LogManager.getLogger(Config.class.getName());
    private static String configLocation = "./meme_the_spire_config.json";
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    boolean enableCardModifications = true;
    boolean enableTooltipMemes = true;
    boolean enableCardPlayMessages = true;
    boolean enableRelicModifications = true;
    boolean enableEnemyDialogues = true;

    public Config() { }

    public static Config loadConfig() {
        File configFile = new File(configLocation);
        logger.info("Loading config from " + configFile.getAbsolutePath() + ".");

        if (!configFile.exists()) {
            logger.info("Config file does not exist, making a new one.");
            return saveDefaultConfig(configFile);
        }

        try {
            Config config = gson.fromJson(new FileReader(configFile), Config.class);
            logger.info("Successfully loaded config from " + configFile.getAbsolutePath() + ".");
            return config;
        } // Shouldn't happen.
        catch (FileNotFoundException ignored) {
            return new Config();
        }
        catch (JsonSyntaxException e) {
            logger.info("Config file is invalid, using default config and replacing it.");
            return saveDefaultConfig(configFile);
        }
    }

    private static Config saveDefaultConfig(File configFile) {
        logger.info("Creating default config.");
        Config defaultConfig = new Config();
        logger.info("Saving default config to " + configFile.getAbsolutePath() + ".");

        try {
            FileWriter writer = new FileWriter(configFile);
            writer.write(gson.toJson(defaultConfig));
            writer.flush();
            writer.close();
            logger.info("Finished saving default config.");
        } catch (IOException e) {
            logger.error("Failed to save default config to " +
                    configFile.getAbsolutePath() + ".");
        }

        return defaultConfig;
    }

    public static void saveConfig() {
        File configFile = new File(configLocation);
        logger.info("Saving config to " + configFile.getAbsolutePath() + ".");

        try {
            FileWriter writer = new FileWriter(configFile);
            writer.write(gson.toJson(MemeTheSpire.config));
            writer.flush();
            writer.close();
            logger.info("Finished saving config.");
        } catch (IOException e) {
            logger.info("Failed to save config file: " + e.getMessage());
        }
    }

    public static void setupConfigMenu() {
        BaseMod.registerModBadge(new Texture("meme_the_spire/icon.png"),
                "Meme The Spire", "Jerry",
                "Adds various different memes to your Slay the Spire experience.",
                new ConfigMenu());
    }
}
