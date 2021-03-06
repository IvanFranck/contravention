/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contravention;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author nzima-ivan
 */
public class Contraventions {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty matricule;
    private SimpleStringProperty dateDebut;    
    private SimpleStringProperty dateFin;
    private SimpleIntegerProperty montant;
    private SimpleStringProperty statut;

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public Contraventions(int id, String matricule, String dateDebut, String dateFin, Integer montant, String statut) {
        this.id = new SimpleIntegerProperty(id);
        this.matricule = new SimpleStringProperty(matricule);
        this.dateDebut = new SimpleStringProperty(dateDebut);
        this.dateFin = new SimpleStringProperty(dateFin);
        this.montant = new SimpleIntegerProperty(montant);
        this.statut = new SimpleStringProperty(statut);
    }

    public void setMatricule(String matricule) {
        this.matricule.set(matricule);
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut.set(dateDebut);
    }

    public void setDateFin(String dateFin) {
        this.dateFin.set(dateFin);
    }

    public void setMontant(Integer montant) {
        this.montant.set(montant);
    }

    public void setStatut(String statut) {
        this.statut.set(statut);
    }

    public Integer getId() {
        return id.get();
    }

    public String getMatricule() {
        return matricule.get();
    }

    public String getDateDebut() {
        return dateDebut.get();
    }

    public String getDateFin() {
        return dateFin.get();
    }


    public Integer getMontant() {
        return montant.get();
    }

    public String getStatut() {
        return statut.get();
    }


    
    
}
