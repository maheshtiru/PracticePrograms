package com.CodingProblems;

public class SpreadSheet {
    public static void main(String[] args){
        SpreadSheetDefinition s = new SpreadSheetDefinition(4,3);
        s.printSheet();

        s.updateCells(0,0, "bob");
        s.updateCells(0,1, 1010);
        s.updateCells(0,2, "foo");

        s.updateCells(1,0, "alice");
        s.updateCells(1,1, 5);

        s.printSheet();
    }
}

class SpreadSheetDefinition {
    int rows;
    int cols;
    Comparable[][] sheet;
    int[][] width;
    int[] colsWidth;

    SpreadSheetDefinition(int _rows, int _cols){
        this.rows = _rows;
        this.cols = _cols;
        this.sheet = new Comparable[rows][cols];
        this.width = new int[rows][cols];
        this.colsWidth = new int[cols];
    }

    public void updateCells(int r, int c, Comparable value){
        if(r<0 || r>= rows || c <0 || c>= cols) return;

        sheet[r][c] = value;
    }

    public void printSheet(){
        String val;
        int len = 0;
        int diff = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                val =  sheet[i][j] == null ? "" : sheet[i][j].toString();
                len = val.length();
                width[i][j] = len;
            }
        }

        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
               if(width[i][j] > colsWidth[j]) colsWidth[j] = width[i][j];
            }
        }

        for(int i = 0; i<rows; i++){
            System.out.println("");
            for(int j = 0; j<cols; j++){
                val =  sheet[i][j] == null ? "" : sheet[i][j].toString();
                diff = colsWidth[j] - val.length();

                for(int k = 0; k<diff; k++) sb.append(" ");

                if(j == cols-1)System.out.print(val +sb);
                else System.out.print(val +sb +"|");
                sb = new StringBuilder();
            }
        }

    }
}




