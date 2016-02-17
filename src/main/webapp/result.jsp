<%@page import="object.VariantGeneScore"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>

<div>
    <%
//        String query = (String) request.getAttribute("query");
        String url = (String) request.getAttribute("url");
        ArrayList<VariantGeneScore> variantGeneScoreList
                = (ArrayList<VariantGeneScore>) request.getAttribute("variantGeneScoreList");

        if (variantGeneScoreList != null) {
    %>

    <div class="row">
        <div class="col-md-10">
        </div>

        <div class="col-md-2" >
            <a style="float: right" href="<%=url%>" >
                <button type="button" class="btn btn-primary" data-toggle="tooltip" 
                        title="Download gene intolerance score information with additional fields">
                    Download</button>
            </a>
        </div>
    </div>

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
