package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import object.Variant;

/**
 *
 * @author nick
 */
public class Download {

    public static String rootPath;
    public static String url;

    public static void init() throws Exception {
        url = "";
    }

    public static void generateFile() throws Exception {
        if (!Output.variantList.isEmpty()) { // TRUE - no results from search query
            String folderPath = rootPath + File.separator;

            if (Input.query.split("-").length == 4) {
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

                bw.write(Variant.title);
                bw.newLine();

                for (Variant variant : Output.variantList) {
                    bw.write(variant.toString());
                    bw.newLine();
                }

                bw.flush();
                bw.close();
            }

            url = "./downloads/" + folder.getName() + "/" + fileName;
        }
    }
}
