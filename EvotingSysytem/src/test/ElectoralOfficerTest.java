import exception.OfficerExistException;
import exception.ValidateAgeException;
import exception.ValidateIdException;
import exception.ValidateStatusException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElectoralOfficerTest {
    ElectoralOfficer electoralOfficer;
    Voter voter1;
    Voter voter2;
    @Before
    public void setup(){
      electoralOfficer=  ElectoralOfficer.createOfficer();
    }

    @After
    public void teardown(){
    electoralOfficer.clear();
    }

    @Test
    public void OfficerCanBeCreated(){
        assertNotNull(electoralOfficer);
    }

    @Test
    public void OfficerDetailsCanBeSet(){
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        assertEquals("Brown",electoralOfficer.getUsername());
    }

    @Test
    public void OfficerCanOnlyBeCreatedOnce(){
        electoralOfficer = ElectoralOfficer.createOfficer();
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        ElectoralOfficer officer = ElectoralOfficer.createOfficer();
        assertThrows(OfficerExistException.class,()-> officer.SetFields("adewole","brown","233444","ogun"));
    }

    @Test
    public void officerCanNOtWhenNotLoginRegisterAVoter(){
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        assertEquals("Brown",electoralOfficer.getUsername());
       assertThrows(ValidateStatusException.class,()->electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",18));
    }

    @Test
    public void OfficerCanLoginAndRegisterVoter(){
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        assertEquals("Brown",electoralOfficer.getUsername());
        electoralOfficer.login("Brown","1234");
        voter1=electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",19);
        assertNotNull(voter1);
        assertEquals("VOT-1",voter1.getVotersId());
    }


    @Test
    public void OfficerCanLoginAndRegisterVoterAndLogout(){
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        assertEquals("Brown",electoralOfficer.getUsername());
        electoralOfficer.login("Brown","1234");
        voter1=electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",20);
        assertNotNull(voter1);
        assertEquals("VOT-1",voter1.getVotersId());
        electoralOfficer.logout();
        assertFalse(electoralOfficer.getActiveStatus());
    }

    @Test
    public void canOnlyRegisterLegalAge(){
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        assertEquals("Brown",electoralOfficer.getUsername());
        electoralOfficer.login("Brown","1234");
        assertThrows(ValidateAgeException.class,()->electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",16));
    }

    @Test
    public void VotersCanBeFoundByVotersId(){
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        assertEquals("Brown",electoralOfficer.getUsername());
        electoralOfficer.login("Brown","1234");
        voter1=electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",20);
        assertNotNull(voter1);
        assertEquals("VOT-1",voter1.getVotersId());
        voter2=electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",20);
        assertNotNull(voter2);
        assertEquals(voter2,electoralOfficer.findByVoterId("VOT-2"));
    }

    @Test
    public void VotersCanNotBeFoundWithWrongAddress(){
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        assertEquals("Brown",electoralOfficer.getUsername());
        electoralOfficer.login("Brown","1234");
        voter1=electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",20);
        assertNotNull(voter1);
        assertEquals("VOT-1",voter1.getVotersId());
        voter2=electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",20);
        assertNotNull(voter2);
        assertThrows(ValidateIdException.class,()->electoralOfficer.findByVoterId("VOT-3"));
        assertThrows(ValidateIdException.class,()->electoralOfficer.findByVoterId("VOg-2"));
        assertThrows(ValidateIdException.class,()->electoralOfficer.findByVoterId("VOT2"));
    }

    @Test
    public void OfficerCanCreateElection(){
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        assertEquals("Brown",electoralOfficer.getUsername());
        electoralOfficer.login("Brown","1234");
        voter1=electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",20);
       Election election= electoralOfficer.createElection("presidential");
        assertNotNull(election);
    }

    @Test
    public void  officerCanAddCandidateTothePoll(){
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        assertEquals("Brown",electoralOfficer.getUsername());
        electoralOfficer.login("Brown","1234");
        voter1=electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",31);
        Election election= electoralOfficer.createElection("presidential");
        assertNotNull(election);
        electoralOfficer.registerCandidate(voter1.getVotersId());

    }

    @Test
    public void officerCanNotRegisterCandidateBelowAge30(){
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        assertEquals("Brown",electoralOfficer.getUsername());
        electoralOfficer.login("Brown","1234");
        voter1=electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",20);
        Election election= electoralOfficer.createElection("presidential");
        assertNotNull(election);
        assertThrows(OfficerExistException.class,()->electoralOfficer.registerCandidate(voter1.getVotersId()));
    }

    @Test
    public void officerCanStartElection(){
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        assertEquals("Brown",electoralOfficer.getUsername());
        electoralOfficer.login("Brown","1234");
        voter1=electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",20);
        Election election= electoralOfficer.createElection("presidential");
        assertNotNull(election);
        electoralOfficer.startElection();
        assertNotNull(election.getStartDate());
    }


    @Test
    public void officerCanNotStartElectionWithoutCreatingElection(){
        electoralOfficer.SetFields("adewole","Brown","1234","sabo");
        assertEquals("Brown",electoralOfficer.getUsername());
        electoralOfficer.login("Brown","1234");
        voter1=electoralOfficer.registerVoter("OLAMIDE ","12345","mide","yaba",20);
        assertThrows(OfficerExistException.class,()->electoralOfficer.startElection());
    }





}