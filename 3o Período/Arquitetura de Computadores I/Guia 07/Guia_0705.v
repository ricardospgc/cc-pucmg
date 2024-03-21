/*
*   Ricardo Soares Cerqueira
*   803833
*/

module test_Guia_0705;

	reg a, b;
	reg [2:0] select;
	output wire result;

	wire not_b, selected_gate;

	assign not_b = ~b;

	assign selected_gate = 
		(select == 3'b000) ? (~a) :
	  (select == 3'b001) ? (a & not_b) :
	  (select == 3'b010) ? ~(a & not_b) :
		(select == 3'b011) ? (a | not_b) :
	 	(select == 3'b100) ? ~(a | not_b) :
	 	(select == 3'b101) ? (a ^ not_b) :
		(select == 3'b110) ? ~(a ^ not_b) : 1'b0;

	assign result = selected_gate;
	
	initial begin
		$display("Guia_0705 - Ricardo Soares Cerqueira - 803833\n");
		$display("a   b   b' select  out");
		$monitor("%b   %b   %b    %3b    %b", a,b,not_b,select,result);
		
			 a = 0; b = 0; select = 3'b000; // (NOT)
		#1 a = 0; b = 0; select = 3'b001; // (AND)
		#1 a = 0; b = 0; select = 3'b010; // (NAND)
		#1 a = 0; b = 0; select = 3'b011; // (OR)
		#1 a = 0; b = 0; select = 3'b100; // (NOR)
		#1 a = 0; b = 0; select = 3'b101; // (XOR)
		#1 a = 0; b = 0; select = 3'b110; // (XNOR)
		/*******************************************/
		#1 a = 0; b = 1; select = 3'b000; // (NOT)
		#1 a = 0; b = 1; select = 3'b001; // (AND)
		#1 a = 0; b = 1; select = 3'b010; // (NAND)
		#1 a = 0; b = 1; select = 3'b011; // (OR)
		#1 a = 0; b = 1; select = 3'b100; // (NOR)
		#1 a = 0; b = 1; select = 3'b101; // (XOR)
		#1 a = 0; b = 1; select = 3'b110; // (XNOR)
		/*******************************************/
		#1 a = 1; b = 0; select = 3'b000; // (NOT)
		#1 a = 1; b = 0; select = 3'b001; // (AND)
		#1 a = 1; b = 0; select = 3'b010; // (NAND)
		#1 a = 1; b = 0; select = 3'b011; // (OR)
		#1 a = 1; b = 0; select = 3'b100; // (NOR)
		#1 a = 1; b = 0; select = 3'b101; // (XOR)
		#1 a = 1; b = 0; select = 3'b110; // (XNOR)
		/*******************************************/
		#1 a = 1; b = 1; select = 3'b000; // (NOT)
		#1 a = 1; b = 1; select = 3'b001; // (AND)
		#1 a = 1; b = 1; select = 3'b010; // (NAND)
		#1 a = 1; b = 1; select = 3'b011; // (OR)
		#1 a = 1; b = 1; select = 3'b100; // (NOR)
		#1 a = 1; b = 1; select = 3'b101; // (XOR)
		#1 a = 1; b = 1; select = 3'b110; // (XNOR)
	
		$finish;
	end

endmodule

/*
Saida esperada:
Guia_0705 - Ricardo Soares Cerqueira - 803833

a   b   b' select  out
0   0   1    000    1
0   0   1    001    0
0   0   1    010    1
0   0   1    011    1
0   0   1    100    0
0   0   1    101    1
0   0   1    110    0
0   1   0    000    1
0   1   0    001    0
0   1   0    010    1
0   1   0    011    0
0   1   0    100    1
0   1   0    101    0
0   1   0    110    1
1   0   1    000    0
1   0   1    001    1
1   0   1    010    0
1   0   1    011    1
1   0   1    100    0
1   0   1    101    0
1   0   1    110    1
1   1   0    000    0
1   1   0    001    0
1   1   0    010    1
1   1   0    011    1
1   1   0    100    0
1   1   0    101    1
1   1   0    110    0
*/
