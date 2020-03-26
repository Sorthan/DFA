package AutomataDFA;

import java.util.*;

public class Automata {
    public static void main(String[] args){
        /*
        Scanner scan = new Scanner(System.in);
        System.out.println("WELCOME AUTOMATA DESIGN");
        ArrayList<String> stageCreate = new ArrayList<>(); // input of stage
        ArrayList<String> alphabetCreate = new ArrayList<>(); // input of alphabet
        ArrayList<String> acceptstageCreate = new ArrayList<>(); // input of accept stage
        
        System.out.println("CREATE YOUR DFA"); 
        System.out.println("STAGE DESIGN"); // when you input finish next one you input alphabet
        String stage = scan.next(); // EXAMPLE INPUT q0,q1,q2,1,2,A,B or else you want to design 
        String[] stageArray = stage.split(",");
        for(int i=0;i<stageArray.length;i++){
            stageCreate.add(stageArray[i]);
        }
        
        System.out.println("ALPHABET DESIGN"); 
        String alphabet = scan.next(); // EXAMPLE INPUT 0,1,2,a,b,c or else you want to design 
        String[] alphabetArray = stage.split(",");
        for(int i=0;i<alphabetArray.length;i++){
            alphabetCreate.add(alphabetArray[i]);
        }
        
        System.out.println("START STAGE DESIGN"); 
        String startstage = scan.next(); // EXAMPLE INPUT q0,q1 or else if input don't match with stage it will tell you input fail
        boolean checkstartstage = true;
        for(String dataSearch : stageArray){
            if(dataSearch.equals(startstage)){
                System.out.println("Correct Input at "+dataSearch+" will be start stage");
                checkstartstage = true;
                break;
            }
            else{
                checkstartstage = false;
            }
        }
        if(!checkstartstage){
            System.out.println("Your input was fail");
        }
        
        System.out.println("ACCEPT STAGE DESIGN"); 
        String acceptstage = scan.next(); // EXAMPLE INPUT q0,q1 or else if input don't match with stage it will tell you input accept stage
        String[] acceptstageArray = acceptstage.split(",");
        for(int i=0;i<acceptstageArray.length;i++){
            acceptstageCreate.add(acceptstageArray[i]);
        }
        //DFAAutomata dfadesign = new DFAAutomata(stageCreate, alphabetCreate, startstage, acceptstageCreate);
        //dfadesign.showDFADetail();
        */
    }
}
