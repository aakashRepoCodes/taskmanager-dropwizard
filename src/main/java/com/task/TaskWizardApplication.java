package com.task;

import com.task.core.Task;
import com.task.db.TaskRepository;
import com.task.resources.TaskResource;
import com.task.service.TaskService;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi3.JdbiFactory;
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

        // Register TaskDao
        TaskRepository taskRepository = jdbi.onDemand(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);
        environment.jersey().register(new TaskResource(taskService));
    }

}
