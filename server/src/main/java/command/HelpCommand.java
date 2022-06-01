package command;

import routes.Routes;
import transfer.PrintToClient;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Вывести список команд
 * @version 1.1
 */
public class HelpCommand extends CommandsUseArray{
    public HelpCommand(Routes routes, File file) {
        super(routes, file);
    }
    private static void help_print_for_command(Command command) throws IOException {
        PrintToClient.print(command.getName() + (command.HaveArgument() ? " " + ((CommandsWithArgument<?>) command).getArgument_name() + " " : " ") + ": " + command.ru_description());
    }

    @Override
    public void execute()  {
        Arrays.stream(getCommands()).forEach(x-> {
            try {
                help_print_for_command(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            PrintToClient.getBuf().SendToClient();
        } catch (IOException e) {
            e.getMessage();
        }

    }

    @Override
    public String ru_description() {
        return "вывести справку по доступным командам";
    }
}
