package object;

/**
 *
 * @author Nick
 */
public class Region {

    private String chr;
    private int start;
    private int end;

    public Region(String _chr, int _start, int _end) {
        chr = _chr;
        start = _start;
        end = _end;
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
