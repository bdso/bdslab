/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.HashMap;
import static junit.framework.Assert.assertEquals;
import static lab.bds.lib.FunctionLib.DivRequest;
import static lab.bds.lib.FunctionLib.JWTData;
import lab.bds.scm.JWTVal;
import lab.bds.scm.ReqVal;

/**
 *
 * @author leo
 */
public class JWT {

    public static void main(String[] args) {

        String request
                = "/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxMDk5Mzg1MCwiZXhwIjoxNTExNTk4NjUwLCJlbnRpdHlfaWQiOiIxIiwiZW50aXR5X3RpdGxlIjoiSGFyZGNvZGUgdGl0bGUiLCJlbnRpdHlfc3RyZWFtX3R5cGUiOiJ2b2QiLCJ1c2VyX2lkIjoiNTdhYTNhMGViNTU1NDIwYTk0NWEyN2I0N2NlOWVmMmYiLCJzdWIiOiI1N2FhM2EwZWI1NTU0MjBhOTQ1YTI3YjQ3Y2U5ZWYyZiJ9.GRyg7m8LEJB7G4fCLKTpJF8k111L5x1Ex452IleAKWw"
                + "/57aa3a0eb555420a945a27b47ce9ef2f-transcode-output/7f042f55-ee4a-4bd0-b043-95ed88233546/other/video/avc1/1280x720/2000000-16.m4s";
        HashMap objRQ = DivRequest(request);
        HashMap objHM = JWTData((String) objRQ.get(ReqVal.JWT));

        assertEquals(objHM.get(JWTVal.USERID), "57aa3a0eb555420a945a27b47ce9ef2f");
        assertEquals(objHM.get(JWTVal.ENTITYID), "1");
        assertEquals(objHM.get(JWTVal.ENTITY_TITLE), "Hardcode title");
        assertEquals(objHM.get(JWTVal.ENTITY_STREAMTYPE), "vod");
////        assertEquals(objHM.get(JWTVal.ENTITY_CONTENTTYPE), "movie");
////        assertEquals(objHM.get(JWTVal.PUBLISHERID),
////                "105276a6-c37a-483e-8cbe-2804d2f37ad7");
        assertEquals(objHM.get(JWTVal.SESSION),
                "eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxMDk5Mzg1MCwiZXhwIjoxNTExNTk4NjUwLCJlbnRpdHlfaWQiOiIxIiwiZW50aXR5X3RpdGxlIjoiSGFyZGNvZGUgdGl0bGUiLCJlbnRpdHlfc3RyZWFtX3R5cGUiOiJ2b2QiLCJ1c2VyX2lkIjoiNTdhYTNhMGViNTU1NDIwYTk0NWEyN2I0N2NlOWVmMmYiLCJzdWIiOiI1N2FhM2EwZWI1NTU0MjBhOTQ1YTI3YjQ3Y2U5ZWYyZiJ9");
    }

}
