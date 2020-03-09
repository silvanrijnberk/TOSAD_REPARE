package WebServices;

import java.util.ArrayList;
import java.util.List;

public class checkMethods {
    private String message;

    public boolean isEmpty(List strings) {
        for (Object x : strings) {
            if (x == null) {
                return true;
            }
            String check = (String) x;
            if (check.isEmpty())
                return true;
        }
        return false;
    }

    public boolean isUnique(List strings) {
        List copy = new ArrayList();
        for (Object x : strings) {
            if (x == null)
                continue;
            if (copy.contains(x))
                return false;
            copy.add(x);
        }
        return true;
    }
    public boolean correctOperator(String operator) {
        switch (operator) {
            case "!=":
            case "=":
            case ">":
            case "<":
            case ">=":
            case "<=":
                    return true;
            default:
                return false;
        }
    }

    public boolean correctOperator(String operator, String compareValue) {
        switch (operator) {
            case "!=":
            case "=":
                if (isNumber(compareValue)) {
                    message = "cant use equal operator with numbers";
                    return false;
                }
            case ">":
            case "<":
            case ">=":
            case "<=":
                if (isNumber(compareValue))
                    return true;
                message = "cant use compare operator on a string";
            default:
                return false;
        }
    }

    public boolean isNumber(String s) {
        return isFloat(s) || isInt(s);
    }

    public boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException nfe) {//no float
        }
        return false;
    }

    public boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe) {//no float
        }
        return false;
    }

    public boolean minMax(String minval, String maxval) {
        if (isNumber(minval) && isNumber(maxval)) {
            float min = Float.parseFloat(minval);
            float max = Float.parseFloat(maxval);
            if (min < max) {
                return true;
            }
        }
        return false;
    }

    public String getMessage() {
        return message;
    }
}
