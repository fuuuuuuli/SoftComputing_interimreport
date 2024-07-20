import java.io.*;


public class Data {
    int[][] Node = new int[281][3];
    Data() {
        try {
            FileReader fr = new FileReader("a280.tsp");
            BufferedReader br = new BufferedReader(fr);
            StreamTokenizer st = new StreamTokenizer(br);
            st.eolIsSignificant(true);
            boolean readStart = false;
            int i=1;
            int j=0;
            while(st.nextToken() !=StreamTokenizer.TT_EOF){
                switch(st.ttype){
                    case StreamTokenizer.TT_NUMBER: if(st.nval==1||readStart) {
                        readStart=true;
                        Node[i][j]=(int)st.nval;
                        j++;
                    }
                    break;
                    case StreamTokenizer.TT_EOL: if(readStart){
                        i++;
                        j=0;
                    }
                    break;
                }
            }


            br.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception:" + e);
        }

    }

    public void printData(){
        for(int i=0;i<281;i++){
            System.out.println(Node[i][0]+","+Node[i][1]+","+Node[i][2]);
        }
    }
}
