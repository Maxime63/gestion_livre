
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

    @XmlElement(name = "return", namespace = "")
    private com.gestion.livre.persistence.TLivre _return;

    /**
     * 
     * @return
     *     returns TLivre
     */
    public com.gestion.livre.persistence.TLivre getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.gestion.livre.persistence.TLivre _return) {
        this._return = _return;
    }

}
