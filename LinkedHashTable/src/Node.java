/**
 * Created by Nikita on 2018/06/07.
 */
public class Node<K, V> {
    private Node<K, V> nextNode;
    private V value;
    private K key;

    public boolean addLastNode(K key, V value) {
        if (!key.equals(this.key)) {
            if (nextNode == null) {
                nextNode = new Node<K,V>(key, value);
                return true;
            } else {
                nextNode.addLastNode(key, value);
            }
        } else {
            System.out.println("Такое значение ключа уже есть!");
        }
        return false;
    }

    public V letsFindSpecialNode(K key){
        if (key == this.key) {
            return value;
        } else if (nextNode != null) {
            return nextNode.letsFindSpecialNode(key);
        }
        return null;
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setNextNode(Node<K, V> nextNode) {
        this.nextNode = nextNode;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public V getValue() {
        return value;
    }
}
