<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>    
    <head>
        <title>Home</title>
        <meta charset="UTF-8">
        <meta name="description" content="VDSdb (Variant Damaging Score Data Browser) is...">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/main.css">

        <script src="js/jquery-latest.min.js"></script>    
        <script src="js/bootstrap.min.js"></script>

        <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
    </head>

    <body>
        <div class="container">

            <%@include file="base/header.jsp" %>

            <div class="container-main">                

                <div class="jumbotron" style="padding:20px 40px 20px 50px">
                    <h2>Data Browser 
                        <small>
                            <a class="white" href="download.jsp">
                                <span class="label label-default">v1</span>
                            </a>
                        </small>
                    </h2>

                    <div class="row">
                        <div class="col-md-10">
                            <form class="form-search" action="Search">
                                <div class="input-group">
                                    <input name="query" class="form-control input-lg tt-input"
                                           type="text" placeholder="Search for a ENSG gene or region or variant" >
                                    <div class="input-group-btn">
                                        <button class="btn btn-default input-lg tt-input" 
                                                type="submit">
                                            <i class="glyphicon glyphicon-search"></i></button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div style="top:6px" class="col-md-2">
                            <form action="Search" method="post" enctype="multipart/form-data">
                                <button type="button" class="btn btn-primary" 
                                        onclick="this.form.input_file.click()"
                                        data-toggle="tooltip" 
                                        title="Upload your variants file (maximum 200KB), single variant per line (chr-pos-ref-alt)">
                                    <i class="fa fa-upload"></i>
                                    Search variants
                                </button>

                                <input id="input_file" type="file" name="input_file" style="visibility:hidden;" 
                                       onchange="this.form.submit()">
                            </form>
                        </div>
                    </div>

                    <p class="text-muted" style="margin-left: 5px">
                        ENSG Gene: <a href="Search?query=ENSG00000186092">ENSG00000186092</a>,
                        Region: <a href="Search?query=1:7905143-7905156">1:7905143-7905156</a>, 
                        Variant: <a href="Search?query=1-7905043-C-T">1-7905043-C-T</a>
                    </p>
                </div>

                <%@include file="result.jsp" %>
            </div>

            <%@include file="base/footer.jsp" %>  

            <%@include file="base/counter.jsp" %> 
        </div>
    </body>
</html>
