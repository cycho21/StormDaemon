package kr.ac.uos.ai;

import org.apache.commons.io.FileUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.GZIPInputStream;

/**
 * @author Chan Yeon, Cho
 * @version 0.0.1 - SnapShot
 *          on 2016-05-31 enemy
 */

public class JSONGetter implements Job {

    private String date;

    public JSONGetter() {
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("JSON Getter Started...");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String date = dateFormat.format(cal.getTime());

        System.out.println(date);

        String urlString = "http://data.githubarchive.org/" + date + "-15.json.gz";

        try {
            File file = new File(date+ "-15.json.gz");
            URL url = new URL(urlString);
            FileUtils.copyURLToFile(url, file);
            gzUnpack();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test() {
        System.out.println("JSON Getter Started...");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        date = dateFormat.format(cal.getTime());

        System.out.println(date);

        String urlString = "http://data.githubarchive.org/" + date + "-15.json.gz";

        try {
            File file = new File(date + "-15.json.gz");
            URL url = new URL(urlString);
            FileUtils.copyURLToFile(url, file);
            gzUnpack();
            } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gzUnpack() {

        byte[] buffer = new byte[1024];
        try {
            GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(date + "-15.json.gz"));
            FileOutputStream out = new FileOutputStream(date + "-15.json");

            int len;

            while((len = gzis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
            }

            gzis.close();
            out.close();

            System.out.println("unpacking done");

            System.out.println("analysis start");

            CustomJSONParser customJSONParser = new CustomJSONParser();
            customJSONParser.parse(date + "-15.json");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
