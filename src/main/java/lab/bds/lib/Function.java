/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.lib;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import lab.bds.lib.FormatLib.LoggerFormat;
import lab.bds.obj.LoggerObj;

/**
 *
 * @author leo
 */
public class Function {

    public static String GetIndexNameES(String name) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd");
        String formatDate = ft.format(dNow);
        return name + "-" + formatDate;

    }

    public static String GetLog(LoggerFormat obj) {

        Gson gson = new Gson();
        LoggerObj jsObj = new LoggerObj();

        jsObj.setMsg(obj.msg);

        /**
         * Json Object send ParserBolt.
         *
         */
        String sObj = gson.toJson(jsObj);
        return sObj;
    }
}
