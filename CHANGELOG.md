# Changelog

## [Unreleased]

#### Changes

* `RelicModification` no longer changes the name and description of the relic (it only changes the relic's power tips now).
* `CardPlayMessage` textboxes now last a bit shorter.
* Added null safety checks for card and relic modifications to ensure that the mod does not crash with other mods.
* Added null safety checks for player conditions to ensure that the mod does not crash with other mods.

#### Content

* Added 2 more silent card memes.
* Added 1 more watcher relic meme.
* Added 1 more colourless relic meme.
* Added evaluate to the roster of *shuffle a card into your draw pile* cards in genuine tips.

## [0.2.5] - 2021-02-21

#### Bug Fixes

* Fixed 2 typos in the silent meme collection.
* Fixed 1 typo in the colourless meme collection.
* Fixed the bug where relics with pickup effects will not restore their texts after being picked up.
* Fixed the bug where long tooltips that span across multiple tooltip screens will cause the boss reward screen to disappear after you skip the card reward or pick a card.

## [0.2.4] - 2021-02-21

#### Bug Fixes

* Fixed a bug where a silent meme crashes the game when you open a boss chest that contains Wrist Blade.

## [0.2.3] - 2021-02-21

#### Features

* Relic memes can now apply to boss relics.

#### Content

* Added numerous relic memes for boss and shop relics on all characters.
  * Added 40 different relic memes applicable on all characters.
  * Added 8 different relic memes for the ironclad.
  * Added 14 different relic memes for the silent.
  * Added 7 different relic memes for the defect.
  * Added 13 different relic memes for the watcher.
* Added 2 colourless card memes in conjunction with some colourless relic memes.
* Added 1 more genuine tip.
  * Self repair now has a tooltip explaining its interaction with meat on the bone.

## [0.2.2] - 2021-02-20

#### Content

* Added a few card play messages for each character.
* Added the colourless meme collection.
  * Contains 37 different relic memes applicable on all characters for common, uncommon and rare relics.
* Added numerous relic memes for common, uncommon and rare relics on all characters<sup>*</sup>.
  * Added 11 different relic memes for the ironclad.
  * Added 8 different relic memes for the silent.
  * Added 4 different relic memes for the defect.
  * Added 7 different relic memes for the watcher.

*The addition are "character specific" because they are triggered by character-specific cards and relics, not because of a hard-coded limit on which character you play (You can see "defect memes" if you pick up defect cards that trigger them for example).

#### Known Bugs

* Bottle relics will not have their names restored after being picked up.

## [0.2.1] - 2021-02-14

#### Features

* Added cards contain check in `PlayerConditions` through the `cardsContain` attribute in the conditions field of meme entries.
* Added a new type of meme: relic modification, where the names and descriptions of relics can be modified based on the player's current condition.

#### Changes

* Card and relic name matches in `PlayerConditions` and `CardModification` are no longer case sensitive.
* Renamed the `modifications` field (card modifications) in meme collection JSONs to `cardModifications` for clarity since `relicModifications` have been added.

## [0.2.0] - 2021-02-13

#### Features

* Added a menu that allows the player to turn on and off different types of memes.
* Fixed a bug where the mod crashes the game if a meme collection is invalid.

#### Changes

* Now requires [BaseMod](https://github.com/daviscook477/BaseMod) as a dependency.

## [0.1.5] - 2021-02-13

#### Features

* Added a new type of meme: card play message, where the player says a line through their textbox before they play specific cards.
* Added modified upgraded card names to `CardModifications` through the `modifiedUpgradedName` field of card modification entries.

#### Changes

* Changed the name of the mod from "Dynamic Card Memes" to "Meme the Spire".
* Changed numerous silent card modifications to better reflect what the cards originally do.
* Changed a few defect card modifications to better reflect what the cards originally do.
* Changed a few ironclad card modifications to better reflect what the cards originally do.

#### Content

* Added the watcher meme collection: containing 38 card memes and 6 tooltip memes.
* Added one new defect meme.
* Added two new silent memes.
* Added more genuine tips.
  * Watcher cards with "shuffle a card into your draw pile" are now reworded to "insert a card at a random position in your draw pile".
  * Watcher cards with "shuffle a card into your draw pile" now have a tooltip explaining what they actually do.
  * Weave description changed to *Deal 4(6) damage. **After** you Scry, return this from your discard pile into your hand.* to better reflect the interaction where the card returns to your hand if it is scried.
  * Weave now has a tooltip explaining its interaction with Scry.
  * Cards that generate cards (Hello World, Infinite Blades, Creative AI, Battle Hymn, Deus Ex Machina) now have a tooltip explaining their interaction with Snecko Eye.
  * Nightmare now has a tooltip explaining its interaction with Snecko Eye (cards retain their modified cost).
  * Recycle now has a tooltip explaining its interaction with X cost cards.
  * Establishment now has a tooltip explaining its interaction with Runic Pyramid.
  * Lesson Learned now has a tooltip explaining that the upgrade is not applied immediately.

#### Known Bugs

* In a boss reward screen, if the player receives a card and the mod shows a tooltip with multiple sections, after the player skip the card they will not be able to press the skip card button (skip rewards). The cause of this bug is still to be found and can only be worked around by saving and quitting to reload the screen, in which case the player will be moved on to the next floor (for some reason).

## [0.1.4] - 2021-02-09

#### Features

* Added deck size checks in `PlayerConditions` through the `minDeckSize` and `maxDeckSize` attribute in the conditions field of meme entries.

#### Content

* Added more genuine tips.
  * Tip for artefact and pellets interaction with Biased Cognition.
* Added the defect meme collection, containing an incredibly colourful aggregate and an unhealthy amount of claw jokes.

## [0.1.3] - 2021-02-07

### Fixes

* Fixed a bug where the game would crash when you see a Slice. 

## [0.1.2] - 2021-02-06

#### Features

* Added act number check option in `PlayerConditions` through the `actNumbers` attribute in the conditions field of meme entries. 

#### Content

* Added more genuine tips.
  * Tip for dual wield dagger interaction.
  * Tip for spoon havoc interaction.
* Added more ironclad card and tooltip memes.
* Added 2 silent tooltip memes. 

#### Fixes

* Fixed the bug where the same tooltip can be shown repeatedly in one session. 

## [0.1.1] - 2021-02-03

#### Features

* Added card reward tooltip memes that display tooltips when a card is seen in a card reward screen. 
* Allowed custom meme collections to be loaded from the `meme_collections` folder in the Slay the Spire folder. 
* Allowed meme collections to be enabled/disabled through the `enabled` attribute in meme collection files.
* Allowed base meme collections (collections included with the mod) to receive/reject updates through the `receiveUpdates` attribute in meme collection files. 

#### Changes

* Moved the `cardMatches` and `relicMatches` fields in the card modification memes to the `conditions` field that contains both properties.

#### Content

* Added a genuine tips meme collection that shows tips for certain cards and changes certain card texts to represent them accurately. 
  * Omniscience description changed to: Choose a card in your draw pile. **Give it Exhaust and play it twice.** Exhaust.
  * Blasphemy description changed to: Enter Divinity. **At the start of your next turn, take 99999 damage.** Exhaust.
  * Added special tips for Blasphemy, Omniscience and Vault.

## [0.1.1-alpha] - 2021-01-29

#### Features

* Added card modification memes that modify the names and descriptions seen in card rewards and in shop screens.

#### Content

* Added ironclad and silent card memes.

## [0.1.0]
Untracked