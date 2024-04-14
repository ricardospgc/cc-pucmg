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

module pulse1 (signal, clock);
    input clock;
    output signal;      
    reg signal;

    always @ (posedge clock ) begin;
        signal = 1'b1;
        #6 signal = 1'b0;
    end
endmodule // pulse1


module Guia_0905;
    wire clock;
    wire p;
    
    clock clk (clock);
    pulse1 pls(p, clock);

    initial begin
        $dumpfile("Guia_0905.vcd");
        $dumpvars(0, Guia_0905);

        $display("\nGuia_0902 - Ricardo Soares Cerqueira - 803833\n");

        $monitor("clock = %b / p = %b ", clock, p);
        #1000 $finish;
    end
endmodule // Guia_0904