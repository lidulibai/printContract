(module
 (type $FUNCSIG$i (func (result i32)))
 (type $FUNCSIG$ii (func (param i32) (result i32)))
 (import "env" "print" (func $print (param i32) (result i32)))
 (import "env" "printlnNum" (func $printlnNum (param i32) (result i32)))
 (table 0 anyfunc)
 (memory $0 1)
 (data (i32.const 16) "1 + 1 = \00")
 (export "memory" (memory $0))
 (export "printOpO" (func $printOpO))
 (func $printOpO (; 2 ;)
  (drop
   (call $print
    (i32.const 16)
   )
  )
  (drop
   (call $printlnNum
    (i32.const 2)
   )
  )
 )
)

