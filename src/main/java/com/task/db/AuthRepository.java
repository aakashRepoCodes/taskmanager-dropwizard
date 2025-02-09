package com.task.db;

import com.task.core.model.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;

@RegisterBeanMapper(User.class)
public interface AuthRepository {

    @SqlUpdate("INSERT INTO users (email, password) VALUES (:email, :password)")
    @GetGeneratedKeys
    Long insertUser(@BindBean User user);

    @SqlQuery("SELECT * from users where email = :email")
    Optional<User> findEmail(@Bind("email") String email);

}
