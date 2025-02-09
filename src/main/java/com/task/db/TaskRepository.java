package com.task.db;

import com.mysql.cj.xdevapi.AbstractDataResult;
import com.task.core.Task;
import io.dropwizard.hibernate.AbstractDAO;
import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class TaskRepository extends AbstractDAO<Task> {

    @Inject
    public TaskRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Task> findAll() {
        return list((Query<Task>) namedQuery("Task.findAll"));
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Task save(Task task) {
        return persist(task);
    }

    public void delete(Task task) {
        currentSession().delete(task);
    }
}
