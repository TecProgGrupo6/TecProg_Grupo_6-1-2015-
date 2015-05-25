package trl.map;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import trl.map.feature.Feature;

public class Room{

	// Represents the width atribute of the room
	private int width;
	// Represents the height atrobite of the room
	private int height;
	// Represents the row atribute of the room
	private int row;
	// Represents the column atribute of the room
	private int column;
	// Coordinate x
	private int x;
	// Coordinate y
	private int y;
	// Represents the hole map
	private Map map;
	// Returns in the method connectTo
	private boolean connected;
	// List to add objects of the type room
	private List<Room> connectedTo;
	// Representation of the boundarie of the map
	private Rectangle boundary;
	
	private final static Logger LOGGER = Logger.getLogger( Room.class.getName() );


	public Room ( Map map, int row, int column ){

		this.row = row;
		this.column = column;
		this.map = map;
		this.connected = false;
		init();
	}

	public void init (){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Room intialized");

		Random r = new Random();
		this.width = (int) ( r.nextDouble() * Map.MAX_ROOM_WIDTH );
		this.height = (int) ( r.nextDouble() * Map.MAX_ROOM_HEIGHT );
		if ( this.width < Map.MIN_ROOM_WIDTH ){

			this.width = Map.MIN_ROOM_WIDTH;
		}
		if ( this.height < Map.MIN_ROOM_HEIGHT ){

			this.height = Map.MIN_ROOM_HEIGHT;
		}
		this.x = ( this.column * Map.MAX_ROOM_WIDTH ) + ( Map.MAX_ROOM_WIDTH - this.width ) / 2;
		this.y = ( this.row * Map.MAX_ROOM_HEIGHT ) + ( Map.MAX_ROOM_HEIGHT - this.height ) / 2;
		this.boundary = new Rectangle( this.x , this.y , this.width , this.height );
		this.connectedTo = new ArrayList<Room>();
	}

	public void connect ( Room room ){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Connecting room");

		String relationship = getRelationship( room );
		// System.out.println("Room relationship = " + relationship);
		List<Node> connection = new ArrayList<Node>();
		if ( !relationship.equals( "" ) && !this.connectedTo( room ) ){
			// System.out.println("Connecting rooms.");
			/*
			 * StartX, startY should be the node in the outer wall of the
			 * starting room. We should handle this node (set feature to door or
			 * floor) explicitly to prevent a line drawn through from this point
			 * cutting through more than one wall node. We will start
			 * pathfinding from the next adjacent node in the direction we want
			 * to go.
			 */

			int startX = 0 , startY = 0 , endX = 0 , endY = 0;
			// switch(re/ationship) {
			if ( relationship.equals( "above" ) ){
				startY = this.y;
				endY = room.y + room.height - 1;
				startX = this.getCenterX();
				endX = room.getCenterX();
				this.map.getNode( startX , startY ).setFeature( rollDoor() );
				this.map.getNode( endX , endY ).setFeature( rollDoor() );
				startY -= 1;
				endY += 1;
			}else{
				// Nothing
			}
			if ( relationship.equals( "below" ) ){
				startY = this.y + this.height - 1;
				endY = room.y;
				startX = this.getCenterX();
				endX = room.getCenterX();
				this.map.getNode( startX , startY ).setFeature( rollDoor() );
				this.map.getNode( endX , endY ).setFeature( rollDoor() );
				startY += 1;
				endY -= 1;
			}else{
				// Nothing
			}
			if ( relationship.equals( "left" ) ){
				startX = this.x + this.width - 1;
				endX = room.x;
				startY = this.getCenterY();
				endY = room.getCenterY();
				this.map.getNode( startX , startY ).setFeature( rollDoor() );
				this.map.getNode( endX , endY ).setFeature( rollDoor() );
				startX += 1;
				endX -= 1;
			}else{
				// Nothing
			}
			if ( relationship.equals( "right" ) ){
				startX = this.x;
				endX = room.x + room.width - 1;
				startY = this.getCenterY();
				endY = room.getCenterY();
				this.map.getNode( startX , startY ).setFeature( rollDoor() );
				this.map.getNode( endX , endY ).setFeature( rollDoor() );
				startX -= 1;
				endX += 1;
			}else{
				// Nothing
			}
			// }
			Node start = new Node( startX , startY , this.map );
			this.map.createNode( start );
			Node end = new Node( endX , endY , this.map );
			this.map.createNode( end );

			/*
			 * If the connection between nodes is longer than a single node,
			 * find connection
			 */
			if ( !start.equals( end ) ){
				// System.out.println("Connecting rooms with path > 1");
				start = new Node( startX , startY , this.map );
				this.map.createNode( start );
				start.makeFloor();
				end = new Node( endX , endY , this.map );
				this.map.createNode( end );
				end.makeFloor();
				connection = this.map.findRoomConnection( start , end );
			}
			// Connection start and end nodes are the same.
			else{
				this.map.createNode( startX , startY );
				this.map.getNode( startX , startY ).makeFloor();
				connection.add( this.map.getNode( startX , startY ) );
			}
			// System.out.println("Connection list size = " +
			// connection.size());
			// System.out.println("Connecting " + startX + "," + startY + " to "
			// + endX + "," + endY + " via:");
			for ( Node node : connection ){
				this.map.createNode( node );
				node.makeFloor();
			}
			// System.out.println("Connected " + this.column + "," + this.row +
			// " to " + room.column + "," + room.row + " path size " +
			// connection.size());
			this.connectedTo.add( room );
			room.connectedTo.add( this );
		}else{
			// Nothing
		}
	}

	public int getX (){

		return this.x;
	}

	public int getY (){

		return this.y;
	}

	public int getHeight (){

		return this.height;
	}

	public int getCenterX (){

		return this.x + ( this.width / 2 );
	}

	public int getCenterY (){

		return this.y + ( this.height / 2 );

	}

	public int getWidth (){

		return this.width;
	}

	public boolean isConnected (){

		return this.connected;
	}

	public boolean connectedTo ( Room room ){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Verifying connectability with room");

		if ( this.connectedTo != null && this.connectedTo.size() > 0 ){
			if ( this.connectedTo.contains( room ) ){
				// System.out.println(this.toString() + " connected to " +
				// room.toString());
				return true;
			}else{
				// Nothing
			}
		}else{
			// Nothing
		}
		return false;
	}

	public String getRelationship ( Room room ){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Getting relationship with room");

		if ( this.column == room.column && this.row == room.row + 1 ){
			return "above";
		}else{
			// Nothing
		}
		if ( this.column == room.column && this.row == room.row - 1 ){
			return "below";
		}else{
			// Nothing
		}
		if ( this.row == room.row && this.column == room.column + 1 ){
			return "right";
		}else{
			// Nothing
		}
		if ( this.row == room.row && this.column == room.column - 1 ){
			return "left";
		}else{
			// Nothing
		}
		return "";
	}

	public Feature rollDoor (){

		Random r = new Random();
		double doorRoll = r.nextDouble();
		Feature door;
		if ( doorRoll > .90d ){

			door = Node.closedDoor;
		}else{
			door = Node.openDoor;
		}
		return door;
	}

	public Room getOccupiedRoom ( Node node ){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Getting occupied room with node");

		Room[][] rooms = this.map.getRooms();
		Point position = new Point( node.getAxisX() , node.getAxisY() );
		for ( int axisX = 0 ; axisX < rooms.length ; axisX++ ){
			for ( int axisY = 0 ; axisY < rooms[0].length ; axisY++ ){
				if ( rooms[axisX][axisY].boundary.contains( position ) ){
					return rooms[axisX][axisY];
				}else{
					// Nothing
				}
			}
		}
		return null;
	}

	public Room getRandomConnectedRoom (){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Getting random connected room");

		Random random = new Random();
		int randomRoomIndex = (int) ( this.connectedTo.size() * random.nextDouble() );
		// System.out.println("connected rooms size = " + connectedTo.size());
		// System.out.println("connected rooms index = " + randomRoomIndex);
		
		// System.out.println("getRandomConnectedRoom: current room = " +
		// this.toString() + ", next = " + connectedRoom.toString());
		return this.connectedTo.get( randomRoomIndex );
	}

	public Rectangle getBoundary (){

		return this.boundary;
	}

	public Node getRandomNodeInRoom (){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Getting random node in room");

		Random random = new Random();
		// int x = (int)(random.nextDouble() * ((boundary.getMinX() + 1) +
		// boundary.getMaxX()));
		int minX = 0 , maxX = 0 , minY = 0 , maxY = 0 , axisX = 0 , axisY = 0;
		boolean nodePicked = false;
		while ( !nodePicked ){
			minX = (int) this.boundary.getMinX() + 1;
			maxX = (int) this.boundary.getMaxX();
			minY = (int) this.boundary.getMinY() + 1;
			maxY = (int) this.boundary.getMaxY();
			axisX = minX + (int) ( random.nextDouble() * ( maxX - minX ) );
			axisY = minY + (int) ( random.nextDouble() * ( maxY - minY ) );
			if ( this.map.getNode( axisX , axisY ).isFloor() ){
				nodePicked = true;
				break;
			}else{
				// Nothing
			}
		}
		return this.map.getNode( axisX , axisY );
	}

	public List<Room> getConnectedTo (){

		return this.connectedTo;
	}

	public int getRow (){

		return this.row;
	}

	public int getColumn (){

		return this.column;
	}
}
