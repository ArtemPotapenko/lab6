package condition;

public class Exit extends Condition{
    public Exit(Object object) {
        super(object);
    }

    @Override
    public boolean execute() {
        System.exit(0);
        return false;
    }
}
