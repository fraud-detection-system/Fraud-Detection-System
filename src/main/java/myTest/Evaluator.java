package myTest;

import java.io.Serializable;
import java.util.Map;

public class Evaluator implements Serializable {
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Map<String, Object> getArgs() {
        return args;
    }
    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }
    private String name;
    private Map<String, Object> args;
}