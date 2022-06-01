package command;


import command.exception.IDException;
import routes.Route;
import routes.Routes;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Обновить элемент по id
 * @version 1.1
 */
public class UpdateCommand extends CommandsWithArgument<Long>{
    public UpdateCommand(Routes routes, File file) {
        super(routes, file);
        argument_name ="id";
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        boolean ok=routes.getSet().stream().anyMatch(x->x.getId()==argument);
        if (ok){
            routes.removeAll(routes.getSet().stream().filter(x->x.getId()==(argument)).collect(Collectors.toList()));
            Route route = new Route(PrintToClient.getBuf().ReadRoute(),argument);
        routes.add(route);
            PrintToClient.print("Элемент обновлен");}
        else{
            throw new IDException("Элемента с этим ID нет в списке");
        }
        PrintToClient.getBuf().SendToClient();

    }

    @Override
    public String ru_description() {
        return  "обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public void String_to_Arg(String s) {
        this.setArgument(Long.parseLong(s));
    }
}
