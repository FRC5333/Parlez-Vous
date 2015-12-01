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
  "code": "en_AU",					// LCID Language Code
  "name": "English (Australia)",    // Human-Readable Code
  "inherit": "en_US",				// Optional -- Locale Code for inheriting translations from (e.g. en_GB). If no inherit, use "none"
  "translations": {
	  "g'day": "hello",				// All values to translate Left value for language, Right value for English equivilent
	  "oi": "hello"
  }
}
```