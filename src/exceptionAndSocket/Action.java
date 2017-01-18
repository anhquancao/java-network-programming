package exceptionAndSocket;

import java.io.Serializable;

/**
 * Created by caoquan on 1/17/17.
 */
public class Action implements Serializable {
    private ActionType type;
    private Object data;

    Action(ActionType type) {
        this.type = type;
    }

    public Action(ActionType type, Object data) {
        this.type = type;
        this.data = data;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
