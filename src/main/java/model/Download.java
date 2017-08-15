package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import object.VariantGeneScore;
import util.DBManager;

/**
 *
 * @author nick
 */
public class Download {

    public static String rootPath;
    public static String url;
    public static boolean isDownloadOnly;
    public static int maxDownloadNum = 1000;

    public static void init() throws Exception {
        url = "";
        isDownloadOnly = false;

        generateFile();

        isDownloadOnly();
    }

    private static void generateFile() throws Exception {
        if (!Output.variantGeneScoreList.isEmpty()) { // TRUE - no results from search query
            String folderPath = rootPath + File.separator;

            if (Upload.isUpload) {
                folderPath += "variants";
            } else if (Input.query.split("-").length == 4) {
                folderPath += "variant";
            } else if (Input.query.contains(":")) {
                folderPath += "region";
            } else {
                folderPath += "gene";
            }

            File folder = new File(folderPath);

            if (!folder.exists()) {
                folder.mkdir();
            }

            String fileName = Input.query + "." + DBManager.dbVersion + ".csv";

            String filePath = folder.getAbsolutePath() + File.separator + fileName;

            File file = new File(filePath);

            if (!file.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

                bw.write(VariantGeneScore.title);
                bw.newLine();

                int count = 0;

                for (VariantGeneScore variant : Output.variantGeneScoreList) {
                    bw.write(variant.toString());
                    bw.newLine();

                    if (++count == maxDownloadNum) { // restrict to only allow download 1k variants.
                        break;
                    }
                }

                bw.flush();
                bw.close();
            }

            url = "./downloads/" + folder.getName() + "/" + fileName;
        }
    }

    private static void isDownloadOnly() {
        if (Output.variantGeneScoreList.size() <= Output.maxVariantNumToDisplay) {
            isDownloadOnly = false;
        } else {
            isDownloadOnly = true;
//            Output.variantGeneScoreList.clear(); // free memory

            // hack to restrict only display 1000 rows
            Output.variantGeneScoreList = Output.variantGeneScoreList.subList(0, maxDownloadNum);
        }
    }
}
