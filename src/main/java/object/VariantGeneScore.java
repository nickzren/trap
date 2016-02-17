package object;

/**
 *
 * @author Nick
 */
public class VariantGeneScore {

    private String chr;
    private int pos;
    private String ref;
    private String alt;
    private String ensgGene;
    private float score;

    public static final String title
            = "Chr,"
            + "Pos,"
            + "Ref,"
            + "Alt,"
            + "ENSG Gene,"
            + "Score";

    public VariantGeneScore(String chr, int pos, String ref, String alt,
            String ensgGene, float score) {
        this.chr = chr;
        this.pos = pos;
        this.ref = ref;
        this.alt = alt;
        this.ensgGene = ensgGene;
        this.score = score;
    }

    public String getChr() {
        return chr;
    }

    public int getPos() {
        return pos;

    }

    public String getRef() {
        return ref;
    }

    public String getAlt() {
        return alt;
    }

    public String getEnsgGene() {
        return ensgGene;
    }

    public float getScore() {
        return score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(chr).append(",");
        sb.append(pos).append(",");
        sb.append(ref).append(",");
        sb.append(alt).append(",");
        sb.append(ensgGene).append(",");
        sb.append(score);

        return sb.toString();
    }
}
