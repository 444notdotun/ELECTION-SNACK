
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class VoterTest {
     Voter voter;
     ElectoralOfficer electoralOfficer;
     @Before
     public void Setup(){
         electoralOfficer = ElectoralOfficer.createOfficer();
         electoralOfficer.SetFields("ade","brown","1234","ogun");
     }

     @After
    public void tearUp(){
       electoralOfficer.clear();
    }

     @Test
    public void VoterCanLogin(){
         electoralOfficer.login("brown","1234");
         voter = electoralOfficer.registerVoter("nike","2345","niffy","yaba",19);
         voter.login("nike","2345");
         assertTrue(voter.getActiveStatus());
     }

     @Test
    public void voterCanEditProfile(){
         electoralOfficer.login("brown","1234");
         voter = electoralOfficer.registerVoter("nike","2345","niffy","yaba",67);
         voter.login("nike","2345");
         assertTrue(voter.getActiveStatus());
         voter.editProfile("name","dotun");
         assertEquals("dotun",voter.getName());

     }

    @Test
    public void VotersCanVote(){
        electoralOfficer.login("brown","1234");
        voter = electoralOfficer.registerVoter("nike","2345","niffy","yaba",67);
        voter.login("nike","2345");
        Voter voter1;
        voter1=electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",20);
        Election election= electoralOfficer.createElection("presidential");
        assertNotNull(election);
        election.setCandidate(voter);
        electoralOfficer.startElection(election);
        voter1.login("OLAMIDE","12345");
        voter1.vote(election,"12345",1);
        assertEquals(Integer.valueOf(1),election.getPoll().getFirst());

    }



}