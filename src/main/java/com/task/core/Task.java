package com.task.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "tasks")  // Explicit table mapping (recommended)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

  /*  @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;*/

  /*  @ManyToOne(fetch = FetchType.LAZY)  // Use LAZY fetching for better performance
    @JoinColumn(name = "user_id")       // Foreign key mapping
    @JsonIgnore
    private User user;*/
}
