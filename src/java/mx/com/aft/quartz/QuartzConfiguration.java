package mx.com.aft.quartz;

import java.util.HashMap;
import java.util.Map;

import mx.com.aft.model.dao.EstadoAvanceDAO;
import mx.com.aft.model.dao.SubflujoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 *
 * @author Gabriel
 */
@Configuration
@ComponentScan
public class QuartzConfiguration {
    
    @Autowired
    private SubflujoDAO subflujoDAO;
    
    @Autowired
    private EstadoAvanceDAO estadoAvanceDAO;

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        Map<String, Object> map = new HashMap();
        map.put("subflujoDAO", this.subflujoDAO);
        map.put("estadoAvanceDAO", this.estadoAvanceDAO);
        
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(SubflujoTask.class);
        factory.setJobDataAsMap(map);
        factory.setGroup("mygroup");
        factory.setName("myjob");
        
        return factory;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(jobDetailFactoryBean().getObject());
        stFactory.setStartDelay(3000);
        stFactory.setName("mytrigger");
        stFactory.setGroup("mygroup");
        //stFactory.setCronExpression("0/30 * * * * ?"); // Cada 30 segundos
        stFactory.setCronExpression("0 0 0/1 * * ?"); // Cada hora
        
        return stFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(cronTriggerFactoryBean().getObject());
        
        return scheduler;
    }
    
}
