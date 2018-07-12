import java.util.LinkedList;

public class SuperHashTable<K, V> {
    private LinkedList<Row<K, V>> rows = new LinkedList<>();

    public void put(K key, V value) {
        System.out.println("");//для отсутупа
        System.out.println("добавление ключа в таблицу "+key.hashCode()+" | "+key);

        Row<K, V> bufRow = new Row<K, V>(key, value);
        for (Row row : rows) {
            if (row.getKey() == key.hashCode()) {
                if (row.getFirstNode().addLastNode(key, value)){
                    System.out.println("добавлено рекурсией "+ key.hashCode()+ " | "+key);
                }
                return;
            }
        }

        rows.add(bufRow);
        System.out.println("добавлено норм ключ "+key.hashCode()+" | "+key);
        return;
    }

    public V get(K key) {
        int keyCode = key.hashCode();
        for (Row row : rows) {
            if (row.getKey() == keyCode) {
                V result = (V) row.getFirstNode().letsFindSpecialNode(key);
                if (result != null) return result;
            }
        }
        System.err.println("Не найдено значения с таким ключем!");
        return null;
    }
}
