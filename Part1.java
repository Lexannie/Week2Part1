/**
 * Project name: StringsSecondAssignment
 * Description
 * 
 * @author Annie Grubb 
 * @version 0.1 3/5/24
 */
import edu.duke.*;
import java.io.File;
import java.time.LocalTime;

public class Part1 {

    /**
     * findGene:
    Find the index of the first occurrence of the start codon “ATG”. 
    If there is no “ATG”, return the empty string.

    Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” 
    that is a multiple of three away from the “ATG”. Hint: call findStopCodon.

    Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” 
    that is a multiple of three away from the “ATG”. 

    Find the index of the first occurrence of the stop codon “TGA” after the first occurrence of “ATG” 
    that is a multiple of three away from the “ATG”. 

    Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away. 
    If there is no valid stop codon and therefore no gene, return the empty string.
     *
     * @param:  
     *      String parameter dna, representing a string of DNA
     *      int startSearchAt; where to start searching
     * 
     * @return void
     */
    public static String findGene(String dna, int startSearchAt) {
        int indexOfStartCodon = 0;
        int indexOfStopCodonTAA = 0;
        int indexOfStopCodonTAG = 0;
        int indexOfStopCodonTGA = 0;
        int indexOfFirstStopCodon = 0;
        //int searchStartIndex = 0;
        String geneFound;

        // Find the index of the first occurrence of the start codon “ATG” occuring afterstartSearchAt
        indexOfStartCodon = dna.indexOf("ATG", startSearchAt);
        System.out.println("   *** indexOfStartCodon: " + indexOfStartCodon);
        if (indexOfStartCodon == -1){           
            System.out.println("   ***** Start Codon not found! ");
            return "";
        }

        // Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG
        indexOfStopCodonTAA = findStopCodon(dna, indexOfStartCodon, "TAA");
        System.out.println("   *** indexOfStopCodonTAA: " + indexOfStopCodonTAA);

        // Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG”
        indexOfStopCodonTAG = findStopCodon(dna, indexOfStartCodon, "TAG");
        System.out.println("   *** indexOfStopCodonTAG: " + indexOfStopCodonTAG);

        // Find the index of the first occurrence of the stop codon “TGA” after the first occurrence of “ATG”
        indexOfStopCodonTGA = findStopCodon(dna, indexOfStartCodon, "TGA");
        System.out.println("   *** indexOfStopCodonTGA: " + indexOfStopCodonTGA);

        // find first stop codon of codons found
        //if (indexOfStopCodonTAA != -1){
        //    indexOfFirstStopCodon = indexOfStopCodonTAA;
        //}
        //if ((indexOfStopCodonTAA != -1) & (indexOfStopCodonTAG != -1)){
        indexOfFirstStopCodon = Math.min(indexOfStopCodonTAA, indexOfStopCodonTAG);
        //}
        //if (indexOfStopCodonTGA != -1)){
        indexOfFirstStopCodon = Math.min(indexOfFirstStopCodon, indexOfStopCodonTGA);    
        //}
        //else{
        // check that at least one stop codon was found
        if (indexOfFirstStopCodon >= dna.length()){
            System.out.println("   ***** No Stop Codons found! ");
            return "";               
        }
        //    indexOfFirstStopCodon = Math.min(indexOfStopCodonTAA, indexOfStopCodonTAG);       
        //}

        // Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away.
        geneFound = dna.substring(indexOfStartCodon, indexOfFirstStopCodon +3);
        return (geneFound);
    }

    /*
     * findStopCodon 
     * @ parameters:
     *      a String parameter named dna
     *      an integer parameter named startIndex that represents where the first occurrence of ATG occurs in dna
     *      a String parameter named stopCodon.
     * @returns:
     *      the index of the first occurrence of stopCodon 
     *      that appears past startIndex and is a multiple of 3 away from startIndex. 
     *      If there is no such stopCodon, this method returns the length of the dna strand.
     */
    static int findStopCodon(String dna, int startIndex, String stopCodon){

        int indexOfStopCodon;

        indexOfStopCodon = dna.indexOf(stopCodon, startIndex);
        System.out.println("   *** findStopCodon STOP_CODON at index: " + indexOfStopCodon);
        // Check that index found is a multiple of 3, if not return -1
        if ( (indexOfStopCodon < 0) | (indexOfStopCodon - startIndex)%3 != 0){
            System.out.println("   *** Error Stop not found or STOP_CODON found at index is not multiple of 3: " + indexOfStopCodon);
            return dna.length();
        }

        return (indexOfStopCodon); 
    }

    /**
     * printAllGenes 
     *
     * @param:
     *      String parameter dna, representing a string of DNA
     * @return void
     

    */
    public void printAllGenes (String dna) {
    String geneFound;
    int currentSearchIndex = 0;
        // while still more dna to look at
        while (currentSearchIndex < dna.length()){
            geneFound = findGene(dna, currentSearchIndex);
            System.out.println("   *** Gene found: " + geneFound);
            currentSearchIndex += geneFound.length();
            System.out.println("printAllGenes ****** starting at:" + currentSearchIndex);    

        }
    return;
    }   
    
    /*
     * testFindStopCodon  contains test cases with calls to methods
     *
     * @param  none
     * @return void
     */    
    public static void testFindGene(){
        String geneFound;
        int searchStartIndex = 0;
        String dnaTestCase;
        LocalTime time = LocalTime.now();

        // print out blank line and header for each iteration of this test
        System.out.println("  ");
        System.out.println("testFindGene ****** " + time);    

        // DNA test case for no ATG
        //             V  V  V  V  V  V  V  V  V  V  V  V  V  V
        dnaTestCase = "ATATATATATCTAAGCG";
        System.out.println("dnaTestCase = " + dnaTestCase);
        geneFound = findGene(dnaTestCase, searchStartIndex);
        System.out.println("   *** Gene found: " + geneFound);

        // DNA test case for no stop codon
        // print out blank line between test cases
        System.out.println("  ");
        //             V  V  V  V  V  V  V  V  V  V  V  V  V  V
        dnaTestCase = "ATGATATATATCTACAGCG";
        System.out.println("dnaTestCase = " + dnaTestCase);
        geneFound = findGene(dnaTestCase, searchStartIndex);
        System.out.println("   *** Gene found: " + geneFound);

        // DNA test case for no TAA
        dnaTestCase = "ATATGTATATCTACAGTGACGTAGTGA";
        System.out.println("dnaTestCase = " + dnaTestCase);
        geneFound = findGene(dnaTestCase, searchStartIndex);
        System.out.println("   *** Gene found: " + geneFound);

        // DNA test case 4 gene with multiple of 3
        dnaTestCase = "ATATGATATATCGGTAAGCG";
        System.out.println("dnaTestCase = " + dnaTestCase);
        geneFound = findGene(dnaTestCase, searchStartIndex);
        System.out.println("   *** Gene found: " + geneFound);

        // DNA test case 5 gene without multiple of 3
        dnaTestCase = "AATGTTAAATATATATCTAATGATAGGCG";
        System.out.println("dnaTestCase = " + dnaTestCase);
        geneFound = findGene(dnaTestCase, searchStartIndex);
        System.out.println("   *** Gene found: " + geneFound);

        // DNA test case 6 gene 
        dnaTestCase = "ATGGGTATGTAGTGATAAGTC";
        System.out.println("dnaTestCase = " + dnaTestCase);
        geneFound = findGene(dnaTestCase, searchStartIndex);
        System.out.println("   *** Gene found: " + geneFound);
    
        // DNA test case 7 gene in lower case
        dnaTestCase = "gatgctataat";
        System.out.println("dnaTestCase = " + dnaTestCase);
        geneFound = findGene(dnaTestCase, searchStartIndex);
        System.out.println("   *** Gene found: " + geneFound);
    }

    /*
     * testFindStopCodon  contains test cases with calls to methods
     *
     * @param  none
     * @return void
     */
    public static void testFindStopCodon(){
    int stopIndex;
    int searchStartIndex = 0;
    String dnaTestCase;
    LocalTime time = LocalTime.now();
    String STOP_CODON = "TAA";
    
    // print out blank line and header for each iteration of this test
    System.out.println("  ");
    System.out.println("testFindStopCodon ****** " + time);    
    
    // DNA test case for no ATG
    dnaTestCase = "ATATATATATCTAAGCG";
    System.out.println("dnaTestCase = " + dnaTestCase);
    stopIndex = findStopCodon(dnaTestCase, searchStartIndex, STOP_CODON);
    System.out.println("   *** Result of findStopCodon: " + stopIndex);
    
    // DNA test case for no TAA
    // print out blank line between test cases
    System.out.println("  ");
    dnaTestCase = "ATGATATATATCTACAGCG";
    System.out.println("dnaTestCase = " + dnaTestCase);
    stopIndex = findStopCodon(dnaTestCase, searchStartIndex, STOP_CODON);
    System.out.println("   *** Result of findStopCodon: " + stopIndex);
    
    // DNA test case for no ATG or TAA
    dnaTestCase = "ATATATATATCTACAGCG";
    System.out.println("dnaTestCase = " + dnaTestCase);
    stopIndex = findStopCodon(dnaTestCase, searchStartIndex, STOP_CODON);
    System.out.println("   *** Result of findStopCodon: " + stopIndex);
    
    // DNA test case 4 gene with multiple of 3
    dnaTestCase = "ATATGATATATCGGTAAGCG";
    System.out.println("dnaTestCase = " + dnaTestCase);
    stopIndex = findStopCodon(dnaTestCase, searchStartIndex, STOP_CODON);
    System.out.println("   *** Result of findStopCodon: " + stopIndex);
    
    // DNA test case 5 gene without multiple of 3
    dnaTestCase = "AATGTTAAATATATATCTAAGCG";
    System.out.println("dnaTestCase = " + dnaTestCase);
    stopIndex = findStopCodon(dnaTestCase, searchStartIndex, STOP_CODON);
    System.out.println("   *** Result of findStopCodon: " + stopIndex);
    
    // DNA test case 6 gene 
    dnaTestCase = "ATGGGTTAAGTC";
    System.out.println("dnaTestCase = " + dnaTestCase);
    stopIndex = findStopCodon(dnaTestCase, searchStartIndex, STOP_CODON);
    System.out.println("   *** Result of findStopCodon: " + stopIndex);
    
    // DNA test case 7 gene in lower case
    dnaTestCase = "gatgctataat";
    System.out.println("dnaTestCase = " + dnaTestCase);
    stopIndex = findStopCodon(dnaTestCase, searchStartIndex, STOP_CODON);
    System.out.println("   *** Result of findStopCodon: " + stopIndex);
    }
    
    public static void main () {
    LocalTime time = LocalTime.now();
    
    // print out blank line and header for each iteration of this test
    System.out.println("  ");
    System.out.println("main ****** " + time);
    //testFindStopCodon();
    //testFindGene();
    
    }

}
