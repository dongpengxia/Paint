//Oval.java
//Dongpeng Xia
//Oval is a child of the PaintItem class.

package paint;

import java.awt.Graphics;

//Oval
public class Oval extends PaintItem implements java.io.Serializable
{
	//length and width of oval (graphically, in pixels)
	protected int length;
	protected int width;
		
	//constructor with parameters for x, y, length, width, and color
	public Oval( int xCoord, int yCoord, int l, int w, char c )
	{
		x = xCoord;
		y = yCoord;
		length = l;
		width = w;
		color = c;
		
	}//end Oval( int, int, int, int, char )
	
	//moveTo moves the oval so that its top left corner is at (xCoord, yCoord)
	@Override
	public void moveTo(int xCoord, int yCoord)
	{
		x = xCoord - length/2;
		y = yCoord - width/2;
		
	}//end moveTo(int, int)
	
	//inside returns true if (xCoord, yCoord) is inside the oval, false otherwise
	@Override
	public boolean inside(int xCoord, int yCoord)
	{
		//if an ellipse is centered at (centerX, centerY), 
		//with x-radius xRadius and y-radius yRadius, 
		//then a point (xCoord, yCoord) is in the ellipse 
		//if (xCoord-centerX)^2/xRadius^2 + (yCoord-centerY)^2/yRadius^2 <= 1
		
		double centerX = x + length/2.0;
		double centerY = y + width/2.0;
		double xRadius = length/2.0;
		double yRadius = width/2.0;
		
		return ( ( Math.pow( xCoord - centerX, 2 ) ) / ( Math.pow( xRadius, 2 ) ) + ( Math.pow( yCoord - centerY, 2 ) ) / ( Math.pow( yRadius, 2 ) ) <= 1 );
	
	}//end inside(int, int)

	//drawMe draws a filled oval
	@Override
	public void drawMe( Graphics g ) 
	{
		super.drawMe(g); //PaintItem sets the color
	    g.fillOval( x, y, length, width );
	    
	}//end drawMe( Graphics )
	
	//getters and setters
	public int getLength() { return length; }
	public int getWidth() { return width; }
	public void setLength( int l ) { length = l; }
	public void setWidth( int w ) { width = w; }
		
}//end Oval class