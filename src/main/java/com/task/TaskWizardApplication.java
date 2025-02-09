package com.task;

import com.task.auth.BasicAuthenticator;
import com.task.auth.RoleAuthorizer;
import com.task.auth.UserPrincipal;
import com.task.core.AuthService;
import com.task.core.TaskService;
import com.task.db.AuthRepository;
import com.task.exceptions.UserNotFoundException;
import com.task.exceptions.handler.GlobalExceptionHandler;
import com.task.exceptions.handler.UserNotFoundHandler;
import com.task.resource.AuthResource;
import com.task.resource.TaskResource;
import com.task.core.model.Task;
import com.task.db.TaskRepository;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jdbi.v3.core.Jdbi;

public class TaskWizardApplication extends Application<trueConfiguration> {

    private final HibernateBundle<trueConfiguration> hibernate = new HibernateBundle<>(Task.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(trueConfiguration trueConfiguration) {
             return trueConfiguration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new TaskWizardApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap<trueConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final trueConfiguration configuration,
                    final Environment environment) {
        //configuration.runFlywayMigrations(); // Run Flyway migrations

        // Initialize JDBI
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "database");

        // Register
        TaskRepository taskRepository = jdbi.onDemand(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);
        AuthRepository authRepository =  jdbi.onDemand(AuthRepository.class);
        AuthService authService = new AuthService(authRepository);
        AuthResource authResource = new AuthResource(authRepository);
        environment.jersey().register(authResource);
        environment.jersey().register(new TaskResource(taskService));

        //exception Handlers
        environment.jersey().register(new UserNotFoundHandler());
        environment.jersey().register(new GlobalExceptionHandler());

        // Authorization Setup
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<UserPrincipal>()
                        .setAuthenticator(new BasicAuthenticator(authService))
                        .setAuthorizer(new RoleAuthorizer())
                        .setRealm("SUPER SECRET REALM")
                        .buildAuthFilter()));

        environment.jersey().register(RolesAllowedDynamicFeature.class); // Enable @RolesAllowed
    }


}
