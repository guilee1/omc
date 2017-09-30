package com.ltln.modules.ni.omc.framework.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class Logger {

    private static final Log log = LogFactory.getLog(Logger.class);

//    static boolean isLogUserNil() {
//        return MsanServerParam.CONFIGUSER == null;
//    }

    public static void error(Throwable e) {
//        if (isLogUserNil()) {
            log.error(e);
//        } else {
//            MsanServerParam.CONFIGUSER.logException(e, MsanServerParam.DEBUG_CRITICAL);
//        }
    }

    public static void error(String msg, Throwable e) {
//        if (isLogUserNil()) {
            log.error(msg, e);
//        } else {
//            MsanServerParam.CONFIGUSER.fail(msg, e);
//        }
    }

    public static void info(String e) {
//        if (isLogUserNil()) {
            System.out.println(e);
//        } else {
//            MsanServerParam.CONFIGUSER.log(e, MsanServerParam.DEBUG_MAJOR);
//        }
    }

    public static void info(String msg, Throwable e) {
//        if (isLogUserNil()) {
            log.info(msg, e);
//        } else {
//            MsanServerParam.CONFIGUSER.logException(e, MsanServerParam.DEBUG_MAJOR);
//        }
    }

    public static void debug(String e) {
//        if (isLogUserNil()) {
            log.debug(e);
//        } else {
//            MsanServerParam.CONFIGUSER.log(e, MsanServerParam.DEBUG_MINOR);
//        }
    }

    public static void debug(String msg, Throwable e) {
//        if (isLogUserNil()) {
            log.debug(msg, e);
//        } else {
//            MsanServerParam.CONFIGUSER.logException(e, MsanServerParam.DEBUG_MINOR);
//        }
    }
}
