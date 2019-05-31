/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notgit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author Brandon Tan Zhirong
 */
public class FileNames implements Serializable { // To store all the commited FileNames

    private static final long serialVersionUID = 1L;
    static LinkedHashMap<Long, ArrayList> fileNames = Branch.getBranchLHM();    //Key = commitID Value = ArrayList of commited FileNames 
    //Branch.getBranchLHM() is to get the LHM according to the current Branch
    static long latestCommitID = Branch.getLatestCommitID(Branch.currentBranch); //To obtain the latest Commit ID according to the current Branch  

    public static LinkedHashMap<Long, ArrayList> getFileNames() {
        return fileNames;
    }

    public static void setFileNames(LinkedHashMap<Long, ArrayList> fileNames) {
        FileNames.fileNames = fileNames;
    }

    public static long getLatestCommitID() {
        return latestCommitID;
    }

    public static void setLatestCommitID(long latestCommitID) {
        FileNames.latestCommitID = latestCommitID;
    }

    public static void put(ArrayList temp) {
        fileNames.put(latestCommitID, temp);
    }

    public static ArrayList get(Long commitID) {
        return fileNames.get(commitID);
    }

    public static void updateFileNames() {
        FileNames.fileNames = Branch.getBranchLHM();
    }

}
