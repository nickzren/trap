package model;

import object.Variant;
import util.DBManager;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nick
 */
public class Output {

    public static ArrayList<Variant> variantList = new ArrayList<Variant>();
    public static String errorMsg;

    public static void init() throws Exception {
        variantList.clear();
        errorMsg = "";

        Download.init();

        if (Input.query.split("-").length == 4) {
            initVariantListByVariantId(Input.query);
        } else if (Input.query.contains(":")) {
            initVariantListByRegion(Input.query);
        }else {
            initVariantListByGene(Input.query);  
        }
        
        Download.generateFile();
    }

    public static void initVariantListByVariantId(String id) throws Exception {
        String[] tmp = id.split("-"); // chr-pos-ref-alt
        
        String chr = tmp[0];
        String pos = tmp[1];
        String alt = tmp[3];

        String sql = "SELECT * "
                + "FROM snv_score_chr" + chr + " "
                + "WHERE pos = " + pos + " "
                + "AND alt='" + alt + "'";

        ResultSet rset = DBManager.executeQuery(sql);

        while (rset.next()) {
            variantList.add(new Variant(rset));
        }

        rset.close();
    }

    public static void initVariantListByRegion(String region) throws Exception {
        String[] tmp = region.split(":"); // chr:start-end
        String chr = tmp[0];
        
        tmp = tmp[1].split("-");
        String start = tmp[0];
        String end = tmp[1];

        String sql = "SELECT * "
                + "FROM snv_score_chr" + chr + " "
                + "WHERE pos BETWEEN " + start + " AND " + end;

        ResultSet rset = DBManager.executeQuery(sql);

        while (rset.next()) {
            variantList.add(new Variant(rset));
        }

        rset.close();
    }
    
     public static void initVariantListByGene(String gene) throws Exception {
//        String sql = "SELECT * "
//                + "FROM snv_score_chr" + chr + " "
//                + "WHERE pos BETWEEN " + start + " AND " + end;
//
//        ResultSet rset = DBManager.executeQuery(sql);
//
//        while (rset.next()) {
//            variantList.add(new Variant(rset));
//        }
//
//        rset.close();
    }
}
