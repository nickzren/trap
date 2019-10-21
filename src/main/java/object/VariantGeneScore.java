package object;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author Nick
 */
public class VariantGeneScore {

    private String variantId;
    private String chr;
    private int pos;
    private String ref;
    private String alt;
    private String ensgGene;
    private String hgncGene;
    private float score;

    public static final String title
            = "Variant ID,"
            + "Chr,"
            + "Pos,"
            + "Ref,"
            + "Alt,"
            + "ENSG Gene,"
            + "HGNC Gene,"
            + "Score";

    public VariantGeneScore(String chr, int pos, String ref, String alt,
            String ensgGene, String hgncGene, float score) {
        variantId = chr + "-" + pos + "-" + ref + "-" + alt;
        this.chr = chr;
        this.pos = pos;
        this.ref = ref;
        this.alt = alt;
        this.ensgGene = ensgGene;
        this.hgncGene = hgncGene;
        this.score = score;
    }

    public String getVariantId() {
        return variantId;
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

    public String getHgncGene() {
        return hgncGene;
    }

    public float getScore() {
        return score;
    }

    /**
     * helper function to write <tag>value</tag>
     */
    private static void writeValueAsXml(final XMLStreamWriter w, final String tag, final Object value) throws XMLStreamException {
        if (value == null) {
            return;
        }
        final String s = String.valueOf(value);
        if (s.trim().isEmpty()) {
            return;
        }
        w.writeStartElement(tag);
        w.writeCharacters(s);
        w.writeEndElement();
    }

    /**
     * save this variant as XML
     */
    public void writeAsXml(final XMLStreamWriter w) throws XMLStreamException {
        w.writeStartElement("VariantGeneScore");
        w.writeAttribute("id", this.variantId);
        writeValueAsXml(w, "Chr", this.chr);
        writeValueAsXml(w, "Pos", this.pos);
        writeValueAsXml(w, "Ref", this.ref);
        writeValueAsXml(w, "Alt", this.alt);
        writeValueAsXml(w, "ENSG", this.ensgGene);
        writeValueAsXml(w, "HGNC", this.hgncGene);
        writeValueAsXml(w, "score", this.score);
        w.writeEndElement();//close 'variant'
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(variantId).append(",");
        sb.append(chr).append(",");
        sb.append(pos).append(",");
        sb.append(ref).append(",");
        sb.append(alt).append(",");
        sb.append(ensgGene).append(",");
        sb.append(hgncGene).append(",");
        sb.append(score);

        return sb.toString();
    }
}
