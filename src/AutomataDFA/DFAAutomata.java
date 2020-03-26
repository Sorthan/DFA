package AutomataDFA;

import java.util.ArrayList;
import java.util.Iterator;


public class DFAAutomata {
    public String automataName;
    public ArrayList<String> stage = new ArrayList<>();
    public ArrayList<String> alphabet = new ArrayList<>();
    public String startStage;
    public ArrayList<String> acceptStage = new ArrayList<>();
    public String stageall = "";
    public String alphabetall = "";
    public String acceptstageall = "";

    public DFAAutomata() {
    }
    
    public DFAAutomata(String name, ArrayList<String> stage, ArrayList<String> alphabet) {
        this.automataName = name;
        this.stage = stage;
        this.alphabet = alphabet;
        this.startStage = "";
        this.acceptStage = null;
    }
    
    public DFAAutomata(String name, ArrayList<String> stage, ArrayList<String> alphabet, String startStage, ArrayList<String> acceptStage) {
        this.automataName = name;
        this.stage = stage;
        this.alphabet = alphabet;
        this.startStage = startStage;
        this.acceptStage = acceptStage;
        Iterator<String> itr = stage.iterator();
        while(itr.hasNext()){
            String stageItr = itr.next();
            if(!itr.hasNext()){
                stageall += stageItr;
            }
            else{
                stageall += stageItr+",";
            }
        }
        Iterator<String> itrAlphabet = alphabet.iterator();
        while(itrAlphabet.hasNext()){
            String alphabetItr = itrAlphabet.next();
            if(!itrAlphabet.hasNext()){
                alphabetall += alphabetItr;
            }
            else{
                alphabetall += alphabetItr+",";
            }
        }
        Iterator<String> itrAccept = acceptStage.iterator();
        while(itrAccept.hasNext()){
            String acceptItr = itrAccept.next();
            if(!itrAccept.hasNext()){
                acceptstageall += acceptItr;
            }
            else{
                acceptstageall += acceptItr+",";
            }
        }
        
    }

    public String getStartStage() {
        return startStage;
    }

    public void setStartStage(String startStage) {
        this.startStage = startStage;
    }

    

    public ArrayList<String> getAcceptStage() {
        return acceptStage;
    }

    public void setAcceptStage(ArrayList<String> acceptStage) {
        this.acceptStage = acceptStage;
    }
    
    
    
    public void showDFADetail(){
        System.out.print("STAGE :");
        for(String stages : stage){
            stageall += stages+",";
            System.out.print(stages+" ");
        }
        System.out.print("\nALPHABET :");
        for(String alphabets : alphabet){
            alphabetall += alphabets+",";
            System.out.print(alphabets+" ");
        }
        System.out.print("\nSTART STAGE :"+startStage);
        System.out.print("\nACCEPT STAGE :");
        for(String acceptstage : acceptStage){
            acceptstageall += acceptstage+",";
            System.out.print(acceptstage+" ");
        }
        
    }
    
    public String showAllData(){
        return ("NAME :"+automataName+"\nSTAGE :"+stageall+"\nALPHABET :"+alphabetall+"\nSTART STAGE :"+startStage+"\nACCEPT STAGE :"+acceptstageall);
    }
}
