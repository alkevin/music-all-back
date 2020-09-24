package com.musicallcommunity.musicallback.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "profile", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class Profile {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    @Enumerated(value = EnumType.STRING)
    @NaturalId(mutable=true)
    private GenderChar gender;

    @Column
    private int age;

    @Column(name="level_id")
    private Long levelId;

    @OneToOne
    @JoinColumn(name = "level_id", insertable=false, updatable=false)
    private Level level;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profile_instrument",
            joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "instrument_id", referencedColumnName = "id"))
    private Set<Instrument> instruments;

    public Profile(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GenderChar getGender() {
        return gender;
    }

    public void setGender(GenderChar gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Level getLevel() { return level; }

    public void setLevel(Level level) { this.level = level; }

    public Set<Instrument> getInstruments() { return instruments; }

    public void setInstruments(Set<Instrument> instruments) { this.instruments = instruments; }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id)
                .append(", gender=").append(gender)
                .append(", age=").append(age)
                .append(", level_id=").append(levelId).append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile = (Profile) o;
        return getAge() == profile.getAge() &&
                Objects.equals(getId(), profile.getId()) &&
                getGender() == profile.getGender() &&
                Objects.equals(getLevelId(), profile.getLevelId()) &&
                Objects.equals(getLevel(), profile.getLevel()) &&
                Objects.equals(getInstruments(), profile.getInstruments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGender(), getAge(), getLevelId(), getLevel(), getInstruments());
    }
}
