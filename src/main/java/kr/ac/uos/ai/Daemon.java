package kr.ac.uos.ai;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.simpl.SimpleClassLoadHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chan Yeon, Cho
 * @version 0.0.1 - SnapShot
 *          on 2016-05-31 enemy
 */

public class Daemon {

    public Daemon(String[] args) {
        JSONGetter jsonGetter = new JSONGetter();
        jsonGetter.test();

        JobDetail job = new JobDetail();
        job.setName("JSON_update");
        job.setJobClass(JSONGetter.class);

        //configure the scheduler time
        CronTrigger trigger = new CronTrigger();
        trigger.setName("dummyTriggerName");


        //schedule it
        Scheduler scheduler = null;
        try {
            trigger.setCronExpression("0 0 22 * * ?");
//            trigger.setCronExpression("0/30 * * * * ?");
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if(args != null) {
            new Daemon(args);
        } else {
            System.out.println("java -jar StormDaemon.jar \"temporary JSON directory path\"");
            System.exit(0);
        }
    }
}
