package com.learn.shirologin.service;


import com.learn.shirologin.model.Controller;

import java.io.File;
import java.io.IOException;

public class ControllerService {
public boolean release(Controller con){
	String action=String.valueOf(con.getStatus());
	action=action+".txt";
	 ProcessBuilder pb = new ProcessBuilder("./" + action);
	 System.out.println(action);
     pb.directory(new File("/home/holytan/Desktop"));
     int runningStatus = 0;
     String s = null;
     try {
         Process p = pb.start();
         try {
             runningStatus = p.waitFor();
         } catch (InterruptedException e) {
         }

     } catch (IOException e) {
     }
     if (runningStatus != 0) {
     }
     return true;
 }
}
	

