import java.util.Random;

public class Gene {
    int[] route;
    int num_data;

    Gene() {
        Data data = new Data();
        num_data = data.getnumData();
        route = new int[num_data];
        boolean[] routeBookingCheck = new boolean[num_data + 1];
        Random rand = new Random();
        for (int i = 0; i < num_data; i++) {
            int selectedCity = rand.nextInt(num_data - i);
            for (int j = 0; j < num_data; i++) {
                if (!routeBookingCheck[j]) {
                    selectedCity--;
                }
                if (selectedCity == 0) {
                    route[i] = j;
                    routeBookingCheck[j] = true;
                }
            }
        }
    }

    Gene(int[] route) {
        this.route = route;
    }

    public int[] getRoute(){
        return route;
    }

    public int getRoute(int i) {
        return route[i];
    }

    public void setRoute(int i,int n){
        route[i]=n;
    }

    public Gene intersection(Gene parentA, Gene parentB, int n) {// n=0||n=1
        int[] routeA,routeB;
        routeA=parentA.getRoute();
        routeB=parentB.getRoute();
        boolean[] change = new boolean[num_data];
        Random rand = new Random();
        int[] q = new int[num_data];
        int[] k = new int[num_data];
        int box;
        q[0]=rand.nextInt(num_data);
        for(int i=0;true;i++){
            for(int j=0;j<num_data;j++){
                if(parentA.getRoute(j)==q[i]){
                    k[i]=j;
                    change[k[i]]=true;
                    break;
                }
            }
            q[i+1]=parentB.getRoute(k[i]);
            if(q[0]==q[i+1]){
                break;
            }
        }
        for(int i=0;i<num_data;i++){
            if(change[i]){
                box=routeA[i];
                routeA[i]=routeB[i];
                routeB[i]=box;
            }
        }
        if(n==0){
            return new Gene(routeA);
        }else{
            return new Gene(routeB);
        }
    }

    public void mutation(){
        Random rand = new Random();
        int n,m;
        do{
            n=rand.nextInt(num_data);
            m=rand.nextInt(num_data);
        }while(n!=m);
        int box;
        box=route[n];
        route[n]=route[m];
        route[m]=box;
    }
}
