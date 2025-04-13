//Memory 38328kb Time 540ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main{
	static class Position{
		int x, y;
		Position(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	static class Wind extends Position{
		int direction; //-1 : 삭제	 0 : 상		1: 하	2:좌		3:우
		
		Wind(int y, int x, int direction){
			super(y, x);
			this.direction = direction;
		}
	}
	
	
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	static int xLimit, yLimit, sitCnt;
	static int[] dirX = {0,0,-1,1};
	static int[] dirY = {-1,1,0,0};
	static int[][] map;
	static boolean[][] sit;
	static ArrayList<Position> airConditional = new ArrayList<>();
	public static void main(String[] args) {
		input();
		workAirConditional();
		printSit();
	}
	
	static void printSit() {
		System.out.println(sitCnt);
	}
	
	static void workAirConditional() {
		for(Position p : airConditional) {
			for(int i =0;i<4;i++) {
				flowWind(new Wind(p.y, p.x, i));
			}
		}
	}
	static void flowWind(Wind wind) {
		Wind w = wind;
		if(!sit[w.y][w.x]) {
			sit[w.y][w.x] = true;
			sitCnt++;
		}
		while(true) {
			w.y += dirY[w.direction];
			w.x += dirX[w.direction];
			if(!isValidPosition(w.y, w.x)) break;
			if(!sit[w.y][w.x]) {
				sit[w.y][w.x] = true;
				sitCnt++;
			}
			
			w = touchObject(w);
			if(w.direction == -1)break;
		}
	}
	static boolean isValidPosition(int y, int x) {
		if(y >= yLimit || y <0 || x>=xLimit || x<0)
			return false;
		return true;
	}
	
	static Wind touchObject(Wind wind) {
		switch(map[wind.y][wind.x]) {
		case 1:
			return touchObject1(wind);
		case 2:
			return touchObject2(wind);
		case 3:
			return touchObject3(wind);
		case 4:
			return touchObject4(wind);
		case 9:
			return new Wind(wind.y, wind.x, -1);
		}
		return wind;
	}
	static Wind touchObject1(Wind wind) {
		if(wind.direction == 2 || wind.direction == 3)
			return new Wind(wind.y, wind.x, -1);
		return wind;
	}	
	static Wind touchObject2(Wind wind) {
		if(wind.direction == 0 || wind.direction == 1)
			return new Wind(wind.y, wind.x, -1);
		return wind;
	}	
	static Wind touchObject3(Wind wind) {
		Wind w = wind;
		switch(wind.direction) {
		case 0:
			w = new Wind(wind.y, wind.x, 3);
			break;
		case 1:
			w = new Wind(wind.y, wind.x, 2);
			break;
		case 2:
			w = new Wind(wind.y, wind.x, 1);
			break;
		case 3:
			w = new Wind(wind.y, wind.x, 0);
			break;
		}
		return w;
	}	
	static Wind touchObject4(Wind wind) {
		Wind w = wind;
		switch(wind.direction) {
		case 0:
			w = new Wind(wind.y, wind.x, 2);
			break;
		case 1:
			w = new Wind(wind.y, wind.x, 3);
			break;
		case 2:
			w = new Wind(wind.y, wind.x, 0);
			break;
		case 3:
			w = new Wind(wind.y, wind.x, 1);
			break;
		}
		return w;
	}	
	
	static void input() {
		yLimit 	= nextInt();
		xLimit 	= nextInt();
		map 	= new int[yLimit][xLimit];
		sit 	= new boolean[yLimit][xLimit];
		
		for(int y= 0;y<yLimit;y++) {
			for(int x= 0;x<xLimit;x++) {
				map[y][x] = nextInt();
				
				if(map[y][x] == 9)
					airConditional.add(new Position(y,x));
			}
		}
	}
	static int nextInt() {
		try {
			st.nextToken();
			return (int)st.nval;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
}