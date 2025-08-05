package com.ichin23.salbum.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ichin23.salbum.domain.ratings.Ratings;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Table(name="users")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created_at;

    private String image_url;
    private String last_fm;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int followers_count;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int following_count;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToMany
    @JoinTable(
            name="user_following",
            joinColumns = @JoinColumn(name="user_id", columnDefinition = "uuid"), //ID de quem vai seguir
            inverseJoinColumns = @JoinColumn(name = "followed_user_id", columnDefinition = "uuid")
    )
    private Set<User> following = new HashSet<>();

    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ratings> ratings;

    public User(){}

    public User(String name, String username, String email, String password){
        this.name=name;
        this.username=username;
        this.email=email;
        this.password=password;
        this.role= UserRole.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role==UserRole.USER) return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLast_fm() {
        return last_fm;
    }

    public void setLast_fm(String last_fm) {
        this.last_fm = last_fm;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers) {
        this.followers_count = followers;
    }

    public int getFollowing_count(){
        return following_count;
    }

    public void setFollowing_count(int following){
        this.following_count = following;
    }

    public boolean addFollower(User user){
        this.following.add(user);
        return user.followers.add(this);
    }

    public boolean removeFollower(User user){
        this.following.remove(user);
        return user.followers.remove(this);
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Ratings> ratings) {
        this.ratings = ratings;
    }
}
