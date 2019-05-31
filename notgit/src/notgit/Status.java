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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author Brandon Tan Zhirong
 */
public class Status implements Serializable{
private static final long serialVersionUID = 1L;
    static ArrayList<String> trackedFileNames = new ArrayList<>();              //To store the Tracked Files
    static LinkedHashMap<String, String> statusNew = new LinkedHashMap<>();     //To check whether the tracked file is new or not
    public static ArrayList<String> getTrackedFileNames() {
        return trackedFileNames;
    }

    public static void setTrackedFileNames(ArrayList<String> trackedFileNames) {
        Status.trackedFileNames = trackedFileNames;
    }

    public static LinkedHashMap<String, String> getStatusNew() {
        return statusNew;
    }

    public static void setStatusNew(LinkedHashMap<String, String> statusNew) {
        Status.statusNew = statusNew;
    }

    public static void status() {
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> directoryFileNames = Folder.getInitialDirectoryFileNames();
        ArrayList<String> newDirectoryFileNames = Folder.getDirectoryFileNames();
        System.out.println("new files:");                                       //To check the newfiles who just inserted to the folder
        boolean overallNewFiles = false;
        for (int i = 1; i < newDirectoryFileNames.size(); i++) {
            boolean newFile = true;
            for (int j = 1; j < directoryFileNames.size(); j++) {
                if (newDirectoryFileNames.get(i).equals(directoryFileNames.get(j))) {
                    newFile = false;
                    break;
                }
            }
            if (newFile) {
                System.out.println(newDirectoryFileNames.get(i));
                overallNewFiles = true;
            }
        }
        if (!overallNewFiles) {
            System.out.println("No new file is added.");
        }
        System.out.println("deleted files:");                                   // to check the untracked and tracked files which just got deleted
        boolean overallDeletedFiles = false;
        for (int i = 1; i < directoryFileNames.size(); i++) {
            boolean deletedFile = true;
            for (int j = 1; j < newDirectoryFileNames.size(); j++) {
                if (directoryFileNames.get(i).equals(newDirectoryFileNames.get(j))) {
                    deletedFile = false;
                    break;
                }
            }
            if (deletedFile) {
                System.out.println(directoryFileNames.remove(i));
                overallDeletedFiles = true;
            }
        }
        if (!overallDeletedFiles) {
            System.out.println("No file is deleted.");
        }
        for (int i = 0; i < trackedFileNames.size(); i++) {
            temp.add(trackedFileNames.get(i));
        }
        System.out.println("tracked files:");
        if (trackedFileNames.isEmpty()) {
            System.out.println("No file is being tracked.");
        }
        for (int i = 0; i < directoryFileNames.size(); i++) {
            for (int j = 0; j < temp.size(); j++) {                             //temp = trackedFileNames
                if (directoryFileNames.get(i).equals(temp.get(j))) {
                    directoryFileNames.remove(i);
                    String fileName = temp.remove(j);
                    i--;
                    System.out.print(fileName);
                    if (statusNew.get(fileName) != null) {
                        System.out.println(" (new)");
                        break;
                    }
                    if (!modified(fileName)) {
                        System.out.println("");
                    } else {
                        System.out.println(" (modified)");
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            System.out.println(temp.get(i) + " (deleted)");
        }
        System.out.println("untracked files:");
        for (int i = 1; i < directoryFileNames.size(); i++) {                   // int i = 1 because it is to exclude the .not-git folder
            System.out.println(directoryFileNames.get(i));
        }
        if (directoryFileNames.size() == 1) {
            System.out.println("No untracked file.");
        }
    }

    public static boolean modified(String fileName) {
        Scanner s1 = null;
        Scanner s2 = null;
        try {
            File oriFileAddr = new File(Folder.directory, fileName);
            s1 = new Scanner(new FileInputStream(oriFileAddr));
            Object[] listOfCommitID = Branch.getListOfCommitID();                                           //added 22/5 since finalized
            Long latestCommitID = Long.parseLong(listOfCommitID[listOfCommitID.length-1].toString());       //added 22/5 since finalized
            ArrayList<String> commitedFiles = FileNames.get(latestCommitID);                                //latestCommitID = last in the list of Commit ID
            String commitFileName = null;
            for (int i = 0; i < commitedFiles.size(); i++) {
                String[] arrCommitedFile = commitedFiles.get(i).split("_\\d+");
                String[] arrFile = fileName.split("\\.");
                if (arrCommitedFile[0].equals(arrFile[0])) {
                    commitFileName = commitedFiles.get(i);
                    break;
                }
            }
            if (!(commitFileName == null)) {
                File commitedFileAddr = new File(Folder.branchRepository, commitFileName);
                s2 = new Scanner(new FileInputStream(commitedFileAddr));
                while (s1.hasNextLine() && s2.hasNextLine()) {
                    if (!s1.nextLine().equals(s2.nextLine())) {
                        return true;
                    }
                }
                if (s1.hasNextLine() || s2.hasNextLine()) {
                    return true;
                }
                return false;
            }
        } catch (FileNotFoundException ex) {
            System.out.printf("\"%s\" not found\n", fileName);
        } finally {
            s1.close();
            s2.close();
        }
        return false;
    }

}
