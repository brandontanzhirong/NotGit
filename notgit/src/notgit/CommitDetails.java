/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notgit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author Brandon Tan Zhirong
 */
public class CommitDetails {

    private static LinkedHashMap<Long, ArrayList> commitDetails = Branch.getBranchCommitDetails();  //Key = CommitID Value = ArrayList of date and description
    public static LinkedHashMap<Long, ArrayList> getCommitDetails() {
        return commitDetails;
    }
    public static void addCommitDetails() {
        Scanner s = new Scanner(System.in);
        ArrayList<String> commitInfo = new ArrayList<>();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy ");
        String dateText = dateFormat.format(date);
        System.out.print("description: ");
        String description = s.nextLine();
        HistoryTree.addHistoryTree(description);
        commitInfo.add(dateText);
        commitInfo.add(description);
        commitDetails.put(FileNames.latestCommitID, commitInfo);
    }

    public static String getDate(Long commitID) {
        return commitDetails.get(commitID).get(0).toString();
    }

    public static String getDescription(Long commitID) {
        return commitDetails.get(commitID).get(1).toString();
    }

    public static void setCommitDetails(LinkedHashMap<Long, ArrayList> commitDetails) {
        CommitDetails.commitDetails = commitDetails;
    }

}
