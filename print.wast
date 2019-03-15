(module
 (type $FUNCSIG$ii (func (param i32) (result i32)))
 (type $FUNCSIG$iii (func (param i32 i32) (result i32)))
 (import "env" "println" (func $println (param i32 i32) (result i32)))
 (table 0 anyfunc)
 (memory $0 1)
 (data (i32.const 16) "hello world! Hello World! hello world! Hello World!\00")
 (export "memory" (memory $0))
 (export "init" (func $init))
 (func $init (; 1 ;)
  (drop
   (call $println
    (i32.const 16)
    (i32.const 0)
   )
  )
 )
)
