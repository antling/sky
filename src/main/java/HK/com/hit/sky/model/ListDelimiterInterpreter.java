package HK.com.hit.sky.model;

import java.util.*;

/**
 * This is a help class that help to encapsulate the interpretation of list delimiter.
 * In general, developer have to condense an array of codes into a single String
 * (most for storing to database) and vice versa. To avoid hardcode of the list
 * delimiter, this class provides the functions to manipulate the
 * conversion between the array of codes and the single string represenetation. <br>
 * To use this help class, call ListDelimiterInterpreter.getInstance(). <br>
 * for example, ListDelimiterInterpreter.getInstance().dissembleList(inputString))...
 * <br>
 * <pre>
 * NOTE:
 *     When using this class, please take precaution to avoid the collision of
 *     the delimiter with the content of the element.
 * </pre>
 *
 * @author      Infra-structure team
 * @version     
 */
public abstract class ListDelimiterInterpreter {

    private static String DELIMITER_CHAR = ",";

    private static String INFO_DELIMITER_CHAR = " ";
    
    private static String SEMICOLON_DELIMITER_CHAR = ";";

    /**
     * To provide the Default implementation of the ListDelimiterInterpreter.
     */
    public static class BasicListDelimiterInterpreter extends ListDelimiterInterpreter {
    }

    /**
     * To provide the ListDelimiterInterpreter instance. This allow us to plug-in
     * a specific sub-class of ListDelimiterInterpreter based on the Configuration
     * framework.
     */
    public static synchronized ListDelimiterInterpreter getInstance() {
        return new BasicListDelimiterInterpreter();
    }

    /**
     * To return the list delimiter symbol this class currently used. This method
     * should NOT be used in general. Programs should not interpret the
     * symbol directly.
     */
    public String getListDelimiterSymbol() {
        return DELIMITER_CHAR;
    }

    /**
     * To return the delimiter symbol of info list. This method
     * should NOT be used in general. Programs should not interpret the
     * symbol directly.
     */
    public String getInfoDelimiterSymbol() {
        return INFO_DELIMITER_CHAR;
    }
    
    public String getSemicolonDelimiterSymbol() {
    	return SEMICOLON_DELIMITER_CHAR;
    }

    /**
     * To assemble the input string array into a single String representation.
     * It will use the class's list delimiter character as separator. <br>
     * For example, input array String[] {"A", "B"} will return list="A,B"
     */
    public String assembleList(String[] array) {
        return this.assembleList(getListDelimiterSymbol(), array);
    }

    /**
     * To assemble the input string array into a single String representation.
     * It will use the input list delimiter character as separator. <br>
     * For example, input array String[] {"A", "B"} will return list="A,B"
     */
    public String assembleList(String delimiter, String[] array) {
        StringBuffer buf = new StringBuffer();

        if (array != null) {
            for (int i=0; i < array.length; i++) {
                buf.append(array[i]);
                if (i < (array.length-1)) {
                    buf.append(delimiter);
                }
            }
        }
        return buf.toString();
    }

    /**
     * To dissemble the input list into String array according to the list delimiter
     * as separator. <br>
     * For example, input list="A,B" will return String[] {"A", "B"}
     */
    public String[] dissembleList(String list) {
        return this.dissembleList(getListDelimiterSymbol(), list);
    }

    /**
     * To dissemble the input list into String array according to the input list
     * delimiter as separator. <br>
     * For example, input list="A,B" will return String[] {"A", "B"}
     */
    public String[] dissembleList(String delimiter, String list) {
        ArrayList array = new ArrayList();
        if (list != null) {
            for (int i=0; i < list.length();) {
                int j = list.indexOf(delimiter, i);
                array.add(list.substring(i, (j==-1) ? list.length() : j));
                if (j==-1) {
                    break;
                }
                i = j+(delimiter.length());
            }
        }
        return (String[]) array.toArray(new String[0]);
    }

}
