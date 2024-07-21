import java.io.*;
public class Result {
    final private int num_generation;
    final private int num_gene;
    final private int hand_over;
    double[] Best_score;

    Result() {
        num_generation = 1;
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
    }

}
