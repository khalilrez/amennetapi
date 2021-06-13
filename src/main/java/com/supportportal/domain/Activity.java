package com.supportportal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Activitiy")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long ActivityId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "adminId", nullable = false)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    private String description;

    private String type;

    public Activity() {
    }

    public Activity(Long activityId, User user, Date createdAt, String description, String type) {
        ActivityId = activityId;
        this.user = user;
        this.createdAt = createdAt;
        this.description = description;
        this.type = type;
    }

    public Long getActivityId() {
        return ActivityId;
    }

    public void setActivityId(Long activityId) {
        ActivityId = activityId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
