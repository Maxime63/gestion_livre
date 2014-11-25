
package com.gestion.livre.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAllLivresByAuteur", namespace = "http://service.livre.gestion.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllLivresByAuteur", namespace = "http://service.livre.gestion.com/")
public class GetAllLivresByAuteur {

    @XmlElement(name = "AuteurId", namespace = "")
    private long auteurId;

    /**
     * 
     * @return
     *     returns long
     */
    public long getAuteurId() {
        return this.auteurId;
    }

    /**
     * 
     * @param auteurId
     *     the value for the auteurId property
     */
    public void setAuteurId(long auteurId) {
        this.auteurId = auteurId;
    }

}
