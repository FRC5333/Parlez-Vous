package frc.team5333.parlez;

import jaci.openrio.toast.core.loader.annotation.Priority;
import jaci.openrio.toast.lib.crash.CrashHandler;
import jaci.openrio.toast.lib.log.Logger;
import jaci.openrio.toast.lib.module.ToastModule;

public class Parlez extends ToastModule {

    public static Logger logger;

    @Override
    public String getModuleName() {
        return "Parlez-Vous";
    }

    @Override
    public String getModuleVersion() {
        return "0.0.1";
    }

    @Override
    @Priority(level = Priority.Level.HIGHEST)
    public void prestart() {
        logger = new Logger("Parlez-Vous", Logger.ATTR_DEFAULT);
        CrashHandler.registerProvider(new CrashInfoParlez());
        LocaleLoader.init();
        ProgramLoader.init();
    }

    @Override
    public void start() { }
}
