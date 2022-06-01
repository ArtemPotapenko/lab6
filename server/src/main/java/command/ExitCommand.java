package command;

import routes.Routes;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;

/**
 * Закрыть программу
 * @version 1.1
 */
public class ExitCommand extends Commands{
    public ExitCommand(Routes routes, File file) {
        super(routes, file);
    }

    @Override
    public void execute() throws IOException {
        PrintToClient.exit();
        PrintToClient.getBuf().SendToClient();

    }

    @Override
    public String ru_description() {
        return "завершить программу (без сохранения в файл)";
    }
}
