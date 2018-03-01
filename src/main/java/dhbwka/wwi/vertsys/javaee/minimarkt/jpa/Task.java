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

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Eine zu erledigende Aufgabe.
 */
@Entity
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "task_ids")
    @TableGenerator(name = "task_ids", initialValue = 0, allocationSize = 50)
    private long id;

    @ManyToOne
    @NotNull(message = "Die Anzeige muss einem Benutzer geordnet werden.")
    private User owner;

    @ManyToOne
    private Category category;

    @Column(length = 50)
    @NotNull(message = "Der Titel darf nicht leer sein.")
    @Size(min = 1, max = 50, message = "Der Titel muss zwischen ein und 50 Zeichen lang sein.")
    private String shortText;

    @Lob
    @NotNull
    private String longText;
    
    @NotNull(message = "Der Preis darf nicht leer sein.")
    @Pattern (regexp = "[0-9]*", message = "Der Preis muss numerisch sein.")
    private String price;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private AdvertType adtype = AdvertType.NONE;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private PriceType pricetype = PriceType.NONE;

    @NotNull(message = "Das Datum darf nicht leer sein.")
    private Date dueDate;

    @NotNull(message = "Die Uhrzeit darf nicht leer sein.")
    private Time dueTime;
    
    
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Task() {
    }

    public Task(User owner, Category category, AdvertType adtype, String shortText, String longText, String price, PriceType pricetype, Date dueDate, Time dueTime) {
        this.owner = owner;
        this.category = category;
        this.adtype = adtype;
        this.shortText = shortText;
        this.longText = longText;
        this.price = price;
        this.pricetype = pricetype;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Time getDueTime() {
        return dueTime;
    }

    public void setDueTime(Time dueTime) {
        this.dueTime = dueTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public AdvertType getAdtype() {
        return adtype;
    }

    public void setAdtype(AdvertType adtype) {
        this.adtype = adtype;
    }

    public PriceType getPricetype() {
        return pricetype;
    }

    public void setPricetype(PriceType pricetype) {
        this.pricetype = pricetype;
    }
    
    

}
