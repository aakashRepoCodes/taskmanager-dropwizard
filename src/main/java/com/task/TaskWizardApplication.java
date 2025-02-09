package com.task;

import com.task.core.Task;
import com.task.db.TaskDao;
import com.task.db.TaskRepository;
import com.task.resources.TaskResource;
import com.task.service.TaskService;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

public class TaskWizardApplication extends Application<trueConfiguration> {

    private final HibernateBundle<trueConfiguration> hibernate = new HibernateBundle<>(Task.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(trueConfiguration trueConfiguration) {
             return trueConfiguration.getDatabase();
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
        final TaskDao taskDao = new TaskDao(hibernate.getSessionFactory());
        TaskRepository taskRepository = new TaskRepository(hibernate.getSessionFactory());
        TaskService taskService = new TaskService(taskRepository);
        environment.jersey().register(new TaskResource(taskService));
    }

}
