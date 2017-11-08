//TextItem.java
//Dongpeng Xia
//TextItem is a child of the PaintItem class with a string for its textual content.

package paint;

import java.awt.Graphics;

//TextItem
public class TextItem extends PaintItem implements java.io.Serializable
{
	//textual content of the TextItem
	protected String text;
		
	//constructor with parameters for x, y, text, and color
	public TextItem( int xCoord, int yCoord, String content, char c )
	{
		x = xCoord;
		y = yCoord;
		text = content;
		color = c;
		
	}//end TextItem( int, int, String, char )
	
	//drawMe writes the text to the screen
	@Override
	public void drawMe( Graphics g ) 
	{
		super.drawMe(g); //PaintItem sets the color
		g.drawString(text, x, y);
	    
	}//end drawMe( Graphics )
	
	//inside returns true if (xCoord, yCoord) is near the text item on the screen, false otherwise
	@Override
	public boolean inside(int xCoord, int yCoord)
	{
		//constants found by testing (one char ~ 7 pixels)
		return ( x <= xCoord && xCoord <= x + text.length()*7 && y - 20 <= yCoord && yCoord <= yCoord + 20);
		
	}//end inside(int, int)

	//getters and setters
	public String getText() { return text; }
	public void setText( String content ) { text = content; }
		
}//end TextItem class