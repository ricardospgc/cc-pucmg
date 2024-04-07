/*
803833
Ricardo Soares Cerqueira
*/

// Compara um bit usando XNOR
module bitComparator(
	input a,
	input b,
	output diff
); 

	xnor XNOR(diff,a,b);
endmodule

// Compara os 6 bits
module fullComparator(
	input [5:0] a,b,
	output diff
);
	wire c1,c2,c3,c4,c5,c6;
	bitComparator BC1(a[5], b[5], c1);
	bitComparator BC2(a[4], b[4], c2);
	bitComparator BC3(a[3], b[3], c3);
	bitComparator BC4(a[2], b[2], c4);
	bitComparator BC5(a[1], b[1], c5);
	bitComparator BC6(a[0], b[0], c6);
	assign diff = c1 & c2 & c3 & c4 & c5 & c6;

endmodule

module Guia_0803_test;
	reg [5:0] a;
	reg [5:0] b;
	wire diff; 

	fullComparator uut(.a(a), .b(b), .diff(diff));

	initial begin
		$display("Guia_0803 - Ricardo Soares Cerqueira - 803833\n");
		
		$monitor("%b = %b ? %b", a, b, diff);
		a = 6'b001010; b = 6'b00101;
		#10;
		a = 6'b000000; b = 6'b000000;
		#10;
		a = 6'b000010; b = 6'b000010;
		#10;
		a = 6'b000100; b = 6'b000100;
		#10;
		a = 6'b001000; b = 6'b001000;
		#10;
		a = 6'b010000; b = 6'b000001;
		#10;
		a = 6'b100000; b = 6'b000001;
		#10;
		$finish();
	end
endmodule
