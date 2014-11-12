
package com.gestion.livre.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "updateAuteurResponse", namespace = "http://service.livre.gestion.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateAuteurResponse", namespace = "http://service.livre.gestion.com/")
public class UpdateAuteurResponse {

    @XmlElement(name = "return", namespace = "")
    private com.gestion.livre.persistence.TAuteur _return;

    /**
     * 
     * @return
     *     returns TAuteur
     */
    public com.gestion.livre.persistence.TAuteur getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.gestion.livre.persistence.TAuteur _return) {
        this._return = _return;
    }

}
