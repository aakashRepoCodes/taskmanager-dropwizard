package com.task.db;

import com.task.core.model.User;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface AuthRepository {

    @SqlUpdate("INSERT INTO users (email, password) VALUES (:email, :password)")
    @GetGeneratedKeys
    Long insertUser(@BindBean User user);

}
