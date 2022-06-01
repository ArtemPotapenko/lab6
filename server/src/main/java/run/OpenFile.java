package run;

import parse.ParseCSV;
import routes.Routes;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс содержит один метод для открытия файл для чтения и записи данных
 * @version 1.1
 */
public class OpenFile {
    /**
     * В методе проверяется файл на правильность, в него вводится шапка таблицы(если ее не было), и считываются
     * все элементы через парсер
     * @see ParseCSV
     * @param file_name Имя файла
     * @param a Коллеция
     * @return Файл для чтения/записи
     * @throws IOException Ошибка вводв-вывода
     *
     */
    public  static File fileOpen_csv(String file_name, Routes a) throws IOException {
    String hat_table = "id, name, x_coord, y_coord,Date, to class, to_x, to_y, to_z, from class, from_x," +
            "from_y, from_z, distance, spaec_id";
    File file = new File(file_name);
        if (!file.isFile()){
        file.createNewFile();
        PrintWriter out_file = new PrintWriter(file);
        out_file.println(hat_table);
        out_file.close();
    }else if(!file.canRead() || !file.canWrite()){
        System.out.println("Недостаточно прав у файла для работы программы.");
        System.exit(0);
    }

    Scanner in_file=new Scanner(file);
        try{
        String t=in_file.nextLine();
        while (in_file.hasNext()){
            a.add(ParseCSV.parse_csv_to_route(in_file.nextLine()));

        }
    }catch (NoSuchElementException | NumberFormatException e  ){
        System.out.println("Файл не подходит под формат даннных. Пожайлуйста, либо введите имя подходящего файла, либо имя несуществующего файла для создания нового. ");
        System.exit(0);
    }
        return file;
    }
}
