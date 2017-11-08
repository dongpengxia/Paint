//PaintItemList.java
//Dongpeng Xia
//Contains a list of paint items.

package paint;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

//PaintItemList
public class PaintItemList implements java.io.Serializable
{
	//list of paint items
	protected LinkedList<PaintItem> paintItemList;
   
	//constructor to initialize list of paint items
	public PaintItemList()
	{   
		paintItemList = new LinkedList<PaintItem>();
	   
	}//end PaintItemList()
   
	//add a paint item to end of list
	public boolean add( PaintItem item )
	{
		return paintItemList.add(item);
	   
	}//end add(PaintItem)
   
	//findPaintItem returns the most recent paint item close-to/containing (x, y)
	public PaintItem findPaintItem(int x, int y)
	{
		PaintItem temp;
		Iterator<PaintItem> iter = paintItemList.descendingIterator(); //go backwards to find most recent item that contains point (x,y)
		while( iter.hasNext() )
		{
			temp = iter.next();
			if(temp.inside(x,y))
			{
				return temp; //find most recent paint item with (x, y) inside it
			}
		}
		return null; //no paint item found
	   
	}//end findPaintItem(int, int)
   
	//drawMe draws every paint item in the list
	public void drawMe( Graphics g ) 
	{
		Iterator<PaintItem> iter = paintItemList.iterator();
		while ( iter.hasNext() )
		{
			iter.next().drawMe(g);
		}
 
	}//end drawMe( Graphics )
 	
}//end PaintItemList