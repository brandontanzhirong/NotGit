/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notgit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author Brandon Tan Zhirong
 */
public class HistoryTree implements Serializable{
private static final long serialVersionUID = 1L;
    private static ArrayList<String> HISTORY_TREE = new ArrayList<>();
    public static ArrayList<String> getHISTORY_TREE() {
        return HISTORY_TREE;
    }

    public static void setHISTORY_TREE(ArrayList<String> HISTORY_TREE) {
        HistoryTree.HISTORY_TREE = HISTORY_TREE;
    }
    public static void addHistoryTree(String history) {
        Object[] branchList = Branch.getBranchList();
        StringBuilder sb = new StringBuilder();
        if (FileNames.latestCommitID == 1 && !Branch.currentBranch.equals("master")) {
            for (int i = 0; i < branchList.length-1 && FileNames.latestCommitID == 1; i++) {
                sb.append("|");
            }
                sb.append("\\\n");
            }
        for (int i = 0; i < branchList.length && FileNames.latestCommitID >= 1; i++) {
            if (branchList[i].equals(Branch.currentBranch)) {
                sb.append("*");
            } else {
                sb.append("|");
            }
        }
        sb.append(" ").append(HISTORY_TREE.size()+1).append(": ");
        sb.append(history);
        HISTORY_TREE.add(sb.toString());
    }

    public static void displayHISTORY_TREE() {
        HISTORY_TREE.forEach((String description) -> {
            System.out.println(description);
        });
    }
}
