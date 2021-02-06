# Dynamic Card Meme
Replaces certain card names and descriptions in card reward screens and shops with jokes based on your current relic bar and deck.

### How It Works
When a card reward screen is opened or when cards are shown in a shop, it looks for card memes to apply on the cards, when a modification is found to be applicable on a card, the card's name and description will be replaced. 

When the card is picked or purchased (added to your deck), the original name and description will be restored to make sure that the memes are not intrusive.

When a card reward screen is opened, it looks for tooltip memes to apply on the cards, when a tooltip meme is found to be applicable on a card, it will be shown on the screen. One card reward screen can only generate one tooltip screen ~~(best with busted crown)~~. 

## Meme Collections

Each JSON file in the `resources/meme_collections` directory defines a collection of memes. They are structured as follows:

```json
{
  "enabled": true,
  "receiveUpdates": false,
  "modifications": [],
  "tooltips": []
}
```

If the `enabled` attribute is set to true, the meme collection will be enabled and used in the game, otherwise it will be ignored. 

*Only applicable for [base collections](#base-collections)*: If the `receiveUpdates` attribute is set to true, the base collection file will be overridden every time the game starts to install potential updates. 

### Player Condition

In most meme "definitions" there will be a field named `conditions`. This field determines whether the meme will be shown or not. Its format is as follows:

```json
{
  "relicMatches": ["Relic Match", "Relic Match"],
  "cardMatches": ["Relic Match", "Relic Match"],
  "actNumbers": [1, 2, 3, 4]
}
```
**relicMatches**: The player must have at least one of the relics in this list. If left as empty or not defined, this criterion will automatically pass.

**cardMatches**: The player must have at least one of the cards in this list. If left as empty or not defined, this criterion will automatically pass.

**actNumbers**: The player must be in one of the acts in this list. If left as empty or not defined, this criterion will automatically pass.

If all conditions pass, then the meme will be shown. 

Though the fields *of* the condition can be left undefined or as empty arrays, the `conditions` field *must* be defined for every meme entry that requires it, even if it is empty. 


### Card Modifications (`modifications`)
A card modification changes the cards seen at card reward screens and shops, it can modify the card's name and description. A list of these card modifications can be defined in the `modifications` field of the JSON files. Its format is as follows:
```json
{
  "conditions": {},
  "cardName": "Card to modify",
  "modifiedName": "New Name",
  "modifiedDescription": "New description...",
  "modifiedUpgradedDescription": "New upgraded description..."
}
```
`modifiedName`, `modifiedDescription` and `modifiedUpgradedDescription` are optional fields, if they are not defined, the corresponding property of the card will not be changed. If `modifiedUpgradedDescription` is not defined, the mod will fall back and use the `modifiedDescription` instead. However, if `modifiedDescription` is not defined, the mod will not consider `modifiedUpgradedDescription` at all.

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

The same tooltip can be applied to multiple cards and will show for all of them if they are seen, the tooltip will show as long as one of the names in its `cardNames` match with the card's name. Note that the player can only see *one* tooltip in one card reward screen, so the first defined tooltip in the list will be shown first. 

Some tooltips have extended descriptions that do not fit in one tooltip box, so line break characters(`\n`) can be added in the `content` field to split the tooltip into multiple parts that will be shown in order (the next will show after the first is dismissed). 

One tooltip can only be shown once in one session of the game to minimise disruption (restarting the game will reset the record).

### Base Collections

Base collections are meme collections that come with the mod. When the game is launched, they are automatically copied from the mod into the `meme_collection` folder. Base collections are **overridden** every time you start the game if you do not set their `receiveUpdates` attribute to false. If you wish to turn off a base collection, change the value of `enabled` and `receiveUpdates` to both be false.

