package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import model.Output;
import object.VariantGeneScore;
import util.DBManager;

/**
 *
 * @author nick
 */
public class Search extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String version = "hg19";
            if (request.getParameter("version") != null) {
                version = request.getParameter("version");
            } else if(request.getSession().getAttribute("version") != null) {
                version = (String) request.getSession().getAttribute("version");
            }
            request.setAttribute("version", version);
            request.getSession().setAttribute("version", version);

            List<VariantGeneScore> variantGeneScoreList = new ArrayList<>();

            if (request.getParameter("query") != null) {

                DBManager.init();

                // search by gene or region or variant
                String query = request.getParameter("query").toUpperCase().replaceAll("( )+", "");

                boolean isRegionValid = checkRegionValid(request, query);

                Output.init(query, version, variantGeneScoreList);

                request.setAttribute("query", query);
                request.setAttribute("isTruncated", Output.isTruncated(variantGeneScoreList));
                request.setAttribute("variantGeneScoreList", variantGeneScoreList);
                request.setAttribute("isRegionValid", isRegionValid);
            }

            final String content_type = request.getParameter("content-type");
            if ("text/xml".equals(content_type)) {
                writeVariantsAsXml(request, response, variantGeneScoreList);
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("errorMsg4Debug", ex.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private boolean checkRegionValid(HttpServletRequest request, String query) {
        if (query.contains(":")) { // search region
            String[] tmp = query.split(":"); // chr:start-end
            tmp = tmp[1].split("-");
            int start = Integer.valueOf(tmp[0]);
            int end = Integer.valueOf(tmp[1]);

            return Output.isRegionValid(start, end);
        } else {
            return true;
        }
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

    /**
     * output a variant list as XML
     */
    private void writeVariantsAsXml(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final List<VariantGeneScore> variants
    ) throws ServletException, IOException {
        String encoding = request.getCharacterEncoding();
        if (encoding == null || encoding.trim().isEmpty()) {
            encoding = "UTF-8";
        }
        response.setContentType("text/xml");
        response.setCharacterEncoding(encoding);
        final OutputStream out = response.getOutputStream();
        try {
            final XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            final XMLStreamWriter w = xmlOutputFactory.createXMLStreamWriter(out, encoding);
            w.writeStartDocument(encoding, "1.0");
            w.writeStartElement("trap");
            w.writeComment("TraP is protected by copyright. " + new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())
                    + " TThe Trustees of Columbia University in the City of New York. All Rights Reserved.\n"
                    + "TraP is made available for internal, non-commercial, academic and research purposes.  "
                    + "Using TraP for any commercial purpose is strictly prohibited without a license.");
            if (variants != null) {
                for (final VariantGeneScore variant : variants) {
                    variant.writeAsXml(w);
                }
            }
            w.writeEndElement();//close element 'trap'
            w.writeEndDocument();
            w.flush();
            w.close();
        } catch (final XMLStreamException err) {
            throw new IOException(err);
        }
        try {
            out.flush();
        } catch (IOException err) {
        }
    }
}
