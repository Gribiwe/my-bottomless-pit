/**
 * Created by Nikita on 2018/06/07.
 */
public class Row <K, V> {
    private int key;
    private Node<K, V> firstNode;

    public Row(K key, V value) {
        this.key = key.hashCode();
        this.firstNode = new Node<K, V>(key, value);
    }

    public int getKey() {
        return key;
    }

    public Node<K, V> getFirstNode() {
        return firstNode;
    }
}
