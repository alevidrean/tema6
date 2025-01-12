package lab6;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Arrays.parallelSetAll;
import static java.util.Arrays.stream;

/**
 * Clasa principala
 * @author Vidrean Alexandra-Maria
 * @version 1
 * @since 2024
 */
public class Ex6 {
    /**
     * Metoda statica care va salva datele despre angajatii firmei in fisierul "angajati.json"
     * @param lista
     */
    public static void scriere(List<Angajat> lista) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            File file=new File("src/main/resources/angajati.json");
            mapper.writeValue(file,lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda statica care va incarca lista angajatilor din fisierul "angajati.json" in program
     * @return
     */
    public static List<Angajat> citire() {
        try {
            File file=new File("src/main/resources/angajati.json");
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            List<Angajat> angajati = mapper.readValue(file, new TypeReference<List<Angajat>>(){});
            return angajati;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        int opt;
        List<Angajat> angajati=new ArrayList<>();
        angajati=citire();

        Scanner scanner=new Scanner(System.in);
        do{
            System.out.println("0.exit");
            System.out.println("1.Afisarea angajatilor folosind referinte la metode");
            System.out.println("2.Afisarea ang care au salariul peste 2500 ron");
            System.out.println("3.Crearea unei liste cu angajații din luna aprilie, a anului trecut, care au funcție de conducere!");
            System.out.println("4.Afișarea angajaților care nu au funcție de conducere!");
            System.out.println("5.Extragerea din lista de angajați a unei liste de String-uri care conține numele angajaților scrise cu majuscule!");
            System.out.println("6.Afișarea salariilor mai mici de 3000 de RON!");
            System.out.println("7.Afișarea datelor primului angajat al firmei!");
            System.out.println("8.Afișarea de statistici referitoare la salariul angajaților!");
            System.out.println("9.Afișarea unor mesaje care indică dacă printre angajați există cel puțin un “Ion”!");
            System.out.println("10.Afișarea numărului de persoane care s-au angajat în vara anului precedent!");
            System.out.println("Introduceti optiunea:");
            opt=scanner.nextInt();
            switch (opt)
            {
                case 0:System.exit(0);
                    break;
                case 1: /**
                 * Afișarea listei de angajați folosind referințe la metode.
                 */
                    angajati.forEach(System.out::println);
                    break;
                case 2:
                    /**
                     * Afisarea angajatilor care au salariul peste 2500 ron, utilizand stream uri, interfata functionala Predicate, implementata printr o expresie Lambda
                     */
                    System.out.println("Angajati cu salariu mai mare de 2500 ron:");
                    angajati
                            .stream()
                            .filter((a)->a.getSalariu()>2500)
                            .forEach(System.out::println);
                    break;
                case 3: /**
                 * Afisarea angajatilor cu functia de conducere sef sau director, din luna aprilie, a anului trecut, utilizand stream uri, expresii Lambda, operatia terminala fiind collect()
                 */
                    List<Angajat>angajap= angajati
                                .stream()
                                .filter((a)->{
                                    boolean data=a.getData_angajarii().getMonth().toString().compareToIgnoreCase("April")==0 && a.getData_angajarii().getYear()==(LocalDate.now().getYear()-1);
                                    boolean functie=a.getPost().equals("sef") || a.getPost().equals("director");
return functie && data;
                                })
                        .collect(Collectors.toList());
                    System.out.println("Angajatii cu functia de conducere din luna aprilie a anului trecut");
                    angajap.forEach(System.out::println);
                    break;
                case 4:                    /**
                 * Afisarea angajatilor care nu sunt sefi sau directori, in ordine descrescatoare a salariilor, folosind stream uri si expresii Lambda
                 */

                    angajati
                            .stream()
                            .filter((a)->!(a.getPost().equals("sef") || a.getPost().equals("director")))
                            .sorted((a,b)-> a.compareTo(b))
                            .forEach(System.out::println);

                    break;
                case 5:                    /**
                 * Afisarea listei de string uri care contine numele angajatilor scrise cu majuscule, utilizand stream uri, metoda map() si operatia terminala collect()
                 */

                    List<String> ang=angajati
                    .stream()
                            .map(Angajat::numetoUpperCase)
                            .collect(Collectors.toList());
                    ang.forEach(System.out::println);

                    break;
                case 6:  /**
                 * Afișarea salariilor mai mici de 3000 de ron, folosind stream-uri, expresii lambda, referințe la metode şi metoda map()
                 */
                    angajati
                        .stream()
                        .filter((a)->a.getSalariu()<3000)
                        .map(Angajat::getSalariu)
                        .forEach(System.out::println);

                    break;
                case 7:   /**
                 * Afișarea datelor primului angajat al firmei
                 */
                    angajati
                            .stream()
                            .min(Comparator.comparing(Angajat::getData_angajarii))
                            .ifPresent(System.out::println);
                    break;
                case 8:  /**
                 * Afișarea de statistici referitoare la salariul angajaților, afișand salariul mediu, salariul minim şi salariul maxim, utilizand stream-uri şi operația terminală collect()
                 */
                    System.out.println("\nStatistici referitoare la salariul angajatilor:");
                    DoubleSummaryStatistics statistics = angajati.stream().collect(Collectors.summarizingDouble(Angajat::getSalariu));
                    System.out.println("Salariul mediu: " + statistics.getAverage());
                    System.out.println("Salariul minim: " + statistics.getMin());
                    System.out.println("Salariul maxim: " + statistics.getMax());
                    break;
                case 9:  /**
                 * Afișarea unor mesaje care indică dacă printre angajați există cel puțin un “Ion”
                 */
                    angajati
                            .stream()
                            .filter((a)->a.getNume().equalsIgnoreCase("Ion"))
                            .findAny()
                            .ifPresent(Angajat::existaIon);
                    break;
                case 10:   /**
                 * Afișarea numărului de persoane care s-au angajat în vara anului precedent, utilizand metoda count() din interfaţa Stream
                 */
                    int angaj=(int)angajati
                            .stream()
                            .filter((a)->(a.getData_angajarii().getYear()==LocalDate.now().getYear()-1 &&
                          (a.getData_angajarii().getMonth().toString().compareToIgnoreCase("June")==0 ||
                                    a.getData_angajarii().getMonth().toString().compareToIgnoreCase("July")==0 ||
                                    a.getData_angajarii().getMonth().toString().compareToIgnoreCase("August")==0)))
                            .count();
                    System.out.println(angaj);
                    break;
                default:
                    break;
            }
        }while(true);
    }
}
