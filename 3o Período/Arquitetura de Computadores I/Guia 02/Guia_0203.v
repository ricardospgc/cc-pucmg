/*
Guia_0203.v
803833 - Ricardo Soares Cerqueira
*/
module Guia_0203;
// define data
	reg [7:0] a = 8'b011010_00 ; // binary
	reg [7:0] b = 8'b101101_00 ; // binary
	reg [7:0] c = 8'b100111_00 ; // binary
	reg [7:0] d = 8'b111001_00 ; // binary
	reg [7:0] e = 8'b1101_0000 ; // binary
// actions
initial
begin : main
	$display ( "Guia_0203 - Ricardo Soares Cerqueira 803833\n" );
	
	$display ( "a) 0.011010(2) = 0.%o%o%o(4)", a[7:6], a[5:4], a[3:2] );
	$display ( "b) 0.101101(2) = 0.%o%o(8)", b[7:5], b[4:2], );
	$display ( "c) 0.100111(2) = 0.%x%x(16)", c[7:4], c[3:0] );
	$display ( "d) 1.111001(2) = 1.%o%o(8)", d[7:5], d[4:2] );
	$display ( "e) 1110,1001(2) = %x.%x", e[7:4], e[7:4] );	
	
end // main
endmodule // Guia_0203

/*
Saídas:
Guia_0203 - Ricardo Soares Cerqueira 803833

a) 0.011010(2) = 0.122(4)
b) 0.101101(2) = 0.55(8) 
c) 0.100111(2) = 0.9c(16)
d) 1.111001(2) = 1.71(8)
e) 1110,1001(2) = d.d
*/
