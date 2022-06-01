package condition;

public class NeedRoute extends Condition{
    public NeedRoute(Object object) {
        super(object);
    }

    @Override
    public boolean execute() {
        return true;
    }
}
