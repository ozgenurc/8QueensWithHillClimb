/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8queenswithhillclimb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ozgen
 */
public class Board {

    private int[] boardOlustur;
    private int queenSayisi;

    //random board oluşturmak için
    public void randomBoard(int noQueens) {
        boardOlustur = new int[noQueens];
        Random var = new Random();
        for (int i = 0; i < noQueens; i++) {
            boardOlustur[i] = var.nextInt(noQueens);
        }
    }

    public int getQueenSayisi() {
        return queenSayisi;
    }

    public void setQueenSayisi(int queenSayisi) {
        this.queenSayisi = queenSayisi;
    }

    public int[] getBoardOlustur() {
        return boardOlustur;
    }

    public void setBoardOlustur(int[] boardOlustur) {
        this.boardOlustur = boardOlustur;
    }

    //bir sonraki adımı döndürür
    public int[] determineNextStep(int[] currentBoard) {
        int length = (currentBoard.length) * (currentBoard.length);
        int[] moves = new int[length];	//heuristic değeri tutar
        List<int[]> hamle = new ArrayList<int[]>(); //hamlenin o anki durumunu tutar
        int count = 0;
        int[] copyBoard; //copyBoardı tutmak için					 
        int[] k = new int[currentBoard.length]; //bi sonraki değeri döndürmek için

        //her sütunda queen yukarı/aşağı hareket edecek 
        for (int col = 0; col < currentBoard.length; col++) {
            //queeni yukarı/aşağı hareket ettirme
            for (int row = 0; row < currentBoard.length; row++) {
                //currentBoard ile aynı duruma gelirse
                if (currentBoard[col] == row) {
                    moves[count] = Heuristic.calculateHeuristic(currentBoard);
                    hamle.add(count, currentBoard);
                    count++;
                } else {
                    copyBoard = new int[currentBoard.length];
                    System.arraycopy(currentBoard, 0, copyBoard, 0, currentBoard.length);
                    copyBoard[col] = row;
                    moves[count] = Heuristic.calculateHeuristic(copyBoard);
                    hamle.add(count, copyBoard);
                    count++;
                }
            }
        }

        //currentBoardın h değeri
        int hToBeat = Heuristic.calculateHeuristic(currentBoard);

        //lokal maksimuma ulaştığımız durum
        for (int i = 0; i < moves.length; i++) {
            if (moves[i] < hToBeat) {
                hToBeat = moves[i];
            }
        }

        if (hToBeat == Heuristic.calculateHeuristic(currentBoard)) {
            return null;
        } else {

            // h değeri için karşılık gelen hareketi bulmak için
            for (int i = 0; i < moves.length; i++) {
                if (moves[i] == hToBeat) {
                    k = hamle.get(i);
                }
            }
        }
        return k;
    }
}
