package hexlet.code;

public class Difference implements Comparable<Difference> {
    private final String key;
    private final String operation;
    private final Object leftVal;
    private final Object rightVal;

    Difference(String key, String operation, Object value) {
        this(key, operation, value, value);
    }

    Difference(String key, String operation, Object value1, Object value2) {
        if (!operation.equals("-") && !operation.equals("+") && !operation.equals(" ") && !operation.equals("-+")) {
            throw new IllegalArgumentException("Unknown operation: " + operation + ".");
        }

        this.key = key;
        this.operation = operation;
        this.leftVal = value1;
        this.rightVal = value2;
    }

    public String getKey() {
        return key;
    }

    public String getOperation() {
        return operation;
    }

    public Object getLeftVal() {
        return leftVal;
    }

    public Object getRightVal() {
        return rightVal;
    }

    @Override
    public int compareTo(Difference d) {
        return key.compareTo(d.getKey());
    }
}
