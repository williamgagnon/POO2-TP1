package tp1_critique.critiqueur;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {
    private GuestEntity guest;
    private String entropy = Long.toString(System.currentTimeMillis());
    private String currentName = "Will" + entropy;

    @BeforeEach
    void setUp() {
        guest = new GuestEntity(currentName);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenANewInvitee_whenGettingName_ThenNameIsReturned() {
        assertEquals(currentName, guest.getName());
    }
}