import java.util.Random;
import java.util.Scanner;

public class Beginer {
	public Board randomMarking(Board board) {
		int count = 0;
		Random random = new Random();
		Board newBoard = new Board();
		String[][] array = new String[5][6];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				array[i][j] = board.configuration[i][j];
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				if (array[i][j].equals("M")) {
					count++;
				}
			}
		}
 
		int randomNum = random.nextInt(count) + 1; // 1 ~ count
		//System.out.print(randomNum);
		//System.out.print(count);
		boolean flag = false;
		int[] position = new int[2];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				if (array[i][j].equals("M")) {
					randomNum--; // O represent Beginner
					if(randomNum == 0)
					  {
						flag = true;
						position[0] = i;
						position[1] = j;
						break;
					  }
				}
				
				
				
			}
			if(flag) break;
		}
		array[position[0]][position[1]] = "O";
		//System.out.print(position[0]);System.out.println(position[1]);
		newBoard.configuration = array;
		return newBoard;
	}

	public Board markingToGetFourInRow(Board board) {
		Board newBoard = new Board();
		String[][] array = new String[5][6];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				array[i][j] = board.configuration[i][j];
			}
		}
         newBoard.configuration = array;
		for (int i = 0; i < 5; i++) { // row check
			String row = new String();
			for (int j = 0; j < 6; j++) {
				row = row + array[i][j];
			}
			// System.out.println("row " + i + " : " + row);

			if (newBoard.isOpenThree(row, "O")) {
				for (int index = 0;; index++) {
					if (row.substring(index, index + 3).equals("OOO")) {
						if ((index - 1) >= 0 && row.substring(index - 1, index).equals("M")) {
							array[i][index -1] = "O";
							break;
						} else {
							array[i][index + 3] = "O";
							break;
						}
					}
				}
				return newBoard;

			}

		}

		for (int i = 0; i < 6; i++) { // column check
			String column = new String();
			for (int j = 0; j < 5; j++) {
				column = column + array[j][i];
			}

			if (newBoard.isOpenThree(column, "O")) {

				for (int index = 0;; index++) {
					if (column.substring(index, index + 3).equals("OOO")) {
						if ((index - 1) >= 0 && column.substring(index - 1, index).equals("M")) {
							array[index - 1][i] = "O";
							break;
						} else {
							array[index + 3][i] = "O";
							break;
						}
					}
				}
				return newBoard;

			}

		}

		for (int i = 0; i < 6; i++) {
			int[] position = new int[] { 0, i }; // [0,0],[0,1],[0,2],[0,3],[0,4],[0,5]
			String diagonal = newBoard.getDiagonal1(position); // from leftUp to
																// right
			// System.out.println("diagonal1 : " + diagonal); // Down

			if (newBoard.isOpenThree(diagonal, "O")) {
				for (int index = 0;; index++) {
					if (diagonal.substring(index, index + 3).equals("OOO")) {
						if ((index - 1) >= 0 && diagonal.substring(index - 1, index).equals("M")) {
							array[index - 1][index + i - 1] = "O";
							break;
						} else {
							array[index + 3][i + index + 3] = "O";
							break;
						}
					}
				}
				return newBoard;
			}

			if (i == 1) {
				int[] transposition = new int[] { i, 0 };
				diagonal = newBoard.getDiagonal1(transposition);
				// System.out.println("diagonal2 " + diagonal);

				if (newBoard.isOpenThree(diagonal, "O")) {
					for (int index = 0;; index++) {
						if (diagonal.substring(index, index + 3).equals("OOO")) {
							if (index == 0) {
								array[4][3] = "O";
								break;
							} else {
								array[1][0] = "O";
								break;
							}
						}
					}
				}

				return newBoard;

			}
			diagonal = newBoard.getDiagonal2(position); // from rightUp to
														// leftDown
			// System.out.println("diagonal3 " + diagonal);

			if (newBoard.isOpenThree(diagonal, "O")) {
				for (int index = 0;; index++) {
					if (diagonal.substring(index, index + 3).equals("OOO")) {
						if ((index - 1) >= 0 && diagonal.substring(index - 1, index).equals("M")) {
							array[index - 1][-index + i + 1] = "O";
							break;
						} else {
							array[index + 3][i - index - 3] = "O";
							break;
						}
					}
				}
				return newBoard;
			}

			if (i == 1) {
				int[] indexArray = new int[] { i, 5 };
				diagonal = newBoard.getDiagonal2(indexArray);
				// System.out.println("diagonal4 " + diagonal);

				if (newBoard.isOpenThree(diagonal, "O")) {
					for (int index = 0;; index++) {
						if (diagonal.substring(index, index + 3).equals("OOO")) {
							if (index == 0) {
								array[4][2] = "O";
								break;
							} else {
								array[1][5] = "O";
								break;
							}
						}
					}
				}

				return newBoard;

			}

		}

		return newBoard;
	}

	public Board markingToPreventFourInRow(Board board) {
		Board newBoard = new Board();
		String[][] array = new String[5][6];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				array[i][j] = board.configuration[i][j];
			}
		}
          newBoard.configuration = array;
		for (int i = 0; i < 5; i++) { // row check
			String row = new String();
			for (int j = 0; j < 6; j++) {
				row = row + array[i][j];
			}
			// System.out.println("row " + i + " : " + row);

			if (newBoard.isOpenThree(row, "X") || newBoard.isOpenThree(row,"Y")) { // Advanced X
				for (int index = 0;; index++) {
					if (row.substring(index, index + 3).equals("XXX")||row.substring(index, index + 3).equals("YYY")) {
						if ((index - 1) >= 0 && row.substring(index - 1, index).equals("M")) {
							array[i][index -1] = "O";
							break;
						} else {
							array[i][index + 3] = "O";
							break;
						}
					}
				}
				return newBoard;

			}

		}

		for (int i = 0; i < 6; i++) { // column check
			String column = new String();
			for (int j = 0; j < 5; j++) {
				column = column + array[j][i];
			}

			if (newBoard.isOpenThree(column, "X") || newBoard.isOpenThree(column, "Y")) {

				for (int index = 0;; index++) {
					if (column.substring(index, index + 3).equals("XXX") || column.substring(index, index + 3).equals("YYY")) {
						if ((index - 1) >= 0 && column.substring(index - 1, index).equals("M")) {
							array[index-1][i] = "O";
							break;
						} else {
							array[index + 3][i] = "O";
							break;
						}
					}
				}
				return newBoard;

			}

		}

		for (int i = 0; i < 6; i++) {
			int[] position = new int[] { 0, i }; // [0,0],[0,1],[0,2],[0,3],[0,4],[0,5]
			///System.out.println("i = " + i);
			String diagonal = newBoard.getDiagonal1(position); // from leftUp to
			//System.out.println("diagonal1" + diagonal);											// right
			// System.out.println("diagonal1 : " + diagonal); // Down

			if (newBoard.isOpenThree(diagonal, "X") || newBoard.isOpenThree(diagonal, "Y")) {
				
				for (int index = 0;; index++) {
					if (diagonal.substring(index, index + 3).equals("XXX") || diagonal.substring(index, index + 3).equals("YYY")) {
						if ((index - 1) >= 0 && diagonal.substring(index - 1, index).equals("M")) {
							array[index - 1][index + i - 1] = "O";
							break;
						} else {
							array[index + 3][i + index + 3] = "O";
							break;
						}
					}
				}
				return newBoard;
			}

			if (i == 1) {
				int[] transposition = new int[] { i, 0 };
				diagonal = newBoard.getDiagonal1(transposition);
				// System.out.println("diagonal2 " + diagonal);

				if (newBoard.isOpenThree(diagonal, "X") || newBoard.isOpenThree(diagonal, "Y") ) {
					for (int index = 0;; index++) {
						if (diagonal.substring(index, index + 3).equals("XXX") || diagonal.substring(index, index + 3).equals("YYY")) {
							if (index == 0) {
								array[4][3] = "O";
								break;
							} else {
								array[1][0] = "O";
								break;
							}
						}
					}
					return newBoard;
				}

				

			}
			diagonal = newBoard.getDiagonal2(position); // from rightUp to
			//System.out.print(position[0]);
			//System.out.println(position[1]);// leftDown
			// System.out.println("diagonal3 " + diagonal);

			if (newBoard.isOpenThree(diagonal, "X")|| newBoard.isOpenThree(diagonal, "Y")) {
				for (int index = 0;; index++) {
					if (diagonal.substring(index, index + 3).equals("XXX")|| diagonal.substring(index, index + 3).equals("YYY")) {
						//System.out.print(index -1);
						//System.out.print(-index+i+1);
						if ((index - 1) >= 0 && diagonal.substring(index - 1, index).equals("M")) {
							//System.out.print(index -1);
							//System.out.print(-index+i+1);
							array[index - 1][-index + i + 1] = "O";
							break;
						} else {
							array[index + 3][i - index - 3] = "O";
							break;
						}
					}
				}
				return newBoard;
			}

			if (i == 1) {
				int[] indexArray = new int[] { i, 5 };
				diagonal = newBoard.getDiagonal2(indexArray);
				//System.out.println("diagonal4 " + diagonal);

				if (newBoard.isOpenThree(diagonal, "X")|| newBoard.isOpenThree(diagonal, "Y"))  {
					for (int index = 0;; index++) {
						if (diagonal.substring(index, index + 3).equals("XXX") || diagonal.substring(index, index + 3).equals("YYY")) {
							if (index == 0) {
								array[4][2] = "O";
								break;
							} else {
								array[1][5] = "O";
								break;
							}
						}
					}
					return newBoard;
				}

				

			}

		}
		return newBoard;

	}

 /*	public static void main(String[] args) {
		Beginer beginer = new Beginer();
		Scanner scan = new Scanner(System.in);
		String[][] array = new String[5][6];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++)
				array[i][j] = "M";
		}
		Board board = new Board();
		board.configuration = array;
		board.setParamter();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(board.configuration[i][j]);
			
			}
			System.out.println("");
			
		}
		System.out.println("");
		while (!board.gameOver()) { // Beginner move first and then user move
			Board process = new Board();
			if (board.openThreeAdvanced == 0) {
				process = beginer.randomMarking(board); // beginner move
														// randomly
				process.setParamter();
			} else {
				process = beginer.markingToPreventFourInRow(board);
				process.setParamter();
			}
			System.out.println("----------------------------------------");
            for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++)
            		System.out.print(process.configuration[i][j]);
            	System.out.println("");
            }
            System.out.println("");
			if (process.gameOver())
				break;

			int position = scan.nextInt();
			int row = position / 10;
			int column = position % 10;
			process.configuration[row][column] = "X";
			process.setParamter();
			for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++)
            		System.out.print(process.configuration[i][j]);
            	System.out.println("");
            }
			System.out.println("--------------------------------------");
			System.out.println("");
			board = process;

		}
		scan.close();
	}
	*/
	/*public static void main(String[] args) {
		Beginer instance = new Beginer();
		String[][] array = {{"X","M","O","O","O","X"},{"X","X","X","O","X","M"},{"X","O","X","X","X","O"},{"O","X","O","O","O","X"},{"O","M","O","M","X","M"}};
		//String[][] array = {{"X","X","X","O","M","X"},{"X","O","X","X","M","M"},{"M","M","X","O","O","M"},{"X","M","O","O","O","M"},{"M","M","M","M","M","M"}};
		Board board = new Board();
		board.configuration = array;
		//Board result = instance.markingToGetFourInRow(board);
		/*for(int i = 0; i <5; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(result.configuration[i][j]);
			}
			System.out.println("");
		}  
		//System.out.println(instance.isOpenTwo);
	}*/
   
}
