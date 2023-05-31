package HK.com.hit.sky.utility;

import java.io.*;
import HK.com.hit.sky.types.*;

public class ShipmentDangerousDetailInDB implements Serializable, Comparable {

    private int entryId;
    private String entryType;
    private MultiSchemeCode dgCode;
    private String dgContent;
    private int dgWeight;

    public ShipmentDangerousDetailInDB(int entryId, String entryType,
            MultiSchemeCode dgCode, String dgContent, int dgWeight) {
        this.entryId = entryId;
        this.entryType = entryType;
        this.dgCode = dgCode;
        this.dgContent = dgContent;
        this.dgWeight = dgWeight;
    }

    public int getEntryId() {
        return entryId;
    }

    public String getEntryType() {
        return entryType;
    }

    public MultiSchemeCode getDgCode() {
        return dgCode;
    }

    public String getDgContent() {
        return dgContent;
    }

    public int getDgWeight() {
        return dgWeight;
    }


    public int hashCode() {
        StringBuffer buf = new StringBuffer();
            buf.append(entryId);
            buf.append(entryType);
            buf.append(dgCode.toString());
            buf.append(dgContent);
            buf.append(dgWeight);
        return (buf.toString().hashCode());
    }

    /**
     * Return true if <code>obj</code> is identical to the class.
     * <p>
     * <code>obj</code> is identical when the following conditions are true.
     * <pre>
     *  1.  obj is not null.
     *  2.  obj is an instance of the same class.
     *  3.  All attributes of the obj are identical to that of the class.
     * </pre>
     * @param   obj     the object
     */
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null && obj.getClass() == this.getClass()) {
            ShipmentDangerousDetailInDB db = (ShipmentDangerousDetailInDB) obj;
            result = (this.compareTo(db) == 0);
        }
        return result;
    }

    /**
     * Compare <code>obj</code> with the class in order.
     */
    public int compareTo(Object obj) {
        int result = 0;
        if (obj instanceof ShipmentDangerousDetailInDB) {
            ShipmentDangerousDetailInDB db = (ShipmentDangerousDetailInDB) obj;
            if (result == 0) {
                result = (entryId == db.getEntryId()?0:
                        (entryId > db.getEntryId()?1:-1));
            }
            if (result == 0)
                result = entryType.compareTo(db.getEntryType());
            if (result == 0)
                result = dgCode.compareTo(db.getDgCode());
            if (result == 0)
                result = dgContent.compareTo(db.getDgContent());
            if (result == 0)
                result = (dgWeight == db.getDgWeight()?0:
                        (dgWeight > db.getDgWeight()?1:-1));
        } else {//pass-in obj is actual yard location.
            result = -1;
        }
        return result;
    }

    /**
     * Return the String representation.
     */
    public String toString() {
        StringBuffer buf = new StringBuffer(super.toString()).append('-').
                append(entryId).append('-').
                append(entryType).append('-').
                append(dgCode.toString()).append('-').
                append(dgContent).append('-').
                append(dgWeight);
        return buf.toString();
    }
}
