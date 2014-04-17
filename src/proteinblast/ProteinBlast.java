package proteinblast;
import java.io.*;
import java.util.*;
import org.biojava3.core.sequence.io.*;
import org.biojava3.core.sequence.DNASequence;
import org.biojava3.core.sequence.ProteinSequence;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;
public class ProteinBlast {
    public static void main(String[] args) throws IOException, Exception{  

        if(args.length != 4){  
            System.out.println("One of these are not found: Protein Sequences, Matrix file, GOP, GEP");  
            System.exit(1);  
        }  
        LinkedHashMap<String, ProteinSequence> a;
            a = FastaReaderHelper.readFastaProteinSequence(new File(args[0]));
            HashSet<String> hs = new HashSet<>(a.keySet());
            for (ProteinSequence sequence : a.values()) {
            hs.add(sequence.toString());
            }
            if (hs.size()!= 2){
                System.out.println("ERROR!!... Protein Sequences are not equal to 2");
                System.exit(1);
            }
    }
    public static int[] costMatrix(int cData[]) throws FileNotFoundException{
        String[] args = null;
         String filePath = args[0];
        File mathFile = new File(filePath);

        // make sure argument exists!
        if (!mathFile.exists()) {
            printErrAndQuit("File does not exist!");
        }

        // opening and reading a Math file
        FileInputStream inputStream = new FileInputStream(filePath);
            Scanner scanner = new Scanner(inputStream);
            // checking for the line format
            while (scanner.hasNextLine()) {
                // grab line
                String line = scanner.nextLine();

                if (line.isEmpty()) {
                    continue;
                }

                // Splitting the line into 3 components
                String[] mathArgs = line.split(":");
                if (mathArgs.length != 3) {
                    printErrAndQuit("Invalid line!");
                }
            }
            int costData[] = new int[mathFile.nextInt()];
            for (int i=0; i<costData.length; i++){
                costData[i] = mathFile.nextInt();
                
            }
            return costData;
    }
    
    public static void printErrAndQuit(String msg) {
        System.out.println(msg);
        System.out.flush();
        System.exit(-1);
    }
    
}
                
    


 
   

