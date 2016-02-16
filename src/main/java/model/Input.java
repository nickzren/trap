package model;

import object.Region;
import util.DBManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Nick
 */
public class Input {

    public static String query;

    public static void init(HttpServletRequest request) throws Exception {

        query = request.getParameter("query").toUpperCase();

        if (query.split("-").length == 4) {
           
        } else if (query.contains(":")) {
            initRegionListByStr(query);
        } else {
            initRegionListByGeneName(query);
        }
    }

    private static void initRegionListByStr(String regionStr) {
        
    }

    private static Region getRegionByStr(String str) {
        String[] tmp = str.split(":");
        String chr = tmp[0].replace("chr", "");

        tmp = tmp[1].split("-");
        int start = Integer.valueOf(tmp[0]);
        int end = Integer.valueOf(tmp[1]);

        return new Region(chr, start, end);
    }

    private static void initRegionListByGeneName(String geneName) throws Exception {
        
    }
}
