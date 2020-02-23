package tp1_critique.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp1_critique.TestUtil;
import tp1_critique.review.SimpleReviewEntity;

import static org.junit.jupiter.api.Assertions.*;

class ViewActionTest {
    private SimpleReviewEntity simpleReviewEntity;
    private ViewAction viewAction;

    @BeforeEach
    void setUp() {
        simpleReviewEntity = TestUtil.randomReview();
        viewAction = new ViewAction();
    }

    @Test
    void givenAValidReview_whenExecutingTheAction_ThenTheReviewIsDisplayed() {
        assertTrue(viewAction.execute(simpleReviewEntity, TestUtil.randomGuest()).contains(simpleReviewEntity.getTitle()));
    }
}