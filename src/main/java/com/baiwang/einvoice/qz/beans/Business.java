//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.29 at 12:39:11 PM CST 
//


package com.baiwang.einvoice.qz.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestcommonfpkj"
})
@XmlRootElement(name = "business")
public class Business {

    @XmlElement(name = "REQUEST_COMMON_FPKJ", required = true)
    protected RequestCommonFpkj requestcommonfpkj;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "comment")
    protected String comment;

    /**
     * Gets the value of the requestcommonfpkj property.
     * 
     * @return
     *     possible object is
     *     {@link Business.REQUESTCOMMONFPKJ }
     *     
     */
    public RequestCommonFpkj getREQUESTCOMMONFPKJ() {
        return requestcommonfpkj;
    }

    /**
     * Sets the value of the requestcommonfpkj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Business.REQUESTCOMMONFPKJ }
     *     
     */
    public void setREQUESTCOMMONFPKJ(RequestCommonFpkj value) {
        this.requestcommonfpkj = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }


}
