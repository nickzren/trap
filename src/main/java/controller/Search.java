package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Download;
import model.Input;
import model.Output;
import model.Upload;
import util.DBManager;

/**
 *
 * @author nick
 */
public class Search extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (DBManager.dbVersion == null) {
                DBManager.dbVersion = "v2";
            } else if (request.getParameter("version") != null) {
                DBManager.dbVersion = request.getParameter("version");
            }

            if (request.getParameter("query") != null) {
                if (Download.rootPath == null) {
                    Download.rootPath = getServletContext().getRealPath("/downloads/");
                    Upload.rootPath = getServletContext().getRealPath("/uploads/");
                }

                DBManager.init();

                Upload.init(request);

                Input.init(request);

                Output.init();

                Download.init();
                
                setRequest(request);
            }

            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception ex) {
//            request.setAttribute("errorMsg4Debug", ex.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void setRequest(HttpServletRequest request) {
        request.setAttribute("version", DBManager.dbVersion);
        request.setAttribute("query", Input.query);
        request.setAttribute("uploadErrMsg", Upload.uploadErrMsg);
        request.setAttribute("variantGeneScoreList", Output.variantGeneScoreList);
        request.setAttribute("isRegionValid", Output.isRegionValid);
        request.setAttribute("url", Download.url);
        request.setAttribute("isDownloadOnly", Download.isDownloadOnly);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "trap search query";
    }
}
