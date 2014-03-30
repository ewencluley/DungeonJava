package edu.sussex.tele.compiler;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.sussex.tele.game.Game;
import edu.sussex.tele.game.Map;
import edu.sussex.tele.game.Room;
import edu.sussex.tele.game.RoomEvents;
import edu.sussex.tele.ui.MapViewer;
import edu.sussex.tele.ui.RoomButton;

public class BuildGame {

	private static int eventClassNumber =0;
	private static String WRAPPER_CODE_START = "package edu.sussex.tele.game; \n\rimport edu.sussex.tele.game.characters.*;\n\rimport edu.sussex.tele.game.dice.*;\n\rpublic class RoomEvent";
	private static String WRAPPER_CODE_MID = " extends edu.sussex.tele.game.RoomEvents{\n\r"; 
	private static String WRAPPER_CODE_END = "\n\r}"; 
	private static String ENTER_DEF_START = "public void enterRoom(){\n\r";
	private static String ENTER_DEF_END = "\n\r}";
	private static String EXIT_DEF_START = "public void exitRoom(){\n\r";
	private static String EXIT_DEF_END = "\n\r}";
	
	public static Map buildMap(MapViewer map) throws SyntaxErrorException{
		Map theMap= new Map();
		AgentClassLoader classLoader = new AgentClassLoader();
		for(Component roomButton :map.getComponents()){
			List<String> code = new ArrayList<String>();
			RoomButton rb = (RoomButton)roomButton;
			if(rb.isRoomExists()){
				code.add(massageCode(WRAPPER_CODE_START + eventClassNumber + WRAPPER_CODE_MID + ENTER_DEF_START + rb.getEnterRoomCode() + ENTER_DEF_END + EXIT_DEF_START + rb.getExitRoomCode() + EXIT_DEF_END + WRAPPER_CODE_END));
				eventClassNumber++;
				List<ClassInfo> result = null;
				try {
					result = Javac.compile(code);
					Class<?> cls = classLoader.loadThisClass(result.get(0));
					Room room = new Room(rb.x, rb.y,(RoomEvents) cls.newInstance());
					theMap.addRoom(room, rb.x, rb.y);
					room.setEndRoom(rb.isEndRoom());
					room.setBackground(rb.getRoomImage());
					if(rb.isStartRoom()){
						theMap.setStartRoom(room);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return theMap;
	}
	
	private static String massageCode(String code){
		Pattern pattern = Pattern.compile("repeat\\((?<looptimes>[a-zA-Z0-9_]+)\\)");
	    Matcher matcher = pattern.matcher(code);
	    matcher.find();
	    // Check all occurrences
	    code = matcher.replaceAll("for(int i=0; i<${looptimes}; i++)");
	    return code;
	}
}
