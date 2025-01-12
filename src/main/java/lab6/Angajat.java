package lab6;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.swing.plaf.SeparatorUI;
import java.time.LocalDate;
/**
 * Clasa Angajat, ce cuprinde evidenta angajatilor unei firme
 * @author Vidrean Alexandra-Maria
 * @version 1
 * @since 2024
 */
public class Angajat {

    String nume;
    String post;
    @JsonFormat
    LocalDate data_angajarii;
    float salariu;
    /**
     * Constructor fara parametrii
     */
    public Angajat(){}


    /**
     * Constructorul clasei Angajat cu parametrii
     * @param nume - reprezinta numele angajatilor firmei
     * @param post - reprezinta locul pe care il ocupa un angajat in cadrul firmei
     * @param data_angajarii - reprezinta data in care acesta s a angajat
     * @param salariu - reprezinta salariul pe care il primeste fiecare angajat
     */
    public Angajat(String nume,String post,LocalDate data_angajarii,float salariu)
    {
        super();
        this.nume=nume;
        this.post=post;
        this.data_angajarii=data_angajarii;
        this.salariu=salariu;
    }
    /**
     * Getter care da acces de citire a variabilei membre care contine numele angajatului
     * @return numele
     */
    public String getNume()
    {
        return  nume;
    }
    /**
     * Seteaza numele angajatului
     * @param nume
     */
    public void setNume(String nume)
    {
        this.nume=nume;
    }
    /**
     * Getter care da acces de citire a variabilei membre care contine postul pe care il ocupa angajatul
     * @return postul
     */
    public String getPost()
    {
        return post;
    }
    /**
     * Getter care da acces de citire a variabilei membre care contine salariul pe care angajatul il primeste
     * @return salariu
     */
    public float getSalariu()
    {
        return salariu;
    }
    /**
     * Getter care da acces de citire a variabilei membre care contine data in care a fost angajat in cadrul firmei
     * @return data angajarii
     */
    public LocalDate getData_angajarii()

    {
        return  data_angajarii;
    }
    /**
     * Redefinirea metodei toString() din clasa Object, metodă care va fi utilizată pentru afișare
     * @return datele angajatilor din cadrul firmei, separate prin virgula
     */

    @Override
    public String toString() {
return "nume:" + nume + " post:" + post + " data_angajarii:" + data_angajarii + " salariu:" + salariu;
    }
    /**
     * Compara salariul unui angajat
     * @return
     */

    public int compareTo(Angajat b) {
        if(this.salariu<=b.salariu)
            return 1;
        return -1;
    }
    /**
     * Se apeleaza prin referinta atunci cand obiectul din clasa Optional, dupa prelucrarea cu Stream API, NU este gol
     */
    public void existaIon(){
        System.out.println("Firma are cel putin un Ion angajat");

    }
    /**
     * Se apeleaza prin referinta atunci cand obiectul din clasa Optional, dupa prelucrarea cu Stream API, ESTE gol
     */
    public static void NuexistaIon(){
        System.out.println("Firma nu are nici un Ion angajat");
    }
    /**
     * Returneaza numele angajatilor scrise cu majuscule
     * @return
     */
    public String numetoUpperCase() {

        return this.nume.toUpperCase();
    }
}
