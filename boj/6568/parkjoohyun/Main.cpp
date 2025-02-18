//Memory 2020KB
//Time 0ms

#include <iostream>

#define STA			0b00000000
#define LDA			0b00100000
#define BEQ			0b01000000
#define NOP			0b01100000
#define DEC			0b10000000
#define INC			0b10100000
#define JMP			0b11000000
#define HLT			0b11100000

#define OP_MASK		0b11100000
#define PO_MASK		0b00011111

#define MAX_MASK	0b11111111
#define PC_MASK		0b00011111
using namespace std;
int PC = 0;
int adder = 0;
int memory[32];

int btod(int bin) {
	int dec = 0, bit = 1;
	for (int i = 0; i < 8; i++) {
		if (bin & 1)dec += bit;
		bit <<= 1;
		bin /= 10;
	}
	return dec;
}
void dtob(int dec) {
	for (int i = 7; i >= 0; i--)
	{
		int temp = (dec >> i) & 1;
		cout << temp;
	}
	cout << "\n";
}

void STA_operation(int address) {
	memory[address] = adder;
}
void LDA_operation(int address) {
	adder = memory[address];
}
void BEQ_operation(int address) {
	if (adder == 0)
		PC = address;
}
void NOP_operation() {
	;
}
void DEC_operation() {
	adder--;
	adder &= MAX_MASK;
}
void INC_operation() {
	adder++;
	adder &= MAX_MASK;
}
void JMP_operation(int address) {
	PC = address;
}
void HLT_operation() {
	dtob(adder);
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int operation, op, po;
	bool end = false;
	while (true) {
		PC = 0; adder = 0;
		end = false;
		
		for (int i = 0; i < 32; i++) {
			cin >> operation;
			if (cin.eof()) return 0;
			memory[i] = btod(operation);
		}
		while (true) {
			if (end) break;
			PC &= PC_MASK;
			op = memory[PC] & OP_MASK;
			po = memory[PC] & PO_MASK;
			switch (op) {
			case STA:
				PC++;
				STA_operation(po);
				break;
			case LDA:
				PC++;
				LDA_operation(po);
				break;
			case BEQ:
				PC++;
				BEQ_operation(po);
				break;
			case NOP:
				PC++;
				NOP_operation();
				break;
			case DEC:
				PC++;
				DEC_operation();
				break;
			case INC:
				PC++;
				INC_operation();
				break;
			case JMP:
				PC++;
				JMP_operation(po);
				break;
			case HLT:
				PC++;
				HLT_operation();
				end = true;
			}
		}
	}
}