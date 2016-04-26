<footer>
    <nav class="navbar-fixed-bottom">        
        <div class="footer"> 
            Copyright © <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%>
            <a style="color: #aaa; " href="http://igm.columbia.edu" target="_blank">
                Institute for Genomic Medicine</a>.
            All rights reserved.
        </div>
    </nav>

    <script>
        $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
</footer>