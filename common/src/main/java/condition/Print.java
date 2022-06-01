package condition;

import condition.exception.InvalidTypeException;

/**
 * Состояние вывода строки
 */
public class Print extends Condition{
    public Print(Object object) {
        super(object);
        if (!(object instanceof String)){
            throw new InvalidTypeException("Нет тот тип объекта");
        }
    }


    @Override
    public boolean execute() {
        System.out.println((String) getObject());
        return true;
    }
}
