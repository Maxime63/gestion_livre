
package com.gestion.livre.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAuteurResponse", namespace = "http://service.livre.gestion.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAuteurResponse", namespace = "http://service.livre.gestion.com/")
public class GetAuteurResponse {

    @XmlElement(name = "Auteur", namespace = "")
    private com.gestion.livre.persistence.TAuteur auteur;

    /**
     * 
     * @return
     *     returns TAuteur
     */
    public com.gestion.livre.persistence.TAuteur getAuteur() {
        return this.auteur;
    }

    /**
     * 
     * @param auteur
     *     the value for the auteur property
     */
    public void setAuteur(com.gestion.livre.persistence.TAuteur auteur) {
        this.auteur = auteur;
    }

}
