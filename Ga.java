public class Ga {
    public static void main(String[] args) {
        Data data = new Data();
        data.printData();
        City[] city = new City[281];
        for (int i = 1; i < city.length; i++) {
            city[i] = new City(data.getData(i));
        }

    }
}
