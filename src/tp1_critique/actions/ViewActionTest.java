package tp1_critique.actions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp1_critique.critiquable.Review;

import static org.junit.jupiter.api.Assertions.*;

class ViewActionTest {
    private Review review;
    private ViewAction viewAction;

    @BeforeEach
    void setUp() {
        review = TestUtil.randomReview();
        viewAction = new ViewAction();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenAValidReview_whenExecutingTheAction_ThenTheReviewIsDisplayed() {
        assertTrue(viewAction.execute(review).contains(review.getTitre()));
    }
}