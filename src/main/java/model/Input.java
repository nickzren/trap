package model;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Nick
 */
public class Input {

    public static String query;

    public static void init(HttpServletRequest request) throws Exception {
        if (Upload.isUpload) { // search by variants file
            query = Upload.fileName;
        } else { // search by gene or region or variant
            query = request.getParameter("query").toUpperCase().replaceAll("( )+", "");
        }
    }
}
