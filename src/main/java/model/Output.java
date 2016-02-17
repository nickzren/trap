package model;

import object.VariantGeneScore;
import util.DBManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import object.EnsgGene;

/**
 *
 * @author Nick
 */
public class Output {

    public static ArrayList<VariantGeneScore> variantGeneScoreList = new ArrayList<VariantGeneScore>();
    public static String errorMsg;

    public static void init() throws Exception {
        variantGeneScoreList.clear();
        errorMsg = "";

        Download.init();

        if (Input.query.split("-").length == 4) {
            initVariantListByVariantId(Input.query);
        } else if (Input.query.contains(":")) {
            initVariantListByRegion(Input.query);
        } else {
            initVariantListByGene(Input.query);
        }
    }

    public static void initVariantListByVariantId(String id) throws Exception {
        String[] tmp = id.split("-"); // chr-pos-ref-alt

        String chr = tmp[0];
        int pos = Integer.valueOf(tmp[1]);
        String ref = tmp[2];
        String alt = tmp[3];

        String sql = "SELECT * "
                + "FROM snv_score_chr" + chr + " "
                + "WHERE pos = " + pos + " "
                + "AND alt='" + alt + "'";

        ResultSet rset = DBManager.executeQuery(sql);

        while (rset.next()) {
            VariantGeneScore variantGeneScore = new VariantGeneScore(
                    chr,
                    pos,
                    ref,
                    alt,
                    rset.getString("ensg_gene"),
                    rset.getFloat("score"));

            variantGeneScoreList.add(variantGeneScore);
        }

        rset.close();
    }

    public static void initVariantListByRegion(String region) throws Exception {
        String[] tmp = region.split(":"); // chr:start-end

        String chr = tmp[0].toLowerCase();

        if (chr.startsWith("chr")) {
            chr = chr.substring(3);
        }

        tmp = tmp[1].split("-");
        String start = tmp[0];
        String end = tmp[1];

        String sql = "SELECT * "
                + "FROM snv_score_chr" + chr + " "
                + "WHERE pos BETWEEN " + start + " AND " + end;

        ResultSet rset = DBManager.executeQuery(sql);

        while (rset.next()) {
            VariantGeneScore variantGeneScore
                    = new VariantGeneScore(chr,
                            rset.getInt("pos"),
                            rset.getString("ref"),
                            rset.getString("alt"),
                            rset.getString("ensg_gene"),
                            rset.getFloat("score"));

            variantGeneScoreList.add(variantGeneScore);
        }

        rset.close();
    }

    public static void initVariantListByGene(String gene) throws Exception {
        EnsgGene ensgGene = getEnsgGene(gene);

        if (ensgGene == null) { // not a valid ensg gene
            return;
        }

        String sql = "SELECT * "
                + "FROM snv_score_chr" + ensgGene.getChr() + " "
                + "WHERE pos BETWEEN " + ensgGene.getStart() + " AND " + ensgGene.getEnd() + " "
                + "AND ensg_gene ='" + ensgGene.getName() + "'";

        ResultSet rset = DBManager.executeQuery(sql);

        while (rset.next()) {
            VariantGeneScore variantGeneScore
                    = new VariantGeneScore(
                            ensgGene.getChr(),
                            rset.getInt("pos"),
                            rset.getString("ref"),
                            rset.getString("alt"),
                            rset.getString("ensg_gene"),
                            rset.getFloat("score"));

            variantGeneScoreList.add(variantGeneScore);
        }

        rset.close();
    }

    private static EnsgGene getEnsgGene(String gene) throws Exception {
        String sql = "SELECT * "
                + "FROM ensg_gene_region "
                + "WHERE ensg_gene = '" + gene + "'";

        ResultSet rset = DBManager.executeQuery(sql);

        EnsgGene ensgGene = null;

        if (rset.next()) {
            ensgGene = new EnsgGene(
                    gene,
                    rset.getString("chr"),
                    rset.getInt("start"),
                    rset.getInt("end"));
        }

        rset.close();

        return ensgGene;
    }
}
