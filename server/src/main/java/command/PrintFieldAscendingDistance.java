package command;

import routes.Route;
import routes.Routes;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;

/**
 * Вывод отсортированный полей дистанции.
 * @version 1.1
 */
public class PrintFieldAscendingDistance extends Commands {

    public PrintFieldAscendingDistance(Routes routes, File file) {
        super(routes, file);
    }

    @Override
    public void execute() throws IOException {
        PrintToClient.print("");
        routes.getSet().stream().sorted(Comparator.comparing(Route::getDistance)).forEachOrdered(x-> PrintToClient.print(x.getDistance().toString()+" "));
        if(routes.size()==0){PrintToClient.print("В списке нет элементов");}
        PrintToClient.getBuf().SendToClient();

    }

    @Override
    public String ru_description() {
        return  "вывести значения поля distance всех элементов в порядке возрастания";
    }
}
