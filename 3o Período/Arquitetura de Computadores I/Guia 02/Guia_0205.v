/*
Guia_0203.v
803833 - Ricardo Soares Cerqueira
*/
module Guia_0205;
// define data
	// a)
	reg [7:0] a1i = 8'b0000_101; // parte inteira
	reg [7:0] a1f = 8'b01_00000; // parte fracionaria
	
	reg [7:0] a2i = 8'b00000_11; // parte inteira
	reg [7:0] a2f = 8'b011_0000; // parte fracionaria
	
	// b)
	reg [7:0] b1i = 8'b000_1001; // parte inteira
	reg [7:0] b1f = 8'b101_0000; // parte fracionaria

	reg [7:0] b2i = 8'b00000_10; // parte inteira
	reg [7:0] b2f = 8'b11_00000; // parte fracionaria

	// c)
	reg [7:0] c1i = 8'b0000_100; // parte inteira
	reg [7:0] c1f = 8'b101_0000; // parte fracionaria

	reg [7:0] c2i = 8'b00000_11; // parte inteira
	reg [7:0] c2f = 8'b101_0000; // parte fracionaria
// actions
initial
begin : main
	$display ( "Guia_0203 - Ricardo Soares Cerqueira 803833\n" );

	// a)
	$display ("a) 101,01(2) + 11,011(2) = %8b.%8b", a1i+a2i, a1f+a2f);

	//b)
	$display ("b) 1001,101(2) - 10,11(2) = %8b.%8b", b1i-b2i, b1f-b2f);

	//c)
	$display("c) 100,101(2) * 11,101(2) = = %8b.%8b", b1i*b2i, b1f*b2f);
	end // main
endmodule // Guia_0205