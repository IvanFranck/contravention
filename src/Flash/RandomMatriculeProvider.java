/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flash;

import java.util.Random;

/**
 *
 * @author nzima-ivan
 */
public class RandomMatriculeProvider {
 
    private final String matricule;
    private static final String [] SYS_IMMATRICULATION = { "AD", "CE", "EN", "ES", "LT", "NO", "NW", "OU", "SU", "SW" };
    private static final char [] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    
    public RandomMatriculeProvider(){
        
        
        Random random = new Random();
        
        // On prend une région de facon aléatoire dans le SYSTEME D'IMMATRICULATION NATIONAL
        int i = random.nextInt(SYS_IMMATRICULATION.length - 1);
        String region = SYS_IMMATRICULATION[i];
        
        // On choisi par la suite un nombre compris entre 0 et 999
        int num = random.nextInt(999);
        // on le met sous le format "XXX"
        String part2 = String.format("%03d", num);
        
        
        // enfin on choisi aléatoirement deux lettres de l'alphabet francais
        String [] tab = new String[2];
        for(int j=0 ; j<tab.length ; j++){
            int n = random.nextInt(ALPHABET.length - 1);
            tab[j] = Character.toString(ALPHABET[n]);
        }
        // on relie les duex lettre en une seule chaine de caractères
        String part3 = tab[0] + tab[1];
        
        // pour finir on join les trois parties (region, part2 et part3), en les séparant d'un expace , pour obtenir notre numéro d'immatriculation
        matricule = String.join(" ", region, part2, part3);
     }
    
    
    // renvoie le numéro d'immatriculation
    public String getMatricule(){
        return matricule;
    }
    
}
