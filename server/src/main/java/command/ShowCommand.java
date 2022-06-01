package command;

import routes.Routes;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;

/**
 * Вывод коллекции
 * @version 1.1
 */
public class ShowCommand extends Commands{
    public ShowCommand(Routes routes, File file) {
        super(routes, file);
    }

    @Override
    public void execute() throws IOException {
        routes.getSet().forEach(x->x.ShowPrint());
        if (routes.size()==0){
            PrintToClient.print("В списке нет элементов");
        }
        PrintToClient.getBuf().SendToClient();

    }

    @Override
    public String ru_description() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
