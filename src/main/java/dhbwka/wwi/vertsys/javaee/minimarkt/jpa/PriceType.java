/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.minimarkt.jpa;

/**
 *
 * @author Manuel
 */
public enum PriceType {
    NONE, NEGOTIATION, FIX;

    public String getLabel() {
        switch (this) {
            case NONE:
                return "Keine Angabe";
            case NEGOTIATION:
                return "Verhandlungsbasis";
            case FIX:
                return "Festpreis";
            default:
                return this.toString();
        }
    }
    
}
