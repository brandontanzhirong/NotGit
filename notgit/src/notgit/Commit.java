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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brandon Tan Zhirong
 */
public class Commit {

    public static void commit(ArrayList fileName) {
        FileNames.latestCommitID++;
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < fileName.size(); i++) {
            try {
                File fileAddr = new File(Folder.directory, fileName.get(i).toString());
                Scanner s = new Scanner(new FileInputStream(fileAddr));
                StringBuilder newFileName = new StringBuilder();
                String[] str = fileName.get(i).toString().split("\\.");
                newFileName.append(str[0]);
                newFileName.append("_").append(FileNames.latestCommitID);
                newFileName.append(".").append(str[1]);
                File newFileAddr = new File(Folder.branchRepository, newFileName.toString());
                PrintWriter pw = new PrintWriter(new FileOutputStream(newFileAddr));
                while (s.hasNextLine()) {
                    pw.println(s.nextLine());
                }
                temp.add(newFileName.toString());
                if (Status.statusNew.get(fileName.get(i).toString())=="new") {            //added 22/5 after finalized (from here
                    for (long j = FileNames.latestCommitID -1 ; j > 0; j--) {             //to able to revert back to the first changes after added even the file is added after the first commit
                        FileNames.get(j).add(newFileName.toString());                     //until here)         
                    }
                }
                Status.statusNew.put(fileName.get(i).toString(), null);                   //modified 22/5 after finalized since previous one is redundant
                s.close();
                pw.close();
            } catch (FileNotFoundException ex) {
                System.out.printf("\"%s\" not found\n", fileName.get(i));
            }
        }
        FileNames.put(temp);

    }
}
