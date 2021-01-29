# Dynamic Card Meme
Replaces certain card names and descriptions in card reward screens and shops 
with jokes based on your current relic bar and deck.

### How It Works
When a card reward screen or is presented or when cards are shown in a shop, it 
looks for "modifications" to apply on the cards, when a modification is 
applicable on a card, the card's name and description will be replaced. 

When the card is picked or purchased (added to your deck), the original name 
and description will be restored to make sure that the memes are not intrusive.

### modifications.json
The `modifications.json` in the `resources` directory defines all the card 
modifications, the `modifications` (apologies for my bad alliteration skills)
contains a list of "patches" that can be applied on cards based on the 
player's situation. The structure of a "patch" is as follows:
```json
{
    "relicMatches": [],
    "cardMatches": [],
    "cardName": "Card to modify",
    "modifiedName": "New name",
    "modifiedDescription": "New description",
    "modifiedUpgradedDescription": "New upgraded description"
}
```
If the player has one of the relics named in `relicMatches` *and* has one of 
the cards named in `cardMatches`, the patch will be able to be applied on 
the card. If `relicMatches` is left as empty, the player's relic bar will 
not be checked; if `cardMatches` is left as empty, the player's deck will 
not be checked.

`modifiedName`, `modifiedDescription` and `modifiedUpgradedDescription` are 
optional fields, if they are not defined, the corresponding property of the 
card will not be changed. If `modifiedUpgradedDescription` is not defined, 
the mod will fallback and use the `modifiedDescription` instead. However, if 
`modifiedDescription` is not defined, the mod will not consider 
`modifiedUpgradedDescription` at all.