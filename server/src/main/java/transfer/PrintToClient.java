package transfer;

import condition.Condition;
import condition.Exit;
import condition.NeedRoute;
import condition.Print;
import request.RequestRoute;

/**
 * Класс отправки состояния на клиент
 */
public class PrintToClient {
    private static BufferSendToClient buf=new BufferSendToClient();


    public static BufferSendToClient getBuf() {
        return buf;
    }

    /**
     * Отправка клиенту строки
     * @param s строка
     */
    public static void print(String s){
        Print print=new Print(s);
        getBuf().getList().add(print);

    }

    /**
     * Универсальная отправка
     * @param condition Состояние клиента
     */
    public static void ConditionSend(Condition condition){
        getBuf().getList().add(condition);
    }

    /**
     * Отправка клиенту запрос и закрытии
     */
    public static void exit(){
        Exit exit=new Exit(1);
        getBuf().getList().add(exit);
    }
    public static void getRoute(){
        NeedRoute needRoute=new NeedRoute(1);
        getBuf().getList().add(needRoute);
    }
}
