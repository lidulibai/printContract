(module
 (type $FUNCSIG$i (func (result i32)))
 (type $FUNCSIG$iiiii (func (param i32 i32 i32 i32) (result i32)))
 (import "env" "createToken" (func $createToken (param i32 i32 i32 i32) (result i32)))
 (table 0 anyfunc)
 (memory $0 1)
 (data (i32.const 16) "zqb\00")
 (data (i32.const 32) "Z\00")
 (export "memory" (memory $0))
 (export "create" (func $create))
 (func $create (; 1 ;)
  (drop
   (call $createToken
    (i32.const 16)
    (i32.const 100000)
    (i32.const 32)
    (i32.const 3)
   )
  )
 )
)