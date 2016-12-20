#include <iostream> 
#include <memory.h> 
#include <cstring>
#include <typeinfo>
#include <windows.h>
#include <fstream>
using namespace std;

class Cell {
	public:
		virtual print(){
			cout<<"this is a cell";
		}
}; 

class LiveCell:public Cell {
	public:
		print(){
			cout<<"1";
		}
};

class DeadCell:public Cell {
	public:
		print(){
			cout<<"0";
		}
};

class LifeMatrix {
	private:
		bool stateMark[40][40];
		Cell* cells[40][40];
		LiveCell liveCell;
		DeadCell deadCell;
	public:
		LifeMatrix() {
			for (int i=0; i<40; i++) {
				for (int j=0; j<40; j++) {
					cells[i][j] = &deadCell;
					stateMark[i][j] = 0;
				}
			}
		}
		
		setCell(int x, int y, bool state) {
			if (x < 0 || x > 39 || y <0 || y> 39) {
				cout<<"Index Error";
			} else if (state) {
				cells[x][y] = &liveCell;
			}
			else {
				cells[x][y] = &deadCell;
			}
		}
		
		setMark() {
			for (int i=0; i<40; i++)
				for (int j=0; j<40; j++) {
					int liveCount = 0;
					// legal index and point to liveCell win a score for liveCount
					if (i-1>-1 && typeid(*cells[i-1][j]) == typeid(liveCell)) liveCount++;
					if (i-1>-1 && j-1>-1 && typeid(*cells[i-1][j-1]) == typeid(liveCell)) liveCount++;
					if (j-1>-1 && typeid(*cells[i][j-1]) == typeid(liveCell)) liveCount++;
					if (i+1<40 && j-1>-1 && typeid(*cells[i+1][j-1]) == typeid(liveCell)) liveCount++;
					if (i+1<40 && typeid(*cells[i+1][j]) == typeid(liveCell)) liveCount++;
					if (i+1<40 && j+1<40 && typeid(*cells[i+1][j+1]) == typeid(liveCell)) liveCount++;
					if (j+1<40 && typeid(*cells[i][j+1]) == typeid(liveCell)) liveCount++;
					if (i-1>-1 && j+1<40 && typeid(*cells[i-1][j+1]) == typeid(liveCell)) liveCount++;
					// expedition 
					if (j+2<40 && typeid(*cells[i][j+2]) == typeid(liveCell)) liveCount++;
					if (i+2<40 && typeid(*cells[i+2][j]) == typeid(liveCell)) liveCount++;
					if (j-2>-1 && typeid(*cells[i][j-2]) == typeid(liveCell)) liveCount++;
					if (i-2>-1 && typeid(*cells[i-2][j]) == typeid(liveCell)) liveCount++;
					if ( (liveCount > 4 && liveCount < 7 && typeid(*cells[i][j]) == typeid(deadCell)) || ( (liveCount < 3 || liveCount > 8 ) && typeid(*cells[i][j]) == typeid(liveCell)) ) {
						// stateMark == 1 means the cell waits for change
						stateMark[i][j] = 1;
					} else {
						stateMark[i][j] = 0;
					}
				}
		}
		
		nextGen() {
			for (int i=0; i<40; i++)
				for (int j=0; j<40; j++) {
					if (stateMark[i][j] == 1) {
						if (typeid(*cells[i][j]) == typeid(deadCell)) {
							cells[i][j] = &liveCell;
						} else {
							cells[i][j] = &deadCell;
						}
					}
				}
		}
		
		printMatrix(){
			for (int i=0; i<40; i++) {
				for (int j=0; j<40; j++) {
					cells[i][j] -> print();
				}
				cout<<endl;
			}
		}
} matrix;

int main () {
	system("mode con cols=50 lines=40");
	cout<<"input source file name:"<<endl;
	int x, y, n;
	char fileName[20];
	cin>>fileName; 
	ifstream fin(fileName);
	while(fin>>x>>y) {
		if (x==-1 && y==-1) break;
		if (x < 0 || x > 39 || y <0 || y> 39) {
			cout<<"Wrong Index"<<endl;
		} else {
			matrix.setCell(x,y,true);
		}
	}
	fin>>n;
	fin.close();
	for(int i=0; i<n; i++) {
		system("cls");
		matrix.setMark();
		matrix.nextGen();
		matrix.printMatrix();
		Sleep(1000);
	}
	system("pause");
	return 0;
}
