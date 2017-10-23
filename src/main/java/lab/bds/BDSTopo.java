package lab.bds;

import java.io.FileInputStream;
import java.util.Properties;
import static lab.bds.lib.LoggerLib.LOG;
import static lab.bds.lib.TopoLib.submitTopo;


/*
 * This 
 */
/**
 *
 * @author leo
 */
public class BDSTopo {

    private static Properties loadProperties(String propFile) throws Exception {
        Properties prop = new Properties();
        try (FileInputStream in = new FileInputStream(propFile)) {
            prop.load(in);
        }
        return prop;
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            args = new String[]{"./bdslab_local.properties"};
        }

        try {
            String propFile = args[0];
            Properties prop = loadProperties(propFile);
            submitTopo(prop);
        } catch (Exception e) {
            LOG.error("Exception is completed.", e);
            LOG.info("Check your file config.");
            System.exit(1);
        }

    }

}
