/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notgit;

import java.util.Scanner;

/**
 *
 * @author Brandon Tan Zhirong
 */
public class NotGit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner s = new Scanner(System.in);
        String notGit = s.nextLine();
        if (notGit.equals("not-git")) {
            Command.oneArg(notGit);
        } else {
            return;
        }
        while (true) {
            String str = s.nextLine();
            String[] arrayStr = str.split("\\s+");
            if (arrayStr.length == 1) {
                Command.oneArg(arrayStr[0]);
            } else if (arrayStr.length == 2) {
                Command.twoArgs(arrayStr[0], arrayStr[1]);
            }else{
                System.out.println("Command/operation are not recognized. Please type again.");
            }
        }
    }

}
