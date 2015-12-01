# Parlez-Vous
Bonjour! Hallo! Ola! 你好! こんにちは! Hello!

Parlez-Vous is a Toast Module that allows you to code an FRC Robot in your own, native Language.

Parlez-Vous translates your source code to english, and is run as a [Lua](https://github.com/Open-RIO/ToastLUA) program.

## Example
French source file: (`main.fr_FR`)
```
local m = Moteur.drive(0, 1)
Etat.teleop(fonction()
	Registre.info("Bonjour, World!")
	m.tank(0.5, 0.5)
fin)

Fil.async(fonction()
	Fil.dormir(1000)
	Registre.info("Adieu!")
fin)
```

## Install
[>> Click Here <<](https://github.com/FRC5333/Parlez-Vous/wiki/Installing-Parlez-Vous---Visual-Guide)

## Using Parlez-Vous
Files are stored under `toast/parlez/`. Filename should be `<filename>.<lang>`, e.g. `main.fr_FR`

## Contributions
If you wish to contribute translations, you may do so by editing or creating files under
[this directory](src/main/resources/parlez/translations)

If you are creating a new translation, please add the locale code to `locale_list`

Each file must follow this format:  
Filename: `LCID_language_code.json`, e.g. `en_US.json` or `fr_FR.json`  
Contents:
```json
{
  "code": "en_AU",					
  "name": "English (Australia)", 
  "inherit": "en_US",				
  "translations": {
	  "g'day": "hello",	
	  "oi": "hello"
  }
}
```
Where: 
- `code` = LCID Language Code
- `name` = Human-Readable Name
- `inherit` = The Locale Code for inheriting translations for multiple dialects. Set this to 'none' if you don't want to inherit anything
- `translations` = An object full of translations. The key (first term) is the target language, and the value (second term) is the english equivilent

### Footnote
As FIRST grows, more and more countries join the worldwide program. For many of these countries, Language is a hurdle. Parlez-Vous is Team 5333's attempt at helping these teams overcome this hurdle, as Programming is something that is based largely in the English domain. By no means is Parlez-Vous fully featured in terms of Robot Control, but instead a tool to try and spark interest and help new teams get started. 

Parlez-Vous provides a foundation to try and get people who speak other languages to understand the concepts behind basic programming and robot control, in a language or dialect that they are comfortable using. We're hoping that once they are comfortable they will take the plunge and start using more advanced languages and frameworks.
