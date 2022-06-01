package command;

import routes.Routes;

import java.io.File;
import java.io.IOException;

public class ClientExitCommand extends Commands{
    ExitCommand exitCommand;
    SaveCommand saveCommand;

    /**
     * Конструктор для задания команды.
     *
     * @param routes Коллекция элементов
     * @param file   Файл
     */
    public ClientExitCommand(Routes routes, File file) {
        super(routes, file);
        exitCommand=new ExitCommand(routes,file);
        saveCommand=new SaveCommand(routes,file);
    }

    @Override
    public void execute() throws IOException {
        saveCommand.execute();
        exitCommand.execute();

    }

    @Override
    public String ru_description() {
        return exitCommand.ru_description();
    }
}
