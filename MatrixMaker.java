import java.util.Scanner;
import java.util.Random;
import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;

public class MatrixMaker {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Matrix Dimentions: ");
        String mDim = input.nextLine();
        int dim = 0;
        int bRange = 0;
        int uRange = 0;
        try {
            dim = Integer.parseInt(mDim);
         }
         catch (NumberFormatException e)
         {
            System.out.println("Error: Invalid number provided for the dimentions! Clossing Program");
            System.exit(0);
         }

        System.out.println("<=== Enter Range That You Want The Values To Be In (e.g. '-1100') ===>");
        System.out.println("Enter The Bottom Range: ");
        String bottomRange = input.nextLine();

        System.out.println("Enter The Upper Range: ");
        String upperRange = input.nextLine();

        try {
            bRange = Integer.parseInt(bottomRange);
            uRange = Integer.parseInt(upperRange);
         }
         catch (NumberFormatException e)
         {
            System.out.println("Error: Invalid number provided for the ranges! Clossing Program");
            System.exit(0);
         }

         if(dim == 0){
            System.out.println("Error: Invalid Dimentions provided! Closing Program");
            System.exit(0);
         }

         if(bRange > uRange){
            System.out.println("Error: Bottom Range Is Higher Than Upper Range! Closing Program");
            System.exit(0);
         }

         String finalMatrixA = "";
         String finalMatrixB = "";

         int[][] final2DMatrixA = new int[dim][dim];
         int[][] final2DMatrixB = new int[dim][dim];

         int newElementA;
         int newElementB;

         Random rand = new Random();
         for(int i = 0; i < dim; i++){
            for(int j = 0; j < (dim); j++){
                newElementA = rand.nextInt((uRange - bRange) + 1) + bRange;
                newElementB = rand.nextInt((uRange - bRange) + 1) + bRange;

                finalMatrixA += Integer.toString(newElementA) + ",";
                finalMatrixB += Integer.toString(newElementB) + ",";

                final2DMatrixA[i][j] = newElementA;
                final2DMatrixB[i][j] = newElementB;
            }
            finalMatrixA += "\n";
            finalMatrixB += "\n";
         }
         finalMatrixA = finalMatrixA.substring(0, finalMatrixA.length() - 2);
         finalMatrixB = finalMatrixB.substring(0, finalMatrixB.length() - 2);

         int[][] final2DMatrixAns = multiplyMatrix(final2DMatrixA, final2DMatrixB);
         String finalMatrixAns = "";

         for(int i = 0; i < dim; i++){
            for(int j = 0; j < (dim); j++){
                finalMatrixAns += final2DMatrixAns[i][j] + ",";
            }
            finalMatrixAns += "\n";
         }
         finalMatrixAns = finalMatrixAns.substring(0, finalMatrixAns.length() - 2);
         finalMatrixAns += "\n";

         System.out.println("Enter A Prefix For The Files: ");
         String fileName = input.nextLine();

         System.out.println("Enter A Name For The Folder: ");
         String folderName = input.nextLine();

         try {
             //File Destination
             String fileDir = "C:\\Users\\Rayha\\Desktop\\University Work\\6. Distributed Systems\\CW\\Input\\";
             String newFolderDir = fileDir + folderName;
             boolean newFolderCreated = new File(newFolderDir).mkdirs();
             
             if(newFolderCreated != true){
                System.out.println("Error: The Folder Could Not Be Created!");
                System.exit(0);
             }
             else {
                System.out.println("Folder created: " + folderName);

                String fileLocationA = newFolderDir + "\\" + fileName  + "_A.txt";
                String fileLocationB = newFolderDir + "\\" + fileName + "_B.txt";
                String fileLocationAns = newFolderDir + "\\" + fileName + "_Answers.txt";
   
                File myFileA = new File(fileLocationA);
                File myFileB = new File(fileLocationB);
                File myFileAns = new File(fileLocationAns);
   
                if (myFileA.createNewFile() || myFileB.createNewFile() || myFileAns.createNewFile()) {
                  System.out.println("File created: " + myFileA.getName());
                  System.out.println("File created: " + myFileB.getName());
                  System.out.println("File created: " + myFileAns.getName());
                } 
                else {
                  System.out.println("Error: A file with the same name already exists. Closing Program");
                  System.exit(0);
                }
                FileWriter myWriterA = new FileWriter(fileLocationA);
                FileWriter myWriterB = new FileWriter(fileLocationB);
                FileWriter myWriterAns = new FileWriter(fileLocationAns);

                myWriterA.write(finalMatrixA);
                myWriterB.write(finalMatrixB);
                myWriterAns.write(finalMatrixAns);

                myWriterA.close();
                myWriterB.close();
                myWriterAns.close();

                System.out.println("The Matracies Were Successfully Written To File In The Folder!");
             }
             input.close();
             System.out.println("Closing Program");
             System.exit(0);
          } 
          catch (IOException e) {
             System.out.println("Error: An error occurred! Closing Program");
             System.exit(0);
          }
          
    }

    static int[][] multiplyMatrix(int[][] A, int[][] B){
        int [][] result = new int[A.length][A.length];

       for (int i = 0; i < A.length; i++) { 
           for (int j = 0; j < A.length; j++) { 
               for (int k = 0; k < A.length; k++) { 
                   result[i][j] += A[i][k] * B[k][j];
               }
           }
       }
       return result;
    }
}
