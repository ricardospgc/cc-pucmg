/*
*   Ricardo Soares Cerqueira
*   803833
*/

module clock ( output clk );
    reg clk;
    initial begin
        clk = 1'b0;
    end
    always begin
        #12 clk = ~clk;
    end
endmodule // clock

module pulse ( signal, clock );
    input clock;
    output signal;      
    reg signal;
    always @ (posedge clock ) begin
        signal = 1'b1;
        #48 signal = 1'b0;
        #48 signal = 1'b1;
        #48 signal = 1'b0;
    end
endmodule // pulse

module Guia_0903;
    wire clock;
    clock clk ( clock );
    wire p;

    pulse pls( p, clock );

    initial begin
        $dumpfile ( "Guia_0903.vcd" );
        $dumpvars ( 1, clock, p);

        $display("\nGuia_0903 - Ricardo Soares Cerqueira - 803833\n");

        $monitor("clock = %b / p = %b ", clock, p);
        #1000 $finish;
    end
endmodule
