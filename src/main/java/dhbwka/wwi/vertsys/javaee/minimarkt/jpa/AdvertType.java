
package dhbwka.wwi.vertsys.javaee.minimarkt.jpa;


public enum AdvertType {

    NONE, OFFER, SEARCH;

    public String getLabel() {
        switch (this) {
            case NONE:
                return "Keine Angabe";
            case OFFER:
                return "Biete an";
            case SEARCH:
                return "Suche";
            default:
                return this.toString();
        }
    }
}
