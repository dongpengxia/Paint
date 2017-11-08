//Paint, a tool to draw items and save/load drawings. Contains a list of paint items.
//Dongpeng Xia

package paint;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

//Paint
public class Paint extends JFrame implements MouseListener, ActionListener, MouseMotionListener
{	
	char currentColor; 	//color of next paint item to add
	int downX, downY;  	//where the mouse is when mouse button is pressed
	int mouseX, mouseY; 	//mouse last seen at
	int upX, upY; 		//where mouse is when mouse button is released
	
	boolean dragging;		//true if currently dragging a paint item in paint
	boolean makingLine;		//true if currently drawing a line
	boolean makingRectangle;	//true if currently drawing a rectangle
	boolean makingOval;		//true if currently drawing an oval
	boolean makingTextItem;	//true if currently drawing a text item
	int makingTriangle;		//0 if not currently drawing a triangle, 1 if drawing first vertex, 2 if drawing second vertex, 3 if drawing third vertex
	
	Line currentLine;			//line being drawn
	Rectangle currentRectangle;	//rectangle being drawn
	Oval currentOval;			//oval being drawn
	String currentText;			//text item being drawn
	Triangle currentTriangle;	//triangle being drawn
	PaintItem draggedItem;		//item being dragged
	PaintItemList paintItems;	//linked-list of paint items
	
	JButton makeTriangleButton;	//button to make triangles
	JButton makeRectangleButton;	//button to make rectangles
	JButton makeOvalButton;		//button to make ovals
	JButton makeLineButton;		//button to make lines
	JButton makeTextButton;		//button to make text items
	JButton savePaint;  			//button to save paint file
	JButton loadPaint;  			//button to load paint file
	
	//main
   	public static void main ( String[] args )
   	{
   		//call constructor
   		new Paint();
   		
   	}//end main()
   
   	//constructor
   	public Paint()
   	{
   		//not making or dragging any items at start
   		setMakingToFalse();
   		
   		//no color or text set yet
   		currentColor = ' ';
   		currentText = "";
   		
   		//initialize list of paint items
   		paintItems = new PaintItemList();
   		
   		//GUI settings
   		setDefaultCloseOperation(EXIT_ON_CLOSE);
   		setTitle("PAINT");
   		setSize(800, 800);
   		getContentPane().setBackground(Color.WHITE);
   		
   		//Introduce Paint
   		showInstructions();
   		
   		//Layout settings
   		setLayout(new FlowLayout());

   		//Buttons
   		JPanel panelOfButtons = new JPanel();
   	    panelOfButtons.setBackground(Color.WHITE);
   	    panelOfButtons.setLayout(new FlowLayout());
   		makeTriangleButton = new JButton("TRIANGLE");
   		makeTriangleButton.addActionListener(this);
   		makeRectangleButton = new JButton("RECTANGLE");
   		makeRectangleButton.addActionListener(this);
   		makeOvalButton = new JButton("OVAL");
   		makeOvalButton.addActionListener(this);
   		makeLineButton = new JButton("LINE");
   		makeLineButton.addActionListener(this);
   		makeTextButton = new JButton("TEXT");
   		makeTextButton.addActionListener(this);
   		savePaint = new JButton("SAVE");
   		savePaint.addActionListener(this);
   		loadPaint = new JButton("LOAD"); 
   		loadPaint.addActionListener(this);
   	    panelOfButtons.add(makeTriangleButton);
   	    panelOfButtons.add(makeRectangleButton);
   	    panelOfButtons.add(makeOvalButton);
   	    panelOfButtons.add(makeLineButton);
   	    panelOfButtons.add(makeTextButton);
   	    panelOfButtons.add(savePaint);
   	    panelOfButtons.add(loadPaint);
   	    add(panelOfButtons);
   		
   	    //add listeners to paint
   		addMouseListener(this);
   		addMouseMotionListener(this);
   		
   		//show paint
   		setVisible(true);
   		
   	}//end Paint()
   	
   	//pickAColor() lets the user select a color for the next paint item
   	public void pickAColor()
   	{
   		String[] colors = {"Red", "Yellow", "Orange", "Green", "Blue", "Pink", "Magenta", "Black", "Grey"};
   		
   		//Ask user for color of next item
   		int colorIndex = JOptionPane.showOptionDialog(this, "Pick a color:", "SELECT COLOR", 0, JOptionPane.QUESTION_MESSAGE, null, colors, "Grey");
   		
   		//Set currentColor variable based on which color button was clicked
   		switch (colorIndex)
   		{
   			case 0: 
   				currentColor = 'r';
   				break;
   			case 1:
   				currentColor = 'y';
   				break;
   			case 2:
   				currentColor = 'o';
   				break;
   			case 3:
   				currentColor = 'g';
   				break;
   			case 4:
   				currentColor = 'b';
   				break;
   			case 5:
   				currentColor = 'p';
   				break;
   			case 6:
   				currentColor = 'm';
   				break;
   			case 7:
   				currentColor = 'l';
   				break;
   			default:
   				currentColor = '?';
   				
   		}//end switch(colorIndex)
   		
   	}//end pickAColor()
   	
   	//opens a dialog asking for text for next text item
   	public String askForText()
   	{
   		String result = JOptionPane.showInputDialog("Please enter text:");
   		if(result == null)
   		{
   			//better to return an empty space than null
   			result = " ";
   		}
   		return result;
   		
   	}//end askForText()
   	
   	//Handlers for buttons
   	@Override
    public void actionPerformed( ActionEvent e )
    {
   		//about to make a triangle
   		if ( e.getSource() == makeTriangleButton ) 
   		{
   			setMakingToFalse();
   			pickAColor();
   			makingTriangle = 1; //start with first point
    	   	}
   		//about to make a rectangle
   		else if ( e.getSource() == makeRectangleButton ) 
   		{
   			setMakingToFalse();
   			pickAColor();
   			makingRectangle = true;
   		}
   		//about to make an oval
   		else if ( e.getSource() == makeOvalButton ) 
   		{
   			setMakingToFalse();
   			pickAColor();
   			makingOval = true;
   		}
   		//about to make a line
   		else if ( e.getSource() == makeLineButton )
   		{
   			setMakingToFalse();
   			pickAColor();
   			makingLine = true;
   		}
   		//about to make a text item
   		else if ( e.getSource() == makeTextButton ) 
   		{
   			setMakingToFalse();
   			pickAColor();
   			currentText = askForText();
   			makingTextItem = true;
   		}
   		//save current paint file
   		else if ( e.getSource() == savePaint ) 
   		{
   			//ask user for file name to save as
   			String fileName = JOptionPane.showInputDialog( "Please enter the name of the file you want to save (no extensions, ie 'myPainting')" );
   			
   			//try to write list of paint items to file (serialized)
   			try
   			{
   				//make sure fileName is valid
   				if(fileName != null && !fileName.equals(""))
   				{
		   	        	FileOutputStream savedFile = new FileOutputStream(fileName + ".ser");
		   	        	ObjectOutputStream savedObject = new ObjectOutputStream(savedFile);
		   	        	savedObject.writeObject(paintItems); //write list of paint items
		   	        savedObject.close();
		   	        savedFile.close();
   				}
   				
   			}//end try
   			catch(IOException error) 
   			{
   				System.out.println("Error saving data.");
   				
   			}//end catch
   		}
   		//load a saved paint file
   		else if ( e.getSource() == loadPaint )
   		{
   			String fileName = JOptionPane.showInputDialog( "Please enter the name of the file you want to load (no extensions, ie 'myPainting')" );
   			
   			//try to read list of paint items from file
   			try 
   			{	
   				//make sure fileName is valid
   				if(fileName != null && !fileName.equals(""))
   				{
	   				FileInputStream savedFile = new FileInputStream(fileName + ".ser");
		   	        ObjectInputStream savedObject = new ObjectInputStream(savedFile);
		   	        paintItems = (PaintItemList) savedObject.readObject(); //read list of paint items
		   	        savedObject.close();
		   	        savedFile.close();
   				}
   				
   			}//end try
   			catch(IOException error) 
   			{
   				System.out.println("Error loading data.");
   				
   			}//end catch
   			catch(ClassNotFoundException error) 
   			{
	   	         System.out.println("No PaintItemList class.");
	   	         
   			}//end catch
   		}
   		repaint();
    }//end actionPerformed(ActionEvent)
   	
   	//showInstructions opens up a dialog box with a description of Paint
   	private void showInstructions()
   	{
   		JOptionPane.showMessageDialog(null, 	"                                    Welcome to Paint!\n" +
											"Experience art like never before! Draw ovals, rectangles, and lines by dragging.\n" +
											"Make triangles by clicking three times for three vertices. Write messages to the \n" +
											"screen after entering text by clicking on the screen. Move any item by dragging.\n" +
											"Save and load your work any time you want, in any file you want!");
   	}//end showInstructions()
   
   	//MouseClick Handler
   	@Override public void mouseClicked ( MouseEvent m )
   	{	
   		//making a text item
   		if(makingTextItem)
   		{
   			paintItems.add(new TextItem(m.getX(), m.getY(), currentText, currentColor));
   			makingTextItem = false;
   		}
   		//making a triangle
   		else if(makingTriangle > 0)
   		{
   			if(makingTriangle == 1)
   			{
   				//vertex 1
   				currentTriangle = new Triangle(m.getX(), m.getX(), m.getX(), m.getY(), m.getY(), m.getY(), currentColor);
   				paintItems.add(currentTriangle);
   				makingTriangle++;
   			}
   			else if(makingTriangle == 2)
   			{
   				//vertex 2
   				currentTriangle.setXCoord2(m.getX());
   				currentTriangle.setYCoord2(m.getY());
   				makingTriangle++;
   			}
   			else if(makingTriangle == 3)
   			{
   				//vertex 3
   				currentTriangle.setXCoord3(m.getX());
   				currentTriangle.setYCoord3(m.getY());
   				makingTriangle = 0; //finished the triangle
   			}
   		}
   		repaint();
   }//end mouseClicked(MouseEvent)
   
   //Overridden paint draws all the items in paint
   @Override
   public void paint( Graphics g )
   {
      super.paint(g);
      paintItems.drawMe(g);      
   }//end paint(Graphics)
   
   //Handler when mouse button is pressed
   @Override
   public void mousePressed(MouseEvent e) 
   {
	   //record mouse location on screen
	   mouseX = downX = e.getX(); 
	   mouseY = downY = e.getY();
	   
	   //making a line
	   if(makingLine)
	   {
		   currentLine = new Line(downX, downY, downX, downY, currentColor);
		   paintItems.add(currentLine);
	   }
	   //making a rectangle
	   else if(makingRectangle)
	   {
		   currentRectangle = new Rectangle(downX, downY, 0, 0, currentColor);
		   paintItems.add(currentRectangle);
	   }
	   //making an oval
	   else if(makingOval)
	   {
		   currentOval = new Oval(downX, downY, 0, 0, currentColor);
		   paintItems.add(currentOval);
	   }
	   else
	   {
		   //go through the list to see if any shapes are getting dragged
		   draggedItem = paintItems.findPaintItem(downX, downY);
		   if(draggedItem != null)
		   {
			   //something is about to get dragged/is getting dragged
			   dragging = true;
		   }
	   }
	   repaint();
	   
   }//end mousePressed(MouseEvent)
   
   //Handler when mouse button is released.
   @Override
   public void mouseReleased(MouseEvent e) 
   {
	   //record mouse location
	   mouseX = upX = e.getX();
	   mouseY = upY = e.getY();
	   
	   //making a line
	   if(makingLine)
	   {
		   currentLine.setEndX(upX);
		   currentLine.setEndY(upY);
		   makingLine = false;
	   }
	   //making a rectangle
	   else if(makingRectangle)
	   {
		   currentRectangle.setX(Math.min(upX, downX));
		   currentRectangle.setY(Math.min(upY, downY));
		   currentRectangle.setLength(Math.abs(upX - downX));
		   currentRectangle.setWidth(Math.abs(upY - downY));
		   makingRectangle = false;
	   }
	   //making an oval
	   else if(makingOval)
	   {
		   currentOval.setX(Math.min(upX, downX));
		   currentOval.setY(Math.min(upY, downY));
		   currentOval.setLength(Math.abs(upX - downX));
		   currentOval.setWidth(Math.abs(upY - downY));
		   makingOval = false;
	   }
	   //moving an item by dragging
	   else if(dragging)
	   {
		   draggedItem.moveTo(upX, upY);
		   dragging = false;
	   }
	   repaint();
   }
   
   //Mouse handler when mouse is dragged
   @Override
   public void mouseDragged(MouseEvent e) 
   {
	   //record mouse location
	   mouseX = e.getX();
	   mouseY = e.getY();
	   
	   //making a line
	   if(makingLine)
	   {
		   currentLine.setEndX(mouseX);
		   currentLine.setEndY(mouseY);
	   }
	   //making a rectangle
	   else if(makingRectangle)
	   {
		   currentRectangle.setX(Math.min(downX, mouseX));
		   currentRectangle.setY(Math.min(downY, mouseY));
		   currentRectangle.setLength(Math.abs(mouseX - downX));
		   currentRectangle.setWidth(Math.abs(mouseY - downY));
	   }
	   //making an oval
	   else if(makingOval)
	   {
		   currentOval.setX(Math.min(downX, mouseX));
		   currentOval.setY(Math.min(downY, mouseY));
		   currentOval.setLength(Math.abs(mouseX - downX));
		   currentOval.setWidth(Math.abs(mouseY - downY));
	   }
	   //dragging an item across screen
	   else if(dragging)
	   {
		   draggedItem.moveTo(mouseX, mouseY);
	   }
	   repaint();
   }//end mouseDragged(MouseEvent)
   
   //Other mouse handlers
   @Override
   public void mouseEntered(MouseEvent e) {}
   @Override
   public void mouseExited(MouseEvent e) {}
   @Override
   public void mouseMoved(MouseEvent e) {}
   
   //private method sets all indicators so that nothing is being drawn or dragged
   private void setMakingToFalse()
   {
	   makingLine = false;
	   makingRectangle = false;
	   makingOval = false;
	   makingTextItem = false;
	   makingTriangle = 0;
	   dragging = false;
	   
   }//end setMakingToFalse
   
}//end Paint