package kr.ac.uos.ai;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Chan Yeon, Cho
 * @version 0.0.1 - SnapShot
 *          on 2016-05-31 enemy
 * @link http://ai.uos.ac.kr:9000/lovebube/UIMA_Management_Client
 */

public class JSONGetter {

    public JSONGetter() {
        start();
    }

    private void start() {
        try {
            File file = new File("F:/test.jpg");
            URL url = new URL("http://i.imgur.com/pV8kUBK.jpg");
//            FileOutputStream os = new FileOutputStream(file);
//            InputStream dl = url.openStream();
            FileUtils.copyURLToFile(url, file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
