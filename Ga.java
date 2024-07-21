import java.util.Random;

public class Ga {
    public static void main(String[] args) {
        Data data = new Data();

        Result result = new Result();
        final int num_generation = result.getNumGeneration();
        final int num_gene = result.getNumGene();
        Gene[] gene = new Gene[num_gene];
        Gene[] nextgene = new Gene[num_gene];
        for (int i = 0; i < num_gene; i++) {
            gene[i] = new Gene();
        }

        System.out.println("実行中");

        for (int generation = 0; generation < num_generation; generation++) {

            Gene box;
            for (int i = 0; i < num_gene; i++) { // 遺伝子を優秀な順に並び替え
                for (int j = 0; j < num_gene - 1 - i; j++) {
                    // if(i<3){
                    // //
                    // System.out.println("i="+i+",j="+j+",gene[j].getScore()="+gene[j].getScore()+",gene[j+1].getScore()="+gene[j+1].getScore());
                    // }
                    if (gene[j].getScore() > gene[j + 1].getScore()) {
                        // if(i<3){
                        // // System.out.println("入れ替え発生");
                        // }
                        box = gene[j];
                        gene[j] = gene[j + 1];
                        gene[j + 1] = box;
                    }
                }
            }
            // System.out.printf("第%4d世代：%f\n",generation,gene[0].getScore());
            result.setBest_score(generation, gene[0].getScore());
            // System.out.println("第"+generation+"世代："+gene[0].getScore());
            // for(int i=0;i<num_gene;i++){
            // System.out.println("第"+generation+"世代：No."+i+":"+gene[i].getScore());
            // }
            // System.out.println();

            final int hand_over = result.getHandOver();
            Random rand = new Random();
            int parentA, parentB, q;
            for (int i = 0; i < hand_over; i++) { // エリートを引き継ぎ
                nextgene[i] = gene[i];
            }

            for (int i = hand_over; i < num_gene; i++) { // 交叉
                parentA = rand.nextInt(hand_over);
                parentB = rand.nextInt(hand_over);
                q = rand.nextInt(data.getnumData());
                nextgene[i] = Gene.intersection(gene[parentA],
                        gene[parentB], q, 0);
                i++;
                nextgene[i] = Gene.intersection(gene[parentA],
                        gene[parentB], q, 1);
            }

            boolean[] mutation = new boolean[num_gene]; // 突然変異
            for (int i = 0; i < 5; i++) {
                mutation[rand.nextInt(num_gene - 1) + 1] = true;
            }
            for (int i = 0; i < num_gene; i++) {
                if (mutation[i]) {
                    nextgene[i].mutation();
                }
            }
            for (int i = 0; i < num_gene; i++) { // Geneオブジェクトの更新
                gene[i] = nextgene[i];
            }

        }
        result.fileOutput();
        System.out.println("実行終了");
    }
}
