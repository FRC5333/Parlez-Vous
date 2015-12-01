package frc.team5333.parlez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

public class Locale {

    public HashMap<String, String> translations;
    String code, name, inherit;
    boolean combined = false;

    public Locale(String code, String name) {
        translations = new HashMap<>();
        this.code = code;
        this.name = name;
    }

    public String getLocaleCode() {
        return code;
    }

    public String getLocaleName() {
        return name;
    }

    public void inherit(String name) {
        inherit = name;
    }

    public void addTranslation(String local, String english) {
        translations.put(local, english);
    }

    public HashMap<String, String> combine() {
        if (!combined) {
            HashMap<String, String> newHash = new HashMap<>();
            newHash.putAll(translations);
            if (inherit != null && !inherit.equalsIgnoreCase("none"))
                newHash.putAll(LocaleLoader.getInherit(inherit, code).combine());
            translations = newHash;
            combined = true;
            return newHash;
        } else return translations;
    }

    public String convert(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        HashMap<String, String> conversions = combine();
        String total = "";
        final String[] line = {""};
        while ((line[0] = br.readLine()) != null) {
            conversions.forEach((local, english) -> {
                line[0] = line[0].replace(local, english);
            });
            total += line[0] + "\n";
        }
        br.close();
        return total;
    }

}
