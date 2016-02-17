<%@page import="object.VariantGeneScore"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>

<div>
    <%
        String query = (String) request.getAttribute("query");
        String url = (String) request.getAttribute("url");
        Boolean isDownloadOnly = (Boolean) request.getAttribute("isDownloadOnly");
        ArrayList<VariantGeneScore> variantGeneScoreList
                = (ArrayList<VariantGeneScore>) request.getAttribute("variantGeneScoreList");

        if (query != null) {
    %>
    <div class="row">
        <div class="col-md-10">
            <h4>
                Search: <mark><%=query%></mark>
            </h4>
        </div>

        <div class="col-md-2" >
            <a style="float: right" href="<%=url%>" >
                <button type="button" class="btn btn-primary" data-toggle="tooltip" 
                        title="Download gene intolerance score information with additional fields">
                    Download</button>
            </a>
        </div>
    </div>
                
    <br/>
                
    <%}
        if (isDownloadOnly != null && isDownloadOnly) {
    %>
    
    <div>
        <h3>
            <span class="label label-warning">
                Search result is too large, please click the blue 'Download' button above to download it.
            </span>  
        </h3> 
    </div>
    <%
        }
        if (variantGeneScoreList != null && !variantGeneScoreList.isEmpty()) {
    %>
     
    <table class="table table-striped">
        <thead>
            <tr>
                <th class="text-center">Chr</th>
                <th class="text-center">Pos</th>
                <th class="text-center">Ref</th>
                <th class="text-center">Alt</th>
                <th class="text-center">ENSG Gene</th>
                <th class="text-center">Score</th>
            </tr> 
        </thead>
        <tbody>
            <%
                for (VariantGeneScore variant : variantGeneScoreList) {
            %>
            <tr class="text-center">    
                <td><%=variant.getChr()%></td>
                <td><%=variant.getPos()%></td>
                <td><%=variant.getRef()%></td>
                <td><%=variant.getAlt()%></td>
                <td><%=variant.getEnsgGene()%></td>
                <td><%=variant.getScore()%></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <%
        }
    %>
