<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Download</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/main.css">
    </head>

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
                        variant_damaging_score_v1.gz
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