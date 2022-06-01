import command.*;
import command.exception.IDException;
import condition.Condition;
import condition.END;
import condition.Print;
import routes.Routes;
import run.*;
import run.Server;
import transfer.PrintToClient;

import java.io.*;
import java.net.SocketException;

public class Main {
    /**
     * Метод исполнение команд доступных серверу
     * @param user Пользователь
     * @param file Файл
     * @throws IOException
     */
    public static void stop(User user,File file) throws IOException {
        InputStreamReader fileInputStream = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(fileInputStream);
        String string ="";
        if (bufferedReader.ready()) {
            string =bufferedReader.readLine();}

        if (string.equals("exit")){
            (new SaveCommand(user.getRoutes(), file)).execute();
            System.exit(0);
        }
        if (string.equals("save")){
            (new SaveCommand(user.getRoutes(),file)).execute();
            System.out.println("Файл сохранен");

        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String filename=args[0];
        int port=Integer.parseInt(args[1]);

        File file=new File(filename);
        Routes a = new Routes();
        ReadFunction readCommand = new ReadFunction();
        User user=new User(file,a,new HelpCommand(a,file),new InfoCommand(a,file),new ShowCommand(a,file),new AddCommand(a,file)
                , new UpdateCommand(a,file),new RemoveCommand(a,file),new ClearCommand(a,file),
                new ExecuteScriptCommand(a,file),new ClientExitCommand(a,file),new AddIfMaxCommand(a,file),new RemoveGreater(a,file),
                readCommand.new HistoryCommand(a,file),new MaxByToCommand(a,file),new PrintDescendingCommand(a,file),new PrintFieldAscendingDistance(a,file));
        Runnable run = () -> {
            while (true){
                try {
                    stop(user,file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();
        Server server =new Server(filename,a,port,user);
        server.connect();
        RunCommand runCommand=new RunCommand(user,readCommand);
        PrintToClient.getBuf().setServer(server);
       Thread threadMain=new Thread(()-> {while(true){
         try{
             server.run(runCommand);
         }
         catch (NullPointerException e){


         }catch (SocketException | StreamCorruptedException e){
             System.out.println("Клиент разорвал соединение");
             try {
                 server.connect();
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
         }
         catch (IDException idException){
             try {
                 PrintToClient.print(idException.getMessage());
                 PrintToClient.getBuf().SendToClient();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         } catch (IOException | ClassNotFoundException e) {
             e.printStackTrace();
         }

       }});
       threadMain.start();

    }
}
