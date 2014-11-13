
package com.gestion.livre.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "deleteLivreResponse", namespace = "http://service.livre.gestion.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteLivreResponse", namespace = "http://service.livre.gestion.com/")
public class DeleteLivreResponse {

    @XmlElement(name = "IsDeleted", namespace = "")
    private boolean isDeleted;

    /**
     * 
     * @return
     *     returns boolean
     */
    public boolean isIsDeleted() {
        return this.isDeleted;
    }

    /**
     * 
     * @param isDeleted
     *     the value for the isDeleted property
     */
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
