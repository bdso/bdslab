/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.lib;

import java.text.SimpleDateFormat;

/**
 *
 * @author leo
 */
public class TimeLib {

    public static final SimpleDateFormat INDEX_FORMAT_TIME
            = new SimpleDateFormat("yyyy.MM.dd");
    public static final SimpleDateFormat ISO_FORMAT_TIME
            = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public static final SimpleDateFormat ISO_NGX_TIME
            = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");

}
