import java.util.*;

public class Catesian {
    public static void main(String[] args){
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
        System.out.println("WELCOME AUTOMATA DESIGN");
        ArrayList<String> stageCreate2 = new ArrayList<>(); // input of stage
        ArrayList<String> alphabetCreate2 = new ArrayList<>(); // input of alphabet
        ArrayList<String> acceptstageCreate2 = new ArrayList<>(); // input of accept stage
        
        System.out.println("CREATE YOUR DFA"); 
        System.out.println("STAGE DESIGN"); // when you input finish next one you input alphabet
        String stage2 = scan.next(); // EXAMPLE INPUT q0,q1,q2,1,2,A,B or else you want to design 
        String[] stageArray2 = stage2.split(",");
        for(int i=0;i<stageArray2.length;i++){
            stageCreate2.add(stageArray2[i]);
        }
        
        System.out.println("ALPHABET DESIGN"); 
        String alphabet2 = scan.next(); // EXAMPLE INPUT 0,1,2,a,b,c or else you want to design 
        String[] alphabetArray2 = stage2.split(",");
        for(int i=0;i<alphabetArray2.length;i++){
            alphabetCreate2.add(alphabetArray2[i]);
        }
        
        System.out.println("START STAGE DESIGN"); 
        String startstage2 = scan.next(); // EXAMPLE INPUT q0,q1 or else if input don't match with stage it will tell you input fail
        boolean checkstartstage2 = true;
        for(String dataSearch2 : stageArray2){
            if(dataSearch2.equals(startstage2)){
                System.out.println("Correct Input at "+dataSearch2+" will be start stage");
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
        String acceptstage2 = scan.next(); // EXAMPLE INPUT q0,q1 or else if input don't match with stage it will tell you input accept stage
        String[] acceptstageArray2 = acceptstage2.split(",");
        for(int i=0;i<acceptstageArray2.length;i++){
            acceptstageCreate.add(acceptstageArray2[i]);
        }
        System.out.println(stageCreate.size());
        System.out.println(stageCreate2.size());
        ArrayList<String> catesianCreate = new ArrayList<>();
        for(int i=0;i<stageCreate.size();i++){
            for(int j=0;j<stageCreate2.size();j++){
                String concatStage = stageCreate.get(i).toString()+","+stageCreate2.get(j).toString();
                System.out.println(stageCreate.get(i));
                catesianCreate.add(concatStage);
            }
        }
        for(String catesian : catesianCreate){
            System.out.println("("+catesian+")");
        }
        if(alphabetCreate.contains(alphabetCreate2)){
            System.out.println("SAME Alphabet");
        }
        else{
            System.out.println("NOT EqualAlphabet");
        }
    }
}
