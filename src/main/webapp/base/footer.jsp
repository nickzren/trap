<footer>
    <nav class="navbar-fixed-bottom">        
        <div class="footer"> 
            TraP is protected by copyright. © <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%> 
            The Trustees of Columbia University in the City of New York. All Rights Reserved.
            
            <p>TraP is made available for internal, non-commercial, academic and research purposes. 
                Using TraP for any commercial purpose is strictly prohibited without a license.</p>
        </div>
    </nav>

    <script>
        $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
</footer>