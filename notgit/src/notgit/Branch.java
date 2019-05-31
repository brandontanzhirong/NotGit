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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Brandon Tan Zhirong
 */
public class Branch implements Serializable{
private static final long serialVersionUID = 1L;
    private static LinkedHashMap<String, LinkedHashMap> BRANCH = new LinkedHashMap<>();
    //Key = branchName Value = LinkedHashMap of fileNames (key = commitID value = commited fileNames)
    private static LinkedHashMap<String, Long> BRANCH_COMMIT_ID = new LinkedHashMap<>();
    //Key = branchName Value = latestCommitID of that particular branch
    private static LinkedHashMap<String, LinkedHashMap> BRANCH_COMMIT_DETAILS = new LinkedHashMap<>();
    //Key = branchName Value = LinkedHashMap of CommitDetails (Key = commitID Value = commitDetails)
    //for "log" command purpose
    static String currentBranch = "master";

    public static void addMasterBranch() {
        Folder.branchRepository.mkdir();
        LinkedHashMap<Long, ArrayList> branch = new LinkedHashMap<>();
        LinkedHashMap<Long, ArrayList> commitDetails = new LinkedHashMap<>();
        BRANCH.put("master", branch);
        BRANCH_COMMIT_ID.put("master", 0L);
        BRANCH_COMMIT_DETAILS.put("master", commitDetails);
    }
    public static String getCurrentBranch() {
        return currentBranch;
    }

    public static void setCurrentBranch(String currentBranch) {
        Branch.currentBranch = currentBranch;
    }

    public static void setBRANCH(LinkedHashMap<String, LinkedHashMap> BRANCH) {
        Branch.BRANCH = BRANCH;
    }

    public static void setBRANCH_COMMIT_ID(LinkedHashMap<String, Long> BRANCH_COMMIT_ID) {
        Branch.BRANCH_COMMIT_ID = BRANCH_COMMIT_ID;
    }

    public static void setBRANCH_COMMIT_DETAILS(LinkedHashMap<String, LinkedHashMap> BRANCH_COMMIT_DETAILS) {
        Branch.BRANCH_COMMIT_DETAILS = BRANCH_COMMIT_DETAILS;
    }

    public static LinkedHashMap<String, LinkedHashMap> getBRANCH() {
        return BRANCH;
    }

    public static LinkedHashMap<String, Long> getBRANCH_COMMIT_ID() {
        return BRANCH_COMMIT_ID;
    }
    public static Long getBRANCH_COMMIT_ID(String branch) {
        return BRANCH_COMMIT_ID.get(branch);
    }

    public static LinkedHashMap<String, LinkedHashMap> getBRANCH_COMMIT_DETAILS() {
        return BRANCH_COMMIT_DETAILS;
    }

    public static LinkedHashMap getBranchLHM() {        //LHM = LinkedHashMap
        return BRANCH.get(currentBranch);
    }

    public static ArrayList<String> getLatestCommitFiles() {
        Object a = getBranchLHM().get(getLatestCommitID(currentBranch));
        ArrayList<String> b = (ArrayList<String>) a;
        return b;
    }

    public static void addBranch(String branchName, Long commitID) {
        File branchRepository = new File(Folder.repository, branchName);
        branchRepository.mkdir();   //create a repository file
        LinkedHashMap<Long, ArrayList> branch = new LinkedHashMap<>();
        branch.put(commitID + 1000, FileNames.get(commitID));       //the commitID +1000 is to avoid clash of same commitID later on
        LinkedHashMap<Long, ArrayList> commitDetails = new LinkedHashMap<>();
        BRANCH.put(branchName, branch);
        BRANCH_COMMIT_ID.put(branchName, 0L);
        BRANCH_COMMIT_DETAILS.put(branchName, commitDetails);
    }

    public static Long getLatestCommitID(String branchName) {
        return BRANCH_COMMIT_ID.get(branchName);
    }

    public static Object[] getListOfCommitID() {
        return getBranchLHM().keySet().toArray();
    }

    public static void displayBranchList() {
        Object[] branchNames = BRANCH.keySet().toArray();
        for (Object branchName : branchNames) {
            if (branchName.equals(Branch.currentBranch)) {
                System.out.print("*" + branchName + "\n");
            } else {
                System.out.println(branchName);
            }
        }
    }

    public static Object[] getBranchList() {
        return BRANCH.keySet().toArray();
    }

    public static LinkedHashMap getBranchCommitDetails() {
        return BRANCH_COMMIT_DETAILS.get(currentBranch);
    }

    public static void updateLatestCommitID(Long commitID) {
        BRANCH_COMMIT_ID.put(currentBranch, commitID);
    }

}
