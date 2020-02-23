package tp1_critique.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp1_critique.commandline.CLI;
import tp1_critique.commandline.CLIComponent;
import tp1_critique.review.SimpleReviewEntity;
import tp1_critique.TestUtil;
import tp1_critique.user.User;

import static org.junit.jupiter.api.Assertions.*;

class EraseActionTest {
    SimpleReviewEntity simpleReviewEntity;
    EraseAction eraseAction;
    User userEntity;
    CLI cliComponent;


    @BeforeEach
    void setUp() {
        simpleReviewEntity = TestUtil.randomReview();
        cliComponent = new CLIComponent();
        eraseAction = new EraseAction(cliComponent);
        userEntity = TestUtil.randomGuest();
    }

    @Test
    void getCode() {
    }

    @Test
    void execute() {
        assertEquals("o", eraseAction.execute(simpleReviewEntity, userEntity));
    }
}