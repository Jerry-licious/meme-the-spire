package memethespire;

import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class ConfigMenu extends ModPanel {
    private static float TOGGLE_BUTTON_X = 360.0f;
    private static float TOGGLE_BUTTON_Y = 750.0f;
    private static float TOGGLE_BUTTON_SPACE = 70.0f;

    public ConfigMenu() {
        ModLabeledToggleButton enableCardModifications = new ModLabeledToggleButton(
                "Enable Card Modifications",
                TOGGLE_BUTTON_X, TOGGLE_BUTTON_Y - TOGGLE_BUTTON_SPACE * 0,
                Settings.CREAM_COLOR, FontHelper.charDescFont,
                MemeTheSpire.config.enableCardModifications,
                this, (modLabel) -> {}, (modToggleButton) -> {
            MemeTheSpire.config.enableCardModifications = !MemeTheSpire.config.enableCardModifications;
            Config.saveConfig();
        });
        this.addUIElement(enableCardModifications);

        ModLabeledToggleButton enableTooltipMemes = new ModLabeledToggleButton(
                "Enable Tooltip Memes",
                TOGGLE_BUTTON_X, TOGGLE_BUTTON_Y - TOGGLE_BUTTON_SPACE * 1,
                Settings.CREAM_COLOR, FontHelper.charDescFont,
                MemeTheSpire.config.enableTooltipMemes,
                this, (modLabel) -> {}, (modToggleButton) -> {
            MemeTheSpire.config.enableTooltipMemes = !MemeTheSpire.config.enableTooltipMemes;
            Config.saveConfig();
        });
        this.addUIElement(enableTooltipMemes);

        ModLabeledToggleButton enableCardPlayMessages = new ModLabeledToggleButton(
                "Enable Card Play Messages",
                TOGGLE_BUTTON_X, TOGGLE_BUTTON_Y - TOGGLE_BUTTON_SPACE * 2,
                Settings.CREAM_COLOR, FontHelper.charDescFont,
                MemeTheSpire.config.enableCardPlayMessages,
                this, (modLabel) -> {}, (modToggleButton) -> {
            MemeTheSpire.config.enableCardPlayMessages = !MemeTheSpire.config.enableCardPlayMessages;
            Config.saveConfig();
        });
        this.addUIElement(enableCardPlayMessages);

        ModLabeledToggleButton enableRelicModifications = new ModLabeledToggleButton(
                "Enable Relic Modifications",
                TOGGLE_BUTTON_X, TOGGLE_BUTTON_Y - TOGGLE_BUTTON_SPACE * 3,
                Settings.CREAM_COLOR, FontHelper.charDescFont,
                MemeTheSpire.config.enableRelicModifications,
                this, (modLabel) -> {}, (modToggleButton) -> {
            MemeTheSpire.config.enableRelicModifications = !MemeTheSpire.config.enableRelicModifications;
            Config.saveConfig();
        });
        this.addUIElement(enableRelicModifications);

        ModLabeledToggleButton enableEnemyDialogues = new ModLabeledToggleButton(
                "Enable Enemy Dialogues",
                TOGGLE_BUTTON_X, TOGGLE_BUTTON_Y - TOGGLE_BUTTON_SPACE * 4,
                Settings.CREAM_COLOR, FontHelper.charDescFont,
                MemeTheSpire.config.enableEnemyDialogues,
                this, (modLabel) -> {}, (modToggleButton) -> {
            MemeTheSpire.config.enableEnemyDialogues = !MemeTheSpire.config.enableEnemyDialogues;
            Config.saveConfig();
        });
        this.addUIElement(enableEnemyDialogues);
    }
}
