

public class Board {
	public String[][] configuration = new String[5][6];
	int openThreeAdvanced = 0; // X
	int openTwoAdvanced = 0;
	int openThreeBeginner = 0; // O
	int openTwoBeginner = 0;
    int depth = 0;
    int openThreeMaster = 0;
    int openTwoMaster = 0;
	/*
	 * public Advanced() { for (int i = 0; i < 5; i++) { for (int j = 0; j < 6;
	 * j++) configuration[i][j] = " "; } }
	 */

	public void setParamter() { // call this after each player make
								// an action

		for (int i = 0; i < 5; i++) { // row check
			String row = new String();
			for (int j = 0; j < 6; j++) {
				row = row + configuration[i][j];
			}
		//	System.out.println("row " + i + " : " + row);
			if (isOpenTwo(row, "X")!=0) {
				//System.out.println("row " + i + " X two open");
				openTwoAdvanced += isOpenTwo(row,"X");
			}

			if (isOpenThree(row, "X")) {
				openThreeAdvanced++;
				//System.out.println("row " + i + " X three open");
			}

			if (isOpenTwo(row, "O")!=0) {
				//System.out.println("row " + i + " O two open");
				openTwoBeginner+= isOpenTwo(row,"O");
			}

			if (isOpenThree(row, "O")) {
			//	System.out.println("row " + i + " O three open");
				openThreeBeginner++;
			}
			
			if(isOpenThree(row,"Y")) {
				openThreeMaster++;
			}
            if(isOpenTwo(row,"Y")!=0) {
            	
            	openTwoMaster += isOpenTwo(row,"Y");
            }
		}

		for (int i = 0; i < 6; i++) { // column check
			String column = new String();
			for (int j = 0; j < 5; j++) {
				column = column + configuration[j][i];
			}
			if (isOpenTwo(column, "X")!=0)
				openTwoAdvanced+=isOpenTwo(column, "X");
			if (isOpenThree(column, "X"))
				openThreeAdvanced++;
			if (isOpenTwo(column, "O")!=0)
				openTwoBeginner+=isOpenTwo(column, "O");
			if (isOpenThree(column, "O"))
				openThreeBeginner++;
			if(isOpenThree(column,"Y"))
				openThreeMaster++;
			if(isOpenTwo(column,"Y")!=0)
				openTwoMaster+=isOpenTwo(column,"Y");
		}

		for (int i = 0; i < 6; i++) {
			int[] position = new int[] { 0, i }; // [0,0],[0,1],[0,2],[0,3],[0,4],[0,5]
			String diagonal = getDiagonal1(position); // from leftUp to right
			//System.out.println("diagonal1 : " + diagonal); // Down
			if (isOpenThree(diagonal, "X")) {
			//	System.out.println("open three X");
				openThreeAdvanced++;
			}

			if (isOpenThree(diagonal, "O")) {
				openThreeBeginner++;
				//System.out.println("open three O");
			}
			if(isOpenThree(diagonal,"Y")) {
				openThreeMaster++;
			}

			if (isOpenTwo(diagonal, "X")!=0) {
				openTwoAdvanced+=isOpenTwo(diagonal, "X");
			//	System.out.println("open two X");
			}

			if (isOpenTwo(diagonal, "O")!=0) {
				openTwoBeginner+=isOpenTwo(diagonal, "O");
				//System.out.println("open two O");
			}
			
			if(isOpenTwo(diagonal,"Y")!=0) {
				openTwoMaster+=isOpenTwo(diagonal,"Y");
			}

			if (i != 5 && i != 0) {
				int[] transposition = new int[] { i, 0 };
				diagonal = getDiagonal1(transposition);
				//System.out.println("diagonal2 " + diagonal);
				if (isOpenThree(diagonal, "X")) {
					//System.out.println("open three X");
					openThreeAdvanced++;
				}

				if (isOpenThree(diagonal, "O")) {
					openThreeBeginner++;
					//System.out.println("open three O");
				}
               
				if(isOpenThree(diagonal,"Y")) {
					openThreeMaster++;
				}
				if (isOpenTwo(diagonal, "X")!=0) {
					openTwoAdvanced+=isOpenTwo(diagonal, "X");
					//System.out.println("open two X");
				}

				if (isOpenTwo(diagonal, "O")!=0) {
					openTwoBeginner+=isOpenTwo(diagonal, "O");
					//System.out.println("open two O");
				}
				if(isOpenTwo(diagonal,"Y")!=0) {
					openTwoMaster+=isOpenTwo(diagonal,"Y");
				}

			}
			diagonal = getDiagonal2(position); // from rightUp to leftDown
			//System.out.println("diagonal3 " + diagonal);
			if (isOpenThree(diagonal, "X")){
				//System.out.println("open three X");
				openThreeAdvanced++;
			}
				
			if (isOpenThree(diagonal, "O")){
				openThreeBeginner++;
				//System.out.println("open three O");
			}
			if(isOpenThree(diagonal,"Y")) {
				openThreeMaster++;
			}
				
			if (isOpenTwo(diagonal, "X")!=0) {
				openTwoAdvanced+=isOpenTwo(diagonal, "X");
				//System.out.println("open two X");
			}
				
			if (isOpenTwo(diagonal, "O")!=0) {
				openTwoBeginner+=isOpenTwo(diagonal, "O");
				//System.out.println("open two O");
			}
			if(isOpenTwo(diagonal,"Y")!=1) {
				openTwoMaster+=isOpenTwo(diagonal,"Y");
			}
				
			if (i != 5 && i != 0) {
				int[] array = new int[] { i, 5 };
				diagonal = getDiagonal2(array);
				//System.out.println("diagonal4 " + diagonal);
				if (isOpenThree(diagonal, "X")){
				//	System.out.println("open three X");
					openThreeAdvanced++;
				}
					
				if (isOpenThree(diagonal, "O")){
					openThreeBeginner++;
				//	System.out.println("open three O");
				}
				
				if(isOpenThree(diagonal,"Y")) {
					openThreeMaster++;
				}
					
				if (isOpenTwo(diagonal, "X")!=0) {
					openTwoAdvanced+=isOpenTwo(diagonal, "X");
					//System.out.println("open two X");
				}
					
				if (isOpenTwo(diagonal, "O")!=0) {
					openTwoBeginner+=isOpenTwo(diagonal, "O");
					//System.out.println("open two O");
				}
				if(isOpenTwo(diagonal,"Y")!=0) {
					openTwoMaster+=isOpenTwo(diagonal,"Y");
				}

			}

		}

	}

	public String getDiagonal1(int[] position) { // leftUp->rightDown
		String result = new String();
		int row = position[0];
		int column = position[1];
		while (row < 5 && column < 6) {
			result = result + configuration[row][column];
			row++;
			column++;
		}
		return result;
	}

	public String getDiagonal2(int[] position) { // rightUp->leftDown
		String result = new String();
		int row = position[0];
		int column = position[1];
		while (row < 5 && column >= 0) {
			result = result + configuration[row][column];
			//System.out.println(configuration[row][column]);
			row++;
			column--;
		}
		return result;
	}

	public boolean isOpenThree(String str, String pattern) {
		if (str.length() <= 3)
			return false;

		boolean result = false;
		for (int i = 0; i < str.length() - 3; i++) {
			if (pattern.equals("X")) {
				if (str.substring(i, i + 4).equals("MXXX")) {
					result = true;

				}

				if (str.substring(i, i + 4).equals("XXXM")) {
					result = true;

				}
			}
			if (pattern.equals("O")) {
				if (str.substring(i, i + 4).equals("MOOO")) {
					result = true;

				}

				if (str.substring(i, i + 4).equals("OOOM")) {
					result = true;

				}
			}
			
			if (pattern.equals("Y")) {
				if (str.substring(i, i + 4).equals("MYYY")) {
					result = true;

				}

				if (str.substring(i, i + 4).equals("YYYM")) {
					result = true;

				}
			}
		}

		return result;
	}

	public int isOpenTwo(String str, String pattern) {
		if (str.length() <= 2)
			return 0;
		int res = 0;

		boolean result = false;
		if (pattern.equals("X")) {
		for (int i = 0; i < str.length() - 2; i++) {
			
				if (str.substring(i, i + 3).equals("MXX")) {
					if (i + 3 < str.length() && str.charAt(i + 3) != 'X') {
						//result = true;
						result = true;
						break;
						
					}
					if((i + 3) == str.length()) {
						//result = true;
						result = true;
						break;
						
					}
				}

				if (str.substring(i, i + 3).equals("XXM")) {
					if (i - 1 >= 0 && str.charAt(i - 1) != 'X') {
						//result = true;
						result = true;
						break;
						
					}
					if(i == 0) {
						//result = true;
						//System.out.println(str+"4");
						result = true;
						//System.out.println(result);
						
					}
				}
				

			}
		if(!result) res = 0;
		else {
			if(str.equals("XXMXX")) 
				res = 2;
			else res = 1;
		}
		
		
}
			
		if (pattern.equals("O")) {
			for (int i = 0; i < str.length() - 2; i++) {
				
					if (str.substring(i, i + 3).equals("MOO")) {
						if (i + 3 < str.length() && str.charAt(i + 3) != 'O') {
							//result = true;
							result = true;
							break;
							
						}
						if((i + 3) == str.length()) {
							//result = true;
							result = true;
							break;
							
						}
					}

					if (str.substring(i, i + 3).equals("OOM")) {
						if (i - 1 >= 0 && str.charAt(i - 1) != 'O') {
							//result = true;
							result = true;
							break;
							
						}
						if(i == 0) {
							//result = true;
							//System.out.println(str+"4");
							result = true;
							//System.out.println(result);
							
						}
					}
					

				}
			if(!result) res = 0;
			else {
				if(str.equals("OOMOO")) 
					res = 2;
			
			    else res = 1;
			
			}
	
		}
			
		if (pattern.equals("Y")) {
			for (int i = 0; i < str.length() - 2; i++) {
				
					if (str.substring(i, i + 3).equals("MYY")) {
						if (i + 3 < str.length() && str.charAt(i + 3) != 'Y') {
							//result = true;
							result = true;
							break;
							
						}
						if((i + 3) == str.length()) {
							//result = true;
							result = true;
							break;
						
						}
					}

					if (str.substring(i, i + 3).equals("YYM")) {
						if (i - 1 >= 0 && str.charAt(i - 1) != 'Y') {
							//result = true;
							result = true;
							break;
							
						}
						if(i == 0) {
							//result = true;
							//System.out.println(str+"4");
							result = true;
							//System.out.println(result);
							
						}
					}
					

				}
			if(!result) res = 0;
			else {
				if(str.equals("YYMYY")) 
					res = 2;
				else res = 1;
			}
			
			
		}
		

		return res;
	}
	
	public boolean hasBlank(){
		boolean result = false;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				if(configuration[i][j].equals("M")) {
					result = true;
					break;
				}
			}
			if(result) break;
		}
		return result;
	}
	
	public int gameOver() {
		int result = 3; // 3-> not over; 1-> beginer win; 2-> advanced win; 4-> master win  5-> tie,no blank
		String str1 = configuration[0][0] + configuration[1][1] + configuration[2][2] + configuration[3][3] + configuration[4][4];
		String str2 = configuration[0][1] + configuration[1][2] + configuration[2][3] + configuration[3][4] + configuration[4][5];
		String str3 = configuration[0][2] + configuration[1][3] + configuration[2][4] + configuration[3][5];
		String str4 = configuration[1][0] + configuration[2][1] + configuration[3][2] + configuration[4][3];
		
		String str5 = configuration[0][5] + configuration[1][4] + configuration[2][3] + configuration[3][2] + configuration[4][1];
		String str6 = configuration[0][4] + configuration[1][3] + configuration[2][2] + configuration[3][1] + configuration[4][0];
		String str7 = configuration[0][3] + configuration[1][2] + configuration[2][1] + configuration[3][0];
		String str8 = configuration[1][5] + configuration[2][4] + configuration[3][3] + configuration[4][2];
		String[] array = new String[]{str1,str2,str3,str4,str5,str6,str7,str8};
		if(!hasBlank()) {
			 result = 5;
			}
		for(int i = 0; i < 8; i++) {
			if(array[i].contains("XXXX")){
				result = 2;
				break;
			}
		    if(array[i].contains("OOOO")) {
				result = 1;
				break;
			}
		    
		    if(array[i].contains("YYYY")) {
				result = 4;
				break;
			}
		}
		
		for(int i = 0; i < 5; i++) {   // check row
			String str = new String();
			for(int j = 0; j <6; j++) {
				str = str + configuration[i][j];
			}
			if(str.contains("XXXX")){
				result = 2;
				break;
			}
			if(str.contains("OOOO")) {
				result = 1;
				break;
			}
			if(str.contains("YYYY")) {
				result = 4;
				break;
			}
			 
		}
		
		for(int i = 0; i < 6; i++) {
			String str = new String();
			for(int j = 0; j < 5; j++) {
				str = str + configuration[j][i];
			}
			if(str.contains("OOOO")) {
				result = 1;
				break;
			}
			if(str.contains("XXXX")) {
				result = 2;
				break;
			}
			if(str.contains("YYYY")) {
				result = 4;
				break;
			}
		}
		
		// check whether has blank left 
		
		return result;
	}

	/*public static void main(String args[]) {
		Board instance = new Board();
		String[][] array = new String[][] { { "O", "X", "X", "M", "M", "X" }, { "X", "O", "O", "M", "X", "X" },
				{ "M", "M", "M", "M", "O", "O" }, { "X", "O", "X", "O", "O", "O" }, { "O", "X", "O", "O", "X", "O" } };
	//	String[][] array = new String[][] {{"O","M","O","X","M","M"},{"M","O","X","M","X","M"},{"O","X","O","M","O","M"},{"X","M","M","O","M","M"},{"M","M","O","O","O","M"}};
		instance.configuration = array;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++)
				System.out.print(instance.configuration[i][j]);
			System.out.println("");
		}
		instance.setParamter();
		int openTwoAdvanced = instance.openTwoAdvanced;
		int openTwoBeginner = instance.openTwoBeginner;
		//int openThreeAdvanced = instance.openThreeAdvanced;
		int openThreeBeginner = instance.openThreeBeginner;
		System.out.println(openTwoAdvanced);
		System.out.println(openTwoBeginner);
		//System.out.println(openThreeAdvanced);
		//System.out.println(openThreeBeginner);
	}*/


}
