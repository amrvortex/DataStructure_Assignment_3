package eg.edu.alexu.csd.datastructure.iceHockey;
import java.awt.*;

public class PlayerFinder implements IPlayersFinder {
	public int minRow,maxRow,minColumn,maxColumn;
	public int existingArea=0;
	public int player=0 ;
	public int [][] sparseArray;
	public Point[] center_point ;
	public Point[]requir_arr;
	
	public void convertToSparse(String[] photo,int team) {
		sparseArray = new int[photo.length][photo[0].length()];
		for(int i = 0 ; i < photo.length;i++) {
			for(int j =0 ;j < photo[0].length();j++) {
				if(Character.getNumericValue(photo[i].charAt(j))==team)
					sparseArray[i][j]=1;
				else
					sparseArray[i][j]=0;
			}
		}
	}
	
public void recursion(int i, int j , int threshold) {
		
	if(sparseArray[i][j]!=0) {
		if(minRow>j) {
			minRow=j;
		}
		if(maxRow<j) {
			maxRow=j;
		}	
		if(minColumn>i) {
			minColumn=i;
		}
		if(maxColumn<i) {
			maxColumn=i;
		}
		existingArea++;
		sparseArray[i][j]=0;
		if((j+1)<sparseArray[0].length)
		recursion(i,j+1,threshold);
		if((j-1)>=0)
		recursion(i,j-1,threshold);
		if((i+1)<sparseArray.length)
		recursion(i+1,j,threshold);
		if((i-1)>=0)
		recursion(i-1,j,threshold);
		if(existingArea*4>=threshold) 
			player=1;
		else 
			player=0;
		}
	}

public void bubble_sort(Point[]arr) {
	for(int i=0 ; i<arr.length ; i++) {
		for(int j=0 ; j<arr.length-1-i ; j++) {
			if(arr[j].getX()>arr[j+1].getX()) {
				swap(arr,j,j+1);
			}
			if((arr[j].getX()==arr[j+1].getX())&&(arr[j].getY()>arr[j+1].getY())) {
				swap(arr,j,j+1);
			}
		}
	}
	
}

public void swap(Point[]arr,int index1,int index2) {
	Point p=new Point();
	p=arr[index1];
	arr[index1]=arr[index2];
	arr[index1]=p;
}
	
public java.awt.Point[] findPlayers(String[] photo, int team, int threshold) {
	int m=0;
    center_point=new Point[2500];
	convertToSparse(photo,team);
	for(int i=0 ; i<sparseArray.length;i++) {
		for(int j=0 ; j<sparseArray[0].length;j++) {
			if(sparseArray[i][j]==1) {
				minColumn=i;
				maxColumn=i;
				minRow=j;
				maxRow=j;
				recursion(i,j,threshold);
				if(player==1) {
					center_point[m] = new Point();
					center_point[m].setLocation(maxRow+ minRow+1, maxColumn +minColumn+1);
					m++;
				}
				existingArea=0;
				player=0;
			}
		}
	}
	requir_arr=new Point[m];
	for(int i=0;i<m;i++) {
		requir_arr[i]=center_point[i];
	}
	bubble_sort(requir_arr);
	
	return requir_arr;
}
}
