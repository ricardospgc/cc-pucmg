/*
* Ricardo Soares Cerqueira
* 803833
*/

module Guia_0701;
	reg a, b, select;
	wire out1_and, out2_nand, selected_output;
	
	assign out1_and = a & b;
	assign out2_nand = ~(a & b);
	assign selected_output = select ? out2_nand : out1_and;
	
	initial begin
		$display("Guia_0701 - Ricardo Soares Cerqueira - 803833\n");
		$display("a   b   s  out");
		$monitor("%b   %b   %b   %b", a,b,select,selected_output);
	
		a = 0; b = 0; select = 0;    // 0 (AND)
		#1 a = 0; b = 0; select = 1; // 1 (NAND)
		#1 a = 0; b = 1; select = 0; // 0 (AND)
		#1 a = 0; b = 1; select = 1; // 1 (NAND)
		#1 a = 1; b = 0; select = 0; // 0 (AND)
		#1 a = 1; b = 0; select = 1; // 1 (NAND)
		#1 a = 1; b = 1; select = 0; // 0 (AND)
		#1 a = 1; b = 1; select = 1; // 1 (NAND)
	
		$finish;
	end
endmodule

/*
Saida esperada:
Guia_0701 - Ricardo Soares Cerqueira - 803833

a   b   s  out
0   0   0   0
0   0   1   1
0   1   0   0
0   1   1   1
1   0   0   0
1   0   1   1
1   1   0   1
1   1   1   0
*/
