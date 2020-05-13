package com.musicallcommunity.musicallback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    @Column(nullable = false)
    @NotNull
    @Enumerated(value = EnumType.STRING)
    @NaturalId
    private RoleName name;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Collection<User> getUsers() { return users; }

    public void setUsers(Collection<User> users) { this.users = users; }

    public Collection<Privilege> getPrivileges() { return privileges; }

    public void setPrivileges(Collection<Privilege> privileges) { this.privileges = privileges; }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Roles [id=").append(id).append("]").append("[name=").append(name).append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role role = (Role) obj;
        if (!name.equals(role.name)) {
            return false;
        }
        return true;
    }
}
