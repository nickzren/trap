package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import object.VariantGeneScore;

/**
 *
 * @author nick
 */
public class Download {

    public static String rootPath;
    public static String url;
    public static boolean isDownloadOnly;

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

            String fileName = Input.query + ".csv";

            String filePath = folder.getAbsolutePath() + File.separator + fileName;

            File file = new File(filePath);

            if (!file.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

                bw.write(VariantGeneScore.title);
                bw.newLine();

                for (VariantGeneScore variant : Output.variantGeneScoreList) {
                    bw.write(variant.toString());
                    bw.newLine();
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
            Output.variantGeneScoreList.clear(); // free memory
        }
    }
}