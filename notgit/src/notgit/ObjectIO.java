/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notgit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/**
 *
 * @author User-PC
 */
public class ObjectIO {

    private static String filePath = "C:\\Users\\Brandon Tan\\Desktop\\New folder\\.not-git\\SaveFile\\";      //Assume that you already have this file
    static File saveFolder = new File(Folder.repository, "SaveFile");
    private static boolean load=false;

    public static void writeObjectToFile(Object o) {
        try {

            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath));
            output.writeObject(o);
            output.close();
            System.out.println(o + " written.");
        } catch (IOException e) {
            System.out.println("Failed");
        }
    }

    public static Object ReadObjectFromFile(String filePath) {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath));
            Object object = input.readObject();
            input.close();
            return object;
        } catch (Exception e) {
            System.out.println("There is no save file.");
            load=false;
            return null;
        }
    }

    public static void Save() {
        String[] savefiles = {"branch.txt", "branch_id.txt", "branch_details.txt", "currentBranch.txt", "fn.txt", "latestID.txt",
            "bRepository.txt","dContent.txt","dFileName.txt","ht.txt", "tFileName.txt", "st.txt", "load.txt"};
        saveFolder.mkdir();
        String temp = filePath;
        Branch branch = new Branch();
        FileNames fn = new FileNames();
        Folder f = new Folder();
        HistoryTree ht = new HistoryTree();
        Status st = new Status();
        filePath+="load.txt";
        load=true;
        boolean loadTemp=load;
        writeObjectToFile(loadTemp);
        filePath=temp;
        int i = 0;
        while (i <savefiles.length-1) {
            switch (i) {
                case 0: {
                    LinkedHashMap<String, LinkedHashMap> branchTemp =new LinkedHashMap();
                    branchTemp=branch.getBRANCH();
                    filePath += savefiles[i];
                    writeObjectToFile(branchTemp);
                    i++;
                    filePath = temp;
                    break;
                }
                 case 1: {
                    LinkedHashMap<String, Long> branchTemp =new LinkedHashMap();
                    branchTemp=branch.getBRANCH_COMMIT_ID();
                    filePath += savefiles[i];
                    writeObjectToFile(branchTemp);
                    i++;
                    filePath = temp;
                    break;
                }
                 case 2: {
                    LinkedHashMap<String, LinkedHashMap>  branchTemp =new LinkedHashMap();
                    branchTemp=branch.getBRANCH_COMMIT_DETAILS();
                    filePath += savefiles[i];
                    writeObjectToFile(branchTemp);
                    i++;
                    filePath = temp;
                    break;
                }
                 case 3: {
                    String branchTemp=branch.getCurrentBranch();
                    filePath += savefiles[i];
                    writeObjectToFile(branchTemp);
                    i++;
                    filePath = temp;
                    break;
                }

                case 4: {
                    LinkedHashMap<Long, ArrayList> fnTemp=new LinkedHashMap();
                    fnTemp=fn.getFileNames();
                    filePath += savefiles[i];
                    writeObjectToFile(fnTemp);
                    i++;
                    filePath = temp;
                    break;
                }
                case 5: {
                    long fnTemp=fn.getLatestCommitID();
                    filePath += savefiles[i];
                    writeObjectToFile(fnTemp);
                    i++;
                    filePath = temp;
                    break;
                }
                case 6: {
                    File fTemp=f.getBranchRepository();
                    filePath += savefiles[i];
                    writeObjectToFile(fTemp);
                    i++;
                    filePath = temp;
                    break;
                }
                case 7: {
                    String[] fTemp=f.getDirectoryContents();
                    filePath += savefiles[i];
                    writeObjectToFile(fTemp);
                    i++;
                    filePath = temp;
                    break;
                }
                case 8: {
                   ArrayList<String> fTemp=new ArrayList();
                           fTemp=f.getDirectoryFileNames();
                    filePath += savefiles[i];
                    writeObjectToFile(fTemp);
                    i++;
                    filePath = temp;
                    break;
                }
                case 9: {
                    ArrayList<String> htTemp=new ArrayList();
                    htTemp=ht.getHISTORY_TREE();
                    filePath += savefiles[i];
                    writeObjectToFile(htTemp);
                    i++;
                    filePath = temp;
                    break;
                }
                case 10: {
                    ArrayList<String> stTemp= new ArrayList();
                    stTemp=st.getTrackedFileNames();
                    filePath += savefiles[i];
                    writeObjectToFile(stTemp);
                    i++;
                    filePath = temp;
                    break;
                }
                case 11: {
                    LinkedHashMap<String, String> stTemp= new LinkedHashMap();
                    stTemp=st.getStatusNew();
                    filePath += savefiles[i];
                    writeObjectToFile(stTemp);
                    i++;
                    filePath = temp;
                    break;
                }

            }

        }
        
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public static boolean isLoad() {
        return load;
    }

    public static void Load() {

        String[] loadfiles = {"branch.txt", "branch_id.txt", "branch_details.txt", "currentBranch.txt", "fn.txt", "latestID.txt",
            "bRepository.txt","dContent.txt","dFileName.txt","ht.txt", "tFileName.txt", "st.txt","load.txt"};
        Branch branch = new Branch();

        FileNames fn = new FileNames();
        Folder f = new Folder();
        HistoryTree ht = new HistoryTree();
        Status st = new Status();
        String temp = filePath;
        filePath+="load.txt";
        ObjectIO obj=new ObjectIO();
        try{
        obj.setLoad((boolean)ReadObjectFromFile(filePath));
        }catch(NullPointerException e){
            
        }
        filePath=temp;
        int i = 0;
        while (i <loadfiles.length-1 && load==true) {
            switch (i) {
                case 0: {

                    filePath += loadfiles[i];
                    branch.setBRANCH(((LinkedHashMap)ReadObjectFromFile(filePath)));
                    i++;
                    filePath = temp;
                    break;
                }
                case 1: {
                    filePath += loadfiles[i];
                    branch.setBRANCH_COMMIT_ID(((LinkedHashMap)ReadObjectFromFile(filePath)));
                    i++;
                    filePath = temp;
                    break;
                }
                case 2: {
                    filePath += loadfiles[i];
                    branch.setBRANCH_COMMIT_DETAILS(((LinkedHashMap)ReadObjectFromFile(filePath)));
                                                 CommitDetails cd = new CommitDetails();
                    i++;
                    filePath = temp;
                    break;
                }
                case 3: {
                    filePath += loadfiles[i];
                    branch.setCurrentBranch(((String)ReadObjectFromFile(filePath)));
                    i++;
                    filePath = temp;
                    break;
                }
                case 4: {
                    filePath += loadfiles[i];
                    fn.setFileNames((LinkedHashMap)ReadObjectFromFile(filePath));
                    i++;
                    filePath = temp;
                    break;
                }
                case 5: {
                    filePath += loadfiles[i];
                    fn.setLatestCommitID((Long)ReadObjectFromFile(filePath));
                    i++;
                    filePath = temp;
                    break;
                }
                case 6: {
                    filePath += loadfiles[i];
                    f.setBranchRepository((File)ReadObjectFromFile(filePath));
                    i++;
                    filePath = temp;
                    break;
                }
                case 7: {
                    filePath += loadfiles[i];
                    f.setDirectoryContents((String[])ReadObjectFromFile(filePath));
                    i++;
                    filePath = temp;
                    break;
                }
                case 8: {
                    filePath += loadfiles[i];
                    f.setDirectoryFileNames((ArrayList)ReadObjectFromFile(filePath));
                    i++;
                    filePath = temp;
                    break;
                }
                case 9: {
                    filePath += loadfiles[i];
                    ht.setHISTORY_TREE((ArrayList)ReadObjectFromFile(filePath));
                    i++;
                    filePath = temp;
                    break;
                }
                case 10: {
                    filePath += loadfiles[i];
                    st.setTrackedFileNames((ArrayList)ReadObjectFromFile(filePath));
                    i++;
                    filePath = temp;
                    break;
                }
                case 11: {
                    filePath += loadfiles[i];
                    st.setStatusNew((LinkedHashMap)ReadObjectFromFile(filePath));
                    i++;
                    filePath = temp;
                            System.out.println("Loaded.");
                    break;
                }
            }

        }

    }
}

