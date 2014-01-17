package com.technotricks.paint.customclass;

import java.util.Stack;

import android.graphics.Bitmap;
import android.graphics.Point;

public class FloodFill {
	private Bitmap _bmp;
	private int _oldColor;
	private int _newColor;
	private int _bmpWidth;
	private int _bmpHeight;
	int count = 0;

	public FloodFill(Bitmap bmp, int oldColor, int newColor) {
		_bmp = bmp;
		_oldColor = oldColor;
		_newColor = newColor;
		_bmpWidth = bmp.getWidth();
		_bmpHeight = bmp.getHeight();                            
	}
	
	public boolean fill(int xInitial, int yInitial) {
		
		Point pt = new Point(xInitial, yInitial);
		Stack<Point> s = new Stack<Point>();
		
		if(_oldColor == _newColor) return true;
	    s.clear();
	    
	    int y1; 
	    Boolean spanLeft, spanRight;
	  
	    s.push(pt);
	    
	    while(true)
	    {    
	    	if(s.empty()|| _bmp==null){             
	    		return true;
	    	}
	    	Point p = s.pop();
	        y1 = p.y;
	        try{
	        while(y1 >= 0 && p.x<_bmp.getWidth()&&_bmp.getPixel(p.x, y1) == _oldColor &&  _bmp.getPixel(p.x, y1)!=-16777216) y1--;
	        y1++;
	        spanLeft = spanRight = false;
	        while(y1 < _bmpHeight && _bmp.getPixel(p.x, y1) == _oldColor &&  _bmp.getPixel(p.x, y1)!=-16777216 )
	        {
	            _bmp.setPixel(p.x, y1, _newColor);
	            if(!spanLeft && p.x > 0 && _bmp.getPixel(p.x-1, y1) == _oldColor) 
	            {	      
	                s.push(new Point(p.x - 1, y1));
	                spanLeft = true;
	            }
	            else if(spanLeft && p.x > 0 && _bmp.getPixel(p.x-1, y1) != _oldColor)
	            {
	                spanLeft =false;
	            }
	            if(!spanRight && p.x < _bmpWidth - 1 && _bmp.getPixel(p.x+1, y1) == _oldColor) 
	            {
	                s.push(new Point(p.x + 1, y1));
	                spanRight = true;
	            }
	            else if(spanRight && p.x < _bmpWidth - 1 && _bmp.getPixel(p.x+1, y1) != _oldColor)
	            {
	                spanRight = false;
	            } 
	            y1++;
	        }
	        }catch (Exception e) {
				// TODO: handle exception
	        	e.printStackTrace();
			} 
	    }
		// TODO : remove before release.
		// Debug.startMethodTracing();
	}
}
