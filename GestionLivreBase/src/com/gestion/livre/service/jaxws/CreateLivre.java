
package com.gestion.livre.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "createLivre", namespace = "http://service.livre.gestion.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createLivre", namespace = "http://service.livre.gestion.com/", propOrder = {
    "titre",
    "prix",
    "description"
})
public class CreateLivre {

    @XmlElement(name = "Titre", namespace = "")
    private String titre;
    @XmlElement(name = "Prix", namespace = "")
    private double prix;
    @XmlElement(name = "Description", namespace = "")
    private String description;

    /**
     * 
     * @return
     *     returns String
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * 
     * @param titre
     *     the value for the titre property
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * 
     * @return
     *     returns double
     */
    public double getPrix() {
        return this.prix;
    }

    /**
     * 
     * @param prix
     *     the value for the prix property
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 
     * @param description
     *     the value for the description property
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
