
package com.gestion.livre.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "createAuteur", namespace = "http://service.livre.gestion.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createAuteur", namespace = "http://service.livre.gestion.com/", propOrder = {
    "nom",
    "prenom",
    "domicile"
})
public class CreateAuteur {

    @XmlElement(name = "Nom", namespace = "")
    private String nom;
    @XmlElement(name = "Prenom", namespace = "")
    private String prenom;
    @XmlElement(name = "Domicile", namespace = "")
    private String domicile;

    /**
     * 
     * @return
     *     returns String
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * 
     * @param nom
     *     the value for the nom property
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * 
     * @param prenom
     *     the value for the prenom property
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getDomicile() {
        return this.domicile;
    }

    /**
     * 
     * @param domicile
     *     the value for the domicile property
     */
    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

}
