/* 
Guia_0202.v 
803833 - Ricardo Soares Cerqueira
*/ 
module Guia_0202; 
// define data 
real      a = 0.125;        // decimal 
real      b = 0.625;       // decimal 
real      c = 0.75;       // decimal 
real      d = 0.03125;   // decimal 
real      e = 0.875;    // decimal 

integer   i = 7  ;    // counter 
reg [7:0] x = 0  ;   // binary (Big Endian)
// actions 
initial begin : main 
	$display ( "Guia_0202 - 803833 - Ricardo Soares Cerqueira\n\n " ); 

	/****** a) ********/
	while ( a > 0 && i >= 0 ) begin
		if( a*2 >= 1 ) begin 
			x[i] = 1; 
			a = a*2.0 - 1.0; 
		end 
		else begin
			x[i] = 0; 
			a = a*2.0; 
		end // if 

		i=i-1; 
	end// while 
	$display ( "a) 0.125(10) = 0.%8b(2)\n", x ); 

	//reset
	i = 7;	
	x = 0;

		/****** b) ********/
	while ( b > 0 && i >= 0 ) begin
		if( b*2 >= 1 ) begin 
			x[i] = 1; 
			b = b*2.0 - 1.0; 
		end 
		else begin
			x[i] = 0; 
			b = b*2.0; 
		end // if 
		
		i=i-1; 
	end// while 
	$display ( "b) 2.625(10) = 10.%8b(2) \n", x ); 

	//reset
	i = 7;
	x = 0;

	/****** c) ********/
	while ( c > 0 && i >= 0 ) begin
		if( c*2 >= 1 ) begin 
			x[i] = 1; 
			c = c*2.0 - 1.0; 
		end 
		else begin
			x[i] = 0; 
			c = c*2.0; 
		end // if 
		
		i=i-1; 
	end// while 
	$display ( "c) 3.75(10) = 11.%8b(2) \n", x );

	//reset
	i = 7;
	x = 0;

	/****** d) ********/
	while ( d > 0 && i >= 0 ) begin
		if( d*2 >= 1 ) begin 
			x[i] = 1; 
			d = d*2.0 - 1.0; 
		end 
		else begin
			x[i] = 0; 
			d = d*2.0; 
		end // if 
		
		i=i-1; 
	end// while 
	$display ( "d) 6.03125(10) = 110.%8b(2) \n", x );

	//reset
	i = 7;
	x = 0;

	/****** e) ********/
	while ( e > 0 && i >= 0 ) begin
		if( e*2 >= 1 ) begin 
			x[i] = 1; 
			e = e*2.0 - 1.0; 
		end 
		else begin
			x[i] = 0; 
			e = e*2.0; 
		end // if 
		
		i=i-1; 
	end// while 
	$display ( "e) 9.875(10) = 1001.%8b(2) \n", x );
	
end// main 
endmodule// Guia_0202 

/*
Saidas:
Guia_0202 - 803833 - Ricardo Soares Cerqueira

 
a) 0.125(10) = 0.00100000(2)

b) 2.625(10) = 10.10100000(2) 

c) 3.75(10) = 11.11000000(2) 

d) 6.03125(10) = 110.00001000(2) 

e) 9.875(10) = 1001.11100000(2)
*/