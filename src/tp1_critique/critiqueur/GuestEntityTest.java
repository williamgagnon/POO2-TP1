package tp1_critique.critiqueur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp1_critique.actions.TestUtil;
import tp1_critique.actions.ViewAction;
import tp1_critique.critiquable.Review;

import static org.junit.jupiter.api.Assertions.*;

class GuestEntityTest {
    Review review;

    @BeforeEach
    void setUp() {
        review = TestUtil.randomReview();
    }

//    @Test
//    void givenAGuest_whenRatingAReview_GuestLikesAreModified() {
//        GuestEntity guest = new GuestEntity("Will");
//
//        guest.rateReview(review, TestUtil.randomGuest());
//        assertEquals(1, review.getGuestLikes());
//    }
}