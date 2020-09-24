package com.musicallcommunity.musicallback.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "level")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Enumerated(value = EnumType.STRING)
    @NaturalId
    private LevelName name;

    public Level() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LevelName getName() {
        return name;
    }

    public void setName(LevelName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Level [id=").append(id)
                .append("[name=").append(name).append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Level)) return false;
        Level level = (Level) o;
        return Objects.equals(getId(), level.getId()) &&
                getName() == level.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
