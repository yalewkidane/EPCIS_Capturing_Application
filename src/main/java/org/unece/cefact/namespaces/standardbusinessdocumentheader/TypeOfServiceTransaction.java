//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.14 at 11:35:11 PM KST 
//


package org.unece.cefact.namespaces.standardbusinessdocumentheader;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TypeOfServiceTransaction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TypeOfServiceTransaction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RequestingServiceTransaction"/>
 *     &lt;enumeration value="RespondingServiceTransaction"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TypeOfServiceTransaction")
@XmlEnum
public enum TypeOfServiceTransaction {

    @XmlEnumValue("RequestingServiceTransaction")
    REQUESTING_SERVICE_TRANSACTION("RequestingServiceTransaction"),
    @XmlEnumValue("RespondingServiceTransaction")
    RESPONDING_SERVICE_TRANSACTION("RespondingServiceTransaction");
    private final String value;

    TypeOfServiceTransaction(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeOfServiceTransaction fromValue(String v) {
        for (TypeOfServiceTransaction c: TypeOfServiceTransaction.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
