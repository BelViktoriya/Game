import java.util.Random;
public class Field {
	
	private int [][] field={{0,2,5,9},{11,1,10,6},{8,12,3,14},{7,4,13,15}};
	
	public int[][] getField(){
		mixField();
		return field;
	}
	
	private void mixField(){//перемешивать массив каждый раз при создании новой игры
		Random rand=new Random();
		int i,j,tmp,randomRow,randomCol;
		for (i=0; i<4; i++)
			for (j=0; j<4; j++){
				tmp=field[i][j];
				int r1=rand.nextInt(),r2=rand.nextInt();
				if (r1<0) r1=r1*(-1);
				if (r2<0) r2=r2*(-1);
				randomRow=r1%4;
				randomCol=r2%4;
				field[i][j]=field[randomRow][randomCol];
				field[randomRow][randomCol]=tmp;
			}
	}
	
	private boolean centrZeroExists(int posRow,int posCol){ // центральные €чейки
		return (field[posCol][posRow-1]==0 || field[posCol][posRow+1]==0 || field[posCol-1][posRow]==0 
				|| field[posCol+1][posRow]==0);
	}
	
	private boolean upRowZeroExists(int posRow,int posCol){
		return (field[posCol][posRow+1]==0 || field[posCol-1][posRow]==0 || field[posCol+1][posRow]==0);
	}
	
	private boolean lowRowZeroExists(int posRow,int posCol){
		return (field[posCol][posRow-1]==0 || field[posCol-1][posRow]==0 || field[posCol+1][posRow]==0);
	}
	
	private boolean leftColZeroExists(int posRow,int posCol){
		return (field[posCol+1][posRow]==0 || field[posCol][posRow+1]==0 || field[posCol][posRow-1]==0);
	}
	
	private boolean rightColZeroExists(int posRow,int posCol){
		return (field[posCol-1][posRow]==0 || field[posCol][posRow+1]==0 || field[posCol][posRow-1]==0);
	}
	
	private boolean cornerZeroExists(int posRow,int posCol){
		if (posCol==0 && posRow==0) 
			return (field[posCol][posRow+1]==0 || field[posCol+1][posRow]==0);
		if (posCol==3 && posRow==0) 
			return (field[posCol][posRow+1]==0 || field[posCol-1][posRow]==0);//
		if (posCol==0 && posRow==3) 
			return (field[posCol][posRow-1]==0 || field[posCol+1][posRow]==0);
		else
			return (field[posCol][posRow-1]==0 || field[posCol-1][posRow]==0);
	}
	
	private boolean possibleToMakeMove(int posRow,int posCol){
		if (((posRow==1 || posRow==2) &&( posCol==1 || posCol==2))) // центральные €чейки
			return centrZeroExists(posRow,posCol);
		if (posRow==0 && (posCol==1 || posCol==2)) 
			return upRowZeroExists(posRow,posCol);
		
		if (posRow==3 && (posCol==1 || posCol==2)) 
			return lowRowZeroExists(posRow,posCol);
		
		if (posCol==0 && (posRow==1 || posRow==2)) 
			return leftColZeroExists(posRow,posCol);
		
		if (posCol==3 && (posRow==1 || posRow==2)) 
			return rightColZeroExists(posRow,posCol);
		else 
			return cornerZeroExists(posRow,posCol);
	}
	
	public boolean changeOnClick(int col,int row){
		boolean win=false;
		if (possibleToMakeMove(row,col)) {
			//go=findZero();
			makeMove(row,col);
			win=winGame();
		}
		return win;
	}
	
	private int[] findZero(){
		int[] xy=new int[]{0,0};
		for (int i=0;i<4;i++)
			for (int j=0;j<4;j++)
				if (field[i][j]==0) {
					xy[0]=i;
					xy[1]=j;
					break;
				}
		return xy;
	}
	
	public boolean winGame(){
		boolean flag=false;
		int[][] winar={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
		for (int i=0; i<4; i++)
			for (int j=0; j<4; j++)
				if (field[i][j]==winar[i][j]){
					continue;
				}
				else return false;
		return true;
	}
	
	private void  makeMove(int row,int col){
		int[] xy=findZero();
		int tmp;
		tmp=field[col][row];
		field[col][row]=field[xy[0]][xy[1]];
		field[xy[0]][xy[1]]=tmp;
	}
}
