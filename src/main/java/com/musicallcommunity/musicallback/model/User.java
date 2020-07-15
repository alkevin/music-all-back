package com.musicallcommunity.musicallback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.musicallcommunity.musicallback.payload.SignUpRequest;
import com.musicallcommunity.musicallback.dto.UserDto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mail", "id"})
})
public class User {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Email
    @Column(nullable = false)
    private String mail;

    @JsonIgnore
    @Column(length = 60, nullable = false)
    private String password;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @Column(updatable = false)
    private Date creationDate;

    @Column
    private Date modificationDate;

    @Column
    private boolean enabled;

    @Column
    private boolean connected;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider = AuthProvider.local;

    @Column
    private String providerId;

    public User(){
        super();
        this.enabled = false;
        this.connected = false;
    }

    public User(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.mail = userDto.getMail();
        this.creationDate = new Date();
        this.modificationDate = new Date();
        this.provider = userDto.getProvider();
        this.providerId = userDto.getProviderId();
    }

    public User(SignUpRequest signup) {
        this.firstName = signup.getFirstName();
        this.lastName = signup.getLastName();
        this.mail = signup.getMail();
        this.password = signup.getPassword();
        this.creationDate = new Date();
        this.modificationDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public Date getModificationDate() { return modificationDate; }

    public void setModificationDate(Date modificationDate) { this.modificationDate = modificationDate; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public boolean getConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
                .append(lastName).append(", mail=").append(mail).append(", password=").append(password)
                .append(", roles=").append(roles).append(", creationDate=").append(creationDate).append(", modificationDate=")
                .append(modificationDate).append(", enabled=").append(enabled).append(", provider=").append(provider)
                .append(", providerId=").append(providerId).append(", connected=").append(connected).append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((mail == null) ? 0 : mail.hashCode());
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
        final User user = (User) obj;
        if (!mail.equals(user.mail)) {
            return false;
        }
        return true;
    }
}