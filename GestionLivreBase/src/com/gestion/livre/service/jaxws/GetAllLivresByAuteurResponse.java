
package com.gestion.livre.service.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAllLivresByAuteurResponse", namespace = "http://service.livre.gestion.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllLivresByAuteurResponse", namespace = "http://service.livre.gestion.com/")
public class GetAllLivresByAuteurResponse {

    @XmlElement(name = "Livre", namespace = "")
    private List<com.gestion.livre.persistence.TLivre> livre;

    /**
     * 
     * @return
     *     returns List<TLivre>
     */
    public List<com.gestion.livre.persistence.TLivre> getLivre() {
        return this.livre;
    }

    /**
     * 
     * @param livre
     *     the value for the livre property
     */
    public void setLivre(List<com.gestion.livre.persistence.TLivre> livre) {
        this.livre = livre;
    }

}
