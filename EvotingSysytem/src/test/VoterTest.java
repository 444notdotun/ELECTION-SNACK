
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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




}