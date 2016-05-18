<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="base/head.jsp" %>

    <body>
        <div class="container">

            <%@include file="base/header.jsp" %>

            <div class="container-main">
                <h3 class="page-header">Download 
                    <span class="label label-primary">v1</span>
                </h3>

                <p class="lead">
                    <mark>The following flat files contain ...</mark>
                </p>

                <p>
                <u>
                    <mark>Coordinates are currently based on GRCh37 (hg19), 
                    and represent chromosomes 1-22, X and Y.</mark>
                </u>
                </p>

                <p>
                    <a href="">
                        <i class="fa fa-file"></i>
                        trap_score_v1.gz
                    </a> 
                    (variant file in <mark>???</mark> format)
                </p>

                <small>
                    The current release (v1) was last updated on <mark><em>?/?/2016</em></mark>.
                </small>
            </div>
        </div>

        <%@include file="base/footer.jsp" %>
        
        <%@include file="base/counter.jsp" %> 
    </body>
</html>