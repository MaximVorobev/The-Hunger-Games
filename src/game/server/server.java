package src.game.server;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;


public class server extends Listener {

	static Server server;
	static final int port = 27960;
	static Map<Integer, Player> players = new HashMap<Integer, Player>();
	
	public static void main(String[] args) throws IOException{
		server = new Server();
		server.getKryo().register(PacketUpdateX.class);
		server.getKryo().register(PacketUpdateY.class);
		server.getKryo().register(PacketAddPlayer.class);
		server.getKryo().register(PacketRemovePlayer.class);
		server.bind(port, port);
		server.start();
		server.addListener(new server());
		System.out.println("The server is ready");
	}
	
	public void connected(Connection c){
		Player player = new Player();
		player.x = 256;
		player.y = 256;
		player.c = c;
		
		PacketAddPlayer packet = new PacketAddPlayer();
		packet.id = c.getID();
		server.sendToAllExceptTCP(c.getID(), packet);
		
		for(Player p : players.values()){
			PacketAddPlayer packet2 = new PacketAddPlayer();
			packet2.id = p.c.getID();
			c.sendTCP(packet2);
		}
		
		players.put(c.getID(), player);
		System.out.println("Connection received.");
	}
	
	public void received(Connection c, Object o){
		if(o instanceof PacketUpdateX){
			PacketUpdateX packet = (PacketUpdateX) o;
			players.get(c.getID()).x = packet.x;
			
			packet.id = c.getID();
			server.sendToAllExceptUDP(c.getID(), packet);
			System.out.println("received and sent an update X packet");
			
		}else if(o instanceof PacketUpdateY){
			PacketUpdateY packet = (PacketUpdateY) o;
			players.get(c.getID()).y = packet.y;
			
			packet.id = c.getID();
			server.sendToAllExceptUDP(c.getID(), packet);
			
		}
	}
	
	public void disconnected(Connection c){
		players.remove(c.getID());
		PacketRemovePlayer packet = new PacketRemovePlayer();
		packet.id = c.getID();
		server.sendToAllExceptTCP(c.getID(), packet);
		System.out.println("Connection dropped.");
	}
}
