package command;

import command.exception.IDException;
import routes.Route;
import routes.Routes;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;

/**
 * Удалить по id
 * @version 1.1
 */
public class RemoveCommand extends CommandsWithArgument<Long> {

    public RemoveCommand(Routes routes, File file) {
        super(routes, file);
        argument_name = "id";

    }

    @Override
    public void execute() throws IOException {
        boolean ok = false;
        for (Route x : routes.getSet()) {
            if (argument.equals(x.getId())) {
                ok = true;
                routes.remove(x);
                PrintToClient.print( "Элемент удален");
                break;
            }
        }
        if (!ok) {
            throw new IDException("Элемента с этим ID нет в списке.");
        }
        PrintToClient.getBuf().SendToClient();



    }

    @Override
    public String ru_description() {
        return "удалить элемент из коллекции по его id";

    }

    @Override
    public void String_to_Arg(String s) {
        this.setArgument(Long.parseLong(s));
    }
}
