package third;

/**
 * Created by nikit on 17.02.2018.
 */
public class ClassInfo {
    String name;
    String NSM;
    int childrenCount;

    public ClassInfo(String name, int childrenCount, String NSM) {
        this.name = name;
        this.NSM = NSM;
        this.childrenCount = childrenCount;
    }

    public String getName() {

        return name;
    }

    public String getFSM() {
        return NSM;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void sout(){
        System.out.print(getName()+"|"+getChildrenCount()+"|"+getFSM()+"\n");
    }
}
