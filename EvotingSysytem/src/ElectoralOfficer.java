import exception.OfficerExistException;
import exception.ValidateIdException;

import java.util.ArrayList;
import java.util.List;
public class ElectoralOfficer extends User {
    private int regCount;
    private List<Voter> voters;
    private List<Election> elections;
    private boolean isCreated;


    private  ElectoralOfficer( ){
        super("username", "password", "name", "address");
        voters=new ArrayList<>();
        elections=new ArrayList<>();

    }

    private  static  ElectoralOfficer instance;

    public static ElectoralOfficer createOfficer(){
        if(instance==null){
            instance=new ElectoralOfficer();
        }
        return  instance;
    }

    public  void clear(){
        instance=null;
        isCreated=false;
    }


    public void SetFields(String name,String userName,String password,String address){
        if(isCreated){
            throw new OfficerExistException("YOU ALREADY HAVE A OFFICER");
        }
        this.setName(name);
        this.setAddress(address);
        this.setPassword(password);
        this.setUsername(userName);
        this.isCreated =true;
    }


    public Voter registerVoter(String username,String password,String name,String address ,int age){
        checkStatus();
        Voter voter=new Voter(username,password,name,address,age);
        voter.setVotersId(generateId());
        voters.add(voter);
        return voter;
    }



    public Voter findByVoterId(String id){
        int result = checkForVoter(id);
        return voters.get(result - 1);
    }

    public void registerCandidate(Election election ,String id){
        if(findByVoterId(id).getAge()<30) {
            throw new OfficerExistException("AGE SHOULD BE 30 AND ABOVE TO BE A CANDIDATE");
        }
        election.setCandidate(findByVoterId(id));
    }

    public Election createElection(String name){
        Election election =new Election(name);
        elections.add(election);
        return election;
    }

    public void startElection( Election election){
        if(election==null){
            throw new OfficerExistException("ELECTION WAS NOT CREATED");
        }
        election.startElection();
    }

    public String electionResult(Election election){
        return election.electionResult();
    }





    private int checkForVoter(String id){
       binarySearch(id);
         String[] result = id.split("-");
         int newId= Integer.parseInt(result[1]);
         if(newId<=0|newId>voters.size()){
             throw new ValidateIdException("INVALID ID");
         }
         if(!result[0].equals("VOT")){
             throw new ValidateIdException("INVALID ID");
         }
         return newId;
    }

    private void binarySearch(String id){
       for(int i =0;i<id.length();i++){
           if(id.charAt(i)== '-'){
              return;
           }
       }
       throw new ValidateIdException("INVALID ID");
    }

    private String generateId(){
        return "VOT-"+ ++regCount;
    }


}
