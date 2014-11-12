
package com.gestion.livre.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "updateLivre", namespace = "http://service.livre.gestion.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateLivre", namespace = "http://service.livre.gestion.com/", propOrder = {
    "arg0",
    "arg1",
    "arg2",
    "arg3"
})
public class UpdateLivre {

    @XmlElement(name = "arg0", namespace = "")
    private String arg0;
    @XmlElement(name = "arg1", namespace = "")
    private double arg1;
    @XmlElement(name = "arg2", namespace = "")
    private String arg2;
    @XmlElement(name = "arg3", namespace = "")
    private long arg3;

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns double
     */
    public double getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(double arg1) {
        this.arg1 = arg1;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg2() {
        return this.arg2;
    }

    /**
     * 
     * @param arg2
     *     the value for the arg2 property
     */
    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    /**
     * 
     * @return
     *     returns long
     */
    public long getArg3() {
        return this.arg3;
    }

    /**
     * 
     * @param arg3
     *     the value for the arg3 property
     */
    public void setArg3(long arg3) {
        this.arg3 = arg3;
    }

}
