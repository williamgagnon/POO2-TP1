package tp1_critique.critiquable;

import tp1_critique.critiqueur.GuestEntity;

import java.util.ArrayList;

/**
 * Classe qui représente une critique. Ce sustème accorde une valeur
 * aux différents éléments plutôt qu'une note.
 */
public class Review {
    private String titre;
    private float valeurAccordee;
    private float valeurReelle;
    private long nombreDeLike = 0;
    private long nombreDeLikeInvites = 0;
    private long nombreDeNotLike = 0;
    private long nombreDeNotLikeInvites = 0;
    private String auteur;
    private ArrayList<String> likers = new ArrayList<>(20);


    public Review(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    /**
     * Permet d'apprécier une critique
     *
     * @param nom      Le nom de la personne qui fait l'appréciation.
     *                 Le mot "Invite" est réservé pour indiqué tout utilisateur invité
     * @param isLiking vrai si la critique a été appréciée, faux sinon.
     */
    public void likeOrNot(String nom, boolean isLiking) {
        if (nom.equalsIgnoreCase(GuestEntity.MENTION_INVITE)) {
            if (isLiking) {
                nombreDeLikeInvites++;
            } else {
                nombreDeNotLikeInvites++;
            }
        } else {
            if (isLiking) {
                nombreDeLike++;
            } else {
                nombreDeNotLike++;
            }
        }
    }


    public String getTitle() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
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
        System.out.println("Vos différentes sortes de critique devront deamnder les informations nécessaires !!!");
    }

    @Override
    public String toString() {
        return "Critique{" +
                "titre='" + titre + '\'' +
                ", valeurAccordee=" + valeurAccordee +
                ", valeurReelle=" + valeurReelle +
                ", nombreDeLike=" + (nombreDeLike + nombreDeLikeInvites) +
                ", nombreDeNotLike=" + (nombreDeNotLike + nombreDeNotLikeInvites) +
                ", auteur='" + auteur + '\'' +
                '}';
    }
}
