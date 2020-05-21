/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8queenswithhillclimb;

/**
 *
 * @author ozgen
 */
public class Main {

    static int randomRestarts = 0;
    static int stepsOfClimbing = 0;
    static long startTime=0;
    
    public static void main(String[] args) {
         
        long beginTime = 0, endTime = 0;
        Board initBoard = new Board();
        Board solBoard = new Board();
        Board loopBoard = new Board();

        //input number of queens
        initBoard.setQueenSayisi(8);
        loopBoard.setQueenSayisi(initBoard.getQueenSayisi());
        initBoard.randomBoard(initBoard.getQueenSayisi());

        int[] localNext = new int[initBoard.getQueenSayisi()];
        localNext = initBoard.determineNextStep(initBoard.getBoardOlustur());
        
        beginTime = System.currentTimeMillis();
        while (true) {
            localNext = initBoard.determineNextStep(localNext);
            
            //toplam yer değiştirme sayısını hesaplamak için
            Main.stepsOfClimbing++;
            if (localNext == null) {
                //toplam yer değiştirme sayısını hesaplamak için
                Main.randomRestarts++;
                loopBoard.randomBoard(loopBoard.getQueenSayisi());
                localNext = loopBoard.determineNextStep(loopBoard.getBoardOlustur());
            } else {
                if (Heuristic.calculateHeuristic(localNext) == 0) {
                    solBoard.setBoardOlustur(localNext);
                    break;
                }
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("Başlangıç durumu:\n");
        int[] localInit = initBoard.getBoardOlustur();

        //print initial board
        for (int col = 0; col < initBoard.getQueenSayisi(); col++) {
            for (int row = 0; row < initBoard.getQueenSayisi(); row++) {
                if (localInit[col] == row) {
                    System.out.print("Q");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println("\n");
        }

        System.out.println("Çözüm durumu:\n");
        localNext = solBoard.getBoardOlustur();

        //print solution board
        for (int col = 0; col < initBoard.getQueenSayisi(); col++) {
            for (int row = 0; row < initBoard.getQueenSayisi(); row++) {
                if (localNext[col] == row) {
                    System.out.print("Q");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println("\n");
        }

        System.out.println("\nRandom restart sayısı : " + Main.randomRestarts);
        System.out.println("Total yer değiştirme sayısı : " + Main.stepsOfClimbing);
        System.out.println("Çalışma Süresi(saniye) : " + ((double) (endTime - beginTime)) / 1000);
    }
}
