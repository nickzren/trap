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
                    Trap score stands for Transcript-inferred Pathogenicity score and is constructed to evaluate a single nucleotide variant’s ability to cause disease by damaging the final transcript. Possible effects on the final transcript may include changes in splicing machinery recognition of the pre-mRNA sequence, an introduction of a new cryptic splice site, a regulatory change that will affect exon inclusion or an intron retention that could result in a protein loss/gain of function or the mRNA’s nonsense-mediated decay. TraP does not predict the structure of the novel transcript isoforms, but evaluates whether a change will occur to the original transcripts of a gene in question.
                </p>

                <p class="lead">
                    To assure that TraP captures indirect transcript formation effects, the model was trained using 75 synonymous variants that cause rare disease but do not change the amino acid sequence; as negative controls we used 402 de novo synonymous variants from presumably healthy individuals. To ensure that TraP captures signals unrelated to amino acid substitutions, TraP was also evaluated using only datasets of either intronic or synonymous annotations. Finally, TraP is pre-computed for all human protein coding genes and scored for each variant based on the gene it resides in. This means, for example, that a specific variant in a position that resides in two overlapping genes will have two different scores, depending on each gene features.
                </p>

                <br/>

                <p class="lead">
                    What is a TraP score that can be considered damaging?
                </p>

                <p class="lead">
                    The TraP score is a prediction between 0-1 of the chance for pathogenicity, the higher the score the higher the damage the variant is predicted to have. To facilitate usability among the general user community we describe three recommended thresholds based on the allele frequency tests (as described in the TraP paper, in the Results relating to figure 2D). We consider a TraP score below 0.459 to, in general, be enriched for benign variants. We consider scores ≥ 0.459 and < 0.93 as the intermediate pathogenic range, akin to possibly damaging classifications. These ranges are enriched for cryptic slice sites, effects of cis-acting regulatory sequences and weak to intermediate splice region changes. TraP scores ≥ 0.93 are most likely to damage the final transcript and are considered as probably damaging. These variants are enriched for strong splice region changes and strong cryptic splice site creations.
                </p>

                <p class="lead">
                    The TraP manuscript describing the methods, calculated features and validations of the scores was published by Nature Communications:
                </p>  

                <p class="lead">
                    Gelfman, S., Q. Wang, K.M McSweeney, Z. Ren, F. La Carpia, M. Halvorsen, K. Schoch, F. Ratzon, E.L. Heinzen, M.J. Boland, S. Petrovski and D. B. Goldstein. 2017. Annotating Pathogenic Non-Coding Variants in Genic Regions. Accepted June 5th, 2017. 
                </p>

                <p class="lead">
                    Nat Comm. 2017 June 5. doi: <a href="https://www.nature.com/articles/s41467-017-00141-2">10.1038/s41467-017-00141-2</a>
                </p>

                <p class="lead">
                    During the review process, TraP was further developed to catch additional edge 
                    cases and the new TraP V2 model achieves a better accuracy of 92.24% 
                    (compared to 91.8% of the V1 model described in the paper). Therefore, 
                    TraP V2 scores are now the default in the webserver. To view the previous 
                    TraP V1 scores based on the published model, please <a href="Search?version=v1">click here</a>.
                </p>

                <p class="lead">For other TraP related questions please contact 
                    <a href="mailto: sahar.gelfman@columbia.edu" target="_blank">
                        sahar.gelfman@columbia.edu
                    </a>
                </p>
            </div>
        </div>

        <%@include file="base/footer.jsp" %>    
    </body>
</html>