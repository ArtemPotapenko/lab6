package run;

import condition.Condition;
import condition.ExecuteCondition;
import condition.NeedRoute;
import condition.SendArgument;
import request.RequestCommand;
import request.RequestRoute;
import run.exception.ExecException;

import java.io.*;
import java.net.*;
import java.util.Arrays;

/**
 * Класс для передачи данных на сервер.
 */
public class Client {

    InetAddress host; int port;
    SocketAddress addr; Socket sock;

    /**
     *  соединение с сервером
     * @throws IOException
     */
public void ConnectServer() throws IOException, InterruptedException {
    for (int i = 0; true; i++){
        try {
            sock=new Socket(host,port);

            System.out.println("Подключение к удаленному адресу "+host+" по порту "  +port+"...");
            break;
        } catch (SocketException ex) {
            System.out.println("Не удалось подключиться к серверу...");
            if (i == 5) {System.exit(0);}
            System.out.println("Повторная попытка");
            Thread.sleep(2000);
        }
    }
    }
    /**
     * Запись на сервер
     * @param obj
     * @throws IOException
     */
    public void write(byte[] obj) throws IOException, ClassNotFoundException {
        OutputStream outputStream = sock.getOutputStream();
        outputStream.write(obj);
        outputStream.flush();
    }
    public byte[] read() throws IOException {
        byte[] obj = new byte[10000];
        InputStream inputStream = sock.getInputStream();
        inputStream.read(obj);
        return obj;
    }

    /**
     * Отправка obj на сервер.
     * @param obj сериализованные данные.
     */
    public void send(byte[] obj) throws IOException, ClassNotFoundException, ExecException {
        write(obj);
        Condition conditionEnum =Condition.convertFromBytes(read());
        while (conditionEnum.execute()){
            if (conditionEnum instanceof NeedRoute){obj= (new RequestRoute()).convertToBytes();
                write(obj);}
            else if (conditionEnum instanceof ExecuteCondition){
                ExecException execException =new ExecException();
                execException.setNameScript((String) conditionEnum.getObject());

                throw execException;

            }else if (conditionEnum instanceof SendArgument){
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bos);
                RequestCommand command=(RequestCommand) RequestCommand.convertFromBytes(obj);
                out.writeObject(command.getCommandEnum().getArgument());
                obj=bos.toByteArray();

                write(obj);

            }else {
            write(obj);}
            conditionEnum =Condition.convertFromBytes(read());
        }

        write(obj);

    }
    public Client(InetAddress host, int port){
        this.host=host;
        this.port=port;
    }

}