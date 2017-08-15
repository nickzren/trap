<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="base/head.jsp" %>

    <body>
        <div class="container">

            <%@include file="base/header.jsp" %>

            <div class="container-main">
                <h3 class="page-header">Terms</h3>

                <h4><i class="fa fa-gavel"></i> Terms of use</h4>
                <p>
                    The TraP tool and the TraP scores generated and presented by the TraP tool are protected by copyright.  © <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%> The Trustees of Columbia University in the City of New York.  All Rights Reserved.  Any reproduction, distribution, display, or creation of derivative works is prohibited without the express written permission of the copyright owner.
                </p>

                <br/>

                <h4><i class="fa fa-star"></i> Citation</h4>                
                <p>TraP, New York City, New York (URL: http://trap-score.org) [date (month, year) accessed].</p>
                <p>                    
                    Gelfman, S., Wang, Q., McSweeney, K.M., Ren, Z., La Carpia, F., Halvorsen, M., Schoch, K., Ratzon, F., Heinzen,
                    E.L., Boland, M.J., Petrovski, S. & D. B. Goldstein. Annotating pathogenic non-coding variants in genic regions. 
                    <em>Nat Commun 8, 236, <a href="https://www.nature.com/articles/s41467-017-00141-2" 
                                   target="_blank">doi:10.1038/s41467-017-00141-2 (2017)</a></em>.
                </p>
            </div>

        </div>

        <%@include file="base/footer.jsp" %>
    </body>
</html>