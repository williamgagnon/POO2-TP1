package tp1_critique.user;

import org.junit.jupiter.api.BeforeEach;
import tp1_critique.TestUtil;
import tp1_critique.review.SimpleReviewEntity;

class GuestEntityTest {
    SimpleReviewEntity simpleReviewEntity;

    @BeforeEach
    void setUp() {
        simpleReviewEntity = TestUtil.randomReview();
    }

//    @Test
//    void givenAGuest_whenRatingAReview_GuestLikesAreModified() {
//        GuestEntity guest = new GuestEntity("Will");
//
//        guest.rateReview(review, TestUtil.randomGuest());
//        assertEquals(1, review.getGuestLikes());
//    }
}