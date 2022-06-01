package command;


import routes.Route;
import routes.Routes;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Добавить элемент если он больше всех элементов массива.
 * Элементы сравниваются по имени.
 * @version 1.1
 */
public class AddIfMaxCommand extends Commands{

    public AddIfMaxCommand(Routes routes, File file) {
        super(routes, file);
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        long my_id=1;
        Set<Long> S_id = new HashSet<>();
        routes.getSet().stream().forEach(x->S_id.add(x.getId()));
        while (S_id.contains(my_id)){my_id++;}
        Route route = new Route(PrintToClient.getBuf().ReadRoute(),my_id);

        if (routes.size()==0 || route.compareTo(Collections.max(routes))>0){
            routes.add(route);
            PrintToClient.print( "Элемент добавлен");

        }else {
            PrintToClient.print("Элемент не добавлен");
        }
        PrintToClient.getBuf().SendToClient();
        }

    @Override
    public String ru_description() {
        return "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

}
