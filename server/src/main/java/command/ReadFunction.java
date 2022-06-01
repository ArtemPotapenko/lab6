package command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import command.exception.IDException;
import routes.Routes;
import transfer.PrintToClient;

/**
 * Класс для чтения команд <b>Command</b> с консоли
 * @see Command
 * @version 1.1
 */
public class ReadFunction {

    /**
     * Массив команд
     */
    Command[] commands;
    /**
     * Очередь команд
     */
    Deque<Command> Last_comands = new LinkedList<>();

    /**
     * Задать массив команд
     * @param commands Массив команд
     */
    public void setCommands(Command[] commands) {
        this.commands = commands;
    }

    /**
     * Обработка строки из консоли для выполнение команды
     * @param s строка из консоли
     */
    public void read(String s) {
        boolean ok = false;
        String[] S = s.split(" ");
        String command_name = "";
        for (String x:S){
        if (x.trim().length()!=0){
        command_name = x.trim();
        break;}}
        Command ok_com = null;
        for (Command x : commands) {
            Pattern pattern = Pattern.compile(x.getName());
            if (pattern.matcher(command_name).matches()) {
                try{
                if (x.HaveArgument()) {
                    try {
                        CommandsWithArgument<?> command_arg = (CommandsWithArgument<?>) x;
                        int count_non_space=0;
                        String command_arg_name="";
                        for (String y:S){
                        if (y.trim().length()!=0){
                            count_non_space++;
                            if (count_non_space==2){
                            command_arg_name = y.trim();
                            break;}}}
                        command_arg.String_to_Arg(command_arg_name);
                        command_arg.execute();
                        ok = true;
                        ok_com = command_arg;
                    } catch (IDException e) {
                        PrintToClient.print("Неверный ввод аргумента.");
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    ok = true;
                    ok_com = x;
                    if (x.NeedCommands()) {
                        CommandsUseArray command_with_arr = (CommandsUseArray) x;
                        command_with_arr.setCommands(commands);
                        command_with_arr.execute();
                    } else {
                        x.execute();
                    }
                }}catch (NoSuchElementException e){

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }


        if (!ok) {
            System.out.println("Ошибка выполнения : неправильный ввод. Введите help для вывода возможных команд.");
        }
        if (ok) {
            Last_comands.addFirst( ok_com);
        }
    }

    /**
     * История команд
     */
    public class HistoryCommand extends Commands {


        public HistoryCommand(Routes routes, File file) {
            super(routes, file);
        }

        @Override
        public void execute() throws IOException {
            if (ReadFunction.this.Last_comands.size() < 9) {
                Last_comands.forEach(x ->PrintToClient.print(x.toString()));
            if (ReadFunction.this.Last_comands.size()==0){
                PrintToClient.print("Команд нет.");
            }

            } else {
                int i = 0;
                for (Command x : Last_comands) {
                    i++;
                    System.out.println(x);
                    if (i == 9) {
                        break;
                    }
                }
            }
            PrintToClient.getBuf().SendToClient();
        }

        @Override
        public String ru_description() {
            return "вывести последние 9 команд (без их аргументов)";
        }
    }

    /**
     * Запуск скрипта.
     */
    public class ExecuteCommand extends CommandsWithArgument<String> {

        public ExecuteCommand(Routes routes, File file) {
            super(routes, file);
            argument_name = "file_name";
        }
        private List<File> FileArray= new ArrayList<File>();


        @Override
        public void execute() {

           File script =new File(argument);
           if (!FileArray.contains(script)){
           FileArray.add(script);


            try {
                Scanner in_script = new Scanner(script);
                while (in_script.hasNext()) {
                    read(in_script.nextLine());
                }
            } catch (FileNotFoundException e) {
                PrintToClient.print("Файл со скриптом не найден");
            }
            FileArray.remove(script);


        }}

        @Override
        public String ru_description() {
            return "считать и исполнить скрипт из указанного файла.";
        }
    }
    public void  appendCommand(Command command){
        Last_comands.addFirst(command);
    }

}
