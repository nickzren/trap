package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Nick
 */
public class Input {

    public static String query;

    public static void init(HttpServletRequest request) throws Exception {
        if (Upload.isUpload) { // search by variants file
            query = "variants file";
        } else { // search by gene or region or variant
            query = request.getParameter("query").toUpperCase().replaceAll("( )+", "");
        }
    }
}
