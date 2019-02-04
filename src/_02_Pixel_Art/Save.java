package _02_Pixel_Art;

import java.io.Serializable;

public class Save implements Serializable {
	
	private static final long serialVersionUID = 4344490158969245218L;
	
	public final Pixel[][] grid;
	
	public Save(Pixel[][] grid) {
		this.grid = grid;
	}
	
}
