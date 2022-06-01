package command;

import routes.Routes;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;

/**
 * Вывод максимального элемента по полю локации въезда
 * Элементы сравниваются по хэшкоду
 * @version 1.1
 */
public class MaxByToCommand extends Commands{
    public MaxByToCommand(Routes routes, File file) {
        super(routes, file);
    }

    @Override
    public void execute() throws IOException {
        if (routes.size()!=0){
        PrintToClient.print(routes.getSet().stream().max((x, y)->x.getTo().compareTo(y.getTo())).toString());}
        else {
            PrintToClient.print("Список не содержит элементов");
        }
        PrintToClient.getBuf().SendToClient();

    }

    @Override
    public String ru_description() {
        return "вывести любой объект из коллекции, значение поля to которого является максимальным";
    }
}
