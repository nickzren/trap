<%@page import="object.VariantGeneScore"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>

<div>
    <%
        String query = (String) request.getAttribute("query");
        String uploadErrMsg = (String) request.getAttribute("uploadErrMsg");
        Boolean isRegionValid = (Boolean) request.getAttribute("isRegionValid");
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
            <button style="float: right" type="button"
                    onclick="location.href = '<%=url%>';"
                    class="btn btn-default" 
                    <%if (url.isEmpty()) {%>disabled<%}%>>
                <i class="fa fa-download">
                    Download
                </i>
            </button>
        </div>
    </div>

    <br/>

    <%
        if (uploadErrMsg != null) {
    %>
    <div class="alert alert-warning" style="width:50%">
        <h4>
            <%=uploadErrMsg%>
        </h4>
    </div>
    <%
    } else if (!isRegionValid) {
    %>
    <div class="alert alert-warning" style="width:50%">
        <h4>
            Your region is too large. Please search a region at most 100 kb.
        </h4>
    </div>
    <%
    } else if (isDownloadOnly) {
    %>
    <div class="alert alert-warning" style="width:70%">
        <h4>
            Please click the 'Download' button above to download your results.
        </h4>
    </div>
    <%
    } else if (variantGeneScoreList.isEmpty()) {
    %>
    <div class="alert alert-warning" style="width:30%">
        <h4>
            No results found from search query.
        </h4>
    </div>
    <%
    } else {
    %>   
    <table class="table table-striped">
        <thead>
            <tr>
                <th class="text-center">Variant ID</th>
                <th class="text-center">Chr</th>
                <th class="text-center">Pos</th>
                <th class="text-center">Ref</th>
                <th class="text-center">Alt</th>
                <th class="text-center">ENSG Gene</th>
                <th class="text-center">HGNC Gene</th>
                <th class="text-center">Score</th>
            </tr> 
        </thead>
        <tbody>
            <%
                for (VariantGeneScore variant : variantGeneScoreList) {
            %>
            <tr class="text-center">
                <td>
                    <a href="Search?query=<%=variant.getVariantId()%>">
                        <%=variant.getVariantId()%>
                    </a>
                </td>
                <td><%=variant.getChr()%></td>
                <td><%=variant.getPos()%></td>
                <td><%=variant.getRef()%></td>
                <td><%=variant.getAlt()%></td>
                <td>
                    <a href="Search?query=<%=variant.getEnsgGene()%>">
                        <%=variant.getEnsgGene()%>
                    </a>
                </td>
                <td>
                    <a href="Search?query=<%=variant.getHgncGene()%>">
                        <%=variant.getHgncGene()%>
                    </a>
                </td>
                <td><%=variant.getScore()%></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <%
            }
        }
    %>
