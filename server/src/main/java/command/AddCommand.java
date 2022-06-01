package command;


import routes.*;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Доьавить элемент
 * @version 1.1
 */
public class AddCommand extends Commands{


    public AddCommand(Routes routes, File file) {
        super(routes, file);
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        long my_id=1;
        Set<Long> S_id = new HashSet<>();
        routes.getSet().stream().forEach(x->S_id.add(x.getId()));
        while (S_id.contains(my_id)){my_id++;}
        Route route = new Route(PrintToClient.getBuf().ReadRoute(),my_id);
        routes.add(route);

        PrintToClient.print("Элемент добавлен");
        PrintToClient.getBuf().SendToClient();


    }

    @Override
    public String ru_description() {
        return "добавить новый элемент в коллекцию";
    }

    @Override
    public boolean HaveArgument() {
        return false;
    }


}
