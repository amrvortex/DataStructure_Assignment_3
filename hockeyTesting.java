package eg.edu.alexu.csd.datastructure.iceHockey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Point;

import org.junit.jupiter.api.Test;


class jute_test {
	
	PlayerFinder test1;
	
	
	@Test
	void conv_string_arr_test() {
		test1=new PlayerFinder();
		String[] photo=new String [2];
		photo[0]="3h3ed3";
		photo[1]="ah3ed3";
		int team=3;
		test1.convertToSparse(photo,team);
		assertEquals(1,test1.sparseArray[0][0]);
		assertEquals(0,test1.sparseArray[0][1]);
		int counter=0;
		for(int i=0 ; i<6 ; i++) {
			for(int j=0 ; j<2 ; j++) {
				if(test1.sparseArray[j][i]==1) {
					counter++;
				}
			}
		}
		assertEquals(5,counter);
	}
	
	@Test
	void game_test() {
		test1=new PlayerFinder();
		String[] photo=new String [5];
		photo[0]="333ed3";
		photo[1]="a33ed3";
		photo[2]="h30ed3";
		photo[3]="a035d0";
		photo[4]="a33333";
		int team=3;
		test1.convertToSparse(photo,team);		
		Point[]test =new Point[2];
		test[0]=new Point(3,3);
		test[1]=new Point(7,8);
		Point [] test2=new Point[2];
		test2=test1.findPlayers(photo, team, 16);
		System.out.println(test2[0]);
		System.out.println(test2[1]);
		assertEquals(test[0],test2[0]);	
		assertEquals(test[1],test2[1]);
	}
}