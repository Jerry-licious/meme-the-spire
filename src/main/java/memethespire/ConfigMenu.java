package memethespire;

import basemod.ModButton;
import basemod.ModLabel;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class ConfigMenu extends ModPanel {
    private static float UI_X = 360.0f;
    private static float UI_Y = 650.0f;
    private static float TOGGLE_BUTTON_SPACE = 70.0f;

    public ConfigMenu() {
        ModLabel buttonLabel = new ModLabel(
                "Reload Meme Collections",
                UI_X + 120,
                UI_Y + 50, this, (me) -> {});
        this.addUIElement(buttonLabel);

        ModButton consoleKeyButton = new ModButton(UI_X, UI_Y, this,
                (me) -> {
            MemeCollection.collections.clear();
            MemeCollection.loadMemeCollections();
        });
        this.addUIElement(consoleKeyButton);

        ModLabeledToggleButton enableCardModifications = new ModLabeledToggleButton(
                "Enable Card Modifications",
                UI_X, UI_Y - TOGGLE_BUTTON_SPACE * 1,
                Settings.CREAM_COLOR, FontHelper.charDescFont,
                MemeTheSpire.config.enableCardModifications,
                this, (modLabel) -> {}, (modToggleButton) -> {
            MemeTheSpire.config.enableCardModifications = !MemeTheSpire.config.enableCardModifications;
            Config.saveConfig();
        });
        this.addUIElement(enableCardModifications);

        ModLabeledToggleButton enableTooltipMemes = new ModLabeledToggleButton(
                "Enable Tooltip Memes",
                UI_X, UI_Y - TOGGLE_BUTTON_SPACE * 2,
                Settings.CREAM_COLOR, FontHelper.charDescFont,
                MemeTheSpire.config.enableTooltipMemes,
                this, (modLabel) -> {}, (modToggleButton) -> {
            MemeTheSpire.config.enableTooltipMemes = !MemeTheSpire.config.enableTooltipMemes;
            Config.saveConfig();
        });
        this.addUIElement(enableTooltipMemes);

        ModLabeledToggleButton enableCardPlayMessages = new ModLabeledToggleButton(
                "Enable Card Play Messages",
                UI_X, UI_Y - TOGGLE_BUTTON_SPACE * 3,
                Settings.CREAM_COLOR, FontHelper.charDescFont,
                MemeTheSpire.config.enableCardPlayMessages,
                this, (modLabel) -> {}, (modToggleButton) -> {
            MemeTheSpire.config.enableCardPlayMessages = !MemeTheSpire.config.enableCardPlayMessages;
            Config.saveConfig();
        });
        this.addUIElement(enableCardPlayMessages);

        ModLabeledToggleButton enableRelicModifications = new ModLabeledToggleButton(
                "Enable Relic Modifications",
                UI_X, UI_Y - TOGGLE_BUTTON_SPACE * 4,
                Settings.CREAM_COLOR, FontHelper.charDescFont,
                MemeTheSpire.config.enableRelicModifications,
                this, (modLabel) -> {}, (modToggleButton) -> {
            MemeTheSpire.config.enableRelicModifications = !MemeTheSpire.config.enableRelicModifications;
            Config.saveConfig();
        });
        this.addUIElement(enableRelicModifications);

        ModLabeledToggleButton enableEnemyDialogues = new ModLabeledToggleButton(
                "Enable Enemy Dialogues",
                UI_X, UI_Y - TOGGLE_BUTTON_SPACE * 5,
                Settings.CREAM_COLOR, FontHelper.charDescFont,
                MemeTheSpire.config.enableEnemyDialogues,
                this, (modLabel) -> {}, (modToggleButton) -> {
            MemeTheSpire.config.enableEnemyDialogues = !MemeTheSpire.config.enableEnemyDialogues;
            Config.saveConfig();
        });
        this.addUIElement(enableEnemyDialogues);
    }
}
