<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Variant Damaging Score Data Browser (VDSdb)</title>

        <meta name="description" content="The Variant Damaging Score Data Browser is...">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="canonical" href="http://vdsdb.org/">

        <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/main.css">
    </head>

    <body>
        <div class="container">

            <%@include file="base/header.jsp" %>

            <div class="container-main">
                <div class="jumbotron" style="padding:20px 40px 20px 50px">
                    <h1>VDSdb</h1>

                    <p>
                    <mark>The Variant Damaging Score Data Browser is ...</mark>
                    </p>
                    
                    <p>
                        <a class="btn btn-primary btn-lg" href="browser.jsp">
                            <i class="fa fa-bar-chart">
                                Browse data now!
                            </i>
                        </a>
                    </p>
                </div>

            </div>
        </div>

        <%@include file="base/footer.jsp" %>  

        <%@include file="base/counter.jsp" %> 
    </body>
</html>