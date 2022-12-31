import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ToFile {

    public ArrayList<String> list;


    public  ToFile(){
        this.list = new ArrayList<String>();
    }

    synchronized void saveToList(String result) {
        list.add(result);
    }

    void saveToFile(String fileName) throws IOException{
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file, false);
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            for(String str : list){
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
