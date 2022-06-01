package parse;

import java.util.Date;

import routedir.Coordinates;
import routedir.FloatLocation;
import routedir.IntLocation;
import routedir.Location;
import routes.Route;

/**
 * Класс - парсер CSV-файлов.
 * @version 1.0
 */
public final class ParseCSV {

    /**
     * @param s Из файла *.csv
     * @return Элемент коллекции
     */
    public static Route parse_csv_to_route(String s) {
        String[] parse_args = s.split(",");
        long id = Long.parseLong(parse_args[0]);
        String name = parse_args[1];
        Coordinates coordinates = new Coordinates(Long.parseLong(parse_args[2]), Double.parseDouble(parse_args[3]));
        Date creationDate = new Date(Long.parseLong(parse_args[14]));
        Location location_from;
        Location location_to;
        boolean ok = "class commоn.routedir.IntLocation".equals(parse_args[5]);
        if (ok) {
            location_from = new IntLocation(Integer.parseInt(parse_args[6]),
                    Long.parseLong(parse_args[7]), parse_args[8]);
        } else if ("class commоn.routedir.Location".equals(parse_args[5])) {
            location_from = null;
        } else {
            location_from = new FloatLocation(Float.parseFloat(parse_args[6]),
                    Float.parseFloat(parse_args[7]), Integer.parseInt(parse_args[8]));
        }
        ok = "class commоn.routedir.IntLocation".equals(parse_args[9]);
        if (ok) {
            location_to = new IntLocation(Integer.parseInt(parse_args[10]),
                    Long.parseLong(parse_args[11]), parse_args[12]);
        } else {
            location_to = new FloatLocation(Float.parseFloat(parse_args[10]),
                    Float.parseFloat(parse_args[11]), Integer.parseInt(parse_args[12]));
        }
        long distance = Long.parseLong(parse_args[13]);


        return new Route(id, creationDate, coordinates, name, location_from, location_to, distance);
    }

    /**
     * @param route Элемент коллекции
     * @return Строка для записи в файл
     */
    public static String parse_route_to_csv(Route route) {
        long id = route.getId();
        long x_coordinate = route.getCoordinates().getX();
        Double y_coordinate = route.getCoordinates().getY();
        Class<? extends Location> Loc_class_from = route.getFrom() != null ? route.getFrom().getClass() : Location.class;
        Location from = route.getFrom();
        String from_get_All = from != null ? from.getAll() : " , , ";
        Location to = route.getTo();
        Class<? extends Location> Loc_class_to = route.getTo().getClass();
        long distance = route.getDistance();


        return id + "," + route.getName() + "," + x_coordinate + "," + y_coordinate + "," + route.getCreationDate() + "," + Loc_class_from + "," + from_get_All + "," + Loc_class_to +
                "," + to.getAll() + "," + distance + "," + route.getCreationDate().getTime();
    }
}
