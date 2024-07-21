import java.io.*;
public class Result {
    final private int num_generation;
    final private int num_gene;
    final private int hand_over;
    double[] Best_score;
    FileWriter fw;
    BufferedWriter bw;

    Result() {
        num_generation = 5000;
        num_gene = 100;
        hand_over = 10;
        Best_score = new double[num_generation];
    }

    public void setBest_score(int generation, double best_score) {
        Best_score[generation] = best_score;
    }

    public int getNumGeneration() {
        return num_generation;
    }

    public int getNumGene() {
        return num_gene;
    }

    public int getHandOver() {
        return hand_over;
    }

    public void fileOutput(){
        try {
            fw = new FileWriter("result.csv");
            bw = new BufferedWriter(fw);
            for(int i=1;i<=num_generation;i++){
                bw.write(i+","+Best_score[i-1]+"\n");
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Exceprion:"+e);
        }
    }

}
