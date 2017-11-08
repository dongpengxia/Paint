//PaintItem.java
//Dongpeng Xia
//PaintItem is an abstract class. It represents an item that can be graphically depicted in Paint.

package paint;

import java.awt.Color;
import java.awt.Graphics;

//PaintItem
public abstract class PaintItem implements java.io.Serializable
{
	protected int x;       //x-coordinate of top-left corner of item (x-coordinate of one of the end points of a line)
	protected int y;       //y-coordinate of top-left corner of item (y-coordinate of one of the end points of a line)
	protected char color;  //color of paint item (red (r), yellow (y), green (g), etc...)
	
	//drawMe method draws the paint item in child classes. Here it just sets the color.
	public void drawMe( Graphics g )
	{
		switch(color)
		{
			case 'r': 
				g.setColor( Color.RED );
				break;
			case 'y':
				g.setColor( Color.YELLOW );
				break;
			case 'o':
				g.setColor( Color.ORANGE );
				break;
			case 'g':
				g.setColor( Color.GREEN );
				break;
			case 'b':
				g.setColor( Color.BLUE );
				break;
			case 'p':
				g.setColor( Color.PINK );
				break;
			case 'm':
				g.setColor( Color.MAGENTA );
				break;
			case 'l':
				g.setColor( Color.BLACK );
				break;
			default:
				g.setColor( Color.GRAY ); //gray is the default color
				
		}//end switch statement for color
	}//end drawMe

	//inside returns true if (xCoord, yCoord) is near the top-left corner of the paint item
	public boolean inside(int xCoord, int yCoord)
	{
		return(Math.abs(x - xCoord) < 50 && Math.abs(y - yCoord) < 50);
		
	}//end inside(int, int)
	
	//moveTo moves the paintItem so that its top-left corner is at (xCoord, yCoord)
	public void moveTo(int xCoord, int yCoord)
	{
		x = xCoord;
		y = yCoord;
		
	}//end moveTo(int, int)
	
	//getters and setters
	public int getX() { return x; }
	public int getY() { return y; }
	public char getColor() { return color; }
	public void setX( int xCoord ) { x = xCoord; }
	public void setY( int yCoord ) { y = yCoord; }
	public void setColor( char c ) { color = c; }
	
}//end PaintItem