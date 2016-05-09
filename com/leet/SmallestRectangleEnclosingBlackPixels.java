package com.leet;

//An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. 
//The black pixels are connected, i.e., there is only one black region. 
//Pixels are connected horizontally and vertically. 
//Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
//
//For example, given the following image:
//
//[
//  "0010",
//  "0110",
//  "0100"
//]
//		
//and x = 0, y = 2,
//Return 6.

//Google
public class SmallestRectangleEnclosingBlackPixels {

	public SmallestRectangleEnclosingBlackPixels() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		char[][] image = {{'0','0','1','0'},{'0','1','1','0'},{'0','1','0','0'}};
		
		System.out.println(minArea(image, 0, 2));
		
		System.out.println();
	}
	
	
	//AC:  36%
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) return 0;
        int row = image.length;
        int col = image[0].length;
        int i, j;
        boolean valid = false;
        int lowerRow = x, upperRow = x, lowerCol = y, upperCol = y;
        
        //Find the first row which contains black pixel
        for (i=0; i<x; i++) {
            valid = false;
            for (j=0; j<col; j++) {
                if (image[i][j] == '1') {
                    valid = true;
                    break;
                } 
            }
            if (valid == true) {
                lowerRow = i;
                break;
            }
        }

        //Find the last row which contains black pixel
        for (i=row-1; i>=x+1; i--) {
            valid = false;
            for (j=0; j<col; j++) {
                if (image[i][j] == '1') {
                    valid = true;
                    break;
                }
            }
            if (valid == true) {
                upperRow = i;
                break;
            }
        }

        //Find the first column which contains black pixel
        for (i=0; i<y; i++) {
            valid = false;
            for (j=lowerRow; j<=upperRow; j++) {
                if (image[j][i] == '1') {
                    valid = true;
                    break;
                }
            }
            if (valid == true) {
                lowerCol = i;
                break;
            }
        }

        //Find the last column which contains black pixel
        for (i=col-1; i>=y+1; i--) {
            valid = false;
            for (j=lowerRow; j<=upperRow; j++) {
                if (image[j][i] == '1') {
                    valid = true;
                    break;
                }
            }
            if (valid == true) {
                upperCol = i;
                break;
            }
        }

        return  (upperRow-lowerRow+1)*(upperCol-lowerCol+1);
    }

}
