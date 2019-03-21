package TicTacToe_pkg;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

@SuppressWarnings("serial")
public class beginner extends JFrame
{
	// Named-constants for the game board
	public static final int ROWS = 5;  // ROWS by COLS cells
	public static final int COLS = 6;
	 
	// some drawing stuff
	// Named-constants of the various dimensions used for graphics drawing
	public static final int CELL_SIZE = 50; // cell width and height (square)
	public static final int CANVAS_WIDTH = CELL_SIZE * COLS;  // the drawing canvas
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
	public static final int GRID_WIDTH = 4;                   // Grid-line's width
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; // Grid-line's half-width
	// Symbols (X/ O) are displayed inside a cell, with padding from border
	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; // width/height
	public static final int SYMBOL_STROKE_WIDTH = 5; // pen's stroke width
	 
	// Use an enumeration (inner class) to represent the various states of the game
	public enum GameState { PLAYING, DRAW, You_WON, AI_WON }
	private GameState currentState;  // the current game state
	 
	// Use an enumeration (inner class) to represent the seeds and cell contents
	public enum Seed { EMPTY, X, O }
	private Seed currentPlayer;  // the current player
	 
	private Seed[][] board   ; // Game board of ROWS-by-COLS cells
	private DrawCanvas canvas; // Drawing canvas (JPanel) for the game board
	private JLabel statusBar;  // Status Bar
	   
	// used when there exists open 3-in-a-row case
	private int blankX;
	private int blankY;
	 
	/** Constructor to setup the game and the GUI components */
	public beginner() 
	{
		board = new Seed[ROWS][COLS]; // allocate array
		initGame();
		canvas = new DrawCanvas();  // Construct a drawing canvas (a JPanel)
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));	
		while(true)
		{
			// The canvas (JPanel) fires a MouseEvent upon mouse-click
			if(currentPlayer == Seed.X)
			{
				PlayerMove();
			}
			else if(currentPlayer == Seed.O)
			{
				AIMove();
			}

			visualSet();
			
			if(currentState != GameState.PLAYING)
			{
				int res=JOptionPane.showConfirmDialog(null, "Play Again", "Restart", JOptionPane.YES_NO_OPTION);
				if(res==JOptionPane.YES_OPTION)
				{ 
					initGame();
				}
				else
				{
					System.exit(0);
				}				
			}
		}
	}
 
	/** Initialize the game-board contents and the status */
	public void initGame()
	{
		for (int row = 0; row < ROWS; ++row)
		{
			for (int col = 0; col < COLS; ++col)
			{
				board[row][col] = Seed.EMPTY; // all cells empty
			}
		}
		currentState = GameState.PLAYING; // ready to play
		currentPlayer = Seed.O;       // cross plays first
   }
 
	/** Update the currentState after the player with "theSeed" has placed on
	(rowSelected, colSelected). */
	public void updateGame(Seed theSeed, int rowSelected, int colSelected)
	{
		if (hasWon(theSeed, rowSelected, colSelected)) 
		{  // check for win
			currentState = (theSeed == Seed.X) ? GameState.You_WON : GameState.AI_WON;
		}
		else if (isDraw())
		{  // check for draw
			currentState = GameState.DRAW;
		}
		// Otherwise, no change to current state (still GameState.PLAYING).
	}
 
	/** Return true if it is a draw (i.e., no more empty cell) */
	public boolean isDraw()
	{
		for (int row = 0; row < ROWS; ++row) 
		{
			for (int col = 0; col < COLS; ++col) 
			{
				if (board[row][col] == Seed.EMPTY)
				{
					return false; // an empty cell found, not draw, exit
				}
			}
		}
		return true;  // no more empty cell, it's a draw
	}
 
	/** Return true if the player with "theSeed" has won after placing at
		(rowSelected, colSelected) */
	public boolean hasWon(Seed theSeed, int rowSelected, int colSelected) 
	{
		boolean rowWon = false, columnWon = false, diagonalWon = false, oppoDiagonalWon = false;
		// rowWon
		int consecutiveNum = 1;
		for(int i = rowSelected - 1; i >= 0; i--)
		{
			if(board[i][colSelected] == theSeed)
			{
				consecutiveNum++;
			}
			else
			{
				break;
			}
		}
		for(int i = rowSelected + 1; i < ROWS; i++)
		{
			if(board[i][colSelected] == theSeed)
			{
				consecutiveNum++;
			}
			else
			{
				break;
			}
		}	 
		if(consecutiveNum >= 4)
		{
			rowWon = true;
		}
		// columnWon
		consecutiveNum = 1;
		for(int i = colSelected - 1; i >= 0; i--)
		{
			if(board[rowSelected][i] == theSeed)
			{
				consecutiveNum++;
			}
			else
			{
				break;
			}
		}
		for(int i = colSelected + 1; i < COLS; i++)
		{
			if(board[rowSelected][i] == theSeed)
			{
				consecutiveNum++;
			}
			else
			{
				break;
			}
		}	 
		if(consecutiveNum >= 4)
		{
			columnWon = true;
		}	   
	   
		// diagonalWon
		consecutiveNum = 1;
		int i = rowSelected - 1;
		int j = colSelected - 1;
		for( ; i >= 0 && j >= 0; i--, j--)
		{
			if(board[i][j] == theSeed)
			{
				consecutiveNum++;
			}
			else
			{
				break;
			}
		}
		i = rowSelected + 1;
		j = colSelected + 1;
		for( ; i < ROWS && j < COLS; i++, j++)
		{
			if(board[i][j] == theSeed)
			{
				consecutiveNum++;
			}
			else
			{
				break;
			}
		}	 
		if(consecutiveNum >= 4)
		{
			diagonalWon = true;
		}	
	   
		// oppoDiagonalWon
		consecutiveNum = 1;
		i = rowSelected - 1;
		j = colSelected + 1;
		for( ; i >= 0 && j < COLS; i--, j++)
		{
			if(board[i][j] == theSeed)
			{
				consecutiveNum++;
			}
			else
			{
				break;
			}
		}
		i = rowSelected + 1;
		j = colSelected - 1;
		for( ; i < ROWS && j >= 0; i++, j--)
		{
			if(board[i][j] == theSeed)
			{
				consecutiveNum++;
			}
			else
			{
				break;
			}
		}	 
		if(consecutiveNum >= 4)
		{
			oppoDiagonalWon = true;
		}	   
	   
		return (rowWon || columnWon || diagonalWon || oppoDiagonalWon);
	}

	// check if there exists the case open 3-in-a-row
	public boolean open3InARow(Seed theSeed)
	{
		// check each row
		for(int j = 0; j < COLS; j++)
			for(int i = 0; i+3 < ROWS; i++)
			{
				if((board[i][j] == theSeed && board[i+1][j] == theSeed && board[i+2][j] == theSeed &&board[i+3][j] == Seed.EMPTY))
				{
					blankX = i+3;
					blankY = j;
					return true;
				}
				if((board[i][j] == Seed.EMPTY && board[i+1][j] == theSeed && board[i+2][j] == theSeed &&board[i+3][j] == theSeed))
				{
					blankX = i;
					blankY = j;
					return true;
				}			   
			}
		// check each column
		for(int i = 0; i < ROWS; i++)
			for(int j = 0; j+3 < COLS; j++)
			{
				if((board[i][j] == theSeed && board[i][j+1] == theSeed && board[i][j+2] == theSeed &&board[i][j+3] == Seed.EMPTY))
				{
					blankX = i;
					blankY = j+3;
					return true;
				}
				if((board[i][j] == Seed.EMPTY && board[i][j+1] == theSeed && board[i][j+2] == theSeed &&board[i][j+3] == theSeed))
				{
					blankX = i;
					blankY = j;
					return true;
				}			   
			}
				   
		// check each diagonal
		int[][] startPos1 = {{0,0},{0,1},{0,2},{1,0},{1,1},{1,2}};
		for(int k = 0; k < startPos1.length; k++)
		{
			int i = startPos1[k][0];
			int j = startPos1[k][1];
			if((board[i][j] == theSeed && board[i+1][j+1] == theSeed && board[i+2][j+2] == theSeed &&board[i+3][j+3] == Seed.EMPTY))
			{
				blankX = i+3;
				blankY = j+3;		   
				return true;
			}
			if((board[i][j] == Seed.EMPTY && board[i+1][j+1] == theSeed && board[i+2][j+2] == theSeed &&board[i+3][j+3] == theSeed))
			{
				blankX = i;
				blankY = j;			   
				return true;
			}		   
		}
		// check each opposite diagonal
		int[][] startPos2 = {{0,3},{0,4},{0,5},{1,3},{1,4},{1,5}};
		for(int k = 0; k < startPos2.length; k++)
		{
			int i = startPos2[k][0];
			int j = startPos2[k][1];
			if((board[i][j] == theSeed && board[i+1][j-1] == theSeed && board[i+2][j-2] == theSeed &&board[i+3][j-3] == Seed.EMPTY))
			{
				blankX = i+3;
				blankY = j-3;				   
				return true;
			}
			if((board[i][j] == Seed.EMPTY && board[i+1][j-1] == theSeed && board[i+2][j-2] == theSeed &&board[i+3][j-3] == theSeed))
			{
				blankX = i;
				blankY = j;				   
				return true;
			}		   
		}
		return false;
	}

	// player move
	public void PlayerMove()
	{
		canvas.addMouseListener(new MouseAdapter()
		{
			 @Override
			 public void mouseClicked(MouseEvent e) 
			 {  // mouse-clicked handler
				int mouseX = e.getX();
				int mouseY = e.getY();
				// Get the row and column clicked
				int rowSelected = mouseY / CELL_SIZE;
				int colSelected = mouseX / CELL_SIZE;
				 
				if (currentState == GameState.PLAYING) 
				{
				    if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0
				          && colSelected < COLS && board[rowSelected][colSelected] == Seed.EMPTY) 
				    {
				    	board[rowSelected][colSelected] = currentPlayer; // Make a move
				    	updateGame(currentPlayer, rowSelected, colSelected); // update state
				    	// Switch player
						currentPlayer = (currentPlayer == Seed.X) ? Seed.O : Seed.X;
				    }
				}            
				repaint();  // Call-back paintComponent().
			 }
		});		
	}
	// AI move
	public void AIMove()
	{
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		if (currentState == GameState.PLAYING) 
		{
		    if (open3InARow(Seed.O) || open3InARow(Seed.X)) 
		    {
		    	board[blankX][blankY] = currentPlayer; // Make a move
		    	updateGame(currentPlayer, blankX, blankY); // update state				    						
		    }
			else // randomly placing
			{
				Random random = new Random();
				while(true)
				{
					int rowSelected = random.nextInt(ROWS)%(ROWS+1);
					int colSelected = random.nextInt(COLS)%(COLS+1);
					if(board[rowSelected][colSelected] == Seed.EMPTY)
					{
						board[rowSelected][colSelected] = Seed.O;
						updateGame(currentPlayer, rowSelected, colSelected); // update state
						break;
					}
				}
			}
		    // Switch player
		    currentPlayer = (currentPlayer == Seed.X) ? Seed.O : Seed.X;
		}            
		repaint();  // Call-back paintComponent().		
	}
	/**
	 *  Inner class DrawCanvas (extends JPanel) used for custom graphics drawing.
	 *  background + lines + 'X' or 'O' (if any) + message
	 */
	class DrawCanvas extends JPanel 
	{
		@Override
		public void paintComponent(Graphics g) 
		{  	// invoke via repaint()
			super.paintComponent(g);    // fill background
			setBackground(Color.WHITE); // set its background color
 
			// Draw the grid-lines
			g.setColor(Color.LIGHT_GRAY);
			for (int row = 1; row < ROWS; ++row)
			{
				g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF, CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
			}
			for (int col = 1; col < COLS; ++col) 
			{
				g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0, GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
			}
 
			// Draw the Seeds of all the cells if they are not empty
			// Use Graphics2D which allows us to set the pen's stroke
			Graphics2D g2d = (Graphics2D)g;
			// Graphics2D only
			g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));  
			for (int row = 0; row < ROWS; ++row) 
			{
				for (int col = 0; col < COLS; ++col) 
				{
					int x1 = col * CELL_SIZE + CELL_PADDING;
					int y1 = row * CELL_SIZE + CELL_PADDING;
					if (board[row][col] == Seed.X) 
					{
						g2d.setColor(Color.RED);
						int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
						int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
						g2d.drawLine(x1, y1, x2, y2);
						g2d.drawLine(x2, y1, x1, y2);
					}
					else if (board[row][col] == Seed.O)
					{
						g2d.setColor(Color.BLUE);
						g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
					}
				}
			}
		}
	}
	// set some stuff for visual
	public void visualSet()
	{
		// Setup the status bar (JLabel) to display status message
		statusBar = new JLabel("X -- You; O -- AI");
		statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		 
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(canvas, BorderLayout.CENTER);
		cp.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();  // pack all the components in this JFrame
		setTitle("Tic Tac Toe - Beginner Mode");
		setVisible(true);  // show this JFrame	
	}
   /** The entry main() method */
   public static void main(String[] args)
   {
	   new beginner(); // Let the constructor do the job
   }
}