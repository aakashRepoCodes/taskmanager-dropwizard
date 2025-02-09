package com.task.db;

import com.task.core.Task;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

import com.task.core.Task;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Task.class) // Maps SQL result sets to Task objects
public interface TaskDao {

    @SqlQuery("SELECT * FROM tasks")
    List<Task> findAll();

    @SqlQuery("SELECT * FROM tasks WHERE id = :id")
    Optional<Task> findById(Long id);

    @SqlUpdate("INSERT INTO tasks (title, description, due_date) VALUES (:title, :description, :dueDate)")
    void insert(Task task);

    @SqlUpdate("DELETE FROM tasks WHERE id = :id")
    void delete(Long id);
}
