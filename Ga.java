import java.util.Random;

public class Ga {
    public static void main(String[] args) {
        Data data = new Data();

        Result result = new Result();
        final int num_generation = result.getNumGeneration();
        final int num_gene = result.getNumGene();
        Gene[][] gene = new Gene[num_generation][num_gene];
        for (int i = 0; i < num_gene; i++) {
            gene[0][i] = new Gene();
        }

        for (int generation = 0; generation < num_generation; generation++) {
            
            Gene box;
            for (int i = 0; i < num_gene; i++) {                                    // 遺伝子を優秀な順に並び替え
                for (int j = i; j < num_gene - 1; j++) {
                    if(i<3){
                        System.out.println("i="+i+",j="+j+",gene[generation][j].getScore()="+gene[generation][j].getScore()+",gene[generation][j+1].getScore()="+gene[generation][j+1].getScore());
                    }
                    if (gene[generation][j].getScore() > gene[generation][j + 1].getScore()) {
                        if(i<3){
                            System.out.println("入れ替え発生");
                        }
                        box = gene[generation][j];
                        gene[generation][j] = gene[generation][j + 1];
                        gene[generation][j + 1] = box;
                    }
                }
            }
            System.out.println("第"+generation+"世代："+gene[generation][0].getScore());
            for(int i=0;i<num_gene;i++){
                System.out.println("第"+generation+"世代：No."+i+":"+gene[generation][i].getScore());
            }
            System.out.println();
            result.setBest_score(generation, gene[generation][0].getScore());       //結果を記録

            final int hand_over = result.getHandOver();
            Random rand = new Random();
            int parentA, parentB, q;
            for (int i = 0; i < hand_over; i++) {                                   //エリートを引き継ぎ
                gene[generation + 1][i] = gene[generation][i];
            }

            for (int i = hand_over; i < num_gene; i++) {                            //交叉
                parentA = rand.nextInt(hand_over);
                parentB = rand.nextInt(hand_over);
                q = rand.nextInt(data.getnumData());
                gene[generation + 1][i] = Gene.intersection(gene[generation][parentA],
                        gene[generation][parentB], q, 0);
                i++;
                gene[generation + 1][i] = Gene.intersection(gene[generation][parentA],
                        gene[generation][parentB], q, 1);
            }

            boolean[] mutation = new boolean[num_gene];                             //突然変異
            for (int i = 0; i < 5; i++) {
                mutation[rand.nextInt(num_gene - 1) + 1] = true;
            }
            for (int i = 0; i < num_gene; i++) {
                if (mutation[i]) {
                    gene[generation + 1][i].mutation();
                }
            }
        }

    }
}
