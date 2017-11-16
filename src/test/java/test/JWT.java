/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.HashMap;
import static junit.framework.Assert.assertEquals;
import static lab.bds.lib.FunctionLib.JWTData;
import lab.bds.scm.JWTVal;

/**
 *
 * @author leo
 */
public class JWT {

    public static void main(String[] args) {

        String request = "/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJlNWU2NGMxMi0zYzRhLTRmNjUtYWM0MC0xZTdhMzdkYjllZmIiLCJlbnRpdHlJZCI6MSwidGl0bGUiOiJUaG9yOiBSYWduYXJvayIsImNvbnRlbnRUeXBlIjoibW92aWUiLCJzdHJlYW1UeXBlIjoidm9kIiwicHVibGlzaGVySWQiOiIxMDUyNzZhNi1jMzdhLTQ4M2UtOGNiZS0yODA0ZDJmMzdhZDcifQ.eGJL4VBUbk8zr8Vb-1oGRRQGHpYi5uonsYAUx8jOiso/57aa3a0eb555420a945a27b47ce9ef2f-transcode-output/229e4c9c-9c14-4da5-9801-4d357ec44d9c/other/video/avc1/640x360/500000-93.m4s";
        HashMap objHM = JWTData(request);
        assertEquals(objHM.get(JWTVal.USERID), "e5e64c12-3c4a-4f65-ac40-1e7a37db9efb");
        assertEquals(objHM.get(JWTVal.ENTITYID), "1");
        assertEquals(objHM.get(JWTVal.TITLE), "Thor: Ragnarok");
        assertEquals(objHM.get(JWTVal.CONTENTTYPE), "movie");
        assertEquals(objHM.get(JWTVal.STREAMTYPE), "vod");
        assertEquals(objHM.get(JWTVal.PUBLISHERID), "105276a6-c37a-483e-8cbe-2804d2f37ad7");
        assertEquals(objHM.get(JWTVal.SESSION), "eyJ1c2VySWQiOiJlNWU2NGMxMi0zYzRhLTRmNjUtYWM0MC0xZTdhMzdkYjllZmIiLCJlbnRpdHlJZCI6MSwidGl0bGUiOiJUaG9yOiBSYWduYXJvayIsImNvbnRlbnRUeXBlIjoibW92aWUiLCJzdHJlYW1UeXBlIjoidm9kIiwicHVibGlzaGVySWQiOiIxMDUyNzZhNi1jMzdhLTQ4M2UtOGNiZS0yODA0ZDJmMzdhZDcifQ");

    }

}
