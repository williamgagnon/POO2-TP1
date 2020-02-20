package tp1_critique.critiquable;

import tp1_critique.users.GuestEntity;

import java.util.ArrayList;

public class Review {
    private String title;
    private float valeurAccordee;
    private float valeurReelle;
    private long likes = 0;
    private long guestLikes = 0;
    private long dislikes = 0;
    private long guestDislikes = 0;
    private String author;
    private ArrayList<String> likers = new ArrayList<>(20);

    public Review(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void likeOrNot(String userType, boolean isLiking) {
        if (userType.equalsIgnoreCase(GuestEntity.USER_TYPE)) {
            if (isLiking) {
                guestLikes++;
            } else {
                guestDislikes++;
            }
        } else {
            if (isLiking) {
                likes++;
            } else {
                dislikes++;
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getGuestLikes() {
        return guestLikes;
    }

    public void demandeInfos() {
        System.out.println("Saisissez les informations demandées pour votre critique");
        //Choisir le type de critique qui doit être fait et instancier le bon objet


        //la critique doit être remplie en demandant toutes les informations nécessaires  en console



    }

    /**
     * Rempli la critique en demandant en console les informations nécessaires pour cette critique.
     */
    public void rempli() {
        System.out.println("Vos différentes sortes de critique devront demander les informations nécessaires !!!");
    }

    @Override
    public String toString() {
        return "Critique{" +
                "titre='" + title + '\'' +
                ", valeurAccordee=" + valeurAccordee +
                ", valeurReelle=" + valeurReelle +
                ", nombreDeLike=" + (likes + guestLikes) +
                ", nombreDeNotLike=" + (dislikes + guestDislikes) +
                ", auteur='" + author + '\'' +
                '}';
    }
}
