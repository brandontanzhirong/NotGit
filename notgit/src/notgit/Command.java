/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notgit;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Brandon Tan Zhirong
 */
public class Command {

    public static void oneArg(String str) {
        switch (str) {
            case "not-git":
                System.out.println("Welcome to not-git!");
                Folder.repository.mkdir();
                Branch.addMasterBranch();
                ObjectIO object = new ObjectIO();
                System.out.println("Do you want to continue on your previous project?");
                Scanner s = new Scanner(System.in);
                String ans = s.nextLine();
                if (ans.equalsIgnoreCase("yes")) {
                    object.Load();
                }
                return;
            case "commit":
                if (!Status.trackedFileNames.isEmpty()) {
                    Commit.commit(Status.trackedFileNames);
                    Branch.updateLatestCommitID(FileNames.latestCommitID);
                    CommitDetails.addCommitDetails();
                    System.out.printf("files commited with commit id \"%d\"\n", FileNames.latestCommitID);
                } else {
                    System.out.println("No file is being tracked.");
                }
                return;

            case "status":
                Status.status();
                Folder.setDirectoryFileNames(Folder.getDirectoryFileNames());
                return;
            case "log":
                if (FileNames.latestCommitID == 0) {
                    System.out.println("No commit yet.");
                } else {
                    Object[] a = Branch.getListOfCommitID();
                    for (int i = 0; i < a.length; i++) {
                        Long commitID = Long.parseLong(a[i].toString());
                        if (commitID > 1000) {            //for branching purpose as the first commitID stored is more than 1000
                            continue;
                        }
                        System.out.printf("commit \"%d\" - %s\n", commitID, CommitDetails.getDate(commitID));
                        System.out.printf("\t\"%s\"\n", CommitDetails.getDescription(commitID));
                    }
                }
                return;
            case "branch":
                Branch.displayBranchList();
                return;
            case "save":
                ObjectIO.Save();
                return;
            case "tree":
                HistoryTree.displayHISTORY_TREE();
                return;
            default:
                System.out.println("Command/operation are not recognized. Please type again.");
        }
    }

    public static void twoArgs(String str1, String str2) {
        switch (str1) {
            case "add":
                String str3 = str2.substring(1, str2.length() - 1);
                ArrayList<String> DirectoryFileNames = Folder.getInitialDirectoryFileNames();
                for (int i = 0; i < DirectoryFileNames.size(); i++) {
                    if (str3.equals(DirectoryFileNames.get(i))) {
                        for (String fileName : Status.trackedFileNames) {
                            if (fileName.equals(str3)) {
                                System.out.printf("%s is already being tracked\n", str2);
                                return;
                            }
                        }
                        Status.trackedFileNames.add(str3);
                        Status.statusNew.put(str3, "new");
                        System.out.printf("%s is now being tracked\n", str2);
                        return;
                    }
                }
                System.out.println("File not found.");
                return;
            case "revert":
                Long commitID = new Long(str2);
                if (FileNames.latestCommitID > 0 && !(FileNames.latestCommitID < commitID)) {          //modified 23/5 after finalized - to able to revert to the current commit           
                    Commit.commit(Status.trackedFileNames);
                    CommitDetails.addCommitDetails();
                    Branch.updateLatestCommitID(FileNames.latestCommitID);
                    System.out.printf("revert to commit-id \"%s\" and committed with commit-id \"%d\"\n", str2, FileNames.latestCommitID);
                    Revert.revert(FileNames.get(commitID));
                } else {
                    System.out.println("Invalid revert");
                }
                return;
            case "branch":
                if (Branch.currentBranch.equals("master") && FileNames.latestCommitID > 0) {
                    for (int i = 0; i < Branch.getBranchList().length; i++) {
                        if (str2.equals(Branch.getBranchList()[i])) {
                            System.out.printf("Branch \"%s\" is already exist.\n", str2);
                            return;
                        }
                    }
                    Branch.addBranch(str2, FileNames.latestCommitID);
                    System.out.printf("branch \"%s\" created.\n", str2);
                } else if (!Branch.currentBranch.equals("master")) {
                    System.out.println("You are not in master branch.");
                } else {
                    System.out.println("No commit yet");
                }
                return;
            case "checkout":
                for (Object branchName : Branch.getBranchList()) {
                    if (str2.equals(branchName)) {
                        Branch.currentBranch = str2;
                        FileNames.latestCommitID = Branch.getLatestCommitID(Branch.currentBranch);
                        CommitDetails.setCommitDetails(Branch.getBranchCommitDetails());
                        Folder.setBranchRepository(Branch.currentBranch);
                        FileNames.updateFileNames();
                        if (FileNames.latestCommitID > 0) {
                            Revert.revert(new ArrayList(Branch.getLatestCommitFiles()));
                        }
                        return;
                    }
                }
                System.out.printf("\"%s\" not found.\n", str2);
                return;
            default:
                System.out.println("Command/operation are not recognized. Please type again.");

        }
    }

}
