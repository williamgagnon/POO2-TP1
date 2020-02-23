package tp1_critique.action;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp1_critique.TestUtil;
import tp1_critique.review.SimpleReviewEntity;

import static org.junit.jupiter.api.Assertions.*;

class ActionExecutorTest {
    private String entropy = Long.toString(System.currentTimeMillis());
    private ActionExecutor actionExecutor;
    private SimpleReviewEntity currentSimpleReviewEntity;

    @BeforeEach
    void setUp() {
        actionExecutor = new ActionExecutor(new ViewAction());
        currentSimpleReviewEntity = TestUtil.randomReview();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenAnActionCodeAndAReview_whenRequestingToExecute_thenActionExecutorExecutesTheRightAction() {
        assertNotNull(actionExecutor.execute(ViewAction.VIEW, currentSimpleReviewEntity, TestUtil.randomGuest()));
        assertTrue(actionExecutor.execute(ViewAction.VIEW, currentSimpleReviewEntity, TestUtil.randomGuest()).contains(currentSimpleReviewEntity.getTitle()));
    }
}