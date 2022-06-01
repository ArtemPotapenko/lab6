package command;


import routes.Route;
import routes.Routes;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;

/**
 * Удалить все большие елементы.
 * @version 1.1
 */
public class RemoveGreater extends Commands{
    public RemoveGreater(Routes routes, File file) {
        super(routes, file);
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        Route route = new Route(PrintToClient.getBuf().ReadRoute(),1);
        for (Route x: routes.getSet()){
            if (x.compareTo(route)>0){
                routes.remove(x);
            }
        }
        PrintToClient.print( "Элементы удалены.");
        PrintToClient.getBuf().SendToClient();

    }

    @Override
    public String ru_description() {
        return "удалить из коллекции все элементы, превышающие заданный";
    }
}
