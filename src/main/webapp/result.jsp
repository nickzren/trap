<%@page import="java.util.List"%>
<%@page import="object.VariantGeneScore"%>
<%@page import="java.util.Map"%>

<div>
    <%
        String version = (String) request.getAttribute("version");
        String query = (String) request.getAttribute("query");
        String uploadErrMsg = (String) request.getAttribute("uploadErrMsg");
        Boolean isRegionValid = (Boolean) request.getAttribute("isRegionValid");
        String url = (String) request.getAttribute("url");
        Boolean isDownloadOnly = (Boolean) request.getAttribute("isDownloadOnly");
        List<VariantGeneScore> variantGeneScoreList
                = (List<VariantGeneScore>) request.getAttribute("variantGeneScoreList");

        if (query != null) {
    %>
    <div class="row">
        <div class="col-md-10">
            <h4>
                Data version: <mark><%=version%></mark>
            </h4>
        </div>
    </div>

    <div class="row">
        <div class="col-md-10">
            <h4>
                Search: <mark><%=query%></mark>
            </h4>
        </div>

        <!--        <div class="col-md-2" >
                    <button style="float: right" type="button"
                            onclick="location.href = '<%//=url%>';"
                            class="btn btn-default" 
        <% //if (url.isEmpty()) {%>disabled<%//}%>>
    <i class="fa fa-download">
        Download
    </i>
</button>
</div>-->
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
            Your region is too large. Please search a region at most 10 kb.
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
        if (isDownloadOnly) {
    %>
    <div class="alert alert-warning" style="width:90%">
        <h4>
            <!--The search result is too large to display. Please click the 'Download' button to download your results.-->
            Please notice that the displayed variant list is truncated. To obtain the full database please refer to the terms of use.
        </h4>
    </div>
    <%
        }
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
                <td><%=variant.getVariantId()%> &nbsp 
                    <a target="_blank" href="https://varsome.com/variant/hg19/<%=variant.getVariantId()%>">
                        <span class="label label-default">varsome</span>
                    </a>
                </td>
                <td><%=variant.getChr()%></td>
                <td><%=variant.getPos()%></td>
                <td><%=variant.getRef()%></td>
                <td><%=variant.getAlt()%></td>
                <td><%=variant.getEnsgGene()%></td>
                <td><%=variant.getHgncGene()%></td>
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
