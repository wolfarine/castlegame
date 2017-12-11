package castle;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private Room currentRoom;
    private HashMap<String,Handler> handlers = new HashMap<String, Handler>();
    public class Handler {
//    	protected Game game;
//    	public Handler(Game game) {
//    		this.game = game;
//    	}
    	public void doCmd(String word) {
    		
    	}
    	public boolean isBye() {
    		return false;
    	}
    }
    
    public class HandlerBye extends Handler{
//    	public HandlerBye(Game game) {
//    		super(game);
//    	}
    	@Override
    	public boolean isBye() {
    		// TODO Auto-generated method stub
    		return true;
    	}
    	
    }
    
    public class HandlerHelp extends Handler {
//    	public HandlerHelp(Game game) {
//    		super(game);
//    	}
    	@Override
    	public void doCmd(String word) {
    		System.out.println("迷路了吗？你可以做的命令有：go bye help");
    		System.out.println("如：\tgo east");
    	}

    }
    
    public class HandlerGo extends Handler {
//    	public HandlerGo(Game game) {
//    		super(game);
//    	}

    	@Override
    	public void doCmd(String word) {
    		// TODO Auto-generated method stub
    		goRoom(word);
    	}
    	

    }
    
    
        
    public Game() 
    {
//    	handlers.put("go", new HandlerGO());
    	handlers.put("bye", new HandlerBye());
    	handlers.put("help", new HandlerHelp());
    	handlers.put("go", new HandlerGo());
        createRooms();
    }

    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom;
      
        //	制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");
        
        //	初始化房间的出口
        outside.setExit("east", lobby);
        outside.setExit("south",study);
        outside.setExit("west",pub);
        lobby.setExit("west", outside);
        pub.setExit("east", outside);
        study.setExit("north",outside);
        study.setExit("east", bedroom);
        bedroom.setExit("west", study);

        currentRoom = outside;  //	从城堡门外开始
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到城堡！");
        System.out.println("这是一个超级无聊的游戏。");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        showPrompt();
    }

    // 以下为用户命令

//    private void printHelp() 
//    {
//        System.out.print("迷路了吗？你可以做的命令有：go bye help ");
//        System.out.println("如：\tgo east");
//    }
    
    private void showPrompt(){
    	 System.out.println("你在" + currentRoom);
         System.out.print("出口有: ");
         System.out.println(currentRoom.getExitDesc());
         System.out.println();
    }

    public void play() {
    	Scanner in = new Scanner(System.in);
    	while ( true ) {
    		String line = in.nextLine();
    		String[] words = line.split(" ");
    		Handler handler = handlers.get(words[0]);
    		String value = "";
    		if(words.length>1)
    			value = words[1];
    		if (handler != null) {
    			handler.doCmd(value);
    			if ( handler.isBye())
    				break;
    		}

    	}
    	in.close();
    }
    public void goRoom(String direction) 
    {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("那里没有门！");
        }
        else {
            currentRoom = nextRoom;
            showPrompt();
           
        }
    	
    }
	
	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
		Game game = new Game();
		game.printWelcome();   
		game.play();
        
        System.out.println("感谢您的光临。再见！");
//        in.close();
	}

}
