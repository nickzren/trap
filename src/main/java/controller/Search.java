package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import model.Download;
import model.Output;
import model.Upload;
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
            if (DBManager.dbVersion == null) {
                DBManager.dbVersion = "v2";
            } else if (request.getParameter("version") != null) {
                DBManager.dbVersion = request.getParameter("version");
            }

            // search by upload variant file
            Upload.init(request);

            if (request.getParameter("query") != null || Upload.isUpload) {
                if (Download.rootPath == null) {
                    Download.rootPath = getServletContext().getRealPath("/downloads/");
                    Upload.rootPath = getServletContext().getRealPath("/uploads/");
                }

                DBManager.init();
                
                String query;

                if (Upload.isUpload) { // search by variants file
                    query = Upload.fileName;
                } else { // search by gene or region or variant
                    query = request.getParameter("query").toUpperCase().replaceAll("( )+", "");
                }

                Output.init(query);

                Download.init(query);

                setRequest(request, query);
            }

            final String content_type = request.getParameter("content-type");
            if("text/xml".equals(content_type) && 
            		Upload.uploadErrMsg!=null &&
            		Output.isRegionValid &&
            		!Download.isDownloadOnly
            		)
            	{
            	writeVariantsAsXml(request, response, Output.variantGeneScoreList);
            	}
            else
            	{
            	request.getRequestDispatcher("index.jsp").forward(request, response);
            	}
        } catch (Exception ex) {
//            request.setAttribute("errorMsg4Debug", ex.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void setRequest(HttpServletRequest request, String query) {
        request.setAttribute("version", DBManager.dbVersion);
        request.setAttribute("query", query);
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
    
    /** output a variant list as XML */
    private void writeVariantsAsXml(
    		final HttpServletRequest request,
    		final HttpServletResponse response,
    		final List<VariantGeneScore> variants
    		) throws ServletException,IOException {
    	String encoding = request.getCharacterEncoding();
    	if(encoding==null || encoding.trim().isEmpty()) encoding = "UTF-8";
    	response.setContentType("text/xml");
    	final OutputStream out = response.getOutputStream();
    	try {
    		final XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
    		final XMLStreamWriter w= xmlOutputFactory.createXMLStreamWriter(out,encoding);
    		w.writeStartDocument(encoding, "1.0");
    		w.writeStartElement("trap");
    		w.writeComment("TraP is protected by copyright. 2017 The Trustees of Columbia University in the City of New York.");
    		if(variants!=null)
	    		{
	    		for(final VariantGeneScore variant: variants)
	    			{
	    			variant.writeAsXml(w);
	    			}
	    		}
    		w.writeEndElement();//close element 'trap'
    		w.writeEndDocument();
    		w.flush();
    		w.close();
    		}
    	catch(final XMLStreamException err)
    		{
    		throw new IOException(err);
    		}
    	try {out.flush();} catch(IOException err) {} 
    	}
}
