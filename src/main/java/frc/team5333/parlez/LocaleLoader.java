package frc.team5333.parlez;

import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import jaci.openrio.toast.core.io.Storage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocaleLoader {

    public static HashMap<String, Locale> locales = new HashMap<>();

    public static ArrayList<String> resourceLocales = new ArrayList<>();
    public static ArrayList<String> fileLocales = new ArrayList<>();

    public static Locale getInherit(String code, String parentCode) {
        if (locales.containsKey(code)) return locales.get(code);
        else throw new LocaleException("Missing inherit locale: " + code + " (required by: " + parentCode + ")");
    }

    public static Locale getLocale(String code) {
        if (locales.containsKey(code)) return locales.get(code);
        else throw new LocaleException("Locale does not exist: " + code);
    }

    public static void init() {
        try {
            loadLocaleResources();
        } catch (Exception e) {
            Parlez.logger.error("Error loading Locale Resources: " + e);
            Parlez.logger.exception(e);
        }

        for (Locale locale : locales.values())
            locale.combine();
    }

    public static void loadLocaleResources() throws IOException, JsonParserException {
        String line = null;
        BufferedReader resource = new BufferedReader(getJarResource("/parlez/translations/locale_list"));
        while ((line = resource.readLine()) != null)
            load(getJarResource("/parlez/translations/" + line + ".json"), true);
        resource.close();

        Storage.USB_Module("parlez/locales", (file, usb, device) -> {
            if (!file.exists()) file.mkdirs();
            File[] files = file.listFiles((file_resource) -> file_resource.getName().endsWith(".json"));
            if (files != null)
                for (File fl : files) {
                    try {
                        load(new FileReader(fl), false);
                    } catch (Exception e) {
                        Parlez.logger.error("Error loading Translation File " + fl.getName() + ": " + e);
                        Parlez.logger.exception(e);
                    }
                }
        });
    }

    public static Reader getJarResource(String name) {
        return new InputStreamReader(LocaleLoader.class.getResourceAsStream(name));
    }

    public static void load(Reader reader, boolean resource) throws JsonParserException {
        JsonObject parentObject = JsonParser.object().from(reader);
        String code = parentObject.getString("code");
        String name = parentObject.getString("name");
        String inherit = parentObject.getString("inherit");

        if (code == null) throw new LocaleException("Locale 'code' key must be set!");
        if (name == null) throw new LocaleException("Locale 'name' key must be set!");

        Locale locale = new Locale(code, name);
        locale.inherit(inherit);
        JsonObject translations = parentObject.getObject("translations");
        for (Map.Entry<String, Object> entry : translations.entrySet())
            locale.addTranslation(entry.getKey(), (String) entry.getValue());
        locales.put(code, locale);

        if (resource) resourceLocales.add(code); else fileLocales.add(code);
    }

    public static class LocaleException extends RuntimeException {
        public LocaleException(String message) {
            super(message);
        }
    }

}
