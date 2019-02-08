/***********************************************************************
 * MieruPC2008 MipsCore                                                *
 ***********************************************************************/
`define ADDR 15:0
`define RETURN_ADDR 16'h0000
`include "../rtl/define.v"

/* 32bit-32cycle mutiplier (signed or unsigned)                       */
/**********************************************************************/
module MULUNIT (CLK, RST_X, INIT, SIGNED, A, B, RSLT, BUSY);
    input         CLK;
    input         RST_X;
    input         INIT;
    input         SIGNED;
    input  [31:0] A;
    input  [31:0] B;
    output [63:0] RSLT;
    output        BUSY;

    wire [31:0]   uint_a;
    wire [31:0]   uint_b;
    wire [63:0]   uint_rslt;
    reg           sign;

    MULUNITCORE mulcore(.CLK(CLK), .RST_X(RST_X), .INIT(INIT),
                        .A(uint_a), .B(uint_b), .RSLT(uint_rslt),
                        .BUSY(BUSY));
                        
    assign uint_a = (SIGNED & A[31])? ~A + 1 : A;
    assign uint_b = (SIGNED & B[31])? ~B + 1 : B;
    assign RSLT   = (SIGNED & sign)? ~uint_rslt + 1 : uint_rslt;
    
    always @( posedge CLK or negedge RST_X )
      if( !RST_X ) sign <= 0;
      else         sign <= (INIT)? A[31]^B[31] : sign;

endmodule // MULUNIT

module MULUNITCORE (CLK, RST_X, INIT, A, B, RSLT, BUSY);
    input         CLK;
    input         RST_X;
    input         INIT;
    input  [31:0] A;
    input  [31:0] B;
    output [63:0] RSLT;
    output        BUSY;

    reg [31:0]    multiplicand;
    reg [5:0]     count;
    wire [32:0]   sum;
    reg [63:0]    RSLT;

    assign BUSY   = (count < 32);
    assign sum    = RSLT[63:32] + multiplicand;

    always @( posedge CLK or negedge RST_X ) begin
        if( !RST_X ) begin
            multiplicand   <= 0;
            RSLT           <= 0;
            count          <= 0;
        end else if( INIT )begin
            multiplicand   <= A;
            RSLT           <= {32'h0, B};
            count          <= 0;
        end else begin
            multiplicand   <= multiplicand;
            RSLT           <= (RSLT[0])? {sum, RSLT[31:1]} : 
                                         {1'h0, RSLT[63:1]};
            count          <= count + 1;
        end
    end
endmodule // MULUNITCORE

/* 32bit-32cycle divider (signed or unsigned)                         */
/**********************************************************************/
module DIVUNIT (CLK, RST_X, INIT, SIGNED, A, B, RSLT, BUSY);
    input         CLK;
    input         RST_X;
    input         INIT;
    input         SIGNED;
    input  [31:0] A;
    input  [31:0] B;
    output [63:0] RSLT;
    output        BUSY;

    wire [31:0]   uint_a;
    wire [31:0]   uint_b;
    wire [63:0]   uint_rslt;
    reg           sign_a;
    reg           sign_b;
    reg [31:0]    mod_inc;

    DIVUNITCORE divcore(.CLK(CLK), .RST_X(RST_X), .INIT(INIT), 
                        .A(uint_a), .B(uint_b), .RSLT(uint_rslt),
                        .BUSY(BUSY));

    assign uint_a = (SIGNED & A[31])? ~A + 1 : A;
    assign uint_b = (SIGNED & B[31])? ~B + 1 : B;
    assign RSLT[63:32] =
        (~SIGNED)?                    uint_rslt[63:32]           :
        ({sign_a, sign_b} == 2'b00)?  uint_rslt[63:32]           :
        ({sign_a, sign_b} == 2'b01)? ~uint_rslt[63:32] + mod_inc :
        ({sign_a, sign_b} == 2'b10)? ~uint_rslt[63:32] + 1       :
                                     ~uint_rslt[63:32] + 1;
    assign RSLT[31: 0] =
        (~SIGNED)?                    uint_rslt[31: 0]     :
        ({sign_a, sign_b} == 2'b00)?  uint_rslt[31: 0]     :
        ({sign_a, sign_b} == 2'b01)? ~uint_rslt[31: 0]     :
        ({sign_a, sign_b} == 2'b10)? ~uint_rslt[31: 0] + 1 :
                                      uint_rslt[31: 0];
        
    always @( posedge CLK or negedge RST_X )
      if( !RST_X ) begin
          sign_a  <= 0;
          sign_b  <= 0;
          mod_inc <= 0;
      end else begin
          sign_a  <= (INIT)? A[31] : sign_a;
          sign_b  <= (INIT)? B[31] : sign_b;
          mod_inc <= (INIT)? ~B + 2 : mod_inc;
      end

endmodule // DIVUNIT

module DIVUNITCORE (CLK, RST_X, INIT, A, B, RSLT, BUSY);
    input         CLK;
    input         RST_X;
    input         INIT;
    input  [31:0] A;
    input  [31:0] B;
    output [63:0] RSLT;
    output        BUSY;

    reg [63:0]    RSLT;
    reg [31:0]    divisor;
    reg [5:0]     count;
    wire [31:0]   differ;

    assign BUSY   = (count < 32);
    assign differ = RSLT[62:31] - divisor;

    always @( posedge CLK or negedge RST_X ) begin
        if( !RST_X ) begin
            divisor   <= 0;
            RSLT      <= 0;
            count     <= 0;
        end else if( INIT ) begin
            divisor   <= B;
            RSLT      <= {32'h0, A};
            count     <= 0;
        end else begin
            divisor   <= divisor;
            RSLT      <= (differ[31])? {RSLT[62:0], 1'h0} :
                                       {differ, RSLT[30:0], 1'h1};
            count     <= count + 1;
        end
    end
endmodule // DIVUNITCORE

/* 32bitx32 2R/W General Purpose Register                             */
/**********************************************************************/
module GPR(CLK, REGNUM0, REGNUM1, DIN0, DIN1, WE0, WE1, DOUT0, DOUT1);
    input         CLK;
    input [4:0]   REGNUM0;
    input [4:0]   REGNUM1;
    input [31:0]  DIN0;
    input [31:0]  DIN1;
    input         WE0;
    input         WE1;
    output [31:0] DOUT0;
    output [31:0] DOUT1;

    reg [31:0]    r[0:31];
    reg [31:0]    DOUT0;
    reg [31:0]    DOUT1;

    always @( posedge CLK ) begin
        if(WE0)
          r[REGNUM0] <= DIN0;          
        DOUT0 <= r[REGNUM0];
    end

    always @( posedge CLK ) begin
        if(WE1)
          r[REGNUM1] <= DIN1;          
        DOUT1 <= r[REGNUM1];
    end

endmodule // GPR

/* 32bit-MIPS multicycle processor                                    */
/**********************************************************************/
module MipsCore(CLK, RST_X, ADDR, DATA_IN, DATA_OUT, WE);
    input                   CLK;
    input                   RST_X;
    output [`ADDR]          ADDR;
    output                  WE;
    output [7:0]            DATA_OUT;
    input [7:0]             DATA_IN;
    
    // internal register
    reg [1:0]               cpu_state;
    reg [3:0]               state;
    reg [`ADDR]             pc;
    reg [`ADDR]             delay_npc;
    reg                     exec_delay;                  
    reg [31:0]              hi;
    reg [31:0]              lo;
    
    
    //instこいつらがR[]ってこと
    //たとえば、inst_rrsは、R[rs]である。
    reg [23:0]              inst_ir;
    reg [`ADDR]             inst_pc;
    reg [4:0]               inst_rs;
    reg [4:0]               inst_rt;
    reg [4:0]               inst_rd;
    reg [4:0]               inst_shamt;
    reg [15:0]              inst_imm;
    reg [15:0]              inst_attr;
    reg [6:0]               inst_op;
    reg [31:0]              inst_rrs;
    reg [31:0]              inst_rrt;
    reg [4:0]               inst_dst;
    reg [31:0]              inst_rslt;
    reg [31:0]              inst_rslthi;
    reg [`ADDR]             inst_eaddr;                 
    reg [`ADDR]             inst_npc;
    reg                     inst_cond;
    reg [3:0]               inst_datamask;
    reg [1:0]               inst_dataext;
    reg [23:0]              inst_loaddata;
    reg [7:0]               inst_loadext;

    // internal wire
    wire [31:0]             IFINST;
    reg [4:0]               IDRS;
    reg [4:0]               IDRT;
    reg [4:0]               IDRD;
    reg [4:0]               IDSHAMT;
    reg [15:0]              IDIMM;
    reg [15:0]              IDATTR;
    reg [6:0]               IDOP;
    wire [31:0]             RFRSVAL;
    wire [31:0]             RFRTVAL;
    wire [4:0]              RFDST;
    reg [31:0]              EXRSLT;
    reg [31:0]              EXRSLTHI;
    reg [`ADDR]             EXEADDR;
    reg                     EXCOND;
    reg [`ADDR]             EXNPC;
    reg [3:0]               EXDATAMASK;
    reg [1:0]               EXDATAEXT;
    reg                     EXBUSY;
    wire                    EXSIGNED;
    wire [31:0]             MALOADDATA;
    wire [63:0]             DURSLT;
    wire [63:0]             MURSLT;
    wire                    DUBUSY;
    wire                    MUBUSY;
    wire                    DIVMULINIT;
    wire [4:0]              GPRNUM0;
    wire [4:0]              GPRNUM1;
    wire [31:0]             GPRREADDT0;
    wire [31:0]             GPRREADDT1;
    wire [31:0]             GPRWRITEDT0;
    wire [31:0]             GPRWRITEDT1;
    wire                    GPRWE0;
    wire                    GPRWE1;
    
    //追加 それぞれの結果を保持するところ。
    wire[31:0]              addr;
    wire[31:0]              addir;
    wire[31:0]              subr;
    

    wire signed [31:0]      inst_rrt_signed;
    
    /*** Sub module declaration ***************************************/
    DIVUNIT du(.CLK(CLK), .RST_X(RST_X), .INIT(DIVMULINIT), .SIGNED(EXSIGNED),
               .A(RFRSVAL), .B(RFRTVAL), .RSLT(DURSLT), .BUSY(DUBUSY));

    MULUNIT mu(.CLK(CLK), .RST_X(RST_X), .INIT(DIVMULINIT), .SIGNED(EXSIGNED), 
               .A(RFRSVAL), .B(RFRTVAL), .RSLT(MURSLT), .BUSY(MUBUSY));

    GPR gpr(.CLK(CLK), .REGNUM0(GPRNUM0), .REGNUM1(GPRNUM1),
            .DIN0(GPRWRITEDT0), .DIN1(GPRWRITEDT1), 
            .WE0(GPRWE0), .WE1(GPRWE1),
            .DOUT0(GPRREADDT0), .DOUT1(GPRREADDT1));

    /*** Mips::proceedstate() ****************************************/
    always @( posedge CLK or negedge RST_X ) begin
        if( !RST_X ) 
          state       <= `CPU_START;
        else if( cpu_state != `CPU_EXEC || state == `CPU_WAIT )
          state       <= state;
        else if( exec_delay && delay_npc == `RETURN_ADDR )
          state       <= `CPU_WAIT;
        else if( state == `CPU_EX )
          if( EXBUSY )
            state     <= state;
          else
            state     <= (inst_attr & `LOADSTORE)? `CPU_MA0 : `CPU_WB;
        else if( state == `CPU_WB ) 
          state       <= `CPU_IF0;
        else
          state       <= state + 1;
    end
    
    //CPU_START...動作開始
    //CPU_IF0~IF3...命令フェッチ
    //CPU_ID...命令デコード
    //CPU_RF... レジスタフェッチ
    //CPU_EX...実行
    //CPU_WB...書き直し
    
    //stateにこいつらがはいって、条件分岐している。
    
    

    /*** Mips::fetch() ***********************************************/
    assign ADDR = (state <= `CPU_IF0)? pc     : 
                  (state <= `CPU_IF1)? pc + 1 :
                  (state <= `CPU_IF2)? pc + 2 :
                  (state <= `CPU_IF3)? pc + 3 :
                  (state <= `CPU_MA0)? inst_eaddr     :
                  (state <= `CPU_MA1)? inst_eaddr + 1 :
                  (state <= `CPU_MA2)? inst_eaddr + 2 :
                  inst_eaddr + 3;

    always @( posedge CLK or negedge RST_X ) begin
        if(!RST_X)
          inst_pc     <= 0;
        else if( cpu_state == `CPU_EXEC ) begin
            if     ( state == `CPU_IF0 )
              inst_pc         <= pc;
            if     ( state == `CPU_IF1 )
              inst_ir[ 7: 0]  <= DATA_IN;
            else if( state == `CPU_IF2 )
              inst_ir[15: 8]  <= DATA_IN;
            else if( state == `CPU_IF3 )
              inst_ir[23:16]  <= DATA_IN;
        end
    end

    /*** Mips::decode() **********************************************/
    assign IFINST = {DATA_IN, inst_ir[23:0]};
    always@ ( IFINST ) begin
        IDRS           = IFINST[25:21];
        IDRT           = IFINST[20:16];
        IDRD           = IFINST[15:11];
        IDSHAMT        = IFINST[10:6];
        IDIMM          = IFINST[15:0];
        IDOP           = `NOP______;
        IDATTR         = `WRITE_NONE;
        
        case ( IFINST[31:26] /* opecode */ ) 
          6'd0: begin 
              case ( IFINST[5:0] /* funct */ ) 
                6'd0: begin
                    if (( IDRT | IDRD | IDSHAMT ) == 0) begin
                        IDOP    = `NOP______;
                    end else if (( IDRT | IDRD ) == 0 && IDSHAMT == 1) begin
                        IDOP    = `NOP______; // `SSNOP____;
                    end else begin
                        IDOP    = `SLL______;
                        IDATTR  = `WRITE_RD;
                    end
                end
                6'd2: begin
                    IDOP        = `SRL______;
                    IDATTR      = `WRITE_RD;
                end
                6'd3: begin
                    IDOP        = `SRA______;
                    IDATTR      = `WRITE_RD;
                end
                6'd4: begin
                    IDOP        = `SLLV_____;
                    IDATTR      = `WRITE_RD;
                end
                6'd6: begin
                    IDOP        = `SRLV_____;
                    IDATTR      = `WRITE_RD;
                end
                6'd7: begin
                    IDOP        = `SRAV_____;
                    IDATTR      = `WRITE_RD;
                end
                6'd8: begin
                    if ( IDSHAMT == 0 ) begin
                        IDOP    = `JR_______;
                        IDATTR  = `BRANCH;
                    end else if ( IDSHAMT == 5'd16 ) begin
                        IDOP    = `JR_HB____;
                        IDATTR  = `BRANCH;
                    end 
                end
                6'd9: begin
                    if ( IDSHAMT == 0 ) begin
                        IDOP    = `JALR_____;
                        IDATTR  = `BRANCH | `WRITE_RD;
                    end else if ( IDSHAMT == 5'd16 ) begin 
                        IDOP    = `JALR_HB__;
                        IDATTR  = `BRANCH | `WRITE_RD;
                    end
                end
                6'd10: begin
                    IDOP        = `MOVZ_____;
                    IDATTR      = `WRITE_RD_COND;
                end
                6'd11: begin
                    IDOP        = `MOVN_____;
                    IDATTR      = `WRITE_RD_COND;
                end
                6'd12: begin
                    IDOP        = `SYSCALL__;
                end
                6'd16: begin
                    IDOP        = `MFHI_____;
                    IDATTR      = `WRITE_RD;
                end
                6'd17: begin
                    IDOP        = `MTHI_____;
                    IDATTR      = `WRITE_HI;
                end
                6'd18: begin
                    IDOP        = `MFLO_____;
                    IDATTR      = `WRITE_RD;
                end
                6'd19: begin
                    IDOP        = `MTLO_____;
                    IDATTR      = `WRITE_LO;
                end
                6'd24: begin
                    IDOP        = `MULT_____;
                    IDATTR      = `WRITE_HILO;
                end
                6'd25: begin
                    IDOP        = `MULTU____;
                    IDATTR      = `WRITE_HILO;
                end
                6'd26: begin
                    IDOP        = `DIV______;
                    IDATTR      = `WRITE_HILO;
                end
                6'd27: begin
                    IDOP        = `DIVU_____;
                    IDATTR      = `WRITE_HILO;
                end
				6'd32: begin
					IDOP		= `ADD______;
					IDATTR		= `WRITE_RD;
				end
				6'd33: begin
					IDOP		= `ADDU_____;
					IDATTR		= `WRITE_RD;
                end
				6'd34: begin
					IDOP		= `SUB______;
					IDATTR		= `WRITE_RD;
                end
				6'd35: begin
					IDOP		= `SUBU_____;
					IDATTR		= `WRITE_RD;
                end
                6'd36: begin
                    IDOP        = `AND______;
                    IDATTR      = `WRITE_RD;
                end
                6'd37: begin
                    IDOP        = `OR_______;
                    IDATTR      = `WRITE_RD;
                end
                6'd38: begin
                    IDOP        = `XOR______;
                    IDATTR      = `WRITE_RD;
                end
                6'd39: begin
                    IDOP        = `NOR______;
                    IDATTR      = `WRITE_RD;
                end
                6'd42: begin
                    IDOP        = `SLT______;
                    IDATTR      = `WRITE_RD;
                end
                6'd43: begin
                    IDOP        = `SLTU_____;
                    IDATTR      = `WRITE_RD;
                end
              endcase
          end
          6'd1: begin
              case ( IDRT )
                6'd0: begin
                    IDOP        = `BLTZ_____;
                    IDATTR      = `BRANCH;
                end
                6'd1: begin
                    IDOP        = `BGEZ_____;
                    IDATTR      = `BRANCH;
                end
                6'd2: begin
                    IDOP        = `BLTZL____;
                    IDATTR      = `BRANCH_LIKELY;
                end
                6'd3: begin
                    IDOP        = `BGEZL____;
                    IDATTR      = `BRANCH_LIKELY;
                end
                6'd16: begin
                    IDOP        = `BLTZAL___;
                    IDATTR      = `BRANCH | `WRITE_RRA;
                end
                6'd17: begin
                    IDOP        = `BGEZAL___;
                    IDATTR      = `BRANCH  | `WRITE_RRA;
                end
                6'd18: begin
                    IDOP        = `BLTZALL__;
                    IDATTR      = `BRANCH_LIKELY | `WRITE_RRA;
                end
                6'd19: begin
                    IDOP        = `BGEZALL__;
                    IDATTR      = `BRANCH_LIKELY | `WRITE_RRA;
                end
              endcase
          end 
          6'd2: begin
              IDOP              = `J________;
              IDATTR            = `BRANCH;
          end
          6'd3: begin
              IDOP              = `JAL______;
              IDATTR            = `BRANCH | `WRITE_RRA;
          end
          6'd4: begin
              IDOP              = `BEQ______;
              IDATTR            = `BRANCH;
          end
          6'd5: begin
              IDOP              = `BNE______;
              IDATTR            = `BRANCH;
          end
          6'd6: begin
              IDOP              = `BLEZ_____;
              IDATTR            = `BRANCH;
          end
          6'd7: begin
              IDOP              = `BGTZ_____;
              IDATTR            = `BRANCH;
          end
          6'd8: begin
              IDOP              = `ADDI_____;
              IDATTR            = `WRITE_RT;
          end
          6'd9: begin
              IDOP              = `ADDIU____;
              IDATTR            = `WRITE_RT;
          end
          6'd10: begin
              IDOP              = `SLTI_____;
              IDATTR            = `WRITE_RT;
          end
          6'd11: begin
              IDOP              = `SLTIU____;
              IDATTR            = `WRITE_RT;
          end
          6'd12: begin
              IDOP              = `ANDI_____;
              IDATTR            = `WRITE_RT;
          end
          6'd13: begin
              IDOP              = `ORI______;
              IDATTR            = `WRITE_RT;
          end
          6'd14: begin
              IDOP              = `XORI_____;
              IDATTR            = `WRITE_RT;
          end
          6'd15: begin
              IDOP              = `LUI______;
              IDATTR            = `WRITE_RT;
          end
          6'd20: begin
              IDOP              = `BEQL_____;
              IDATTR            = `BRANCH_LIKELY;
          end
          6'd21: begin
              IDOP              = `BNEL_____;
              IDATTR            = `BRANCH_LIKELY;
          end
          6'd22: begin
              IDOP              = `BLEZL____;
              IDATTR            = `BRANCH_LIKELY;
          end
          6'd23: begin
              IDOP              = `BGTZL____;
              IDATTR            = `BRANCH_LIKELY;
          end
          6'd28: begin
              IDOP              = `MUL______;
              IDATTR            = `WRITE_RD;
          end
          6'd32: begin
              IDOP              = `LB_______;
              IDATTR            = `WRITE_RT | `LOAD_1B;
          end
          6'd33: begin
              IDOP              = `LH_______;
              IDATTR            = `WRITE_RT | `LOAD_2B;
          end
          6'd34: begin
              IDOP              = `LWL______;
              IDATTR            = `WRITE_RT |
                                  `LOAD_4B_UNALIGN;
          end
          6'd35: begin
              IDOP              = `LW_______;
              IDATTR            = `WRITE_RT | `LOAD_4B_ALIGN;
          end
          6'd36: begin
              IDOP              = `LBU______;
              IDATTR            = `WRITE_RT | `LOAD_1B;
          end
          6'd37: begin
              IDOP              = `LHU______;
              IDATTR            = `WRITE_RT | `LOAD_2B;
          end
          6'd38: begin
              IDOP              = `LWR______;
              IDATTR            = `WRITE_RT |
                                  `LOAD_4B_UNALIGN;
          end
          6'd40: begin
              IDOP              = `SB_______;
              IDATTR            = `STORE_1B;
          end
          6'd41: begin
              IDOP              = `SH_______;
              IDATTR            = `STORE_2B;
          end
          6'd42: begin
              IDOP              = `SWL______;
              IDATTR            = `STORE_4B_UNALIGN;
          end
          6'd43: begin
              IDOP              = `SW_______;
              IDATTR            = `STORE_4B_ALIGN;
          end
          6'd46: begin
              IDOP              = `SWR______;
              IDATTR            = `STORE_4B_UNALIGN;
          end
        endcase
    end

    always @( posedge CLK or negedge RST_X ) begin
        if( !RST_X ) begin
            inst_rs             <= 0;
            inst_rt             <= 0;
            inst_rd             <= 0;
            inst_shamt          <= 0;
            inst_imm            <= 0;
            inst_attr           <= 0;
            inst_op             <= 0;
        end else if( cpu_state == `CPU_EXEC && state == `CPU_ID ) begin
            inst_rs             <= IDRS;
            inst_rt             <= IDRT;
            inst_rd             <= IDRD;
            inst_shamt          <= IDSHAMT;
            inst_imm            <= IDIMM;
            inst_attr           <= IDATTR;
            inst_op             <= IDOP;
        end
    end

    assign GPRNUM0 = (state == `CPU_ID) ?      IDRS :
                     (inst_op == `SYSCALL__) ? 'd2 :
                     inst_dst;
    assign GPRNUM1 = (state == `CPU_ID) ? IDRT : 'd7;

    /*** Mips::regfetch() ********************************************/
    assign RFRSVAL   =  (inst_rs == 0)? 0 : GPRREADDT0; 
    assign RFRTVAL   =  (inst_rt == 0)? 0 : GPRREADDT1;

    assign RFDST     = ((inst_attr & `WRITE_RD) ||
                        (inst_attr & `WRITE_RD_COND))? inst_rd :
                       (inst_attr & `WRITE_RT)?        inst_rt :
                       (inst_attr & `WRITE_RRA)?       'd31    :
                       'd0;

    assign DIVMULINIT = (cpu_state == `CPU_EXEC && state == `CPU_RF);

    always @( posedge CLK or negedge RST_X ) begin
        if( !RST_X ) begin
            inst_rrs  <= 0;
            inst_rrt  <= 0;
            inst_dst  <= 0;
        end else if( cpu_state == `CPU_EXEC && state == `CPU_RF ) begin
            inst_rrs  <= RFRSVAL;
            inst_rrt  <= RFRTVAL;
            inst_dst  <= RFDST;
        end
    end

    /*** Mips::execute() *********************************************/
	//inst_opは、各命令のマクロの値が代入されている。
    function [31:0] EXTS32;
        input [15:0] in;
        EXTS32 = (in[15])? {16'hffff, in} : {16'h0000, in};
    endfunction
    
    function [15:0] EXTSADDR;
        input [15:0] in;
        EXTSADDR = in;
    endfunction
    
    function [3:0] MASKUNALIGN;
        input [24:0] eaddr;
        input        left;
        MASKUNALIGN = (left) ? 4'b1111 << (3 - eaddr[1:0]) :
                               4'b1111 >>      eaddr[1:0];
    endfunction
    
    assign inst_rrt_signed = inst_rrt;
    assign EXSIGNED = (inst_op == `MULT_____ || inst_op == `DIV______);
    always @( DUBUSY or DURSLT or MUBUSY or MURSLT or  hi 
              or inst_imm or inst_op or inst_pc or inst_rrs
              or inst_rrt or inst_rrt_signed or inst_shamt or lo ) begin
        EXRSLT           = 0;
        EXRSLTHI         = 0;
        EXNPC            = 0;
        EXCOND           = 0;
        EXEADDR          = 0;
        EXDATAMASK       = 0;
	   EXDATAEXT        = 0;
        EXBUSY           = 0;
        
        case ( inst_op )
		`NOP______ :
		  EXRSLT  = 1;
          `SLL______ : begin
              EXRSLT  = inst_rrt << inst_shamt;
          end
          `SRL______ : begin
              EXRSLT  = inst_rrt >> inst_shamt;
          end
          `SRA______ : begin
              EXRSLT  = inst_rrt_signed >>> inst_shamt;
          end
          `SLLV_____ : begin
              EXRSLT  = inst_rrt << inst_rrs[4:0];
          end
          `SRLV_____ : begin
              EXRSLT  = inst_rrt >> inst_rrs[4:0];
          end
          `SRAV_____ : begin
              EXRSLT  = inst_rrt_signed >>> inst_rrs[4:0];
          end
          `JR_______ : begin
              EXNPC   = inst_rrs;
              EXCOND  = 1;
          end
          `JR_HB____ : begin
              EXNPC   = inst_rrs;
              EXCOND  = 1;
          end
          `JALR_____ : begin
              EXRSLT  = inst_pc + 8;
              EXNPC   = inst_rrs;
              EXCOND  = 1;
          end
          `JALR_HB__ : begin
              EXRSLT  = inst_pc + 8;
              EXNPC   = inst_rrs;
              EXCOND  = 1;
          end
          `MOVZ_____ : begin
              EXRSLT  = inst_rrs;
              EXCOND  = (inst_rrt == 0);
          end
          `MOVN_____ : begin
              EXRSLT  = inst_rrs;
              EXCOND  = (inst_rrt != 0);
          end
          `SYSCALL__ : begin
          end
          `MFHI_____ : begin
              EXRSLT  = hi;
          end
          `MTHI_____ : begin
              EXRSLTHI = inst_rrs;
          end
          `MFLO_____ : begin
              EXRSLT  = lo;
          end
          `MTLO_____ : begin
              EXRSLT  = inst_rrs;
          end
          `MULT_____ : begin
              {EXRSLTHI, EXRSLT} = MURSLT;
              EXBUSY = MUBUSY;
          end
          `MULTU____ : begin
              {EXRSLTHI, EXRSLT} = MURSLT;
              EXBUSY = MUBUSY;
          end
          `MUL______ : begin
              {EXRSLTHI, EXRSLT}  = MURSLT;
              EXBUSY = MUBUSY;
          end
          `DIV______ : begin
              {EXRSLTHI, EXRSLT} = (inst_rrt)? DURSLT : 0;
              EXBUSY = DUBUSY;
          end
          `DIVU_____ : begin
              {EXRSLTHI, EXRSLT} = (inst_rrt)? DURSLT : 0;
              EXBUSY = DUBUSY;
          end
		  `ADD______ : begin
			  EXRSLT = addr;
		  end
		  `ADDU_____ : begin
			  EXRSLT = addr;
		  end
		  `SUB______ : begin
			  EXRSLT = subr;
		  end
		  `SUBU_____ : begin
			  EXRSLT = subr;
		  end

          `AND______ : begin
              EXRSLT  = inst_rrs & inst_rrt;
          end
          `OR_______ : begin
              EXRSLT  = inst_rrs | inst_rrt;
          end
          `XOR______ : begin
              EXRSLT  = inst_rrs ^ inst_rrt;
          end
          `NOR______ : begin
              EXRSLT  = ~(inst_rrs | inst_rrt);
          end
          `SLT______ : begin
              EXRSLT  = (inst_rrs[31] ^ inst_rrt[31])? inst_rrs[31] :
                        (inst_rrs < inst_rrt) ? 32'h1 : 32'h0;
          end
          `SLTU_____ : begin
              EXRSLT  = (inst_rrs < inst_rrt) ? 32'h1 : 32'h0;
          end
          `BLTZ_____ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = inst_rrs[31];
          end
          `BLTZL____ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2)+ 4;
              EXCOND  = inst_rrs[31];
          end
          `BLTZAL___ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = inst_rrs[31];
          end
          `BLTZALL__ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = inst_rrs[31];
          end
          `BGEZ_____ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = ~inst_rrs[31];
          end
          `BGEZL____ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = ~inst_rrs[31];
          end
          `BGEZAL___ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = ~inst_rrs[31];
          end
          `BGEZALL__ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = ~inst_rrs[31];
          end
          `J________ : begin
              EXNPC   = (inst_pc & 32'hf0000000) | (inst_imm << 2);
              EXCOND  = 1;
          end
          `JAL______ : begin
              EXNPC   = (inst_pc & 32'hf0000000) | (inst_imm << 2);
              EXCOND  = 1;
          end
          `BEQ______ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = (inst_rrs == inst_rrt);
          end
          `BEQL_____ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = (inst_rrs == inst_rrt);
          end
          `BNE______ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = (inst_rrs != inst_rrt);
          end
          `BNEL_____ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = (inst_rrs != inst_rrt);
          end
          `BLEZ_____ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = (inst_rrs[31] || (inst_rrs == 0)) ? 1'b1 : 1'b0;
          end
          `BLEZL____ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = (inst_rrs[31] || (inst_rrs == 0)) ? 1'b1 : 1'b0;
          end
          `BGTZ_____ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = (~inst_rrs[31] && (inst_rrs != 0)) ? 1'b1 : 1'b0;
          end
		  `ADDI_____ : begin
			  EXRSLT = addir;
		  end
		  `ADDIU____ : begin
			  EXRSLT = addir;
		  end
          `BGTZL____ : begin
              EXNPC   = inst_pc + (EXTSADDR(inst_imm) << 2) + 4;
              EXCOND  = (~inst_rrs[31] && (inst_rrs != 0)) ? 1'b1 : 1'b0;
          end
          `SLTI_____ : begin
              EXRSLT  = (inst_rrs[31] ^ inst_imm[15]) ? inst_rrs[31] :
                        (inst_rrs < EXTS32(inst_imm)) ? 32'h1 : 32'h0;
          end
          `SLTIU____ : begin
              EXRSLT  = (inst_rrs < EXTS32(inst_imm)) ? 32'h1 :32'h0;
          end
          `ANDI_____ : begin
              EXRSLT  = inst_rrs & {16'h0, inst_imm};
          end
          `ORI______ : begin
              EXRSLT  = inst_rrs | {16'h0, inst_imm};
          end
          `XORI_____ : begin
              EXRSLT  = inst_rrs ^ {16'h0, inst_imm};
          end
          `LUI______ : begin
              EXRSLT  = {inst_imm, 16'h0};
          end
          `LW_______ : begin
              EXEADDR    = inst_rrs + EXTSADDR(inst_imm);
              EXDATAMASK = 4'b1111;
              EXRSLT     = 0;
          end
          `LB_______ : begin
              EXEADDR    = inst_rrs + EXTSADDR(inst_imm);
              EXDATAMASK = 4'b0001;
		    EXDATAEXT  = 2'b11;
              EXRSLT     = 0;
          end
          `LBU______ : begin
              EXEADDR    = inst_rrs + EXTSADDR(inst_imm);
              EXDATAMASK = 4'b0001;
              EXRSLT     = 0;
          end
          `LH_______ : begin
              EXEADDR    = inst_rrs + EXTSADDR(inst_imm);
              EXDATAMASK = 4'b0011;
		    EXDATAEXT  = 2'b10;
              EXRSLT     = 0;
          end
          `LHU______ : begin
              EXEADDR    = inst_rrs + EXTSADDR(inst_imm);
              EXDATAMASK = 4'b0011;
              EXRSLT     = 0;
          end            
          `LWL______ : begin
              EXEADDR    = inst_rrs + EXTSADDR(inst_imm) - 3;
              EXDATAMASK = MASKUNALIGN(inst_rrs + EXTSADDR(inst_imm), 1);
              EXRSLT     = inst_rrt;
          end
          `LWR______ : begin
              EXEADDR    = inst_rrs + EXTSADDR(inst_imm);
              EXDATAMASK = MASKUNALIGN(inst_rrs + EXTSADDR(inst_imm), 0);
              EXRSLT     = inst_rrt;
          end
          `SWR______ : begin
              EXEADDR    = inst_rrs + EXTSADDR(inst_imm);
              EXDATAMASK = MASKUNALIGN(inst_rrs + EXTSADDR(inst_imm), 0);
              EXRSLT     = inst_rrt;
          end
          `SWL______ : begin
              EXEADDR    = inst_rrs + EXTSADDR(inst_imm) - 3;
              EXDATAMASK = MASKUNALIGN(inst_rrs + EXTSADDR(inst_imm), 1);
              EXRSLT     = inst_rrt;
          end
          `SB_______ : begin
              EXEADDR    = inst_rrs + EXTSADDR(inst_imm);
              EXDATAMASK = 4'b0001;
              EXRSLT     = inst_rrt;
          end
          `SH_______ : begin
              EXEADDR    = inst_rrs + EXTSADDR(inst_imm);
              EXDATAMASK = 4'b0011;
              EXRSLT     = inst_rrt;
          end
          `SW_______ : begin
              EXEADDR    = inst_rrs + EXTSADDR(inst_imm);
              EXDATAMASK = 4'b1111;
              EXRSLT     = inst_rrt;
          end
          default    : begin
          end
        endcase
    end
    
    always @( posedge CLK or negedge RST_X ) begin
        if(!RST_X) begin
            inst_rslt     <= 0;
            inst_rslthi   <= 0;
            inst_eaddr    <= 0;
            inst_cond     <= 0;
            inst_npc      <= 0;
            inst_datamask <= 0;
		  inst_dataext  <= 0;
        end else if( cpu_state == `CPU_EXEC && state == `CPU_EX ) begin
            inst_rslt     <= EXRSLT;
            inst_rslthi   <= EXRSLTHI;
            inst_eaddr    <= EXEADDR;
            inst_cond     <= EXCOND;
            inst_npc      <= EXNPC;
            inst_datamask <= EXDATAMASK;
            inst_dataext  <= EXDATAEXT;
        end
    end

    /*** Mips::memaccess() *******************************************/
    assign MALOADDATA = (inst_datamask[3])? 
                        {DATA_IN, inst_loaddata[23:0]} :
                        {inst_loadext, inst_loaddata[23:0]};
    assign DATA_OUT   = (state == `CPU_MA0)? inst_rslt[ 7: 0] :
                        (state == `CPU_MA1)? inst_rslt[15: 8] :
                        (state == `CPU_MA2)? inst_rslt[23:16] :
                        inst_rslt[31:24];
    assign WE         = (cpu_state == `CPU_EXEC && 
					(inst_attr & `STORE_ANY) &&                       
					((state == `CPU_MA0 && inst_datamask[0]) ||
					 (state == `CPU_MA1 && inst_datamask[1]) ||
					 (state == `CPU_MA2 && inst_datamask[2]) ||
					 (state == `CPU_MA3 && inst_datamask[3])));

    always @( posedge CLK or negedge RST_X ) begin
        if( !RST_X ) begin
            inst_loaddata        <= 0;
		  inst_loadext         <= 0;
        end else if( cpu_state == `CPU_EXEC ) begin
            if     (state == `CPU_MA1) begin
			 inst_loadext         <= (!inst_dataext[0])? inst_rslt[15: 8]:
								(DATA_IN[7]) ? 8'hff : 8'h00;
			 inst_loaddata[ 7: 0] <= (inst_datamask[0])? 
								DATA_IN : inst_rslt[ 7: 0];
           end else if(state == `CPU_MA2) begin
			inst_loadext         <= (!inst_dataext[1])? inst_rslt[23:16]:
							    (inst_dataext[0]) ? inst_loadext:
							    (DATA_IN[7]) ? 8'hff : 8'h00;
			inst_loaddata[15: 8] <= (inst_datamask[1])? 
                                       DATA_IN : inst_loadext;
           end else if(state == `CPU_MA3) begin
			inst_loadext         <= (!inst_dataext)? inst_rslt[31:24]:
							    inst_loadext;
			inst_loaddata[23:16] <= (inst_datamask[2])? 
                                       DATA_IN : inst_loadext;
		 end
        end
    end

    /*** Mips::writeback() *******************************************/
    assign GPRWE0   = ((cpu_state == `CPU_EXEC && state == `CPU_WB) &&
                       ((inst_attr & `WRITE_RD_COND) == 0 || inst_cond));
    assign GPRWE1   = ((cpu_state == `CPU_EXEC && state == `CPU_WB) &&
                       (inst_op == `SYSCALL__ ));

    assign GPRWRITEDT0 = (inst_attr & `WRITE_RRA)? inst_pc + 8 :
                         (inst_attr & `LOADSTORE)? MALOADDATA :
                         (inst_op == `SYSCALL__)?  32'h0:
                         inst_rslt;
    assign GPRWRITEDT1 = 32'h0;
    
    always @( posedge CLK or negedge RST_X ) begin
        if( !RST_X ) begin
            hi              <= 0;
            lo              <= 0;
        end else if( cpu_state == `CPU_EXEC && state == `CPU_WB ) begin
            if(inst_attr & `WRITE_HI)
              hi            <= inst_rslthi;
            if(inst_attr & `WRITE_LO)
              lo            <= inst_rslt;
        end
    end
    
    /*** Mips::setnpc() **********************************************/
    always @( posedge CLK or negedge RST_X ) begin
        if( !RST_X ) begin
            pc              <= 0;
            delay_npc       <= 0;
            exec_delay      <= 0;
        end else if( cpu_state != `CPU_EXEC || state != `CPU_WB ) begin
            pc              <= pc;
            delay_npc       <= delay_npc;
            exec_delay      <= exec_delay;
        end else begin
            if( exec_delay ) begin
                pc          <= delay_npc;
                delay_npc   <= 0;
                exec_delay  <= 0;
            end else if( ((inst_attr & `BRANCH) || 
                          (inst_attr & `BRANCH_LIKELY))
                         && inst_cond ) begin
                pc          <= pc + 4;
                delay_npc   <= inst_npc;
                exec_delay  <= 1;
            end else if( (inst_attr & `BRANCH_LIKELY) && !inst_cond ) begin
                pc          <= pc + 8;
                delay_npc   <= delay_npc;
                exec_delay  <= exec_delay;
            end else begin
                pc          <= pc + 4;
                delay_npc   <= delay_npc;
                exec_delay  <= exec_delay;
            end
        end
    end
    
    /*** CPU State ***************************************************/
    always @( posedge CLK or negedge RST_X ) begin
        if( !RST_X )
          cpu_state         <= `CPU_STOP;
        else if( cpu_state == `CPU_STOP )
          cpu_state         <= `CPU_INIT;
        else
          cpu_state         <= `CPU_EXEC;
    end
    
endmodule // MipsCore

/**********************************************************************/