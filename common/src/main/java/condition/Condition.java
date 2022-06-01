package condition;



import java.io.*;

/**
 * Класс для сериализации Condition
 * @version 1.0
 */
public abstract class Condition implements Serializable {

    /**
     * Исполнение
     */
    public abstract boolean execute();

    /**
     * Объект действия
     */
    private Object object;

    /**
     *
     * @return Объект действия
     */
    public Object getObject() {
        return object;
    }

    public Condition(Object object){
        this.object=object;
    }

    /**
     *
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Condition convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException{
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return (Condition) in.readObject();
        }}
    /**
     * Конвертирование данных в сериализованный вид
     * @return Сериализованные данные
     * @throws IOException
     * @param condition Состояние
     */
    public static byte[] convertToBytes(Condition condition) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(condition);
        return bos.toByteArray();
    }
}
