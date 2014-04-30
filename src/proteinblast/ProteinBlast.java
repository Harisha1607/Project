package proteinblast;
import java.io.*;
import java.util.*;
import org.biojava3.core.sequence.io.*;
import org.biojava3.core.sequence.ProteinSequence;
import org.biojava3.alignment.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;
import org.biojava3.alignment.Alignments.PairwiseSequenceAlignerType;
import org.biojava3.alignment.template.SequencePair;
import org.biojava3.core.sequence.compound.AminoAcidCompound;
import org.biojava3.core.sequence.template.Sequence;

public class ProteinBlast {
    public static short gop;
    public static short gep;
    
    public static void main(String[] args) throws IOException, Exception{  
        
        //Argument validation
        if(args.length != 4){  
            System.out.println("Invalid number of arguments");  
            System.out.println("Usage: Java ProtienBlast Fasta_FILE Matrix_FILE GOP GEP");  
            System.exit(1);  
        }
        
        String Fasta_File = args[0];
        String Matrix_File = args[1];
        gop = Short.parseShort(args[2]);
        gep = Short.parseShort(args[3]);
        
        //Argument printing
        System.out.println("Fasta_File:"+Fasta_File);
        System.out.println("Matrix_File:"+Matrix_File);
        System.out.println("gop:"+gop);
        System.out.println("gep:"+gep);
        
        //Fasta file validation
        File fastafile = new File(Fasta_File);
        if(fastafile.exists())
            System.out.println("Fasta file " +Fasta_File+" exists");
        else 
            printErrAndQuit("Fasta file "+Fasta_File+" does not exist!");
        
        //Matrix file validation
        File matfile = new File(Matrix_File);
        if(matfile.exists())
            System.out.println("Matrix file " +Matrix_File+" exists");
        else 
            printErrAndQuit("Matrix file " +Matrix_File+" does not exist!");
        
        //Reading Fasta file containing protein sequences
        LinkedHashMap<String, ProteinSequence> a;
            a = FastaReaderHelper.readFastaProteinSequence(new File(Fasta_File));
            if (a.size()!= 2)
                printErrAndQuit("Invalid number of protein Sequences "+a.size());
            else
                System.out.println("Total "+a.size()+" protein sequences found in Fasta file");
            
            //Reading matrix file for validations
            FileInputStream inputStream = new FileInputStream(Matrix_File);
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
                if (mathArgs.length != 3)
                    printErrAndQuit("Invalid line!");   
            }
           System.out.println("Matrix file " +Matrix_File+" validated");
            
            //Cost matrix calculation
            List keys = new ArrayList(a.keySet());
            ProteinSequence seq[] = new ProteinSequence[2];
            for (int i = 0; i<keys.size(); ++i)
                seq[i] = (ProteinSequence) keys.get(i);
            SimpleSubstitutionMatrix costMatrix = costMatrix(seq[0], seq[1], gop, gep, Matrix_File);
    }
    
    public static void printErrAndQuit(String msg) {
        System.out.println(msg);
        System.out.flush();
        System.exit(-1);
    } 
    
    public static SimpleSubstitutionMatrix costMatrix(Sequence seq1, Sequence seq2, Short gop, Short gep, String Matrix_File) throws FileNotFoundException{
      
       SimpleSubstitutionMatrix<AminoAcidCompound> matrix = new SimpleSubstitutionMatrix<>();
       SequencePair<ProteinSequence, AminoAcidCompound> pair = Alignments.getPairwiseAlignment(seq1, seq2,
               PairwiseSequenceAlignerType.GLOBAL, new SimpleGapPenalty(), matrix);
       System.out.printf("%n%s vs %s%n%s", pair.getQuery().getAccession(), pair.getTarget().getAccession(), pair);
     
       return matrix;
    } 
}
                
    


 
   

