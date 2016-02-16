package object;

import util.DBManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nick
 */
public class Variant {

    private String chr;
    private int position;
    private String ref;
    private String allele;

    public static final String title
            = "Chr,"
            + "Position,"
            + "Ref Allele,"
            + "Alt Allele";

    public Variant(ResultSet rset) throws Exception {
        chr = rset.getString("chr");
        position = rset.getInt("pos");
        ref = rset.getString("ref");
        allele = rset.getString("allele");

    }

    public String getChr() {
        return chr;
    }

    public int getPosition() {
        return position;

    }

    public String getRef() {
        return ref;
    }

    public String getAllele() {
        return allele;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(chr).append(",");
        sb.append(position).append(",");
        sb.append(ref).append(",");
        sb.append(allele).append("\n");

        return sb.toString();
    }
}
