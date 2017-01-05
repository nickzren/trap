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
                    The TraP score is a prediction between 0-1 of the chance for pathogenicity, the higher the score the higher the damage the variant is predicted to have. The figure below represents TraP and the scores for the training set (panel C), showing the clear separation between cases (>0.459) and benign variants (99% < 0.2). This sets a “minimum pathogenic score” of 0.459 based on the scores of known pathogenic variants. This distinction is also very strong when examining several test sets, such as Clinvar pathogenic and benign variants.
                </p>

                <div>
                    <img src="img/figure1.png">
                    <br/>
                    Figure 1. TraP model construction and Evaluation. A) TraP construction workflow and main features calculated for TraP: 1) Information acquisition from all genes and transcripts that harbor by the variant, 2) changes to splice site motif that affect it’s binding affinity to the splicing machinery, 3) creations of new splice junctions that might interact with the splicing machinery, 4) creations and disruptions of cis-acting binding sites to Splicing Regulatory Proteins (SRP), 5) interactions between features, such as a stronger effect of a new splice site on an exon with a weak original splice site (red representing a new splice site). Model is trained using synonymous variants that are either known pathogenic variants (blue box, left) or DNMs from healthy individuals (red box, right). B) A receiver-operating characteristic curve showing the results of ten rounds of 10-fold cross-validations with an average AUC of 0.86. C) Model predictions of the training set show a clear separation of pathogenic variants (blue) vs. control DNMs (red). TraP (Y-axis) exhibits a minimum threshold for pathogenic variants of 0.459, below which reside all control DNMs. GERP++ score (X-axis) considers 49.5% of benign variants as conserved. 
                </div>

                <br/>
                <br/>
                
                <p class="lead">
                    When choosing a TraP threshold, one should consider that TraP has a very high specificity of 97-99% for both intronic and synonymous variants, and scores benign variants between 0.07-0.10.
                </p>
                
                <p class="lead">
                    TraP is still under the review process. The full paper describing TraP will be uploaded once it is published. For deeper explanation and questions about how TraP was validated and evaluated, please contact <a href="mailto: sahar.gelfman@columbia.edu" target="_blank">Sahar Gelfman</a>.
                </p>  
            </div>
        </div>

        <%@include file="base/footer.jsp" %>    
    </body>
</html>