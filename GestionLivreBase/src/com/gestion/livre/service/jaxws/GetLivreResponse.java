
package com.gestion.livre.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getLivreResponse", namespace = "http://service.livre.gestion.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLivreResponse", namespace = "http://service.livre.gestion.com/")
public class GetLivreResponse {

    @XmlElement(name = "Livre", namespace = "")
    private com.gestion.livre.persistence.TLivre livre;

    /**
     * 
     * @return
     *     returns TLivre
     */
    public com.gestion.livre.persistence.TLivre getLivre() {
        return this.livre;
    }

    /**
     * 
     * @param livre
     *     the value for the livre property
     */
    public void setLivre(com.gestion.livre.persistence.TLivre livre) {
        this.livre = livre;
    }

}
