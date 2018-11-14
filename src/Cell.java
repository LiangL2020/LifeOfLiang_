import java.awt.*;

/**
 * Created by lianglu on 11/29/17.
 */
public class Cell {
//    private int[][] loc;
//    Cell[][] loc;

    private boolean isAlive;
    private int row, col, size;
//    private Cell[][] loc;

    public Cell(int r, int c, int size){
//        this.loc = loc;
        isAlive = false;
        row = r;
        col = c;
        this.size = size;
    }

    public void kill(){
        isAlive = false;
    }

    public void spawn(){
        isAlive = true;
    }

    public int numNeighbors(Cell[][] grid){
        int count= 0;
        if(row - 1 > -1 && col + 1 < grid[0].length && grid[row - 1][col + 1].getIsAlive())
            count ++;
        if(row - 1 > -1 && grid[row - 1][col].getIsAlive())
            count ++;
        if(row - 1 > -1 && col - 1 > -1 && grid[row - 1][col - 1].getIsAlive())
            count ++;
        if(col + 1 < grid[0].length && grid[row][col + 1].getIsAlive())
            count ++;
//        if(row > -1 && col < grid[0].length && grid[row][col].getIsAlive())
//            count ++;
        if(col - 1 > -1 && grid[row][col - 1].getIsAlive())
            count ++;
        if(row + 1 < grid.length && col + 1 < grid[0].length && grid[row + 1][col + 1].getIsAlive())
            count ++;
        if(row + 1 < grid.length && grid[row + 1][col].getIsAlive())
            count ++;
        if(row + 1 < grid.length && col - 1 > -1 && grid[row + 1][col - 1].getIsAlive())
            count ++;

        return count;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.drawRect(col*size, row*size, size, size);
        g2.setColor(new Color(100, 120, 160));
        if(isAlive)
            g2.fillRect(col*size, row*size, size, size);
    }

    public boolean getIsAlive(){
        return isAlive;
    }

}
