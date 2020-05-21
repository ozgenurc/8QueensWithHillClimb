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
public class Heuristic {
    //heuristic değeri hesaplamak için
	public static int calculateHeuristic(int[] board)
	{
		int h = 0;
		int offset = 0;
		for (int i=0;i<board.length;i++)
		{
			for(int j=i+1;j<board.length;j++)
			{
				if(board[i]==board[j])
				{
					h = h+1;
				}
				
				offset = j-i;
				
				if((board[i]==board[j]-offset)||(board[i]==board[j]+offset))
				{
					h = h+1;
				}
			}
		}
		return h;
	}
}
