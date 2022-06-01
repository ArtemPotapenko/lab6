package command;

import java.io.IOException;

/**
 * Интерфейс команд
 * @version 1.0
 * @see Commands
 */
public interface Command  {
    /**
     Выполнение команды
     */
    void execute() throws IOException, ClassNotFoundException;

    /**
     *
     * @return Строка с русским описанием
     */
    String ru_description();

    /**
     * Проверка на наличие аргументов
     * @return <b>true</b>, если есть аргумент
     */
    boolean HaveArgument();

    /**
     *
     * @return Имя команды
     */
    String getName();

    /**
     * Задать имя
     * @param name Имя команды
     */
    void setName(String name);

    /**
     * Проверка на необходимость массива команд
     * @return <b>true</b>, если нужен массив команд
     */
    boolean NeedCommands();
}
