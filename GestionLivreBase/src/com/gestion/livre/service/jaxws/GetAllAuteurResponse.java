
package com.gestion.livre.service.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAllAuteurResponse", namespace = "http://service.livre.gestion.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllAuteurResponse", namespace = "http://service.livre.gestion.com/")
public class GetAllAuteurResponse {

    @XmlElement(name = "Auteur", namespace = "")
    private List<com.gestion.livre.persistence.TAuteur> auteur;

    /**
     * 
     * @return
     *     returns List<TAuteur>
     */
    public List<com.gestion.livre.persistence.TAuteur> getAuteur() {
        return this.auteur;
    }

    /**
     * 
     * @param auteur
     *     the value for the auteur property
     */
    public void setAuteur(List<com.gestion.livre.persistence.TAuteur> auteur) {
        this.auteur = auteur;
    }

}
