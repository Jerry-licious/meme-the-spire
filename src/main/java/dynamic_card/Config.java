package dynamic_card;

import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dynamic_card.card_memes.CardModification;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Config {
    private static Gson gson = new Gson();
    public static Config config;

    static {
        try (Reader reader = new InputStreamReader(
                CardModification.class.getResourceAsStream("/modifications.json"))) {
            config = gson.fromJson(reader, Config.class);
        }
        // This shouldn't go wrong.
        catch (IOException ignored) {}
    }

    CardModification[] modifications;

    public Config() {

    }

    public void modifyWithFirstApplicable(AbstractCard card,
                                          AbstractPlayer player) {
        for (CardModification modification : modifications) {
            if (modification.applicableOnCard(card) && modification.applicableOnPlayer(player)) {
                modification.modify(card);
                return;
            }
        }
    }
}
