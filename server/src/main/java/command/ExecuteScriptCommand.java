package command;

import condition.ExecuteCondition;
import routes.Routes;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;

/**
 * Команда исполнения
 */
public class ExecuteScriptCommand extends CommandsWithArgument<File> {
    public ExecuteScriptCommand(Routes routes, File file) {
        super(routes, file);
        argument_name = "file_name";
    }


    @Override
    public void execute() throws IOException, ClassNotFoundException {
        ExecuteCondition condition= new ExecuteCondition(argument);
        PrintToClient.ConditionSend(condition);
        PrintToClient.getBuf().SendToClient();


    }

    @Override
    public String ru_description() {
        return "считать и исполнить скрипт из указанного файла.";
    }
}
