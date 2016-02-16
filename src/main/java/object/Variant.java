package object;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nick
 */
public class Variant {

    private String chr;
    private int pos;
    private String ref;
    private String alt;
    HashMap<String, Float> scoreMap = new HashMap<String, Float>(); // per gene per score

    public static final String title
            = "Chr,"
            + "Pos,"
            + "Ref,"
            + "Alt,"
            + "ENSG Gene,"
            + "Score";

    public Variant(ResultSet rset) throws Exception {
        while (rset.next()) {
            chr = rset.getString("chr");
            pos = rset.getInt("pos");
            ref = rset.getString("ref");
            alt = rset.getString("alt");
            
            scoreMap.put(rset.getString("ensg_gene"), rset.getFloat("score"));
        }
    }

    public String getChr() {
        return chr;
    }

    public int getPosition() {
        return pos;

    }

    public String getRef() {
        return ref;
    }

    public String getAllele() {
        return alt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(chr).append(",");
        sb.append(pos).append(",");
        sb.append(ref).append(",");
        sb.append(alt);

        for (Map.Entry<String, Float> entry : scoreMap.entrySet()) {
            sb.append(",").append(entry.getKey());
            sb.append(",").append(entry.getValue());
        }

        return sb.toString();
    }
}
