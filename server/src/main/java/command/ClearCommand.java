package command;

import routes.Routes;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;

/**
 * Очистка коллекции
 * @version 1.1
 */
public class ClearCommand extends Commands{
    public ClearCommand(Routes routes, File file) {
        super(routes, file);
    }

    @Override
    public void execute() throws IOException {
        routes.removeAll(routes);
        PrintToClient.print("Список очищен");
        PrintToClient.getBuf().SendToClient();
    }

    @Override
    public String ru_description() {
        return "очистить коллекцию";
    }
}
