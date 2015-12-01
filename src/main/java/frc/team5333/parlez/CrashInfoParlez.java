package frc.team5333.parlez;

import jaci.openrio.toast.lib.crash.CrashInfoProvider;

import java.util.ArrayList;
import java.util.List;

public class CrashInfoParlez implements CrashInfoProvider {
    @Override
    public String getName() {
        return "Parlez-Vous";
    }

    @Override
    public String getCrashInfoPre(Throwable t) {
        return null;
    }

    @Override
    public List<String> getCrashInfo(Throwable t) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Resource Locales: ");
        for (String code : LocaleLoader.resourceLocales)
            list.add("\t" + code + ": " + LocaleLoader.getLocale(code).getLocaleName());

        list.add("File Locales: ");
        for (String code : LocaleLoader.fileLocales)
            list.add("\t" + code + ": " + LocaleLoader.getLocale(code).getLocaleName());

        return list;
    }
}
