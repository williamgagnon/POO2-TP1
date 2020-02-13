package tp1_critique.critiqueur;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InviteTest {
    private GuestEntity invite;
    private String entropy = Long.toString(System.currentTimeMillis());
    private String currentName = "Will" + entropy;

    @BeforeEach
    void setUp() {
        invite = new GuestEntity(currentName);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenANewInvitee_whenGettingName_ThenNameIsReturned() {
        assertEquals(currentName, invite.getNom());
    }
}