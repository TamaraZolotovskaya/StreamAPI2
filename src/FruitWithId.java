
public class FruitWithId {
    private final String name;
    private final int id;
    private static int counter = 0;

    public FruitWithId(String name) {
        this.name = name;
        this.id = counter++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "FruitWithId{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
