package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import object.VariantGeneScore;
import util.DBManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import object.EnsgGene;

/**
 *
 * @author Nick
 */
public class Output {

    public static List<VariantGeneScore> variantGeneScoreList = new ArrayList<VariantGeneScore>();
    public static boolean isRegionValid; // only check for out of bound , max 100kb
    public static final int maxRowToQuery = 5000;
    public static final int maxBaseNumToDisplay = 10000;

    public static void init(String query, String dbVersion) throws Exception {
        variantGeneScoreList.clear();
        isRegionValid = true;

        if (query.split("-").length == 4) { // search by variant id
            initVariantListByVariantId(query, dbVersion);
        } else if (query.contains(":")) { // search by region
            initVariantListByRegion(query, dbVersion);
        } else if (query.split("-").length == 2) { // search by variant site
            initVariantListByVariantSite(query, dbVersion);
        } else if (query.startsWith("ENSG")) { // search by ENSG gene
            initVariantListByEnsgGene(query, dbVersion);
        } else { // search by HGNC gene or return nothing found
            initVariantListByHgncGene(query, dbVersion);
        }
    }

    public static void initVariantListByVariantSite(String site, String dbVersion) throws Exception {
        String[] tmp = site.split("-"); // chr-pos

        String chr = tmp[0].replace("XY", "X");
        int pos = Integer.valueOf(tmp[1]);

        String sql = "SELECT v.ref,v.alt,v.ensg_gene,g.hgnc_gene,v.score "
                + "FROM " + DBManager.getDBName(dbVersion) + ".snv_score_chr" + chr + " v,"
                + DBManager.getDBName(dbVersion) + ".ensg_hgnc_gene g "
                + "WHERE v.pos = ? "
                + "AND v.ensg_gene = g.ensg_gene";

        PreparedStatement stmt = DBManager.prepareStatement(sql);
        stmt.setInt(1, pos);
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            VariantGeneScore variantGeneScore = new VariantGeneScore(
                    chr,
                    pos,
                    rset.getString("ref"),
                    rset.getString("alt"),
                    rset.getString("ensg_gene"),
                    rset.getString("hgnc_gene"),
                    rset.getFloat("score"));

            variantGeneScoreList.add(variantGeneScore);
        }

        rset.close();
    }

    public static void initVariantListByVariantId(String id, String dbVersion) throws Exception {
        String[] tmp = id.split("-"); // chr-pos-ref-alt

        String chr = tmp[0].replace("XY", "X");
        int pos = Integer.valueOf(tmp[1]);
        String ref = tmp[2];
        String alt = tmp[3];

        String sql = "SELECT v.ensg_gene,g.hgnc_gene,v.score "
                + "FROM " + DBManager.getDBName(dbVersion) + ".snv_score_chr" + chr + " v , "
                + DBManager.getDBName(dbVersion) + ".ensg_hgnc_gene g "
                + "WHERE v.pos = ? "
                + "AND v.alt=? "
                + "AND v.ensg_gene = g.ensg_gene";

        PreparedStatement stmt = DBManager.prepareStatement(sql);
        stmt.setInt(1, pos);
        stmt.setString(2, alt);
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            VariantGeneScore variantGeneScore = new VariantGeneScore(
                    chr,
                    pos,
                    ref,
                    alt,
                    rset.getString("ensg_gene"),
                    rset.getString("hgnc_gene"),
                    rset.getFloat("score"));

            variantGeneScoreList.add(variantGeneScore);
        }

        rset.close();
    }

    public static void initVariantListByRegion(String region, String dbVersion) throws Exception {
        String[] tmp = region.split(":"); // chr:start-end

        String chr = tmp[0].toLowerCase();

        if (chr.startsWith("chr")) {
            chr = chr.substring(3);
        }

        tmp = tmp[1].split("-");
        int start = Integer.valueOf(tmp[0]);
        int end = Integer.valueOf(tmp[1]);

        isRegionValid = isRegionValid(start, end);

        if (isRegionValid) {
            String sql = "SELECT v.pos,v.ref,v.alt,v.ensg_gene,g.hgnc_gene,v.score "
                    + "FROM " + DBManager.getDBName(dbVersion) + ".snv_score_chr" + chr + " v , "
                    + DBManager.getDBName(dbVersion) + ".ensg_hgnc_gene g "
                    + "WHERE v.pos BETWEEN ? AND ? "
                    + "AND v.ensg_gene = g.ensg_gene "
                    + "LIMIT " + maxRowToQuery;

            PreparedStatement stmt = DBManager.prepareStatement(sql);
            stmt.setInt(1, start);
            stmt.setInt(2, end);
            ResultSet rset = stmt.executeQuery();

            while (rset.next()) {
                VariantGeneScore variantGeneScore
                        = new VariantGeneScore(chr,
                                rset.getInt("pos"),
                                rset.getString("ref"),
                                rset.getString("alt"),
                                rset.getString("ensg_gene"),
                                rset.getString("hgnc_gene"),
                                rset.getFloat("score"));

                variantGeneScoreList.add(variantGeneScore);
            }

            rset.close();
        }
    }

    private static boolean isRegionValid(int start, int end) {
        return end - start <= maxBaseNumToDisplay;
    }

    public static void initVariantListByEnsgGene(String ensg, String dbVersion) throws Exception {
        EnsgGene ensgGene = getEnsgGene(ensg, dbVersion);

        if (ensgGene == null) { // not a valid ensg gene
            return;
        }

        String hgncGene = getHgncGene(ensg, dbVersion);

        if (hgncGene.isEmpty()) {
            return;
        }

        String sql = "SELECT v.pos,v.ref,v.alt,v.ensg_gene,v.score "
                + "FROM " + DBManager.getDBName(dbVersion) + ".snv_score_chr" + ensgGene.getChr() + " v "
                + "WHERE v.pos BETWEEN ? AND ? "
                + "AND v.ensg_gene = ? "
                + "LIMIT " + maxRowToQuery;

        PreparedStatement stmt = DBManager.prepareStatement(sql);
        stmt.setInt(1, ensgGene.getStart());
        stmt.setInt(2, ensgGene.getEnd());
        stmt.setString(3, ensgGene.getName());
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            VariantGeneScore variantGeneScore
                    = new VariantGeneScore(
                            ensgGene.getChr(),
                            rset.getInt("pos"),
                            rset.getString("ref"),
                            rset.getString("alt"),
                            rset.getString("ensg_gene"),
                            hgncGene,
                            rset.getFloat("score"));

            variantGeneScoreList.add(variantGeneScore);
        }

        rset.close();
    }

    private static EnsgGene getEnsgGene(String ensg, String dbVersion) throws Exception {
        String sql = "SELECT * FROM " + DBManager.getDBName(dbVersion) + ".ensg_gene_region WHERE ensg_gene = ?";

        PreparedStatement stmt = DBManager.prepareStatement(sql);
        stmt.setString(1, ensg);
        ResultSet rset = stmt.executeQuery();

        EnsgGene ensgGene = null;

        if (rset.next()) {
            ensgGene = new EnsgGene(
                    ensg,
                    rset.getString("chr"),
                    rset.getInt("start"),
                    rset.getInt("end"));
        }

        rset.close();

        return ensgGene;
    }

    private static String getHgncGene(String ensg, String dbVersion) throws Exception {
        String sql = "SELECT hgnc_gene FROM " + DBManager.getDBName(dbVersion) + ".ensg_hgnc_gene WHERE ensg_gene = ?";

        PreparedStatement stmt = DBManager.prepareStatement(sql);
        stmt.setString(1, ensg);
        ResultSet rset = stmt.executeQuery();

        if (rset.next()) {
            return rset.getString("hgnc_gene");
        }

        return "";
    }

    public static void initVariantListByHgncGene(String hgnc, String dbVersion) throws Exception {
        List<String> ensgList = getEnsgGeneNameByHgnc(hgnc, dbVersion);

        for (String ensg : ensgList) {
            initVariantListByEnsgGene(ensg, dbVersion);
        }
    }

    private static List<String> getEnsgGeneNameByHgnc(String hgnc, String dbVersion) throws Exception {
        List<String> ensgList = new ArrayList<String>();

        String sql = "SELECT ensg_gene FROM " + DBManager.getDBName(dbVersion) + ".ensg_hgnc_gene WHERE hgnc_gene = ?";

        PreparedStatement stmt = DBManager.prepareStatement(sql);
        stmt.setString(1, hgnc);
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            ensgList.add(rset.getString("ensg_gene"));
        }

        rset.close();

        return ensgList;
    }
}
