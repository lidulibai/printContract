(module
 (type $FUNCSIG$i (func (result i32)))
 (type $FUNCSIG$ii (func (param i32) (result i32)))
 (import "env" "println" (func $println (param i32) (result i32)))
 (table 0 anyfunc)
 (memory $0 1)
 (data (i32.const 16) "hello world!\00")
 (export "memory" (memory $0))
 (export "init" (func $init))
 (func $init (; 1 ;)
  (drop
   (call $println
    (i32.const 16)
   )
  )
 )
)
