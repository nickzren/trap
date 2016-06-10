<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="base/head.jsp" %>

    <body>
        <div class="container">

            <%@include file="base/header.jsp" %>

            <div class="container-main">
                <h3 class="page-header">About</h3>

                <p class="lead">
                    Trap score stands for Transcript-inferred Pathogenicity score and is constructed to evaluate a single nucleotide variant’s 
                    ability to cause disease by damaging the final transcript. Possible effects on the final transcript may include changes in 
                    splicing machinery recognition of the pre-mRNA sequence, an introduction of a new cryptic splice site, a regulatory change 
                    that will affect exon inclusion or an intron retention that could result in a protein loss/gain of function or the mRNA’s 
                    nonsense-mediated decay. TraP does not predict the structure of the novel transcript isoforms, but evaluates whether a 
                    change will occur to the original transcripts of a gene in question.
                </p>
                
                <p class="lead">
                    To assure that TraP captures indirect transcript formation effects, the model was trained using 75 synonymous variants 
                    that cause rare disease but do not change the amino acid sequence; as negative controls we used 402 de novo synonymous 
                    variants from presumably healthy individuals. To ensure that TraP captures signals unrelated to amino acid substitutions, 
                    TraP was also evaluated using only datasets of either intronic or synonymous annotations. Finally, TraP is pre-computed 
                    for all human protein coding genes and scored for each variant based on the gene it resides in. This means, for example, 
                    that a specific variant in a position that resides in two overlapping genes will have two different scores, depending on each gene features.
                </p>
            </div>
        </div>

        <%@include file="base/footer.jsp" %>    
    </body>
</html>