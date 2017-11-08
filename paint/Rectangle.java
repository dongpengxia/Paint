//Rectangle.java
//Dongpeng Xia
//Rectangle is a child of the PaintItem class with a length and a width.

package paint;

import java.awt.Graphics;

//Rectangle
public class Rectangle extends PaintItem implements java.io.Serializable
{
	//length and width of rectangle
	protected int length;
	protected int width;
		
	//constructor with no parameters sets length and width to 0
	public Rectangle()
	{
		x = 0;
		y = 0;
		length = 0;
		width = 0;
		color = ' ';
		
	}//end Rectangle()
		
	//constructor with parameters for x, y, length, width, and color
	public Rectangle( int xCoord, int yCoord, int l, int w, char c )
	{
		x = xCoord;
		y = yCoord;
		length = l;
		width = w;
		color = c;
		
	}//end Rectangle( int, int, int, int, char )
		
	//moveTo moves the rectangle so that it is centered at (xCoord, yCoord)
	@Override
	public void moveTo(int xCoord, int yCoord)
	{
		x = xCoord - length / 2;
		y = yCoord - width / 2;
		
	}//end moveTo(int, int)
	
	//inside returns true if (xCoord, yCoord) is inside the rectangle, false otherwise
	@Override
	public boolean inside(int xCoord, int yCoord)
	{
		return ( x <= xCoord && xCoord <= x + length && y <= yCoord && yCoord <= y + width );
	
	}//end inside(int, int)
	
	//drawMe draws a filled rectangle
	@Override
	public void drawMe( Graphics g ) 
	{
		super.drawMe(g); //PaintItem sets the color
	    g.fillRect( x, y, length, width );
	    
	}//end drawMe( Graphics )
	
	//calculatePerimeter method, perimeter = length + length + width + width
	public int calculatePerimeter()
	{
		return ( length + length + width + width );
			
	}//end calculatePerimeter
		
	//calculateArea method, area = length * width
	public int calculateArea()
	{
		return ( length * width );
		
	}//end calculateArea

	//getters and setters
	public int getLength() { return length; }
	public int getWidth() { return width; }
	public void setLength( int l ) { length = l; }
	public void setWidth( int w ) { width = w; }
		
}//end Rectangle class