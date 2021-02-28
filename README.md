# Meme the Spire
Adds various different memes to your Slay the Spire experience. 

### How It Works
When a card reward screen is opened or when cards are shown in a shop, it looks for card memes to apply on the cards, when a modification is found to be applicable on a card, the card's name and description will be replaced. 

When the card is picked or purchased (added to your deck), the original name and description will be restored to make sure that the memes are not intrusive.

When a card reward screen is opened, it looks for tooltip memes to apply on the cards, when a tooltip meme is found to be applicable on a card, it will be shown on the screen. One card reward screen can only generate one tooltip screen ~~(best with busted crown)~~. 

Before a card is played, it looks for card play messages to apply on the card, when a message is found to be applicable on the card, it will be shown on the screen. One card play can only trigger one message.

## Meme Collections

Each JSON file in the `resources/meme_collections` directory defines a collection of memes. They are structured as follows:

```json
{
  "enabled": true,
  "receiveUpdates": false,
  "cardModifications": [],
  "tooltips": [],
  "cardPlayMessages": [],
  "relicModifications": []
}
```

**enabled**: Whether the memes defined in this collection will show up or not. Used to allow players to configure their set of collections.

**receiveUpdates**: Only applicable to [base collections](#base-collections). If set to true, this file will be overridden by the mod every time the game starts to install potential upgrades.

**cardModifications**: The card modifications defined in this collection. Can be left undefined.

**tooltips**: The tooltip memes defined in this collection. Can be left undefined.

**cardPlayMessages**: The card play messages defined in this collection. Can be left undefined.

**relicModifications**: The relic modifications defined in this collection. Can be left undefined.

### Run Conditions

In most meme "definitions" there will be a field named `conditions`. This field determines whether the meme will be shown or not. Its format is as follows:

```json
{
  "relicMatches": ["Relic Match", "Relic Match"],
  "cardMatches": ["Card Match", "Card Match"],
  "actNumbers": [1, 2, 3, 4],
  "minDeckSize": 0,
  "maxDeckSize": 10,
  "cardsContain": ["Block", "Poison"]
}
```
**relicMatches**: The player must have at least one of the relics in this list to meet this condition.

**cardMatches**: The player must have at least one of the cards in this list to meet this condition.

**actNumbers**: The player must be in one of the acts in this list to meet this condition.

**minDeckSize**: The minimum amount of cards that the player must have in their deck to meet this condition.

**maxDeckSize**: The maximum amount of cards that the player can have in their deck to meet this condition. 

**cardsContain**: The player must have cards in their deck with descriptions containing at least one word in this list to meet this condition.

Any empty or undefined conditions are automatically passed.

If all conditions pass, then the meme will be able to be shown. 

Though the fields *of* the condition can be left undefined or as empty arrays, the `conditions` field *must* be defined for every meme entry that requires it, even if it is empty. 


### Combat Situation

In some meme "definitions" there will be a field named `combatSituation`. This field determines whether the meme will be shown or not. Its format is as follows:

```json
{
  "handContains": ["Card", "Card"],
  "handSays": ["Block", "Poison"],
  "handDoesNotSay": ["Block", "Poison"]
}
```

**handContains**: The player's hand must contain at least one of the cards in this list to meet this condition.

**handSays**: The player must have cards in their hand with descriptions containing at least one word in this list to meet this condition.

**handDoesNotSay**: The player must have no cards in their hand with descriptions containing any words in this list to meet this condition.

Any empty or undefined conditions are automatically passed.

If all conditions pass, the meme will be able to be shown.

Though the fields *of* the situation can be left undefined or as empty arrays, the `combatSituation` field *must* be defined for every meme entry that requires it, even if it is empty.


### Card Modifications (`cardModifications`)
A card modification changes the cards seen at card reward screens and shops, it can modify the card's name and description. A list of these card modifications can be defined in the `cardModifications` field of the JSON files. Its format is as follows:
```json
{
  "conditions": {},
  "cardName": "Card to modify",
  "modifiedName": "New Name",
  "modifiedUpgradedName": "New Upgraded Name",
  "modifiedDescription": "New description...",
  "modifiedUpgradedDescription": "New upgraded description..."
}
```
**cardName**: The name of the card that the modification will change.

**modifiedName**: The new name of the card. If left undefined, the card's name will not be changed.

**modifiedUpgradedName**: The new name of the card if the card is upgraded. If left undefined, the mod will use the `modifiedName` field. **if `modifiedName` is undefined, this field will not be considered.**

**modifiedDescription**: The new description of the card. If left undefined, the card's description will not be changed.

**modifiedUpgradedDescription**: The new description of the card if the card is upgraded. If left undefined, the mod will use the `modifiedDescription` field. **If `modifiedDescription` is undefined, this field will not be considered.** 

Card modifications only affect cards shown in the shop and in card rewards. The cards will be restored into their original text after they are added into your deck.

### Reward Screen Tooltips (`tooltips`)

A reward screen tooltip shows the player a tooltip about a card when that card is seen at card reward screens. It has a title and a description. A list of these reward screen tooltips can be defined in the `tooltips` field of the JSON files. Its format is as follows:

```json
{
  "conditions": {},
  "cardNames": ["Card", "Card"],
  "title": "Tooltip Title",
  "content": "Tooltip text..."
}
```

**cardNames**: The name of the cards that will trigger this tooltip.

**title**: The title of the tooltip. Note: you do not need to add "Tip: " as the game does that for you.

**content:** The content of the tooltip. Some tooltips have extended descriptions that do not fit in one tooltip box, so line break characters(`\n`) can be added in the `content` field to split the tooltip into multiple parts that will be shown in order (the next will show after the first is dismissed). 

The same tooltip can be applied to multiple cards and will show for all of them if they are seen, the tooltip will show as long as one of the names in its `cardNames` match with the card's name. Note that the player can only see *one* tooltip in one card reward screen, so the first defined tooltip in the list will be shown first. 

One tooltip can only be shown once in one session of the game to minimise disruption (restarting the game will reset the record).

### Card Play Messages (`cardPlayMessages`)

A card play message is a line that the character will say (through a textbox) before you play a specific card. A list of these messages can be defined in the `cardPlayMessages` field of the JSON files. Its format is as follows:

```json
{
  "cardName": "Card Name",
  "lines": ["message", "message"]
}
```

**cardName**: The name of the card that will trigger this message.

**lines**: The possible lines that the player will say when you play the card. Before the player plays the card, a random line will be chosen.

The player can only say one line when they play one card, in which case the first defined card play message will be shown.

Note that the textbox stays up for about one second, and the card play animation waits for the textbox to disappear, so please consider not adding messages to cards that are played very frequently (such as Flurry of Blows and Shivs) as this will be highly disruptive.

### Relic Modifications (`relicModifications`)

A relic modification changes the relics seen in rewards and shops, it can modify the relic's name and description. A list of these relic modifications can be defined in the `relicModifications` field of the JSON files. Its format is as follows:

```json
{
  "conditions": {},
  "relicName": "Relic to modify",
  "modifiedName": "New Name",
  "modifiedDescription": "New description..."
}
```

**relicName**: The name of the relic that the modification will change.

**modifiedName**: The new name of the relic. If left undefined, the relic's name will not be changed.

**modifiedDescription**: The new description of the relic. If left undefined, the relic's description will not be changed.

Relic modifications only affect relics seen in rewards and shops. The relics will be restored into their original text after you pick them up.

### Base Collections

Base collections are meme collections that come with the mod. When the game is launched, they are automatically copied from the mod into the `meme_collection` folder. Base collections are **overridden** every time you start the game if you do not set their `receiveUpdates` attribute to false. If you wish to turn off a base collection, change the value of `enabled` and `receiveUpdates` to both be false.

