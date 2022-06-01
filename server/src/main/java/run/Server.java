package run;


import command.CommandsWithArgument;
import condition.Condition;
import condition.SendArgument;
import request.RequestCommand;
import command.User;
import routes.Routes;

import java.io.*;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * класс для обмена данными на стороне сервера.
 */
public class Server {
    File file;
    Routes routes;
    InetAddress host; int port;
    Socket sock;
    ServerSocketChannel serv;
    SocketChannel socketChannel ;
    User user;



    /**
     * Подключение к серверу

     * @throws IOException
     */
    public void connect() throws IOException {
        socketChannel=serv.accept();
        sock=socketChannel.socket();



    }

    /**
     *
     * @return
     * @throws IOException
     */
    public byte[] read() throws IOException {
        byte[] obj = new byte[10000];
        InputStream is=sock.getInputStream();
        is.read(obj);
        return obj;
    }

    /**
     * Исполнение команды
     * @param obj данные
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public byte[] exec(byte[] obj, RunCommand runCommand) throws IOException, ClassNotFoundException {
        RequestCommand command=(RequestCommand) RequestCommand.convertFromBytes(obj);
        if (runCommand.map.get(command.getCommandEnum()).HaveArgument()){
            write(Condition.convertToBytes(new SendArgument(1)));
            obj=read();
            String argument="";
            try (ByteArrayInputStream bis = new ByteArrayInputStream(obj);
                 ObjectInputStream in = new ObjectInputStream(bis)) {
                argument=(String) in.readObject();
            }
            command.getCommandEnum().setArgument(argument);
        }
        runCommand.run(command);
        return obj;
    }
    public void write(byte[] obj) throws IOException {
        OutputStream outputStream=sock.getOutputStream();
        outputStream.write(obj);
    }

    /**
     * Обмен данными
     * @return сериализованные данные
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void run(RunCommand runCommand) throws IOException, ClassNotFoundException {
        exec(read(),runCommand);
    }

    public Server(String s,Routes routes,int port,User user) throws IOException {
        this.file=OpenFile.fileOpen_csv(s,routes);
        this.port=port;
        this.user=user;
        SocketAddress socketAddress=new InetSocketAddress(port);
        serv =ServerSocketChannel.open();
        serv.bind(socketAddress);
    }

}
