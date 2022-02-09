<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>    
    <%@include file="base/head.jsp" %>

    <body>
        <div class="container">

            <%@include file="base/header.jsp" %>

            <%
                String query = (String) request.getAttribute("query");

                String build = "hg19";
                if (request.getParameter("build") != null) {
                    build = request.getParameter("build");
                } else if (request.getSession().getAttribute("build") != null) {
                    build = (String) request.getSession().getAttribute("build");
                }
                request.setAttribute("build", build);
                request.getSession().setAttribute("build", build);
            %>

            <div class="container-main">            

                <h4 class="alert alert-success" role="alert"><strong>TraP v3 has been lifted-over to hg38! (Feb 2022)</strong></h4>

                <div class="jumbotron" style="padding:20px 40px 20px 50px">
                    <h2>TraP-score v3.0 Browser
                        <span class="badge badge-pill badge-secondary"><%=build%></span>
                    </h2>

                    <div class="row">
                        <div class="col-md-10">
                            <form class="form-search" action="Search">
                                <div class="input-group">
                                    <input name="query" class="form-control input-lg tt-input"
                                           type="text" placeholder="Search for a ENSG gene or region or variant" 
                                           value="<%=query == null ? "" : query%>">
                                    <div class="input-group-btn">
                                        <button class="btn btn-default input-lg tt-input" 
                                                type="submit">
                                            <i class="glyphicon glyphicon-search"></i></button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div style="top:6px" class="col-md-2">
                            <div class="dropdown">
                                <button class="btn btn-primary dropdown-toggle" type="button" id="versionMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <%=build%>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="versionMenu">
                                    <li><a href="Search?build=hg19">hg19</a></li>
                                    <li><a href="Search?build=hg38">hg38</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <p class="text-muted" style="margin-left: 5px">
                        ENSG: <a href="Search?query=ENSG00000186092">ENSG00000186092</a>,
                        HGNC: <a href="Search?query=PRLH">PRLH</a>,
                        <%
                            if (build.equals("hg19")) {
                        %>
                        Region: <a href="Search?query=1:7905143-7905156">1:7905143-7905156</a>, 
                        Variant: <a href="Search?query=1-7905043-C-T">1-7905043-C-T</a> / 
                        <a href="Search?query=1-7905043">1-7905043</a>
                        <%
                        } else {
                        %>
                        Region: <a href="Search?query=1:7965203-7965216">1:7965203-7965216</a>, 
                        Variant: <a href="Search?query=1-7965103-G-T">1-7965103-G-T</a> / 
                        <a href="Search?query=1-7965103">1-7965103</a>
                        <%
                            }
                        %>
                    </p>
                </div>

                <%@include file="result.jsp" %>
            </div>

            <%@include file="base/footer.jsp" %>  

            <%@include file="base/counter.jsp" %> 
        </div>
    </body>
</html>
