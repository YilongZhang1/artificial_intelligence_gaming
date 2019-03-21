import java.util.*;
import java.util.Random;
public class Advanced {
	int nodeNum = 0;
   public int[] miniMax(Board state,String pattern,int depth2) { // return an action
      boolean flag = false;
	  int[] result = new int[2];
	  List<int[]> actions = actions(state);
	  
	  int Max = Integer.MIN_VALUE;
	
	  for(int i = 0; i < actions.size(); i++) {
		 if(depth2 == 2) { // advanced
			 Board neighbor = result(state,actions.get(i),"X");
			 
			 int temp = minValue(neighbor,depth2, pattern);
		  if(temp > Max) {
			  flag = true;
			  Max = temp;
			 
			  result[0] = actions.get(i)[0];
			  result[1] = actions.get(i)[1];
		  }
		 }
		 if(depth2 == 4) { // master
			 Board neighbor = result(state,actions.get(i),"Y");
			 
			 int temp = minValue(neighbor,depth2,pattern);
		  if(temp > Max) {
			  flag = true;
			  Max = temp;
			 
			  result[0] = actions.get(i)[0];
			  result[1] = actions.get(i)[1];
		  }
		 }
	  }
	  if(flag == false) { // maxmin Integer.MIN_VALUE, so choose an option randomly
		  Random random = new Random();
		  int randomNum = random.nextInt(possibleActions(state).length); // [0 ~ length - 1]
		  result = possibleActions(state)[randomNum];
	  }
	 return result;
    }
   
   public int[][] possibleActions(Board state) {
	   ArrayList<int[]> list = new ArrayList<int[]>();
	   for(int i = 0; i < 5; i++) {
		   for(int j = 0; j < 6; j++) {
			   if(state.configuration[i][j].equals("M")) {
				   int[] temp = {i,j};
				   list.add(temp);
			   }
		   }
	   }
	   int[][] result = new int[list.size()][2];
	   for(int i = 0; i < result.length; i++) {
		   result[i][0] = list.get(i)[0];
		   result[i][1] = list.get(i)[1];
	   }
	   return result;
   }
   
   public Board result(Board state, int[] action,String pattern) {
	   nodeNum++;
	   Board neighbor = new Board();
	   String[][] array = new String[5][6];
	   for(int i = 0; i < 5; i++) {
		   for(int j = 0; j < 6; j++) {
			   array[i][j] = state.configuration[i][j];
		   }
	   }
	 //  System.out.println("");
	   array[action[0]][action[1]] = pattern;
	   neighbor.configuration = array;
	   neighbor.depth = state.depth + 1;
	   return neighbor;
   }
   
   public int minValue(Board state,int depth,String pattern) {
	  // System.out.println("MIN:" + "depth :"+ state.depth);
	  /* System.out.println("MIN:");
	   for(int i = 0; i < 5; i++) {
		   for(int j = 0; j < 6; j++) {
			   System.out.print(state.configuration[i][j]);
		   }
		   System.out.println("");
	   }
	   System.out.println("");*/
	//   System.out.println("hello world");
	   
	  // System.out.println("MIN:" + "depth :"+ state.depth);
	   if(pattern.equals("X") && state.gameOver() == 2)   { // 要改
		   return Integer.MIN_VALUE;
		   
	   } 
	   
	   if(pattern.equals("O") && state.gameOver() == 1)   { // 要改
		   return Integer.MIN_VALUE;
		   
	   } 
	   if(depth == 2 && state.gameOver() == 2)   { // 要改
		   return Integer.MAX_VALUE;
		   
	   } 
	   if(depth == 4 && state.gameOver() == 4)   { // 要改
		   return Integer.MAX_VALUE;
		   
	   } 
	  
	   if(state.depth == depth){ //|| !state.hasBlank()) {
		   state.setParamter();
		  // if(state.gameOver() == 3){
		   if(depth == 2 && pattern.equals("O"))
			   return (state.openThreeAdvanced -state.openThreeBeginner) *3 + state.openTwoAdvanced - state.openTwoBeginner;
		   if(depth == 4 && pattern.equals("O"))
			   return (state.openThreeMaster -state.openThreeBeginner) *3 + state.openTwoAdvanced - state.openTwoBeginner;
		   if(depth == 4 && pattern.equals("X"))
			   return (state.openThreeMaster -state.openThreeAdvanced) *3 + state.openTwoMaster - state.openTwoAdvanced;
		   if(depth == 2 && pattern.equals("Y"))
			   return (state.openThreeAdvanced -state.openThreeMaster) *3 + state.openTwoAdvanced - state.openTwoMaster;
		   
		  // }
		  /* else {
			   if(state.gameOver() == 1)   {
				   return Integer.MIN_VALUE;
				   
			   } // beginner win 
			   if(state.gameOver() == 2) {
                 return Integer.MAX_VALUE;
		   }*/
	   }
	  
	   int min = Integer.MAX_VALUE;
	   List<int[]> actions = actions(state);
	   for(int i = 0; i < actions.size(); i++) {
		   min = Math.min(min,maxValue(result(state,actions.get(i),pattern),depth,pattern));
	   }
	   return min;
   }
   
   public int maxValue(Board state,int depth,String pattern) {
	 //  System.out.println("MAX:" + "depth :"+ state.depth);
	   
	   if(pattern.equals("X") && state.gameOver() == 2)   { // 要改
		   return Integer.MIN_VALUE;
		   
	   } 
	   
	   if(pattern.equals("O") && state.gameOver() == 1)   { // 要改
		   return Integer.MIN_VALUE;
		   
	   } 
	   if(depth == 2 && state.gameOver() == 2)   { // 要改
		   return Integer.MAX_VALUE;
		   
	   } 
	   if(depth == 4 && state.gameOver() == 4)   { // 要改
		   return Integer.MAX_VALUE;
		   
	   } // beginner win 
	   if(state.depth == depth || !state.hasBlank()) {
		   state.setParamter();
		   //if(state.gameOver() == 3){
		   if(depth == 2 && pattern.equals("O"))
			   return (state.openThreeAdvanced -state.openThreeBeginner) *3 + state.openTwoAdvanced - state.openTwoBeginner;
		   if(depth == 4 && pattern.equals("O"))
			   return (state.openThreeMaster -state.openThreeBeginner) *3 + state.openTwoAdvanced - state.openTwoBeginner;
		   if(depth == 4 && pattern.equals("X"))
			   return (state.openThreeMaster -state.openThreeAdvanced) *3 + state.openTwoMaster - state.openTwoAdvanced;
		   if(depth == 2 && pattern.equals("Y"))
			   return (state.openThreeAdvanced -state.openThreeMaster) *3 + state.openTwoAdvanced - state.openTwoMaster;
			 
		   //}
		  
	  }
	   int max = Integer.MIN_VALUE;
	   List<int[]> actions = actions(state);
	   for(int i = 0; i < actions.size(); i++) {
		   if(depth == 2) { 
		   max = Math.max(max, minValue(result(state,actions.get(i),"X"),depth,pattern));
		   }
		   if(depth == 4) {
			   max = Math.max(max, minValue(result(state,actions.get(i),"Y"),depth,pattern));
		   }
	   }
	   return max;
   }
 
   
 
   public List<int[]> actions(Board state) {
	   ArrayList<int[]> neighbors = new ArrayList<int[]>();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				if(state.configuration[i][j].equals("M")) {
					int[] neighbor = new int[2];
					neighbor[0] = i;neighbor[1] = j;
					neighbors.add(neighbor);
					
				}
			}
		}
		return neighbors;
   }
   
   
	  // Advanced advanced = new Advanced();
	  // Board board = new Board();
	 //  String[][] configuration = {{"O","O","X","X","O","X"},{"X","O","O","X","O","M"},{"O","X","X","O","O","M"},{"O","X","X","O","X","M"},{"O","O","X","O","O","M"}};
	 //  board.configuration = configuration;
	   //int[] result = advanced.miniMax(board);
	 //  System.out.print(result[0]);
	   //System.out.print(result[1]);
	   // below are test case 
	  // String[][] configuration = {{"O","O","X","X","O","X"},{"X","O","O","X","O","X"},{"O","X","X","O","O","M"},
			 //  {"O","X","X","O","X","O"},{"O","O","X","O","O","M"}}; // 15 25 -1
	  // String[][] configuration = {{"O","O","X","X","O","X"},{"X","O","O","X","O","X"},{"O","X","X","O","O","O"},
			   //{"O","X","X","O","X","M"},{"O","O","X","O","O","M"}}; // 15 35 -1
	// String[][] configuration = {{"O","O","X","X","O","X"},{"X","O","O","X","O","X"},{"O","X","X","O","O","O"},
	  // {"O","X","X","O","X","O"},{"O","O","X","O","O","X"}}; // 15 45 0 
	   
	// String[][] configuration = {{"O","O","X","X","O","X"},{"X","O","O","X","O","O"},{"O","X","X","O","O","X"},
	  // {"O","X","X","O","X","M"},{"O","O","X","O","O","M"}}; // 25 15 -1
	// String[][] configuration = {{"O","O","X","X","O","X"},{"X","O","O","X","O","M"},{"O","X","X","O","O","X"},
	 //  {"O","X","X","O","X","M"},{"O","O","X","O","O","O"}}; // 25 45 -1
	 // String[][] configuration = {{"O","O","X","X","O","X"},{"X","O","O","X","O","M"},{"O","X","X","O","O","X"},
	 //  {"O","X","X","O","X","O"},{"O","O","X","O","O","M"}}; // 25 35 -2
	// String[][] configuration = {{"O","O","X","X","O","X"},{"X","O","O","X","O","O"},{"O","X","X","O","O","M"},
	  // {"O","X","X","O","X","X"},{"O","O","X","O","O","M"}}; // 35 15 后面 的剪枝了 -2
	 
	// String[][] configuration = {{"O","O","X","X","O","X"},{"X","O","O","X","O","O"},{"O","X","X","O","O","M"},
	 //  {"O","X","X","O","X","M"},{"O","O","X","O","O","X"}}; // 45 15 
	 
	// String[][] configuration = {{"O","O","X","X","O","X"},{"X","O","O","X","O","M"},{"O","X","X","O","O","O"},
	   //{"O","X","X","O","X","M"},{"O","O","X","O","O","X"}}; // 45 25 
	 
	/*public static void main(String[] args) {
		  Advanced advanced = new Advanced();
		  Board board = new Board();
		  String[][] configuration = {{"X","X","X","M","M","M"},{"M","M","M","M","M","M"},{"M","M","M","M","M","M"},{"M","M","M","M","M","M"},{"M","M","M","M","M","M"}};
		  board.configuration = configuration;
		  int[] position = advanced.miniMax(board,"X",4);
		  //System.out.println(position[0]);
		  //System.out.println(position[1]);
		  System.out.println(advanced.nodeNum);
	  }*/
   }

