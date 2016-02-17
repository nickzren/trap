package object;

/**
 *
 * @author nick
 */
public class EnsgGene {

    private String name;
    private String chr;
    private int start;
    private int end;

    public EnsgGene(String name, String chr, int start, int end) {
        this.name = name;
        this.chr = chr;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public String getChr() {
        return chr;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
