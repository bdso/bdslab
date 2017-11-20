/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.HashMap;
import static junit.framework.Assert.assertEquals;
import static lab.bds.lib.FunctionLib.DivRequest;
import static lab.bds.lib.FunctionLib.DivStream;
import lab.bds.scm.ReqVal;

/**
 *
 * @author leo
 */
public class Regex {

    public static void main(String[] args) {

//        String request
//                = "/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxMDk4MDEwOCwiZXhwIjoxNTExNTg0OTA4LCJlbnRpdHlfaWQiOiIxIiwiZW50aXR5X3RpdGxlIjoiSGFyZGNvZGUgdGl0bGUiLCJ2aWRlb19zdHJlYW1fdHlwZSI6InZvZCIsInVzZXJfaWQiOiI1N2FhM2EwZWI1NTU0MjBhOTQ1YTI3YjQ3Y2U5ZWYyZiIsInN1YiI6IjU3YWEzYTBlYjU1NTQyMGE5NDVhMjdiNDdjZTllZjJmIn0.2gw7Bws1u--4eE-fRZykMnHLy-gZpxkA8Y30h2wwNAc"
//                + "/57aa3a0eb555420a945a27b47ce9ef2f-transcode-output"
//                + "/7f042f55-ee4a-4bd0-b043-95ed88233546"
//                + "/other/audio/unk-1/mp4a/64000/64000-16.m4s";
        String request
                = "/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJlNWU2NGMxMi0zYzRhLTRmNjUtYWM0MC0xZTdhMzdkYjllZmIiLCJlbnRpdHlJZCI6MSwidGl0bGUiOiJUaG9yOiBSYWduYXJvayIsImNvbnRlbnRUeXBlIjoibW92aWUiLCJzdHJlYW1UeXBlIjoidm9kIiwicHVibGlzaGVySWQiOiIxMDUyNzZhNi1jMzdhLTQ4M2UtOGNiZS0yODA0ZDJmMzdhZDcifQ.eGJL4VBUbk8zr8Vb-1oGRRQGHpYi5uonsYAUx8jOiso"
                + "/57aa3a0eb555420a945a27b47ce9ef2f-transcode-output"
                + "/229e4c9c-9c14-4da5-9801-4d357ec44d9c"
                + "/other/video/avc1/640x360/500000-93.m4s";

        if (request.contains("/video/")) {

            /**
             * Request Video.
             */
            HashMap objRQ = DivRequest(request);

            assertEquals(objRQ.get(ReqVal.JWT),
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJlNWU2NGMxMi0zYzRhLTRmNjUtYWM0MC0xZTdhMzdkYjllZmIiLCJlbnRpdHlJZCI6MSwidGl0bGUiOiJUaG9yOiBSYWduYXJvayIsImNvbnRlbnRUeXBlIjoibW92aWUiLCJzdHJlYW1UeXBlIjoidm9kIiwicHVibGlzaGVySWQiOiIxMDUyNzZhNi1jMzdhLTQ4M2UtOGNiZS0yODA0ZDJmMzdhZDcifQ.eGJL4VBUbk8zr8Vb-1oGRRQGHpYi5uonsYAUx8jOiso");
            assertEquals(objRQ.get(ReqVal.STREAM),
                    "57aa3a0eb555420a945a27b47ce9ef2f-transcode-output"
                    + "/229e4c9c-9c14-4da5-9801-4d357ec44d9c"
                    + "/other/video/avc1/640x360/500000-93.m4s");

            HashMap objST = DivStream((String) objRQ.get(ReqVal.STREAM));
            assertEquals(objST.get(ReqVal.TRAN_OUT),
                    "57aa3a0eb555420a945a27b47ce9ef2f-transcode-output");
            assertEquals(objST.get(ReqVal.NAME_ENTITY),
                    "229e4c9c-9c14-4da5-9801-4d357ec44d9c");
            assertEquals(objST.get(ReqVal.PROTOCOL), "other");
            assertEquals(objST.get(ReqVal.RQ_TYPE), "video");
            assertEquals(objST.get(ReqVal.CODEC), "avc1");
            assertEquals(objST.get(ReqVal.PROFILE), "640x360");
            assertEquals(objST.get(ReqVal.BITRATE), "500000");
            assertEquals(objST.get(ReqVal.CHUNK), "93");

        } else {

            /**
             * Request Audio.
             */
            HashMap objRQ = DivRequest(request);

            assertEquals(objRQ.get(ReqVal.JWT),
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxMDk4MDEwOCwiZXhwIjoxNTExNTg0OTA4LCJlbnRpdHlfaWQiOiIxIiwiZW50aXR5X3RpdGxlIjoiSGFyZGNvZGUgdGl0bGUiLCJ2aWRlb19zdHJlYW1fdHlwZSI6InZvZCIsInVzZXJfaWQiOiI1N2FhM2EwZWI1NTU0MjBhOTQ1YTI3YjQ3Y2U5ZWYyZiIsInN1YiI6IjU3YWEzYTBlYjU1NTQyMGE5NDVhMjdiNDdjZTllZjJmIn0.2gw7Bws1u--4eE-fRZykMnHLy-gZpxkA8Y30h2wwNAc");
            assertEquals(objRQ.get(ReqVal.STREAM),
                    "57aa3a0eb555420a945a27b47ce9ef2f-transcode-output"
                    + "/7f042f55-ee4a-4bd0-b043-95ed88233546"
                    + "/other/audio/unk-1/mp4a/64000/64000-16.m4s");

            HashMap objST = DivStream((String) objRQ.get(ReqVal.STREAM));
            assertEquals(objST.get(ReqVal.TRAN_OUT),
                    "57aa3a0eb555420a945a27b47ce9ef2f-transcode-output");
            assertEquals(objST.get(ReqVal.NAME_ENTITY),
                    "7f042f55-ee4a-4bd0-b043-95ed88233546");
            assertEquals(objST.get(ReqVal.PROTOCOL), "other");
            assertEquals(objST.get(ReqVal.RQ_TYPE), "audio");
            assertEquals(objST.get(ReqVal.LANGUAGE), "unk-1");
            assertEquals(objST.get(ReqVal.CODEC), "mp4a");
            assertEquals(objST.get(ReqVal.BITRATE), "64000");
            assertEquals(objST.get(ReqVal.CHUNK), "16");
        }

    }

}
