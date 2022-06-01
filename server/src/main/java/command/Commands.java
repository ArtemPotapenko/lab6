package command;


import java.io.File;
/**
 * Адстрактный класс для команд
 * @version 1.1
 */
import routes.Routes;

public abstract class Commands implements Command{
    /**
     * Массив элементов
     */
    protected Routes routes;
    /**
     * Файл для записи
     */
    protected File file;

    /**
     * Конструктор для задания команды.
     * @param routes Коллекция элементов
     * @param file Файл
     */
    public Commands(Routes routes, File file){
        this.routes=routes;
        this.file=file;
    }

    /**
     * Имя команды
     */
    protected String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean HaveArgument(){
        return false;
    }

    @Override
    public boolean NeedCommands() {
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
