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
}

 
   

