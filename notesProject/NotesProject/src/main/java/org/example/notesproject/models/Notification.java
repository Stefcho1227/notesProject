package org.example.notesproject.models;

import jakarta.persistence.*;
import org.example.notesproject.enums.Channel;

import java.time.LocalDateTime;

/* Notification */
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reminder_id")
    private Reminder reminder;

    @Enumerated(EnumType.STRING)
    private Channel channel;

    @Column(name="sent_at")
    private LocalDateTime sentAt;
}

