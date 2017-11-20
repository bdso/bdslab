/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.bds.lib;

/**
 *
 * @author leo
 */
public class RegexLib {

    public static final String RGX_JWT_STREAM
            = "\\/([A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+=]+)\\/(.+)";
    public static final String RGX_STREAM_AUDIO
            = "(.+)"
            + "\\/([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})"
            + "\\/(\\w+)\\/(\\w+)\\/([\\w-]+)\\/(\\w+)\\/(\\d+)\\/(\\d+)-(\\d+)\\.m4s$";
    public static final String RGX_STREAM_VIDEO
            = "(.+)"
            + "\\/([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})"
            + "\\/(\\w+)\\/(\\w+)\\/(\\w+)\\/(\\d+x\\d+)\\/(\\d+)-(\\d+)\\.m4s$";
}
