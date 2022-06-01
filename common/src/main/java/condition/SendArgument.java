package condition;

public class SendArgument extends Condition{

    public SendArgument(Object object) {
        super(object);
    }

    @Override
    public boolean execute() {

        return true;
    }
}
