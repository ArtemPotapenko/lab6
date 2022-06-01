package command;

import routes.Routes;
import run.RunCommand;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Вывод в обратном порядке
 * @version 1.1
 */
public class PrintDescendingCommand extends Commands{
    public PrintDescendingCommand(Routes routes, File file) {
        super(routes, file);
    }

    @Override
    public void execute() throws IOException {
        routes.getSet().stream().sorted(Comparator.reverseOrder()).forEachOrdered(x->x.ShowPrint());
        if (routes.size()==0){
            PrintToClient.print("В списке нет элементов");
        }
        PrintToClient.getBuf().SendToClient();

    }

    @Override
    public String ru_description() {
        return "вывести элементы коллекции в порядке убывания";
    }
}
