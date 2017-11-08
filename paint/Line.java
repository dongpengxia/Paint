//Line.java
//Dongpeng Xia
//Line is a child of the PaintItem class.

package paint;

import java.awt.Graphics;

//Line
public class Line extends PaintItem implements java.io.Serializable
{
	//end-X coordinate and end-Y coordinate of line (one endpoint of the line segment, the other endpoint is (x, y) from PaintItem)
	protected int endX;
	protected int endY;
	
	//constructor with parameters for x, y, end-X, end-Y, and color
	public Line( int xCoord, int yCoord, int eX, int eY, char c )
	{
		x = xCoord;
		y = yCoord;
		endX = eX;
		endY = eY;
		color = c;
		
	}//end Line(int, int, int, int, char)
	
	
	//moveTo moves line so that its top left corner is at (xCoord, yCoord)
	@Override
	public void moveTo(int xCoord, int yCoord)
	{
		endX += xCoord - x;
		endY += yCoord - y;
		x = xCoord;
		y = yCoord;
		
	}//end moveTo(int, int)
	
	//inside returns true if (xCoord, yCoord) is near the line segment, false otherwise
	@Override
	public boolean inside(int xCoord, int yCoord)
	{
		boolean inside = false;
		
		//find slope and intercept of line
		double slope = (double)(endY - y) / (double)(endX - x);
		double intercept = endY - slope * endX;
		
		//check if point is near line segment
		if(Math.min(x, endX) <= xCoord && xCoord <= Math.max(x, endX))
		{
			if(Math.abs(yCoord - (slope * xCoord + intercept)) <= 10)
			{
				inside = true;
			}
		}
		return inside;
		
	}//end inside(int, int)
	
	//drawMe draws a line
	@Override
	public void drawMe( Graphics g ) 
	{
		super.drawMe(g); //PaintItem sets the color
		g.drawLine( x, y, endX, endY );
	
	}//end drawMe(Graphics)

	//getters and setters
	public int getEndX() { return endX; }
	public int getEndY() { return endY; }
	public void setEndX( int x ) { endX = x; }
	public void setEndY( int y ) { endY = y; }
		
}//end Line class