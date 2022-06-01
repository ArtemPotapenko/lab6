package condition;

public class END extends Condition{
    public END(Object object) {
        super(object);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
