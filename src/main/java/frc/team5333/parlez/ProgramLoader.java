package frc.team5333.parlez;

import jaci.openrio.toast.core.io.Storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProgramLoader {

    public static void init() {
        Storage.USB_Module("lua", (file, usb, device) -> {
            file.delete();
        });
        Storage.USB_Module("parlez", (file, usb, device) -> {
            file.mkdirs();
            File[] files = file.listFiles((f) -> !f.getName().equalsIgnoreCase("locales"));
            if (files != null)
                loadSet(files, "");
        });
    }

    public static void loadSet(File[] files, String sub) {
        for (File file : files) {
            if (file.isDirectory())
                loadSet(file.listFiles(), sub + file.getName() + "/");
            else {
                String[] nameAndExtension = file.getName().split("\\.");
                String filename = nameAndExtension[0];
                String locale = nameAndExtension[1];
                try {
                    String contents = LocaleLoader.getLocale(locale).convert(new FileReader(file));
                    String folder = "lua/" + sub;
                    Storage.highestPriority(folder).mkdirs();
                    FileWriter writer = new FileWriter(Storage.highestPriority(folder + filename + ".lua"));
                    writer.write(contents);
                    writer.close();
                } catch (IOException e) {
                    Parlez.logger.error("Could not load Parlez File " + filename + ": " + e);
                    Parlez.logger.exception(e);
                }
            }
        }
    }

}
