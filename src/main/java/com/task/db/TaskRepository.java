package com.task.db;

import com.task.core.Task;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Task.class) // Ensures JDBI maps result sets to Task objects
public interface TaskRepository {

    @SqlQuery("SELECT * FROM tasks")
    List<Task> findAll();

    @SqlQuery("SELECT * FROM tasks WHERE id = :id")
    Optional<Task> findById(@Bind("id") Long id);

    @SqlUpdate("INSERT INTO tasks (title, description) VALUES (:title, :description)")
    @GetGeneratedKeys
    Long insert(@BindBean Task task);

    @SqlUpdate("UPDATE tasks SET title = :title, description = :description WHERE id = :id")
    void update(@BindBean Task task);

    @SqlUpdate("DELETE FROM tasks WHERE id = :id")
    void delete(@Bind("id") Long id);

}
