package HK.com.hit.sky.model;

import java.util.*;

/**
 * This is a helper class that help to encapsulate the interpretation of a null
 * numeric variable. Since Java initializes zero to all primitive numeric
 * datatype (int, float, double ...), this will cause difficulty to know a
 * numeric datum that have assigned a zero value or simply undetermined (Null).
 * To avoid hardcode, this class provides the functions to provide the value that
 * represent null and also provide tester methods to test the given variable is null.<br>
 * To use this helper class, call NullNumberInterpreter.getInstance(). <br>
 * for example, NullNumberInterpreter.getInstance().isNull(inputNumber))...
 * <br>
 *
 * @author      Infra-structure team
 * @version     
 */
public abstract class NullNumberInterpreter {

    private static double NULL_VAL = -1.0;

    /**
     * To provide the Default implementation of the NullNumberInterpreter.
     */
    public static class BasicNullNumberInterpreter extends NullNumberInterpreter {
    }

    /**
     * To provide the NullNumberInterpreter instance. This allow us to plug-in
     * a specific sub-class of NullNumberInterpreter based on the Configuration
     * framework.
     */
    public static synchronized NullNumberInterpreter getInstance() {
        return new BasicNullNumberInterpreter();
    }

    /**
     * To return the exact value for representing the null. Developer should
     * avoid interpreting this value directly.
     */
    public double getNullValue() {
        return this.NULL_VAL;
    }

    /**
     * Test the input number is null or not.
     */
    public boolean isNull(int number) {
        return (number == getNullValue());
    }

    /**
     * Test the input number is null or not.
     */
    public boolean isNull(double number) {
        return (number == getNullValue());
    }

    /**
     * Test the input number is null or not.
     */
    public boolean isNull(Number number) {
        return (number.doubleValue() == getNullValue());
    }

}
