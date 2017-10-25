package fi.muni.pa165.hauntedhouses.entity;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Mario Majernik, 422165
 *
 */
@Entity
public class Ghost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Column(nullable = false, unique = true)
    private String name;
    
    @NotNull
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date hauntFrom ;
    
    @NotNull
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date hauntTo;
    
    @NotNull
    private String description;
    
    @NotNull
    private String hauntReason;
    
    @NotNull
    private long idHouse;
    
    private Set<String> abilities = new HashSet<>();    //Then change String to ability
    
    //Constructors
    public Ghost() {
    }
    
    public Ghost(long id, String name, Date hauntFrom, Date hauntTo) {
        this.id = id;
        this.name = name;
        this.hauntFrom = hauntFrom;
        this.hauntTo = hauntTo;
    }
    
    public Ghost(long id, String name, Date hauntFrom, Date hauntTo, String description, String hauntReason,
            long idHouse, Set<String> abilities) {
        this.id = id;
        this.name = name;
        this.hauntFrom = hauntFrom;
        this.hauntTo = hauntTo;
        this.description = description;
        this.hauntReason = hauntReason;
        this.idHouse = idHouse;
        this.abilities.addAll(abilities);
    }
    
    //Getters and setters
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getHauntFrom() {
        return hauntFrom;
    }
    
    public void setHauntFrom(Date hauntFrom) {
        this.hauntFrom = hauntFrom;
    }
    
    public Date getHauntTo() {
        return hauntTo;
    }
    
    public void setHauntTo(Date hauntTo) {
        this.hauntTo = hauntTo;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getHauntReason() {
        return hauntReason;
    }
    
    public void setHauntReason(String hauntReason) {
        this.hauntReason = hauntReason;
    }
    
    public long getIdHouse() {
        return idHouse;
    }
    
    public void setIdHouse(long idHouse) {
        this.idHouse = idHouse;
    }
    
    public Set<String> getAbilities() {        //Then change String to ability
        return Collections.unmodifiableSet(abilities);
    }
    
    public void setAbilities(Set<String> abilities) {        //Then change String to ability
        this.abilities = abilities;
    }
    
    public void addAbility(String ability) {        //Then change String to ability
        this.abilities.add(ability);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((hauntFrom == null) ? 0 : hauntFrom.hashCode())
                     + ((hauntTo == null) ? 0 : hauntTo.hashCode())
                     + ((name == null) ? 0 : name.hashCode());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || !(obj instanceof Ghost)) {
            return false;
        }
        
        Ghost other = (Ghost) obj;
        
        if (getName() != null ? !getName().equals(other.getName()) : other.getName() != null) return false;
        if (getHauntFrom() != null ? !getHauntFrom().equals(other.getHauntFrom()) : other.getHauntFrom() != null) return false;
        return getHauntTo() != null ? getHauntTo().equals(other.getHauntTo()) : other.getHauntTo() == null;
    }
}
