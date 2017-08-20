package org.hongjie.transformer.socle;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class Main {

    /**
     * Entry point of application
     * @param args
     */
    public static void main(String...args){

        if (args.length<3){
            System.out.println("$0 /path/to/csv XML|JSON /path/to/destination");
        }else{
            File csv = new File(args[0].trim());
            if (!csv.isFile()) {
                System.out.println( csv.getAbsolutePath() + " is not a file.");
            } else {
                String type = args[1].trim();
                File destination = new File(args[2].trim());
                try {
                    Files.createParentDirs(destination);
                    new Worker().process(csv, type, destination);
                } catch (IOException e) {
                    System.out.println("Can't create dir for destination: " + destination.getAbsolutePath());
                }
            }
        }
    }

}
