import java.util.Random;
import java.util.Arrays;
public class Gene {
    int[] route;
    int num_data;
    double score;
    Data data = new Data();
    City[] city = new City[281];

    Gene() {
        for (int i = 1; i < city.length; i++) {
            city[i] = new City(data.getData(i));
        }
        num_data = data.getnumData();
        route = new int[num_data];
        boolean[] routeBookingCheck = new boolean[num_data + 1];
        Random rand = new Random();
        for (int i = 0; i < num_data; i++) {
            int selectedCity = rand.nextInt(num_data - i);
            for (int j = 1; j < num_data + 1; j++) {
                if (!routeBookingCheck[j]) {
                    // System.out.println("i="+i+",j="+j+",selectedCity="+selectedCity);
                    if(selectedCity>0){
                        selectedCity--;
                    }
                    if (selectedCity == 0) {
                        // System.out.println("    i="+i+",j="+j);
                        route[i] = j;
                        routeBookingCheck[j] = true;
                        break;
                    }
                }
            }
        }
        calculateScore();
    }

    Gene(int[] route) {
        for (int i = 1; i < city.length; i++) {
            city[i] = new City(data.getData(i));
        }
        this.route = route;
        num_data = data.getnumData();
        calculateScore();
    }

    public int[] getRoute() {
        return route;
    }

    public int getRoute(int i) {
        return route[i];
    }

    public void setRoute(int i, int n) {
        route[i] = n;
    }

    public int getNumData(){
        return num_data;
    }

    public static Gene intersection(Gene parentA, Gene parentB, int q0,int n) {// n=0||n=1
        int[] routeA, routeB;
        int num_data = parentA.getNumData();
        routeA = parentA.getRoute();
        routeB = parentB.getRoute();
        boolean[] change = new boolean[num_data];
        int[] q = new int[num_data];
        int[] k = new int[num_data];
        int box;
        q[0] = q0;
        for (int i = 0; i<num_data-1; i++) {            //交叉
            for (int j = 0; j < num_data; j++) {
                if (parentA.getRoute(j) == q[i]) {
                    k[i] = j;
                    change[k[i]] = true;
                    break;
                }
            }
            q[i + 1] = parentB.getRoute(k[i]);
            if (q[0] == q[i + 1]) {
                break;
            }
        }
        for (int i = 0; i < num_data; i++) {
            if (change[i]) {
                box = routeA[i];
                routeA[i] = routeB[i];
                routeB[i] = box;
            }
        }
        if (n == 0) {
            return new Gene(routeA);
        } else {
            return new Gene(routeB);
        }
    }

    public void mutation() {
        Random rand = new Random();
        int n, m;
        do {
            num_data = data.getnumData();
            // System.out.println(num_data);
            n = rand.nextInt(num_data);
            m = rand.nextInt(num_data);
        } while (n >= m);   //nがmよりも小さいと終了

        int n_end,m_end;
        n_end = rand.nextInt(m-n);
        m_end = rand.nextInt(num_data-m);
        int[] first_array,n_array,middle_array,m_array,final_array;
        // System.out.println("n="+n+",m="+m);
        // System.out.println("n_end="+n_end+",m_end="+m_end);
        first_array=Arrays.copyOfRange(route, 0, n);
        n_array=Arrays.copyOfRange(route,n,n+n_end+1);
        middle_array=Arrays.copyOfRange(route,n+n_end+1,m);
        m_array=Arrays.copyOfRange(route,m,m+m_end+1);
        final_array=Arrays.copyOfRange(route,m+m_end+1,route.length);
        int i=0;
        while(true){
            if(i<first_array.length){
                route[i]=first_array[i];
            }else if(i<first_array.length+m_array.length){
                route[i]=m_array[i-first_array.length];
            }else if(i<first_array.length+m_array.length+middle_array.length){
                route[i]=middle_array[i-(first_array.length+m_array.length)];
            }else if(i<first_array.length+m_array.length+middle_array.length+n_array.length){
                route[i]=n_array[i-(first_array.length+m_array.length+middle_array.length)];
            }else if(i<first_array.length+m_array.length+middle_array.length+n_array.length+final_array.length){
                route[i]=final_array[i-(first_array.length+m_array.length+middle_array.length+n_array.length)];
            }else if(i>=first_array.length+m_array.length+middle_array.length+n_array.length+final_array.length){
                break;
            }
            i++;
        }
        calculateScore();
    }

    public void setScore(int n) {
        score = n;
    }

    public double getScore() {
        return score;
    }

    public void calculateScore() {
        score = 0;
        for (int i = 0; i < num_data - 1; i++) {
            score += Math.sqrt(Math.pow(city[route[i]].getX() - city[route[i + 1]].getX(), 2)
                    + Math.pow(city[route[i]].getY() - city[route[i + 1]].getY(), 2));
        }
    }

    public void printRoute(){
        for(int i=0;i<num_data;i++){
            System.out.println("route["+i+"]="+route[i]);
        }
    }
}
