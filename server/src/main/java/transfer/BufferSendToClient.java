package transfer;

import condition.*;
import run.Server;

import java.io.IOException;
import java.util.LinkedList;


/**
 * Буфер для отправки данных на сервер.
 */
public class BufferSendToClient{
    /**
     * Сервер
     */
    private Server server;
    public BufferSendToClient(){
    }
    /*private RunCommand runCommand;

    public void setRunCommand(RunCommand runCommand) {
        this.runCommand = runCommand;
    }*/

    public void setServer(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    private LinkedList<Condition> list=new LinkedList<>();

    public LinkedList<Condition> getList() {
        return list;
    }
    public void clear(){
        list.clear();
    }

    public void SendToClient() throws IOException {

        this.list.add(new END(1));
        for (Condition x: list){
                byte[] bytes=Condition.convertToBytes(x);
                server.write(bytes);
                if (x instanceof Exit){
                    this.clear();
                    break;
                }
                server.read();}
        this.clear();

    }
    public byte[] ReadRoute() throws IOException {
        Condition x=new NeedRoute(1);
        byte[] bytes=Condition.convertToBytes(x);
        server.write(bytes);
        this.clear();
        return server.read();
    }


}
