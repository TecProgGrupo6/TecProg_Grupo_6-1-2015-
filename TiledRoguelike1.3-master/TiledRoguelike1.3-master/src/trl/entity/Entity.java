package trl.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;

import trl.main.Game;
import trl.map.Map;
import trl.map.Node;
import trl.map.Room;

public class Entity{

	// Represent on place in the map
	protected Node loc;

	// Represent the map
	protected Map map;

	// Image in buffered
	protected BufferedImage image;

	// Describe if the player can see or not
	protected boolean seenByPlayer;

	// Describe if can be visible for the player or not
	protected boolean visibleToPlayer;

	public Entity ( Map map ){

		this.map = map;
	}

	public void setLoc ( Node node ){

		this.loc = node;
	}

	public Node getLoc (){

		return this.loc;
	}

	public BufferedImage getImage (){

		return this.image;
	}

	public int getAxisX (){

		return this.loc.getAxisX();
	}

	public int getAxisY (){

		return this.loc.getAxisY();
	}

	/*
	 * This could be part of the issue with enemies becoming aware of the player
	 * when it seems like they should not.
	 */
	public boolean inDisplayedNodes (){

		int axisX = Map.displayedNodesMinX;
		int axisY = Map.displayedNodesMinY;

		boolean inXrange = getAxisX() >= axisX && getAxisX() < axisX + Game.W_COLS;
		boolean inYrange = getAxisY() >= axisY && getAxisY() < axisY + Game.W_ROWS;

		if ( inXrange && inYrange ){

			return true;
		}else{

			return false;
		}

	}

	public boolean seenByPlayer (){

		return seenByPlayer;
	}

	public void setSeenByPlayer ( boolean seen ){

		this.seenByPlayer = seen;
	}

	public Room getOccupiedRoom ( Node node ){

		Room[][] rooms = map.getRooms();
		Point position = new Point( node.getAxisX() , node.getAxisY() );

		for ( int x = 0 ; x < rooms.length ; x++ ){

			for ( int y = 0 ; y < rooms[0].length ; y++ ){

				if ( rooms[x][y].getBoundary().contains( position ) ){

					return rooms[x][y];
				}else{

					// Nothing to do

				}
			}
		}
		return null;
	}

	public boolean getSeenByPlayer (){

		return seenByPlayer;
	}

	public boolean getVisibleToPlayer (){

		return visibleToPlayer;
	}

	public void setVisibleToPlayer ( boolean visibleToPlayer ){

		this.visibleToPlayer = visibleToPlayer;
	}
}
