package HK.com.hit.sky.types;

import java.io.*;


/**
 * This class replesents the code that can be encoded by multiple encoding
 * schemes.
 * <p>
 * Different from other simple codes, such as
 * {@link HK.com.hit.ngen.cbo.types.container.CntrStatusCodePolicy container status code},
 * application programs could not presume the encoding scheme of a
 * <code>MultiSchemeCode</code> in their application logic. The encoding scheme
 * of a <code>MultiSchemeCode</code> can be retrieved by the method
 * <code>getEncodingScheme()</code>. While the encoded code text under the
 * encoding scheme can be retrieved by the method <code>getText()</code>.
 * <p>
 * {@link HK.com.hit.ngen.cbo.types.container.CntrSizeCodePolicy container size code} and
 * {@link HK.com.hit.ngen.cbo.types.container.CntrTypeCodePolicy container type code}
 * are examples of <code>MultiSchemeCode</code>.
 *
 * @author      Infra-structure team
 * @version     
 */

public class MultiSchemeCode implements Serializable, Comparable {

    private String encodingScheme = null;
    private String text = null;

    /**
     * Constructor.
     * @todo    this constructor will be declared as <code>protected</code> later
     * @param       scheme      encoding scheme
     * @param       text        code text
     */
    protected MultiSchemeCode(String scheme, String text) {
        this.encodingScheme = scheme;
        this.text = text;
    }

    /**
     * Return the encoding scheme.
     */
    public String getEncodingScheme() {
        return encodingScheme;
    }

    /**
     * Return the code value.
     */
    public String getText() {
        return text;
    }

    /**
     * Return the hash value.
     */
    public int hashCode() {
        return (encodingScheme + text).hashCode();
    }

    /**
     * Return <code>true</code> when <code>obj</code> is equal to the
     * <code>MultiSchemeCode</code>.
     * <p>
     * <code>obj</code> is equal to the <code>MultiSchemeCode</code> when the
     * following conditions are true.
     * <pre>
     *      1. <code>obj</code> is not null.
     *      2. <code>obj</code> is an instance of the same class.
     *      3. The encoding schemes of both objects are the same.
     *      4. The code text of both objects are the same.
     * </pre>
     */
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null && obj.getClass() == this.getClass()) {
            MultiSchemeCode mCode = (MultiSchemeCode) obj;
            result = (this.encodingScheme.equals(mCode.encodingScheme) &&
                    this.text.equals(mCode.text));
        }
        return result;
    }

    /**
     * Compares the <code>MultiSchemeCode</code> with another <code>obj</code>
     * with order.
     * @param   obj     object to be compared.
     * @return  value <code>0</code> if the <code>obj</code> is equal to this class;
     *          a value less than <code>0</code> if the <code>obj</code> is greater than this class;
     *          a value greater than <code>0</code> if the <code>obj</code> is less than this class;
     * @throws  <code>ClassCastException</code> if the argument is not a <code>MultiSchemeCode</code>.
     */
    public int compareTo(Object obj) {
        MultiSchemeCode mCode = (MultiSchemeCode) obj;
        int result = this.encodingScheme.compareTo(mCode.encodingScheme);
        if (result == 0) {
            result = this.text.compareTo(mCode.text);
        }
        return result;
    }

    /**
     * Return the String representation.
     * <p>
     * The result is equal to
     * <pre>
     *      getText()
     * </pre>
     */
    public String toString() {
        return text;
    }

}
