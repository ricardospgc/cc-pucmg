/*
* Ricardo Soares Cerqueira
* 803833
*/

module Guia_0701;
	reg a, b, select;
	wire out1_or, out2_nor, selected_output;

	assign out1_or = a | b;
	assign out2_nor = ~(a | b);
	assign selected_output = select ? out2_nor : out1_or;

	initial begin
		$display("Guia_0702 - Ricardo Soares Cerqueira - 803833\n");
		$display("a   b   s  out");
		$monitor("%b   %b   %b   %b", a,b,select,selected_output);

		a = 0; b = 0; select = 0;    // 0 (OR)
		#1 a = 0; b = 0; select = 1; // 1 (NOR)
		#1 a = 0; b = 1; select = 0; // 0 (OR)
		#1 a = 0; b = 1; select = 1; // 1 (NOR)
		#1 a = 1; b = 0; select = 0; // 0 (OR)
		#1 a = 1; b = 0; select = 1; // 1 (NOR)
		#1 a = 1; b = 1; select = 0; // 0 (OR)
		#1 a = 1; b = 1; select = 1; // 1 (NOR)

		$finish;
	end
endmodule

/*
Saida esperada:
Guia_0702 - Ricardo Soares Cerqueira - 803833

a   b   s  out
0   0   0   0
0   0   1   1
0   1   0   1
0   1   1   0
1   0   0   1
1   0   1   0
1   1   0   1
1   1   1   0
*/
