package com.resolvehub.ticket.infrastructure.persistence;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.resolvehub.ticket.domain.TicketCategory;
import com.resolvehub.ticket.domain.TicketPriority;
import com.resolvehub.ticket.domain.TicketStatus;
import com.resolvehub.user.infrastructure.persistence.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    private UUID id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private UserEntity createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to_user_id")
    private UserEntity assignedTo;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    protected TicketEntity() {
    }

    public TicketEntity(String title, String description, UserEntity createdBy) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.status = TicketStatus.OPEN;
        this.priority = TicketPriority.LOW;
        this.category = TicketCategory.GENERAL;
    }

    @PrePersist
    void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }

        if (status == null) {
            status = TicketStatus.OPEN;
        }

        if (priority == null) {
            priority = TicketPriority.LOW;
        }

        if (category == null) {
            category = TicketCategory.GENERAL;
        }

        OffsetDateTime now = OffsetDateTime.now();

        if (createdAt == null) {
            createdAt = now;
        }

        if (updatedAt == null) {
            updatedAt = now;
        }
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public TicketCategory getCategory() {
        return category;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public UserEntity getAssignedTo() {
        return assignedTo;
    }

    public Long getVersion() {
        return version;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void changeStatus(TicketStatus status) {
        this.status = status;
    }

    public void assignTo(UserEntity supportAgent) {
        this.assignedTo = supportAgent;
    }

    public void changePriority(TicketPriority priority) {
        this.priority = priority;
    }

    public void changeCategory(TicketCategory category) {
        this.category = category;
    }
    
    public void updateDetails(
            String title,
            String description,
            TicketPriority priority,
            TicketCategory category,
            TicketStatus status) {

        this.title = title;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.status = status;
    }
}