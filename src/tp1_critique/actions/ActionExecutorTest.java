package tp1_critique.actions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp1_critique.critiquable.Review;

import static org.junit.jupiter.api.Assertions.*;

class ActionExecutorTest {
    private String entropy = Long.toString(System.currentTimeMillis());
    private ActionExecutor actionExecutor;
    private Review currentReview;

    @BeforeEach
    void setUp() {
        actionExecutor = new ActionExecutor(new ViewAction());
        currentReview = TestUtil.randomReview();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenAnActionCodeAndAReview_whenRequestingToExecute_thenActionExecutorExecutesTheRightAction() {
        assertNotNull(actionExecutor.execute(ViewAction.VIEW, currentReview));
        assertTrue(actionExecutor.execute(ViewAction.VIEW, currentReview).contains(currentReview.getTitre()));
    }
}