import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        SuperHashTable<String,Student> testMap = new SuperHashTable<>();

        testMap.put("Староста", new Student(18, "Вася", "Кеков"));
        testMap.put("Самый отстойный студент", new Student(16, "Арисса", "Poniwkawa"));
        testMap.put("Самый отстойный студент", new Student(16, "Арисса", "Poniwkawa"));

        System.out.println(testMap.get("Староста").getName());
        System.out.println(testMap.get("Самый отстойный студент").getName());
    }
}
