# Dynamic Card Meme
Replaces certain card names and descriptions in card reward screens and shops with jokes based on your current relic bar and deck.

### How It Works
When a card reward screen or is presented or when cards are shown in a shop, it looks for "modifications" to apply on the cards, when a modification is applicable on a card, the card's name and description will be replaced. 

When the card is picked or purchased (added to your deck), the original name and description will be restored to make sure that the memes are not intrusive.

## Meme Collections

Each JSON file in the `resources/meme_collections` directory defines a collection of memes. They are structured as follows:

```json
{
  "modifications": [...],
  "tooltips": [...]
}
```

*Note: currently there is no support for external or additional modification JSON files, so if you intend to add your own memes you would have to modify the existing JSON files and manually package the mod.*

### Player Condition

In most meme "definitions" there will be a field named `conditions`. This field determines whether the meme will be shown or not. Its format is as follows:

```json
{
  "relicMatches": ["Relic Match", "Relic Match"],
  "cardMatches": ["Relic Match", "Relic Match"]
}
```
If the player has one of the relics named in `relicMatches` *and* has one of the cards named in `cardMatches`, the patch will be able to be applied on the card. If `relicMatches` is left as empty or not defined, the player's relic bar will not be checked; if `cardMatches` is left as empty or not defined, the player's deck will not be checked.

If all conditions pass, then the meme will be shown. 

Though the fields *of* the condition can be left undefined or as empty arrays, the `conditions` field *must* be defined for every meme entry that requires it, even if it is empty. 


### Card Modifications (`modifications`)
A card modification changes the cards seen at card reward screens and shops, it can modify the card's name and description. A list of these card modifications can be defined in the `modifications` field of the JSON files. Its format is as follows:
```json
{
  "conditions": {...},
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
  "conditions": {...},
  "cardNames": ["Card", "Card"],
  "title": "Tooltip Title",
  "content": "Tooltip text..."
}
```

The same tooltip can be applied to multiple cards and will show for all of them if they are seen, the tooltip will show as long as one of the names in its `cardNames` match with the card's name. Note that the player can only see *one* tooltip in one card reward screen, so the first defined tooltip in the list will be shown first. 

Some tooltips have extended descriptions that do not fit in one tooltip box, so line break characters(`\n`) can be added in the `content` field to split the tooltip into multiple parts that will be shown in order (the next will show after the first is dismissed). 

One tooltip can only be shown once in one session of the game to minimise disruption (restarting the game will reset the record).