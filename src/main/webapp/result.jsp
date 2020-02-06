<%@page import="java.util.List"%>
<%@page import="object.VariantGeneScore"%>
<%@page import="java.util.Map"%>

<%
    String query = (String) request.getAttribute("query");
    Boolean isRegionValid = (Boolean) request.getAttribute("isRegionValid");
    Boolean isTruncated = (Boolean) request.getAttribute("isTruncated");
    List<VariantGeneScore> variantGeneScoreList
            = (List<VariantGeneScore>) request.getAttribute("variantGeneScoreList");

    if (query != null) {
%>
<div class="row">
    <div class="col-md-10">
        <h4>
            Data version: <mark><%=request.getAttribute("version")%></mark>
        </h4>
    </div>
</div>

<div class="row">
    <div class="col-md-10">
        <h4>
            Search: <mark><%=query%></mark>
        </h4>
    </div>
</div>

<br/>

<%
    if (!isRegionValid) {
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
    if (isTruncated) {
%>
<div class="alert alert-warning" style="width:90%">
    <h4>
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
            <th class="text-center">TraP Score</th>
        </tr> 
    </thead>
    <tbody>
        <%
            for (VariantGeneScore variant : variantGeneScoreList) {
        %>
        <tr class="text-center">
            <td><a href="Search?query=<%=variant.getVariantId()%>">
                    <%=variant.getVariantId()%></a> &nbsp 
                <a target="_blank" href="https://franklin.genoox.com/variant/snp/chr<%=variant.getVariantId()%>">
                    <span class="label label-default">franklin</span>
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
            // search variant
            String[] tmp = query.split("-"); // chr-pos-ref-alt
            if(tmp.length == 4) 
            {
%>    
    
<br/>

<gnx-acmg-classification></gnx-acmg-classification>
<script src="https://s3.amazonaws.com/resources.genoox.com/assets/gnx-elements.js"></script>
<script>
  let elem = document.querySelector('gnx-acmg-classification');
  elem.variantId = {
    ref: '<%=tmp[2]%>',
    alt: '<%=tmp[3]%>',
    chr: 'chr<%=tmp[0]%>',
    pos: <%=tmp[1]%>,
  };
</script>

<%
            }
        }
    }
%>


