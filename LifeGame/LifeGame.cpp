#include <iostream> 
#include <memory.h> 
#include <cstring>
using namespace std;

void printMatrix(bool matrix[][40]) {
	for (int i=0; i<40; i++) {
		for (int j=0; j<40; j++) {
			cout<<matrix[i][j];
		}
		cout<<endl;
	}
}

void lifeIter(bool lastGen[][40], bool thisGen[][40],int n){
	for (int iter=0; iter<n; iter++){
		for (int i=0; i<40; i++)
			for (int j=0; j<40; j++) {
				lastGen[i][j] = thisGen[i][j];
				thisGen[i][j] = 0;
			}
//		cout<<"lastGen:"<<endl;
//		printMatrix(lastGen);
//		cout<<"thisGen:"<<endl;
//		printMatrix(thisGen);
		for (int i=0; i<40; i++)
			for (int j=0; j<40; j++) {
				int liveCells = 0;
				if (i-1>-1 && lastGen[i-1][j]) liveCells++;
				if (i-1>-1 && j-1>-1 && lastGen[i-1][j-1]) liveCells++;
				if (j-1>-1 && lastGen[i][j-1]) liveCells++;
				if (i+1<40 && j-1>-1 && lastGen[i+1][j-1]) liveCells++;
				if (i+1<40 && lastGen[i+1][j]) liveCells++;
				if (i+1<40 && j+1<40 && lastGen[i+1][j+1]) liveCells++;
				if (j+1<40 && lastGen[i][j+1]) liveCells++;
				if (i-1>-1 && j+1<40 && lastGen[i-1][j+1]) liveCells++;
				if (liveCells==3) thisGen[i][j] = 1;
				else if (liveCells==2) thisGen[i][j] = lastGen[i][j];
				else thisGen[i][j]=0;
				if (i==1 && j==1)
				cout<<i<<" "<<j<<" "<<liveCells<<endl; 
			}
	}
}

int main() {
	bool lastGen[40][40], thisGen[40][40];
	memset(lastGen, 0, sizeof(lastGen));
	memset(thisGen, 0, sizeof(thisGen));
	int x,y,n;
	while(cin>>x>>y) {
		if (x==-1 && y==-1) break;
		if (x<0 || x>39 || y<0 || y>39) 
			cout<<"Wrong Input";
		else 
			thisGen[x][y] = 1;
	}
	cin>>n;
	
	if (n==0) 
		printMatrix(thisGen);
	else if (n>0) {
		lifeIter(lastGen, thisGen, n);
		printMatrix(thisGen);
	} else 
		cout<<"Wrong Input For Iteration";
	system("pause");
	return 0;
}
