<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Data Browser</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/main.css">

        <script src="js/jquery-latest.min.js"></script>    
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.tablesorter.js"></script>
        <script src="js/jquery.tablesorter.widgets.js"></script>
    </head>

    <body>
        <div class="container">

            <%@include file="base/header.jsp" %>

            <div class="container-main">
                <h3 class="page-header">Data Browser 
                    <a class="black" href="download.jsp">
                        <span class="label label-primary">v1</span>
                    </a>
                </h3>

                <div class="row">
                    <div class="col-md-6">
                        <form class="form-search" action="Search">
                            <div class="input-group">
                                <input name="query" class="form-control input-lg tt-input"
                                       type="text" placeholder="Search for a gene or region or variant" >
                                <div class="input-group-btn">
                                    <button class="btn btn-default input-lg tt-input" 
                                            type="submit">
                                        <i class="glyphicon glyphicon-search"></i></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <p class="text-muted" style="margin-left: 5px">
                    Examples - 
                    Gene: <a href="Search?query=DNM1">DNM1</a>,
                    Region: <a href="Search?query=15:26792932-26818879">15:26792932-26818879</a>, 
                    Variant: <a href="Search?query=9-140043500-G-A">9-140043500-G-A</a>
                </p>

                <br/>

                <%--<%@include file="browser/result.jsp" %>--%>  
            </div>
        </div>

        <%@include file="base/footer.jsp" %>

        <%@include file="base/counter.jsp" %> 
    </body>
</html>


