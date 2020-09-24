package com.musicallcommunity.musicallback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.musicallcommunity.musicallback.payload.SignUpRequest;
import com.musicallcommunity.musicallback.dto.UserDto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mail", "id"})
})
public class User {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email
    @Column(nullable = false)
    private String mail;

    @JsonIgnore
    @Column(length = 60, nullable = false)
    private String password;

    @Column(name="profile_id")
    private Long profileId;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @Column(name = "creation_date", updatable = false)
    private Date creationDate;

    @Column(name = "modification_date")
    private Date modificationDate;

    @Column
    private boolean enabled;

    @Column
    private boolean connected;

    /*@JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER ,cascade = {CascadeType.ALL})
    @JoinTable(name = "user_friend",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", insertable=false, updatable=false),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id", insertable=false, updatable=false))
    private Set<User> friends;

    @JsonBackReference
    @ManyToMany(mappedBy = "friends")
    private Set<User> users;*/

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Conversation> conversations;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Message> messages;

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private Set<UserFriend> userFriends = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "profile_id", insertable=false, updatable=false)
    private Profile profile;


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
    }

    public User(SignUpRequest signup) {
        this.firstName = signup.getFirstName();
        this.lastName = signup.getLastName();
        this.mail = signup.getMail();
        this.password = signup.getPassword();
        this.creationDate = new Date();
        this.modificationDate = new Date();
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getMail() { return mail; }

    public void setMail(String mail) { this.mail = mail; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Long getProfileId() { return profileId; }

    public void setProfileId(Long profileId) { this.profileId = profileId; }

    public Collection<Role> getRoles() { return roles; }

    public void setRoles(Collection<Role> roles) { this.roles = roles; }

    public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public Date getModificationDate() { return modificationDate; }

    public void setModificationDate(Date modificationDate) { this.modificationDate = modificationDate; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public boolean getConnected() { return connected; }

    public void setConnected(boolean connected) { this.connected = connected; }

    public List<Conversation> getConversations() { return conversations; }

    public void setConversations(List<Conversation> conversations) { this.conversations = conversations; }

    public List<Message> getMessages() { return messages; }

    public void setMessages(List<Message> messages) { this.messages = messages; }

    /*public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }*/

    public Set<UserFriend> getUserFriends() { return userFriends; }

    public void setUserFriends(Set<UserFriend> userFriends) { this.userFriends = userFriends; }

    public Profile getProfile() { return profile; }

    public void setProfile(Profile profile) { this.profile = profile; }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id)
                .append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", mail=").append(mail)
                .append(", password=").append(password)
                .append(", profile_id=").append(profileId)
                .append(", creation_date=").append(creationDate)
                .append(", modificationDate=").append(modificationDate)
                .append(", enabled=").append(enabled)
                .append(", connected=").append(connected)
                .append("]");
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