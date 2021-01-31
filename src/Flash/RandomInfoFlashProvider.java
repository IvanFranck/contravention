/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flash;

import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author nzima-ivan
 */
public class RandomInfoFlashProvider {
    
    
    private static final float MIN_VITESSE = 60;
    private static final float MAX_VITESSE = 190;  
    private static final float LIM_VITESSE = 100;    
    private final float vitesse;
    private final boolean [] infractions;
    private final String matricule;
    
    Random random = new Random();
    
    
    public RandomInfoFlashProvider(){
        // on crée un tableau d'infractions de taille 5
        infractions = new boolean [5];
        
        // on prend un nombre aléatoire compris entre MIN et MAX
        vitesse = random.nextInt((int) (MAX_VITESSE - MIN_VITESSE + 1)) + MIN_VITESSE;
        
        // pour chaque types d'infraction on dit si le chauffeur est coupable (true) ou pas (false) 
        for (int i = 0; i < infractions.length; i++){
            infractions[i] = random.nextBoolean();
        }
        
        // on génére un matricule de manière aléatoire (voir classe RandomMtariculeProvider)
        RandomMatriculeProvider randomMat = new RandomMatriculeProvider();
        matricule = randomMat.getMatricule();
    }
    
    
    // constructeur paramétré: on passe la vitesse en paramètre
    public RandomInfoFlashProvider(float vitesse){
        this.vitesse = vitesse;
        
        // on crée un tableau d'infractions de taille 5
        infractions = new boolean[5];
        
        // pour chaque types d'infraction on dit si le chauffeur est coupable (true) ou pas (false)        
        for (int i = 0; i < infractions.length; i++){
            infractions[i] = random.nextBoolean();
        }
        
        // on génére un matricule de manière aléatoire (voir classe RandomMtariculeProvider)        
        RandomMatriculeProvider randomMat = new RandomMatriculeProvider();
        matricule = randomMat.getMatricule();
    }
    
    
    // determine s'il y a ewcès de vitesse

    /**
     *
     * @return boolean
     */
    public boolean excesVitesse(){
        boolean verdict = false;
        
        if (LIM_VITESSE < 100){
            
            // on vérifie si la vitesse en registrée est au dessus de la vitesse limite avec une marge de 5km en plus
            if(vitesse > LIM_VITESSE + 5){
                verdict = true;
            }
        }else {
            
            // on calcule la nouvelle marge (car la limite est au dessu de 100Km cette fois ci)
            float marge = (float) (LIM_VITESSE * 0.1);
            if (vitesse > LIM_VITESSE +  marge){
                verdict = true;
            }
        }
        
        // on retourne le verdict final
        return verdict;
    }
    
    /**
     *
     */
    public void afficher(){
        System.out.println("vitesse : " + vitesse + "\n");
        System.out.println("matricule : " + matricule);
        System.out.println("Exces de vitesse : "+this.excesVitesse()+"\n");
        for(int i=0; i<infractions.length; i++){
            System.out.println("infraction "+(i+1)+" : "+infractions[i]);
        }
    }
    
    /**
     *
     * @return JSONObject
     */
    public JSONObject sendJson(){
        
        // création d'un objet json
        JSONObject json = new JSONObject();
        
        // nouvelle ligne dans le json ( "matricule": matricule généré aléatoirement)
        json.put("matricule", matricule);
        
        // nouvelle ligen dans le json (vitesse)
        json.put("vitesse", vitesse);
        
        // création d'un nouvel onjet json (tableau d'infractions)
        JSONObject infractionsJson = new JSONObject();
        
        // ajout nouvelle ligne dans le json des infractions ( infraction exces de vitesse)
        infractionsJson.put(0, this.excesVitesse());
        
        // ajouts des autres ligne d'infractions  de facon aléatoire
        for(int i=0; i<this.infractions.length; i++){           
            infractionsJson.put(i+1,this.infractions[i]);
        }
        
        // insertion du tableau d'infractions dans le json global
        json.put("infractions", infractionsJson);        

        return json;
        
        /*
    
    {
        "matricule": CE 223 FX,
        "vitesse": 123,
        "infractions":[
            1:true,
            2:false,
            .
            .
            .
            n:true
        ]
    }
        
        */
    }
  
    
}
