/*
* Ricardo Soares Cerqueira
* 803833
*/
module test_Guia_0703;
	reg a, b, select1, select2;
	wire result;
	wire out_or, out_nor, out_and,  out_nand;
	
	assign out_and = a & b;
	assign out_nand = ~(a & b);
	assign out_or = a | b;
	assign out_nor = ~(a | b);

	assign result = select1 ? (select2 ? out_nor : out_nand) : (select2 ? out_or : out_and);
	/*
	if(select1 == 1'b0){
		if(select2 == 1'b0) { AND }
		else { NAND }
	} else if(select2 == 1"b0){ OR }
	else { NOR }
	*/
	
	initial begin
		$display("Ricardo Soares Cerqueira - 803833\n");
		$display("a   b   sel1  sel2   out");
		$monitor("%b   %b     %b     %b     %b", a, b, select1,select2,result);
		
		   a = 0; b = 0; select1 = 0; select2 = 0; // 0 0 (AND)
		#1 a = 0; b = 0; select1 = 0; select2 = 1; // 0 1 (OR)
		#1 a = 0; b = 0; select1 = 1; select2 = 0; // 1 0 (NAND)
		#1 a = 0; b = 0; select1 = 1; select2 = 1; // 1 1 (NOR) 
		#1 a = 0; b = 1; select1 = 0; select2 = 0; // 0 0 (AND)
		#1 a = 0; b = 1; select1 = 0; select2 = 1; // 0 1 (OR)
		#1 a = 0; b = 1; select1 = 1; select2 = 0; // 1 0 (NAND)
		#1 a = 0; b = 1; select1 = 1; select2 = 1; // 1 1 (NOR) 
		#1 a = 1; b = 0; select1 = 0; select2 = 0; // 0 0 (AND)
		#1 a = 1; b = 0; select1 = 0; select2 = 1; // 0 1 (OR)
		#1 a = 1; b = 0; select1 = 1; select2 = 0; // 1 0 (NAND)
		#1 a = 1; b = 0; select1 = 1; select2 = 1; // 1 1 (NOR) 
		#1 a = 1; b = 1; select1 = 0; select2 = 0; // 0 0 (AND)
		#1 a = 1; b = 1; select1 = 0; select2 = 1; // 0 1 (OR)
		#1 a = 1; b = 1; select1 = 1; select2 = 0; // 1 0 (NAND)
		#1 a = 1; b = 1; select1 = 1; select2 = 1; // 1 1 (NOR) 

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