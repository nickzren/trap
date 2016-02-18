package model;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Nick
 */
public class Input {

    public static String query;

    public static void init(HttpServletRequest request) throws Exception {
        query = request.getParameter("query").toUpperCase().replaceAll("( )+", "");
    }
}
