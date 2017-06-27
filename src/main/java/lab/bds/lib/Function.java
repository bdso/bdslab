/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.lib;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
