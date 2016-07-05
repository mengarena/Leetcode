package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//Design a Snake game that is played on a device with screen size = width x height. 
//Play the game online if you are not familiar with the game.
//
//The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
//
//You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
//
//Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
//
//When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
//
//Example:
//Given width = 3, height = 2, and food = [[1,2],[0,1]].
//
//Snake snake = new Snake(width, height, food);
//
//Initially the snake appears at position (0,0) and the food at (1,2).
//
//|S| | |
//| | |F|
//
//snake.move("R"); -> Returns 0
//
//| |S| |
//| | |F|
//
//snake.move("D"); -> Returns 0
//
//| | | |
//| |S|F|
//
//snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
//
//| |F| |
//| |S|S|
//
//snake.move("U"); -> Returns 1
//
//| |F|S|
//| | |S|
//
//snake.move("L"); -> Returns 2 (Snake eats the second food)
//
//| |S|S|
//| | |S|
//
//snake.move("U"); -> Returns -1 (Game over because snake collides with border)


//Medium
//Google
public class DesignSnakeGame {  //ACC: 83%
	private int width = 0;
	private int height = 0;
	private int[][] food = null;
	private List<Integer> quXY = null;
	private int nextFoodIdx = 0;
	private int lastX = 0;
	private int lastY = 0;
	
	/** Initialize your data structure here.
	    @param width - screen width
	    @param height - screen height 
	    @param food - A list of food positions
	    E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
	public DesignSnakeGame(int width, int height, int[][] food) {
	    this.width = width;
	    this.height = height;
	    this.food = food;
	
	    quXY = new ArrayList<Integer>();
	    
	    quXY.add(Arrays.hashCode(new int[]{0, 0}));
	    
	    nextFoodIdx = 0;
	    lastX = 0;
	    lastY = 0;
	}
	
	
	/** Moves the snake.
	    @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
	    @return The game's score after the move. Return -1 if game over. 
	    Game over when snake crosses the screen boundary or bites its body. */
	public int move(String direction) {
	    if (direction.charAt(0) == 'U') {
	        lastX = lastX - 1;
	        if (lastX < 0) return -1;
	    } else if (direction.charAt(0) == 'L') {
	        lastY = lastY - 1;
	        if (lastY < 0) return -1;
	    } else if (direction.charAt(0) == 'R') {
	        lastY = lastY + 1;
	        if (lastY >= width) return -1;
	    } else {
	        lastX = lastX + 1;
	        if (lastX >= height) return -1;
	    }
	    
	    int newHashCode = Arrays.hashCode(new int[]{lastX, lastY});
	
	    if (food != null && nextFoodIdx < food.length && lastX == food[nextFoodIdx][0] && lastY == food[nextFoodIdx][1]) {
	        nextFoodIdx++;
	    } else {
	        quXY.remove(0);
	    }
	    
	    if (quXY.contains(newHashCode)) return -1;
	    
	    quXY.add(newHashCode);
	    
	    return nextFoodIdx;
	}
}

/**
* Your SnakeGame object will be instantiated and called as such:
* SnakeGame obj = new SnakeGame(width, height, food);
* int param_1 = obj.move(direction);
*/







//ACC:  8%
 class DesignSnakeGameA {

    private int width = 0;
    private int height = 0;
    private int[][] food = null;
    private Deque<Integer> quX = null;
    private Deque<Integer> quY = null;
    private int nextFoodIdx = 0;
    private Set<Integer> hs = null;
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public DesignSnakeGameA(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        hs = new HashSet<Integer>();
        
        quX = new LinkedList<Integer>();
        quY = new LinkedList<Integer>();
        
        quX.offerLast(0);
        quY.offerLast(0);
        
        hs.add(Arrays.hashCode(new int[]{0, 0}));
        
        nextFoodIdx = 0;
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int lastX = quX.peekLast();
        int lastY = quY.peekLast();
        
        if (direction.charAt(0) == 'U') {
            lastX = lastX - 1;
            if (lastX < 0) return -1;
        } else if (direction.charAt(0) == 'L') {
            lastY = lastY - 1;
            if (lastY < 0) return -1;
        } else if (direction.charAt(0) == 'R') {
            lastY = lastY + 1;
            if (lastY >= width) return -1;
        } else {
            lastX = lastX + 1;
            if (lastX >= height) return -1;
        }
        
        quX.offerLast(lastX);
        quY.offerLast(lastY);
        
        int newHashCode = Arrays.hashCode(new int[]{lastX, lastY});

        if (food != null && nextFoodIdx < food.length && lastX == food[nextFoodIdx][0] && lastY == food[nextFoodIdx][1]) {
            nextFoodIdx++;
        } else {
            int tailX = quX.pollFirst();
            int tailY = quY.pollFirst();
            hs.remove(Arrays.hashCode(new int[]{tailX, tailY}));
        }
        
        
        if (hs.contains(newHashCode)) return -1;
        
        hs.add(newHashCode);
        
        return nextFoodIdx;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */

