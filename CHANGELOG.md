# Changelog

## [Unreleased]

#### Features

* Added a new type of meme: card play message, where the player says a line through their textbox before they play specific cards.

#### Changes

* Changed numerous silent card modifications to better reflect what the cards originally do.
* Changed a few defect card modifications to better reflect what the cards originally do.
* Changed a few ironclad card modifications to better reflect what the cards originally do.

#### Content

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