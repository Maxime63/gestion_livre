
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

    @XmlElement(name = "return", namespace = "")
    private List<com.gestion.livre.persistence.TAuteur> _return;

    /**
     * 
     * @return
     *     returns List<TAuteur>
     */
    public List<com.gestion.livre.persistence.TAuteur> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.gestion.livre.persistence.TAuteur> _return) {
        this._return = _return;
    }

}
