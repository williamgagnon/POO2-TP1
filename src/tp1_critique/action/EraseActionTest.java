package tp1_critique.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp1_critique.critiquable.Review;
import tp1_critique.TestUtil;
import tp1_critique.users.User;

import static org.junit.jupiter.api.Assertions.*;

class EraseActionTest {
    Review review;
    EraseAction eraseAction;
    User userEntity;


    @BeforeEach
    void setUp() {
        review = TestUtil.randomReview();
        eraseAction = new EraseAction();
        userEntity = TestUtil.randomGuest();
    }

    @Test
    void getCode() {
    }

    @Test
    void execute() {
        assertEquals("o", eraseAction.execute(review, userEntity));
    }
}