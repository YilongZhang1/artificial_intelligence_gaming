import java.util.*;


import java.util.Random;





import java.io.*;
public class StartHere {
	// beginner is represented as O, advanced is represented as X, master is represented as Y. blank space is represented as M
	public int beginnerVShuman(Scanner scan) { // in this game, we use O to represent beginner and X to represent human
		int result = 3; // 3 means even
		Beginer beginer = new Beginer();
		//Scanner scan = new Scanner(System.in);
		String[][] array = new String[5][6];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++)
				array[i][j] = "M";
		}
		Board board = new Board();
		board.configuration = array;
		//board.setParamter();
		/*for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				if(board.configuration[i][j].equals("M")) {
					System.out.println(" ");
				}
				else {
				System.out.print(board.configuration[i][j]);
				}
			
			}
			System.out.println("");
			
		}*/
		System.out.println("");
		while (true) { // Beginner move first and then user move
			//Board board = new Board();
			board.setParamter();
			if (board.openThreeAdvanced == 0) {
				board = beginer.randomMarking(board); // beginner move
														// randomly
			
			} else {
				board = beginer.markingToPreventFourInRow(board);
				
			}
			
			System.out.println("----------------------------------------");
            for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++)
            		
    				System.out.print(board.configuration[i][j]);
    				
    			System.out.println("");
            }
            System.out.println("");
			if (board.gameOver() != 3){
				if(board.gameOver() == 5) {
					result = 5;
					break;
				}
				else {
				result = 1; // 1 means begin wins
				break;
				}
			}
				
            
			int position = scan.nextInt();
			int row = position / 10;
			int column = position % 10;
			board.configuration[row][column] = "X";
		    
			for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++)
            		System.out.print(board.configuration[i][j]);
    			System.out.println("");
            }
			System.out.println("--------------------------------------");
			System.out.println("");
			if (board.gameOver() != 3){
				if(board.gameOver() == 5) {
					result = 5;
					break;
				}
				else {
				result = 2; // 2 means human wins
				break;
				}
			}
			//board = board;

		}
		//scan1.close();
		return result;
	}
	public int beginnerVSadvanced() {
		int result = 3;
		Advanced advanced = new Advanced();
		Beginer beginer = new Beginer();
		
		String[][] array = new String[5][6];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++)
				array[i][j] = "M";
		}
		Board board = new Board();
		board.configuration = array;
	//	board.setParamter();
	/*	for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(board.configuration[i][j]);
			
			}
			System.out.println("");
			
		}*/
		System.out.println("");
		while (true) { // 3 means not over
		//	Board board = new Board();
			
			/*for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 6; j++) {
					board.configuration[i][j] = board.configuration[i][j];
				}
			}*/
			
			System.out.println("----------------------------------------");
            
			//board.setParamter(); // beginer first
			board.setParamter();
           // if(board.openThreeBeginner != 0) {
			if(board.openThreeBeginner != 0) {
            	//board = beginer.markingToGetFourInRow(board);
				board = beginer.markingToGetFourInRow(board);
            }
            else{
            	//if (board.openThreeAdvanced == 0) {
            	if (board.openThreeAdvanced == 0) {
            
				//board = beginer.randomMarking(board); // beginner move
            		board = beginer.randomMarking(board);
            	}										// randomly
				
			 else {
				//board = beginer.markingToPreventFourInRow(board);
				 board = beginer.markingToPreventFourInRow(board);
			
			}
           }
            for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++) {
            		System.out.print(board.configuration[i][j]);
    		
            	}
            	System.out.println("");
            }
			board.setParamter();
            System.out.println("");
            if (board.gameOver() != 3){
            	if(!board.hasBlank() && board.gameOver() != 1) {
            		result = 5;
            		break;
            	}
            	else {
            		result = 1; // 1 means beginner win
            		break;
            	}
            	
            }
            advanced.nodeNum = 0;
            long startTime = System.currentTimeMillis();
            int[] action = advanced.miniMax(board,"O",2); // beginner VS advanced
            long endTime = System.currentTimeMillis();
           
            board.configuration[action[0]][action[1]] = "X";
            
			
			
			for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++)
            		System.out.print(board.configuration[i][j]);
    			System.out.println("");
            }
			 System.out.println("numebr of nodes : " + advanced.nodeNum);
			 System.out.println("it costs : " + (endTime - startTime) + " ms" );
			if (board.gameOver() != 3){
				if(!board.hasBlank() && board.gameOver() != 2) {
					result = 5;
					break;
				}
				else {
					result = 2; // 2 means advanced win
					break;
				}
            	
            }
				
			System.out.println("--------------------------------------");
			System.out.println("");
			//board = board;

		}
		return result;
	}
	public int beginnerVSmaster() {
		int result = 3; // 3 means a tie
		Advanced master = new Advanced();
		Beginer beginer = new Beginer();
		
		String[][] array = new String[5][6];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++)
				array[i][j] = "M";
		}
		Board board = new Board();
		board.configuration = array;
		//board.setParamter();
		/*for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(board.configuration[i][j]);
			
			}
			System.out.println("");
			
		}*/
		System.out.println("");
		while (true) { // 3 means not over
			//Board board = new Board();
			
			/*for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 6; j++) {
					pr.configuration[i][j] = board.configuration[i][j];
				}
			}*/
			
			System.out.println("----------------------------------------");
            
			board.setParamter(); // beginer first
            if(board.openThreeBeginner != 0) {
            	board = beginer.markingToGetFourInRow(board);
            }
            else{
            	if (board.openThreeMaster == 0) {
            
				board = beginer.randomMarking(board); // beginner move
            	}										// randomly
				
			 else {
				board = beginer.markingToPreventFourInRow(board);
			
			}
           }
            for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++) {
            		
    				
    			System.out.print(board.configuration[i][j]);
    			}
    				
    			System.out.println("");
            	}
            	
            
			
            System.out.println("");
            if (board.gameOver() != 3) {
            	if(!board.hasBlank() && board.gameOver() != 1) {
            		result = 5;
            		break;
            	}
            	else {
            		result = 1; // 1 means beginner win
            		break;
            	}
            	
            }
				
            int[] action = master.miniMax(board,"O",4);// beginner VS master
           
            board.configuration[action[0]][action[1]] = "Y";
            
			
			
			for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++)
            		{System.out.print(board.configuration[i][j]);}
    			System.out.println("");
            }
			if (board.gameOver() != 3) {
				if(!board.hasBlank()&& board.gameOver() != 4) {
					result = 5;
					break;
				}
				else {
					result = 4; // 4 means master win

					break;
				}
            	
            }
			System.out.println("--------------------------------------");
			System.out.println("");
			//board = board;

		}
		return result;
	}
    public int advancedVSmaster() {
    	int result = 3;
    	Advanced advanced = new Advanced();
		Advanced master = new Advanced(); // we also use Advanced to create a master, the only difference is set the depth to 4
		
		String[][] array = new String[5][6];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++)
				array[i][j] = "M";
		} // initialize
		Board board = new Board();
		board.configuration = array;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(board.configuration[i][j]);
			
			}
			System.out.println("");
			
		} // print out initial state
		System.out.println("");
		while (true) { // 3 means not over
			//Board board = new Board();
			
			/*for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 6; j++) {
					board.configuration[i][j] = board.configuration[i][j];
				}
			}*/
			
			System.out.println("----------------------------------------");
            
			//board.setParamter(); // advanced play first
			advanced.nodeNum = 0;
            long startTime = System.currentTimeMillis();
            int[] Advancedaction = advanced.miniMax(board,"Y",2);
            long endTime = System.currentTimeMillis();
            board.configuration[Advancedaction[0]][Advancedaction[1]] = "X";
           
            for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++) {
            		System.out.print(board.configuration[i][j] + " ");
            	}
            	System.out.println("");
            }
            System.out.println("number of nodes: " + advanced.nodeNum);
			System.out.println("it costs: " + (endTime - startTime) + "ms");
            System.out.println("");
            if (board.gameOver() != 3){
            	if(!board.hasBlank() && board.gameOver() != 2) {
            		result = 5;
            		break;
            	}
            	else {
            		result = 2; // 2 means advanced win
            		break;
            	}
            	
            }
            master.nodeNum = 0;
            startTime = System.currentTimeMillis();
            int[] masterAction = master.miniMax(board,"X",4);
            endTime = System.currentTimeMillis();
            board.configuration[masterAction[0]][masterAction[1]] = "Y";
            
			
			
			for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++)
            		System.out.print(board.configuration[i][j] + " ");
            	System.out.println("");
            }
			 System.out.println("number of nodes: " + master.nodeNum);
				System.out.println("it costs: " + (endTime - startTime) + "ms");
			 if (board.gameOver() != 3){
				 if(!board.hasBlank() && board.gameOver() != 4) {
					 result = 5;
					 break;
				 }
				 else {
					 result = 4; // 4 means master wins
					 break;
				 }
	            	
	            }
			System.out.println("--------------------------------------");
			System.out.println("");
		//	board = board;

		}
		return result;
    }	
    
    public int advancedVSbeginner() {
        int result = 3; // 3 means a tie
    	Advanced advanced = new Advanced();
		Beginer beginer = new Beginer();
		
		String[][] array = new String[5][6];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++)
				array[i][j] = "M";
		}
		Board board = new Board();
		board.configuration = array;
	//	board.setParamter();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(board.configuration[i][j] + " ");
			
			}
			System.out.println("");
			
		}
		System.out.println("");
		while (true) { // 3 means not over
		//	Board board = new Board();
			
			/*for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 6; j++) {
					board.configuration[i][j] = board.configuration[i][j];
				}
			}*/
			
			System.out.println("----------------------------------------");
            
			int[] action = advanced.miniMax(board,"O",2);
            
            board.configuration[action[0]][action[1]] = "X";
            
			
			
			for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++)
            		System.out.print(board.configuration[i][j] + " ");
            	System.out.println("");
            }
			
			
			
			 if (board.gameOver() != 3) {
				 if(!board.hasBlank()&& board.gameOver() != 2) {
					 result = 5;
					 break;
				 }
				 else {
					 result = 2; // 2 means advanced win
					 break;
				 }
				
			 }
					
			
			
			
			System.out.println("");
			
			board.setParamter(); 
            if(board.openThreeBeginner != 0) {
            	board = beginer.markingToGetFourInRow(board);
            }
            else{
            	if (board.openThreeAdvanced == 0) {
            
				board = beginer.randomMarking(board); // beginner move
            	}										// randomly
				
			 else {
				board = beginer.markingToPreventFourInRow(board);
			
			}
           }
            for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++) {
            		System.out.print(board.configuration[i][j] + " ");
            	}
            	System.out.println("");
            }
			board.setParamter();
            System.out.println("");
           
            if (board.gameOver() != 3) {
            	if(!board.hasBlank()&& board.gameOver() != 1) {
            		result = 5;
            		break;
            	}
            	else{
            		result = 1; // 1 means beginner win
            		 break;
            	}
				
			 }
			System.out.println("--------------------------------------");
			System.out.println("");
			//board = board;

		}
		return result;
    }
    	
	
    public int masterVSbeginner() {
    	int result = 3;// 3 means there is a tie. nobody wins
    	Advanced master = new Advanced();
		Beginer beginer = new Beginer();
		
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
		while (true) { // 3 means not over
			//Board board = new Board();
			
			/*for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 6; j++) {
					board.configuration[i][j] = board.configuration[i][j];
				}
			}*/
			
			System.out.println("----------------------------------------");
            
			 int[] action = master.miniMax(board,"O",4);
	          
	            board.configuration[action[0]][action[1]] = "Y";
	            
				
				
				for(int i = 0; i < 5; i++) {
	            	for(int j = 0; j < 6; j++)
	            		System.out.print(board.configuration[i][j] + " ");
	            	System.out.println("");
	            }
			
			
				  if (board.gameOver() != 3){
					  if(!board.hasBlank()&& board.gameOver() != 4) {
						  result = 5;
						  break;
					  }
					  else {
						  result = 4; // 4 means master win
						  break;
					  }
					  
				  }
						
			
				  System.out.println("");
			board.setParamter(); // beginer first
            if(board.openThreeBeginner != 0) {
            	board = beginer.markingToGetFourInRow(board);
            }
            else{
            	if (board.openThreeMaster == 0) {
            
				board = beginer.randomMarking(board); // beginner move
            	}										// randomly
				
			 else {
				board = beginer.markingToPreventFourInRow(board);
			
			}
           }
            for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++) {
            		System.out.print(board.configuration[i][j] + " ");
            	}
            	System.out.println("");
            }
            
            if (board.gameOver() != 3){
            	if(!board.hasBlank() && board.gameOver() != 1) {
            		result = 5;
            		break;
            	}
            	else {
            		result = 1; // 1 means beginner win
            		 break;
            	}
				 
			  }
            
			
          
            
           
			System.out.println("--------------------------------------");
			System.out.println("");
			//board = board;
			board.setParamter();

		}
		return result;
    }
    
    public int masterVSadvanced() {
    	int result = 3;
    	Advanced advanced = new Advanced();
		Advanced master = new Advanced(); // we also use Advanced to create a master, the only difference is set the depth to 4
		
		String[][] array = new String[5][6];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++)
				array[i][j] = "M";
		} // initialize
		Board board = new Board();
		board.configuration = array;
		board.setParamter();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(board.configuration[i][j]);
			
			}
			System.out.println("");
			
		} // print out initial state
		System.out.println("");
		while (true) { // 3 means not over
			//Board board = new Board();
			
			/*for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 6; j++) {
					board.configuration[i][j] = board.configuration[i][j];
				}
			}*/
			
			System.out.println("----------------------------------------");
            
			
			int[] masterAction = master.miniMax(board,"X",4);
           
            board.configuration[masterAction[0]][masterAction[1]] = "Y";
            
			
			
			for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++)
            		System.out.print(board.configuration[i][j] + " ");
            	System.out.println("");
            }
			
			 if (board.gameOver() != 3) {
				 if(!board.hasBlank() && board.gameOver() != 4) {
					 result = 5;
					 break;
				 }
				 else {
					 result = 4; // 4 means master wins
					 break;
				 }
				
			 }
					
			board.setParamter(); // advanced play first
			 System.out.println("");
            int[] Advancedaction = advanced.miniMax(board,"Y",2);
          
            board.configuration[Advancedaction[0]][Advancedaction[1]] = "X";
           
            for(int i = 0; i < 5; i++) {
            	for(int j = 0; j < 6; j++) {
            		System.out.print(board.configuration[i][j] + " ");
            	}
            	System.out.println("");
            }
			
            System.out.println("");
           
            if (board.gameOver() != 3) {
            	if(!board.hasBlank() && board.gameOver() != 2) {
            		result = 5;
            		break;
            	}
            	else{
            		result = 2; // 2 means advanced wins
            		 break;
            	}
				
			 }
			System.out.println("--------------------------------------");
			System.out.println("");
			//board = board;

		}
		return result;
    }
    
    public void tournament() {       

    	int[] beginnerAndadvanced = new int[100];
    	int[] beginnerAndmaster = new int[100];
    	int[] advancedAndmaster = new int[100];
    	
    	for(int i = 0; i < 50; i++) {
    		beginnerAndadvanced[i] = beginnerVSadvanced();
    		
    		System.out.println("i =" + i); 
    		if(beginnerAndadvanced[i] == 1) {
    			System.out.println("beginner wins");
    		}
    		if(beginnerAndadvanced[i] == 2) {
    			System.out.println("advanced wins");
    		}
    		if(beginnerAndadvanced[i] == 5) {
    			System.out.println("there is a tie");
    		}
    	}
    	for(int i = 50; i < 100; i++) {
    		beginnerAndadvanced[i] = advancedVSbeginner();
    		
    		System.out.println("i =" + i); 
    		if(beginnerAndadvanced[i] == 1) {
    			System.out.println("beginner wins");
    		}
    		if(beginnerAndadvanced[i] == 2) {
    			System.out.println("advanced wins");
    		}
    		if(beginnerAndadvanced[i] == 5) {
    			System.out.println("there is a tie");
    		}
    	}
    	
    	
    	
    	
    	for(int i = 0; i < 50; i++) {
    		
    		beginnerAndmaster[i] = beginnerVSmaster();
    		if(beginnerAndmaster[i] == 1) {
    			System.out.println("beginner wins");
    		}
    		if(beginnerAndmaster[i] == 4) {
    			System.out.println("master wins");
    		}
    		if(beginnerAndmaster[i] == 5) {
    			System.out.println("there is a tie");
    		}
    		
    		
    	}
    	for(int i = 50; i < 100; i++) {
    		
    		beginnerAndmaster[i] = masterVSbeginner();
    		if(beginnerAndmaster[i] == 1) {
    			System.out.println("beginner wins");
    		}
    		if(beginnerAndmaster[i] == 4) {
    			System.out.println("master wins");
    		}
    		if(beginnerAndmaster[i] == 5) {
    			System.out.println("there is a tie");
    		}
    		
    		
    		
    	}
    	
    	
    	
             for(int i = 0; i < 50; i++) {
    		
    		advancedAndmaster[i] = advancedVSmaster();
    		if(advancedAndmaster[i] == 2) {
    			System.out.println("advanced wins");
    		}
    		if(advancedAndmaster[i] == 4) {
    			System.out.println("master wins");
    		}
    		if(advancedAndmaster[i] == 5) {
    			System.out.println("there is a tie");
    		}
    		
    		
    		
    	}
    	for(int i = 50; i < 100; i++) {
    		
    		advancedAndmaster[i] = masterVSadvanced();
    		
    		if(advancedAndmaster[i] == 2) {
    			System.out.println("advanced wins");
    		}
    		if(advancedAndmaster[i] == 4) {
    			System.out.println("master wins");
    		}
    		if(advancedAndmaster[i] == 5) {
    			System.out.println("there is a tie");
    		}
    		
    	}
    	
    	
    	
    }
   
    
    public static void main(String[] args) {

    	StartHere instance = new StartHere();
       System.out.println("For part II,enter 2,For part III, enter 3 and so on." + "M means blank,O represent beginner, X represent advanced, Y represent master ");
       
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
		 int caseNumber = Integer.parseInt(scan.next());
		 if(caseNumber == 1) {
		 int result = instance.beginnerVShuman(scan);
		 if(result == 1) System.out.println("beginner wins");
		 if(result == 2) System.out.println("human wins");
		 if(result == 5) System.out.println("nobody wins, there is a tie");
			
		 }
		 if(caseNumber == 2) {
			 
			int result = instance.beginnerVSadvanced();
			  if(result == 1) System.out.println("beginner wins");
				 if(result == 2) System.out.println("advanced wins");
				 if(result == 5) System.out.println("nobody wins, there is a tie");
					
			
		 }
		 if(caseNumber == 3) {
			 
			int result = instance.advancedVSmaster();
			 if(result == 2) System.out.println("advanced wins");
			 if(result == 4) System.out.println("master wins");
			 if(result == 5) System.out.println("nobody wins, there is a tie");
				
			
		 }
		 if(caseNumber == 4) {
			 instance.tournament();
		 }
	}
		
		scan.close();
		
		
		
	}
    
}
