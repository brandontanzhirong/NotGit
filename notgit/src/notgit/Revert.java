/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notgit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Brandon Tan Zhirong
 */
public class Revert {

    public static void revert(ArrayList fileName) {
         for (int i = 0; i < fileName.size(); i++) {
            try {
                File fileAddr = new File(Folder.branchRepository, fileName.get(i).toString());
                Scanner s = new Scanner(new FileInputStream(fileAddr));
                String[] temp = fileName.get(i).toString().split("_\\d+");
                StringBuilder oriFileName = new StringBuilder();
                oriFileName.append(temp[0]).append(temp[1]);
                File oriFileAddr = new File(Folder.directory, oriFileName.toString());
                PrintWriter pw = new PrintWriter(new FileOutputStream(oriFileAddr));
                while (s.hasNextLine()) {
                    pw.println(s.nextLine());
                }
                s.close();
                pw.close();
            } catch (FileNotFoundException ex) {
                System.out.printf("\"%s\" not found\n",fileName.get(i));
            }
        }
    }

}
