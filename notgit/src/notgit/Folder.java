/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notgit;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Brandon Tan Zhirong
 */
public class Folder implements Serializable{   //All the folders and directory needed for not-git operation
private static final long serialVersionUID = 1L;
    static String directory = "C:\\Users\\Brandon Tan\\Desktop\\New folder";
    static File repository = new File(Folder.directory, ".not-git");
    static File branchRepository = new File(Folder.repository, Branch.currentBranch);
    private static String[] directoryContents = new File(Folder.directory).list();    //To check the current files in the directory
    private static ArrayList<String> directoryFileNames = new ArrayList<>(Arrays.asList(directoryContents));

    public static void setBranchRepository(File branchRepository) {
        Folder.branchRepository = branchRepository;
    }

    public static String getDirectory() {
        return directory;
    }

    public static void setDirectory(String directory) {
        Folder.directory = directory;
    }

    public static File getRepository() {
        return repository;
    }

    public static void setRepository(File repository) {
        Folder.repository = repository;
    }

    public static String[] getDirectoryContents() {
        return directoryContents;
    }

    public static void setDirectoryContents(String[] directoryContents) {
        Folder.directoryContents = directoryContents;
    }

    public static ArrayList<String> getInitialDirectoryFileNames() {
        return directoryFileNames;
    }
    public static ArrayList<String> getDirectoryFileNames() {
        String[] directoryContents = new File(Folder.directory).list();
        ArrayList<String> directoryFileNames = new ArrayList<>(Arrays.asList(directoryContents));
        return directoryFileNames;
    }

    public static void setDirectoryFileNames(ArrayList<String> directoryFileNames) {
        Folder.directoryFileNames = directoryFileNames;
    }

    public static File getBranchRepository() {
        return branchRepository;
    }

    public static void setBranchRepository(String currentBranch) {
        Folder.branchRepository = new File(Folder.repository, currentBranch);
    }
    
    
}
