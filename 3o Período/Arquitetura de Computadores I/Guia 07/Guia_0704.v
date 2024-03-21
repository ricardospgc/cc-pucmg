/*
* Ricardo Soares Cerqueira
* 803833
*/
module test_Guia_0703;
	reg a, b;
	reg [1:0] select;
	output wire result;

	wire out_or, out_nor, out_xor,  out_xnor;
	
	assign out_or = a | b;
	assign out_nor = ~(a | b);
	assign out_xor = a ^ b;
	assign out_xnor = ~(a ^ b);

	assign result = select[1] ? (select[0] ? out_xnor : out_xor) : (select[0] ? out_nor : out_or);

	/*
	if(select[0] == 1'b0){
		if(select[1] == 1'b0){ OR } 
		else{ NOR }
	} 
	else if(select[1] == 1"b0){ XOR }
	else{ XNOR }
	*/

	initial begin
		$display("Ricardo Soares Cerqueira - 803833\n");
		$display("a   b  slct  out");
		$monitor("%b   %b   %b    %b", a, b, select, result);

		   a = 0; b = 0; select = 2'b00; // 0 0 (OR)
		#1 a = 0; b = 0; select = 2'b01; // 0 1 (NOR)
		#1 a = 0; b = 0; select = 2'b10; // 1 0 (XOR)
		#1 a = 0; b = 0; select = 2'b11; // 1 1 (XNOR) 
		#1 a = 0; b = 1; select = 2'b00; // 0 0 (OR)
		#1 a = 0; b = 1; select = 2'b01; // 0 1 (NOR)
		#1 a = 0; b = 1; select = 2'b10; // 1 0 (XOR)
		#1 a = 0; b = 1; select = 2'b11; // 1 1 (XNOR) 
		#1 a = 1; b = 0; select = 2'b00; // 0 0 (OR)
		#1 a = 1; b = 0; select = 2'b01; // 0 1 (NOR)
		#1 a = 1; b = 0; select = 2'b10; // 1 0 (XOR)
		#1 a = 1; b = 0; select = 2'b11; // 1 1 (XNOR) 
		#1 a = 1; b = 1; select = 2'b00; // 0 0 (OR)
		#1 a = 1; b = 1; select = 2'b01; // 0 1 (NOR)
		#1 a = 1; b = 1; select = 2'b10; // 1 0 (XOR)
		#1 a = 1; b = 1; select = 2'b11; // 1 1 (XNOR) 

		$finish;
	end
endmodule

/*
Saida esperada:
Ricardo Soares Cerqueira - 803833

a   b   sel1  sel2   out
0   0     0     0     0
0   0     0     1     0
0   0     1     0     1
0   0     1     1     1
0   1     0     0     0
0   1     0     1     1
0   1     1     0     1
0   1     1     1     0
1   0     0     0     0
1   0     0     1     1
1   0     1     0     1
1   0     1     1     0
1   1     0     0     1
1   1     0     1     1
1   1     1     0     0
1   1     1     1     0

*/