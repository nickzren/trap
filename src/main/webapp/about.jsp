<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="base/head.jsp" %>

    <body>
        <div class="container">

            <%@include file="base/header.jsp" %>

            <div class="container-main">
                <h3 class="page-header">About</h3>

                <p>
                    The Transcript-inferred Pathogenicity score, or TraP-score, is constructed to evaluate a single nucleotide variant’s ability to cause disease by damaging the final transcript. Possible effects on the final transcript may include changes in splicing machinery recognition of the pre-mRNA sequence, an introduction of a new cryptic splice site, a regulatory change that will affect exon inclusion or an intron retention that could result in a protein loss/gain of function or the mRNA’s nonsense-mediated decay. TraP does not predict the structure of the novel transcript isoforms, but evaluates whether a change will occur to the original transcripts of a gene in question.
                </p>

                <p>
                    To assure that TraP captures indirect transcript formation effects, the model was trained using 75 synonymous variants that cause rare disease but do not change the amino acid sequence; as negative controls we used 402 de novo synonymous variants from presumably healthy individuals. To ensure that TraP captures signals unrelated to amino acid substitutions, TraP was also evaluated using only datasets of either intronic or synonymous annotations. 
                </p>

                <p>
                    TraP is pre-computed for all possible substitutions of 20,686 human protein coding genes, covering ~1.3 billion genomic positions based on the hg19 genome assembly. Since TraP considers features of the gene itself when calculating the score,  variants that reside in two overlapping genes will have two different scores, depending on each gene’s unique features.
                </p>
                
                <br/>

                <h4>TraP News, 2/2022:</h4>

                <p>
                    TraP version 3.0 has been lifted-over to hg38. The new TraP-score uses the same model as TraP v3, yet points to hg38 positions to make it easier for researchers that work on hg38. In the process of lifting over, approximately 99.95% of the TraP v3 scores are successfully converted to hg38 positions, yet in some rare cases the lift over is not successful. In the cases where a variant position is within an hg38 gene coordinates, but does not have a TraP score available on the webserver, we recommend examining the TraP-score for the variant’s hg19 position using TraP v3. 
                </p>

                <br/>

                <h4>TraP News, 10/2019:</h4>

                <p>
                    TraP has been upgraded to version 3.0. The new TraP model has an improved algorithm that catches additional creations and disruptions of cryptic splice sites. TraP will now catch all possible cryptic changes made by a variant and decide which has the stronger effect while giving precedence to creations of cryptic splice sites where none existed, rather than disruptions of existing cryptic splice sites (that are not part of the gene’s known junctions). 
                </p>

                <p>
                    TraP v3.0 is highly correlated with previous TraP versions (Spearman correlation coefficient = 0.98) and improves sensitivity by catching pathogenic variants that were previously missed.
                </p>

                <br/>

                <h4>What TraP-score is considered damaging?</h4>

                <p>
                    The TraP score is a prediction between 0-1 for the chance of a variant being pathogenic, the higher the score the higher the damage the variant is predicted to have. To facilitate usability among the general user community we previously described recommended thresholds (0.459 and 0.93) for possibly and probably damaging classifications, respectively.
                </p>  

                <p>
                    However, the score statistics differ between intronic and coding variants, making the above thresholds too stringent for applying on intronic variants. Moreover, after two years of extensive utilization of TraP for diagnostics and feedback from many of the thousands of TraP webserver users, we have decided that a <strong>score-percentile</strong> would make the TraP threshold decision more straightforward. Regarding the percentile thresholds, we consider a score above the top 90th percentile as possibly damaging and above the 97.5th percentile as probably damaging. The score statistics for coding and non-coding variants are presented in the tables below to allow each institute, lab or researcher to consider the score percentile thresholds that fit their specific objectives. For example: at Columbia University Medical Center, we consider a more lenient TraP score above the 90th percentile when performing gene-burden analyses, but use a score above the 97.5th percentile for diagnostic purposes. 
                </p>
                
                <p>
                    Coding score percentiles based on 1.5M synonymous variants from the ExAC database:
                </p>

                <table class="table">
                    <tr>
                        <th>Percentile</th>
                        <th>10%</th>
                        <th>25%</th>
                        <th>50%</th>
                        <th>75%</th>
                        <th class="warning">90%</th>
                        <th>92.5%</th>
                        <th>95%</th>
                        <th class="danger">97.5%</th>
                        <th>99%</th>
                        <th>99.9%</th>
                    </tr>
                    <tr>
                        <td>TraPv3 score</td>
                        <td>0.004</td>
                        <td>0.015</td>
                        <td>0.049</td>
                        <td>0.116</td>
                        <td class="warning">0.221</td>
                        <td>0.256</td>
                        <td>0.307</td>
                        <td class="danger">0.416</td>
                        <td>0.676</td>
                        <td>0.981</td>
                    </tr>
                </table>
                
                <p>
                    Non-coding score percentiles based on 1.5M intronic variants from 776 whole genomes:
                </p>

                <table class="table">
                    <tr>
                        <th>Percentile</th>
                        <th>10%</th>
                        <th>25%</th>
                        <th>50%</th>
                        <th>75%</th>
                        <th class="warning">90%</th>
                        <th>92.5%</th>
                        <th>95%</th>
                        <th class="danger">97.5%</th>
                        <th>99%</th>
                        <th>99.9%</th>
                    </tr>
                    <tr>
                        <td>TraPv3 score</td>
                        <td>0.005</td>
                        <td>0.016</td>
                        <td>0.044</td>
                        <td>0.096</td>
                        <td class="warning">0.174</td>
                        <td>0.199</td>
                        <td>0.232</td>
                        <td class="danger">0.289</td>
                        <td>0.383</td>
                        <td>0.696</td>
                    </tr>
                </table>
                
                <br/>
                
                <p>
                    Methods, calculated features and validations of the scores can be found in the original TraP manuscript:
                </p>
                
                <p>
                    Gelfman, S., Q. Wang, K.M McSweeney, Z. Ren, F. La Carpia, M. Halvorsen, K. Schoch, F. Ratzon, E.L. Heinzen, M.J. Boland, S. Petrovski and D. B. Goldstein. 2017. Annotating Pathogenic Non-Coding Variants in Genic Regions. Accepted June 5th, 2017. 
                </p>
                
                <p>
                    Nat Comm. 2017 June 5. doi: <a href="https://www.nature.com/articles/s41467-017-00141-2" target="_blank">10.1038/s41467-017-00141-2</a>
                </p>

                <p>For other TraP related questions please contact 
                    <a href="mailto: sahar.gelfman@columbia.edu" target="_blank">
                        sahar.gelfman@columbia.edu
                    </a> or 
                    <a href="mailto: sahargel@gmail.com" target="_blank">
                        sahargel@gmail.com
                    </a>
                </p>
            </div>
        </div>

        <%@include file="base/footer.jsp" %>    
    </body>
</html>