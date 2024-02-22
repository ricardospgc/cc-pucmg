/*
Guia_0201.v
803833 - Ricardo Soares Cerqueira
*/
module Guia_0201;
	// define data
	real x = 0; // decimal
	real power2 = 1.0; // power of 2
	integer y = 4; // counter
	reg [4:0] a = 5'b00101; // binary (only fraction part, Big Endian)
	reg [4:0] b = 5'b01011;
	reg [4:0] c = 5'b10001;
	reg [4:0] d = 5'b11011;
	reg [4:0] e = 5'b10011;
	// actions
	
	initial
	begin : main
		$display ( "Guia_0201 - 803833 - Ricardo Soares Cerqueira\n" );
		
		//1b.)a)
		while ( y >= 0 ) begin
			power2 = power2 / 2.0;
			if ( a[y] == 1 ) begin
				x = x + power2;
			end
			y=y-1;
		end // end while
		
		$display ("a) 0.%b(2) = %f(10)", a, x );

		// Reset
		x = 0;
		power2 = 1.0;
		y = 4;

		//1b.)b)
		while ( y >= 0 ) begin
			power2 = power2 / 2.0;
			if ( b[y] == 1 ) begin
				x = x + power2;
			end
			y=y-1;
		end // end while

		$display ("b) 0.%b(2) = %f(10)", b, x );

		// Reset
		x = 0;
		power2 = 1.0;
		y = 4;

		//1b.)c)
		while ( y >= 0 ) begin
			power2 = power2 / 2.0;
			if ( c[y] == 1 ) begin
				x = x + power2;
			end
			y=y-1;
		end // end while

		$display ("c) 0.%b(2) = %f(10)", c, x );

		// Reset
		x = 0;
		power2 = 1.0;
		y = 4;

		//1b.)d)
		while ( y >= 0 ) begin
			power2 = power2 / 2.0;
			if ( d[y] == 1 ) begin
				x = x + power2;
			end
			y=y-1;
		end // end while

		$display ("d) 1.%b(2) = %f(10)", d, x+1 );

		// Reset
		x = 0;
		power2 = 1.0;
		y = 4;

		//1b.)e)
		while ( y >= 0 ) begin
			power2 = power2 / 2.0;
			if ( e[y] == 1 ) begin
				x = x + power2;
			end
			y=y-1;
		end // end while

		$display ("e) 11.%b(2) = %f(10)", e, x+3 );
	end // main
endmodule // Guia_0201

/*
Saidas:
Guia_0201 - 803833 - Ricardo Soares Cerqueira

a) 0.00101(2) = 0.156250(10)
b) 0.01011(2) = 0.343750(10)
c) 0.10001(2) = 0.531250(10)
d) 1.11011(2) = 1.843750(10)
e) 11.10011(2) = 3.593750(10)
*/