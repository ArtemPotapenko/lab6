package condition;

/**
 * Состояние исполнения команд из файла
 */
public class ExecuteCondition extends Condition{
    public ExecuteCondition(Object object) {
        super(object);
    }

    @Override
    public boolean execute() {

        return true;
    }
}
