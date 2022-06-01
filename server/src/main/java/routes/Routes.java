package routes;



import java.util.*;

/**
 * Класс обертка Route
 * @version 1.0
 * @see Route
 */
public class Routes implements Set<Route>{
    /**
     * За счет этого поля реализуется делегирование.
     */
    private LinkedHashSet<Route> setDeleg=new LinkedHashSet<Route>();
    @Override
    public int size() {
        return setDeleg.size();
    }

    @Override
    public boolean isEmpty() {
        return setDeleg.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return setDeleg.contains(o);
    }

    @Override
    public Iterator<Route> iterator() {
        return setDeleg.iterator();
    }

    @Override
    public Object[] toArray() {
        return setDeleg.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return setDeleg.toArray(a);
    }

    @Override
    public boolean add(Route route) {
        boolean b=setDeleg.add(route);
        sort();
        return b;
    }

    @Override
    public boolean remove(Object o) {
        boolean b=setDeleg.remove(o);
        sort();
        return b;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return setDeleg.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Route> c) {
        boolean b= setDeleg.addAll(c);
        sort();
        return b;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return setDeleg.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean b= setDeleg.removeAll(c);
        sort();
        return b;
    }

    @Override
    public void clear() {
        setDeleg.clear();

    }

    @Override
    public String toString() {
        return setDeleg.toString();
    }

    @Override
    public boolean equals(Object o) {
        return setDeleg.equals(o);
    }

    @Override
    public int hashCode() {
        return setDeleg.hashCode();
    }

    /**
     * сортировка
     */
    public void sort(){
        TreeSet<Route> routeSorted = new TreeSet<>(setDeleg);
        setDeleg.removeAll(setDeleg);
        setDeleg.addAll(routeSorted);
    }

    /**
     *
     * @return множество элементов.
     */
    public LinkedHashSet<Route> getSet() {
        return setDeleg;
    }
}
