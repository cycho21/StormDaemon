package kr.ac.uos.ai;

import org.apache.commons.io.FileUtils;

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
        URL url = new URL("http://i.imgur.com/pV8kUBK.jpg");
        FileUtils.copyURLToFile("http://i.imgur.com/pV8kUBK.jpg", "f:/abc.jpg");
    }


}
