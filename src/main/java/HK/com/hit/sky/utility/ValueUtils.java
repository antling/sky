package HK.com.hit.sky.utility;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import HK.com.hit.sky.model.ListDelimiterInterpreter;
import HK.com.hit.sky.model.NullNumberInterpreter;

/**
 * The Utility class is used to handle the value equation/comparation.
 *
 * @author Tom Lau
 * @date 2006-7-11
 */
public final class ValueUtils {
    private static String LETTER_DIGIT_REGEX = "^[a-z0-9A-Z]+$";
    private static String CAPTIAL_DIGIT_REGEX = "^[0-9A-Z]+$";
    private static String CAPTIAL_DIGIT_SPECIAL_REGEX = "^[0-9A-Z_.,\\-()/=+?!*;@#:%\\[\\]'`\\\\${}^|~\\n\\r\\t ]+$";
    private static String LETTER_DIGIT_SPECIAL_REGEX = "^[0-9a-z_.,\\-()/=+?!*;@#:%\\[\\]'`\\\\${}^|~\\n\\r\\t ]+$";
    private static String LETTER_DIGIT_CHINESE_REGEX = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
    private static String CAPTIAL_DIGIT_CHINESE_REGEX = "^[0-9A-Z\u4e00-\u9fa5]+$";
    private static String LETTER_DIGIT_CHINESE_SPACE_REGEX = "^[a-z0-9A-Z\u4e00-\u9fa5\\s]+$";
    public static final int CNTR_AUDIT_LOG_BEFORE_AFTER_VALUE_LENGTH = 200;
    public static final int NULL_INT = (int) NullNumberInterpreter.getInstance().getNullValue();
    public static final double NULL_DOUBLE = NullNumberInterpreter.getInstance().getNullValue();

    private ValueUtils() {
    }

    public static void main(String[] args) {
        String s1 = "1Aab0A";
        String s2 = "汉fa11f";
        String s3 = "1*%1";
        String s4 = "1     汉  FUX  INGDA";
        String s5 = "A B~`@#$%^* (?)_=|\\/";

        System.out.println("result1 = " + isCaptialDigitSpecial(s5));
    }

    public static String getAdjustedAuditLogValue(String value) {
        return getValueByLength(value, CNTR_AUDIT_LOG_BEFORE_AFTER_VALUE_LENGTH);
    }

    public static int getChineseLength(String s) {
        int len = 0;
        if (isEmpty(s)) {
            return len;
        }
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < s.length(); i++) {
            String temp = s.substring(i, i + 1);
            if (temp.matches(chinese)) {
                len += 3;
            } else {
                len += 1;
            }
        }
        return len;
    }

    public static boolean isSpecialCharacter(String s) {
        if (isEmpty(s)) {
            return false;
        } else {
            Pattern p = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
            Matcher m = p.matcher(s);
            return m.find();
        }
    }

    public static boolean isLetterDigit(String s) {
        if (isEmpty(s)) {
            return false;
        } else {
            return s.matches(LETTER_DIGIT_REGEX);
        }
    }

    public static boolean isCaptialDigit(String s) {
        if (isEmpty(s)) {
            return false;
        } else {
            return s.matches(CAPTIAL_DIGIT_REGEX);
        }
    }

    public static boolean isCaptialDigitSpecial(String s) {
        if (isEmpty(s)) {
            return false;
        } else {
            return s.matches(CAPTIAL_DIGIT_SPECIAL_REGEX);
        }
    }

    public static boolean isLetterDigitSpecial(String s) {
        if (isEmpty(s)) {
            return false;
        } else {
            return s.matches(LETTER_DIGIT_SPECIAL_REGEX);
        }
    }

    public static boolean isLetterDigitChinese(String s) {
        if (isEmpty(s)) {
            return false;
        } else {
            return s.matches(LETTER_DIGIT_CHINESE_REGEX);
        }
    }

    public static boolean isCaptialDigitChinese(String s) {
        if (isEmpty(s)) {
            return false;
        } else {
            return s.matches(CAPTIAL_DIGIT_CHINESE_REGEX);
        }
    }

    public static boolean isLetterDigitChineseSpace(String s) {
        if (isEmpty(s)) {
            return false;
        } else {
            return s.matches(LETTER_DIGIT_CHINESE_SPACE_REGEX);
        }
    }

    public static String getValueByLength(String value, int length) {
        if (isEmpty(value)) {
            return null;
        } else {
            if (value.length() > length) {
                return value.substring(0, length);
            } else {
                return value;
            }
        }
    }

    /**
     * Compare two objects, check whether they are equal or not.
     *
     * @param value
     * @param value2
     * @return
     */
    public static boolean equals(Object value, Object value2) {
        return (value == null && value2 == null) ||
                (value != null && value.equals(value2));
    }

    public static boolean equal(String value, String value2) {
        return (isEmpty(value) && isEmpty(value2)) ||
                (value != null && value.equals(value2));
    }

    public static boolean isEmpty(String value) {
        return (value == null || "".equals(value.trim()));
    }

    public static boolean isEmpty(Object value) {
        if (value instanceof String) {
            return isEmpty((String) value);
        } else {
            return (value == null);
        }
    }

    public static boolean isEmpty(Object[] arr) {
        return arr == null || arr.length == 0;
    }

    public static String getTrimmedString(String value) {
        return (value != null ? value.trim() : "");
    }

    /**
     * get the imdg codes.
     *
     * @param list the original container.DANGEROUS_CODE_LIST properties
     * @return
     */
    public static String[] getImdgCodes(String list) {
        String[] codes = ListDelimiterInterpreter.getInstance().dissembleList(
                ListDelimiterInterpreter.getInstance().getInfoDelimiterSymbol(), list);
        List imdgList = new ArrayList();
        for (int i = 0; (codes != null && i < codes.length); i++) {
            int commaPos = codes[i].indexOf(',');
            String imdgCode = codes[i].substring(commaPos + 1).trim();
            imdgList.add(imdgCode);
        }
        return (String[]) imdgList.toArray(new String[0]);
    }

    /**
     * parse the string value to be int one.
     *
     * @param vlaue
     * @return
     */
    public static int toInt(String value) {
        if (isEmpty(value)) {
            return NULL_INT;
        } else {
            try {
                Number number = NumberFormat.getInstance().parse(value);
                return number.intValue();
            } catch (ParseException e) {
                return NULL_INT;
            }
        }
    }

    public static int getVentilationValue(int ventilationValue, String ventilationUnit) {
        if (ventilationUnit == null || "".equals(ventilationUnit)) {
            return NULL_INT;
        }
        return ventilationValue;
    }

}
