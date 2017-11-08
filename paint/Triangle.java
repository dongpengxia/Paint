//Triangle.java
//Dongpeng Xia
//Triangle is a child of the PaintItem class.

package paint;

import java.awt.Graphics;

//Triangle
public class Triangle extends PaintItem implements java.io.Serializable
{
	//3 pairs of (x, y) coordinates of a triangle
	int xCoord1;
	int yCoord1;
	int xCoord2;
	int yCoord2;
	int xCoord3;
	int yCoord3;
	
	//constructor with parameters
	public Triangle( int x1, int x2, int x3, int y1, int y2, int y3, char c)
	{		
		//3 pairs of coordinates for 3 vertices
		xCoord1 = x1;
		xCoord2 = x2;
		xCoord3 = x3;
		yCoord1 = y1;
		yCoord2 = y2;
		yCoord3 = y3;
		
		//find top-left corner of triangle
		calcXY();
		
		//set color
		color = c;
		
	}//end Triangle( int, int, int, int, int, int, char )
	
	//moveTo moves the triangle so that its approximate center is at (xCoord, yCoord)
	@Override
	public void moveTo(int xCoord, int yCoord)
	{
		int centerX = (calcMax(xCoord1, xCoord2, xCoord3) + calcMin(xCoord1, xCoord2, xCoord3)) / 2;
		int centerY = (calcMax(yCoord1, yCoord2, yCoord3) + calcMin(yCoord1, yCoord2, yCoord3)) / 2;
		
		xCoord1 += xCoord - centerX;
		xCoord2 += xCoord - centerX;
		xCoord3 += xCoord - centerX;
		
		yCoord1 += yCoord - centerY;
		yCoord2 += yCoord - centerY;
		yCoord3 += yCoord - centerY;
		
		calcXY();
		
	}//end moveTo(int, int)
	
	//Inside returns true if (xCoord, yCoord) is inside the triangle, false otherwise
	//https://math.stackexchange.com/questions/51326/determining-if-an-arbitrary-point-lies-inside-a-triangle-defined-by-three-points
	@Override
	public boolean inside(int xCoord, int yCoord)
	{
		//make sure the three vertices are distinct
		if( !( ( xCoord1 == xCoord2 && yCoord1 == yCoord2 ) || ( xCoord2 == xCoord3 && yCoord2 == yCoord3 ) || ( xCoord1 == xCoord3 && yCoord1 == yCoord3 ) ) )
		{
			boolean side1 = whichSideOfLine( xCoord, yCoord, xCoord3, yCoord3, xCoord1, yCoord1 );
			boolean side2 = whichSideOfLine( xCoord, yCoord, xCoord1, yCoord1, xCoord2, yCoord2 );
			boolean side3 = whichSideOfLine( xCoord, yCoord, xCoord2, yCoord2, xCoord3, yCoord3 );
		
			//see if the point is inside the triangle
			return side1 == side2 && side2 == side3;
		}
		return false;
		
	}//end inside(int, int)
	
	//whichSideOfLine returns a boolean indicating which side of the line from (point1X, point1Y) to (point2X, point2Y) that point (xCoord, yCoord) is on
	//https://math.stackexchange.com/questions/51326/determining-if-an-arbitrary-point-lies-inside-a-triangle-defined-by-three-points
	private boolean whichSideOfLine( int xCoord, int yCoord, int point1X, int point1Y, int point2X, int point2Y)
	{
		return ( ( point1X - point2X ) * ( yCoord - point2Y ) - ( xCoord - point2X ) * ( point1Y - point2Y ) > 0 );
		
	}//end whichSideOfLine(int, int, int, int, int, int)
	
	//drawMe draws a triangle
	@Override
	public void drawMe( Graphics g ) 
	{
		super.drawMe(g); //PaintItem sets the color
		
		int[] xList = { xCoord1, xCoord2, xCoord3 };
		int[] yList = { yCoord1, yCoord2, yCoord3 };
		g.fillPolygon( xList, yList, 3);
	
	}//end drawMe( Graphics )
	
	//calcMin returns the minimum value of 3 integers
	private int calcMin(int a, int b, int c) { return Math.min( Math.min( a, b ), c ); }
	
	//calcMax returns the maximum value of 3 integers
	private int calcMax(int a, int b, int c) { return Math.max( Math.max( a, b ), c ); }
	
	//calcXY calculates and sets the x-coordinate and y-coordinate of the triangle's top-left corner based on the triangle's 3 vertices
	private void calcXY()
	{
		//find top-left corner of triangle
		x = calcMin(xCoord1, xCoord2, xCoord3);
		y = calcMin(yCoord1, yCoord2, yCoord3);
		
	}//end calcXY()
	
	//getters and setters
	public int getXCoord1() { return xCoord1; }
	public int getXCoord2() { return xCoord2; }
	public int getXCoord3() { return xCoord3; }
	public int getYCoord1() { return yCoord1; }
	public int getYCoord2() { return yCoord2; }
	public int getYCoord3() { return yCoord3; }
	public void setXCoord1( int x ) 
	{ 
		xCoord1 = x; 
		calcXY();
	}
	public void setXCoord2( int x ) 
	{ 
		xCoord2 = x; 
		calcXY();
	}
	public void setXCoord3( int x ) 
	{
		xCoord3 = x;
		calcXY();
	}
	public void setYCoord1( int y ) 
	{
		yCoord1 = y;
		calcXY();
	}
	public void setYCoord2( int y ) 
	{
		yCoord2 = y; 
		calcXY();
	}
	public void setYCoord3( int y ) 
	{ 
		yCoord3 = y; 
		calcXY();
	}
	
}//end Triangle class